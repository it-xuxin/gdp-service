package com.gdp.service.common.core.constant;

public interface SecurityConstants {

    String CLIENT_ID_KEY = "client_id";

    /**
     * 认证请求头key
     */
    String AUTHORIZATION_KEY = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_PREFIX = "Bearer ";

    /**
     * Basic认证前缀
     */
    String BASIC_PREFIX = "Basic ";

    String REFRESH_TOKEN_KEY = "refresh_token";

    String GRANT_TYPE_KEY = "grant_type";

    /**
     * 认证身份标识
     */
    String AUTHENTICATION_IDENTITY_KEY = "authenticationIdentity";

    /**
     * 验证码key前缀
     */
    String VALIDATE_CODE_PREFIX = "VALIDATE_CODE:";

    /**
     * 短信验证码key前缀
     */
    String SMS_CODE_PREFIX = "SMS_CODE:";


    /**
     * 接口文档 Knife4j 测试客户端ID
     */
    String TEST_CLIENT_ID = "client";

    /**
     * 系统管理 web 客户端ID
     */
    String ADMIN_CLIENT_ID = "gdp-admin-web";

    /**
     * 移动端（H5/Android/IOS）客户端ID
     */
    String APP_CLIENT_ID = "gdp-app";

    /**
     * 小程序端（微信小程序、....） 客户端ID
     */
    String WEAPP_CLIENT_ID = "gdp-weapp";

    String JWT_PAYLOAD_KEY = "payload";
    String AUTHORITY_PREFIX = "ROLE_";
    String ENVIRONMENT_KEY = "environment";
    String AI_USER_ID_KEY = "aiUserId";

}
