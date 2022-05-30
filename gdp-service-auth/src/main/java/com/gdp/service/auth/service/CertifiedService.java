package com.gdp.service.auth.service;

import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.gdp.service.auth.security.core.clientdetails.ClientDetailsServiceImpl;
import com.gdp.service.auth.security.utils.RequestUtils;
import com.gdp.service.common.core.constant.SecurityConstants;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class CertifiedService {

    private final ClientDetailsServiceImpl clientDetailsService;

    private final TokenEndpoint tokenEndpoint;

    public OAuth2AccessToken loginByClientId(String username, String password) {
        Map<String, String> parameters = Maps.newHashMap();
        parameters.put("grant_type", "password");
        parameters.put("username", username);
        parameters.put("password", password);
        return login(parameters);
    }

    public OAuth2AccessToken loginByCode(String mobile, String code) {
        try {
            Map<String, String> parameters = Maps.newHashMap();
            parameters.put("mobile", mobile);
            parameters.put("code", code);
            parameters.put("grant_type", "sms_code");
            return login(parameters);
        } catch (Exception e) {
            log.error("{}", Throwables.getStackTraceAsString(e));
            throw new RuntimeException("Login by code error.");
        }
    }

    public OAuth2AccessToken loginByUsernamePassword(String username, String password, String code, String uuid) {
        Map<String, String> parameters = Maps.newHashMap();
        parameters.put("username", username);
        parameters.put("password", password);
        parameters.put("code", code);
        parameters.put("uuid", uuid);
        parameters.put("grant_type", "captcha");
        return login(parameters);
    }


    private OAuth2AccessToken login(Map<String, String> parameters) {
        /**
         * 获取登录认证的客户端ID
         *
         * 兼容两种方式获取Oauth2客户端信息（client_id、client_secret）
         * 方式一：client_id、client_secret放在请求路径中(注：当前版本已废弃)
         * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA== 明文等于 client:secret
         */
        String clientId = RequestUtils.getOAuth2ClientId();
        if (StringUtils.isBlank(clientId)) {

        }
        log.info("OAuth认证授权 客户端ID:{}，请求参数：{}", clientId, JSONUtil.toJsonStr(parameters));
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                clientDetails.getClientId(),
                clientDetails.getClientSecret(),
                clientDetails.getAuthorities()
        );

        /**
         * knife4j接口文档测试使用
         *
         * 请求头自动填充，token必须原生返回，不能有任何包装，否则显示 undefined undefined
         * 账号/密码:  client_id/client_secret : client/123456
         */
        try {
            if (SecurityConstants.TEST_CLIENT_ID.equals(clientId)) {
                return tokenEndpoint.postAccessToken(token, parameters).getBody();
            }
            OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(token, parameters).getBody();
            accessToken.getAdditionalInformation().clear();
            return accessToken;
        } catch (HttpRequestMethodNotSupportedException e) {
            log.error("Request method not support. {}", e.getMessage());
        }
        throw new RuntimeException("Runtime Exception.");
    }
}
