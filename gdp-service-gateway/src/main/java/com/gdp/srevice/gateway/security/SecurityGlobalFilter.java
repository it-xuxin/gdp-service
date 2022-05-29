package com.gdp.srevice.gateway.security;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.gdp.service.common.core.constant.GlobalConstants;
import com.gdp.service.common.core.constant.SecurityConstants;
import com.gdp.service.common.core.result.ResultCode;
import com.gdp.srevice.gateway.config.AuthProperties;
import com.gdp.srevice.gateway.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    private static final PathPatternParser DEFAULT_PATTERN_PARSER = new PathPatternParser();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String url = exchange.getRequest().getURI().getPath();
        log.debug("请求路径：" + url);
//        log.debug("资源名单：" + JSONUtil.toJsonStr(authProperties.getResource()));
//        log.debug("白名单：" + JSONUtil.toJsonStr(authProperties.getWhites()));
//        log.debug("阻名单：" + JSONUtil.toJsonStr(authProperties.getBlocks()));

        boolean isWhite = false;
        // 跳过不需要验证的白名单路径
        for (String white : authProperties.getWhites()) {
            if (ANT_PATH_MATCHER.match(white, url)) {
                log.info("Match white = {}, url = {}", white, url);
                isWhite = true;
                break;
            }
        }
        if (isWhite){
            return chain.filter(exchange);
        }

        String token = getToken(exchange.getRequest());
        if (StringUtils.isBlank(token)) {
            log.warn("令牌不能为空");
            return ResponseUtils.writeErrorInfo(response, ResultCode.ACCESS_UNAUTHORIZED);
        }
        // 初始值默认零
        String userId = "0";
        String claims = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(token)) {
            claims = JwtHelper.decode(token).getClaims();
            // 负载为空，不在白名单的请求
            if (StringUtils.isBlank(claims)) {
                return ResponseUtils.writeErrorInfo(response, ResultCode.ACCESS_UNAUTHORIZED);
            }
            log.debug("令牌解析payload:{}", claims);
            JSONObject jsonPayload = JSONUtil.parseObj(claims);
//            String environment = jsonPayload.getStr(SecurityConstants.ENVIRONMENT_KEY);
//            String[] actives = SpringUtil.getActiveProfiles();
//            if (StringUtils.isBlank(environment) || Convert.toList(actives).contains(environment.trim())) {
//                log.warn("令牌验证失败,环境不符{},{}", actives, claims);
//                if (!isWhite) {
//                    return ResponseUtils.writeErrorInfo(response, ResultCode.ACCESS_UNAUTHORIZED);
//                }
//            }

            userId = jsonPayload.getStr(GlobalConstants.HEADER_USER_ID);
            // 用户id为空并且不在白名单
            if (StringUtils.isBlank(userId)) {
                log.warn("令牌验证失败,用户详情为{}", claims);
                return ResponseUtils.writeErrorInfo(response, ResultCode.CLIENT_AUTHENTICATION_FAILED);
            }


            long expire = 1111L;//jsonPayload.getLong(AccessTokenConverter.EXP);
            long currentSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8));
            if (expire <= currentSecond) {
                log.warn("登录状态已过期:令牌为{}", token);
                if (!isWhite) {
                    return ResponseUtils.writeErrorInfo(response, ResultCode.TOKEN_INVALID_OR_EXPIRED);
                }
            }
        }

        String payload = StringUtils.EMPTY;
        try {
            payload =  URLEncoder.encode(claims, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error("令牌验证失败,用户详情为{}", claims);
            return ResponseUtils.writeErrorInfo(response, ResultCode.TOKEN_INVALID_OR_EXPIRED);
        }

        HttpHeaders headers = exchange.getRequest().getHeaders();

        List<String> subjectHeader = headers.getOrDefault(GlobalConstants.HEADER_APP_NAME, new ArrayList<>());
        List<String> osTypeHeader = headers.getOrDefault(GlobalConstants.HEADER_OS_TYPE, new ArrayList<>());
        List<String> deviceIdHeader = headers.getOrDefault(GlobalConstants.HEADER_DEVICE_ID, new ArrayList<>());

        // 封装头信息
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                .header("payload", payload)
                .header(GlobalConstants.HEADER_USER_ID, userId)
                .header(GlobalConstants.HEADER_APP_NAME, CollectionUtils.isEmpty(subjectHeader) ? StringUtils.EMPTY : subjectHeader.get(0))
                .header(GlobalConstants.HEADER_OS_TYPE, CollectionUtils.isEmpty(osTypeHeader) ? StringUtils.EMPTY : osTypeHeader.get(0))
                .header(GlobalConstants.HEADER_DEVICE_ID, CollectionUtils.isEmpty(deviceIdHeader) ? StringUtils.EMPTY : deviceIdHeader.get(0))
                .build();

        ServerWebExchange mutableExchange = exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(mutableExchange);
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        if (StringUtils.isNotBlank(token) && token.startsWith(SecurityConstants.JWT_PREFIX)) {
            token = StrUtil.replaceIgnoreCase(token, SecurityConstants.JWT_PREFIX, Strings.EMPTY);
        }
        return token;
    }
    @Override
    public int getOrder() {
        return -200;
    }
}
