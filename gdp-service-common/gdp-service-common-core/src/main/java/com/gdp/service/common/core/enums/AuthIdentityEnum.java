package com.gdp.service.common.core.enums;

import com.gdp.service.common.core.base.IBaseEnum;
import lombok.Getter;

/**
 * 身份认证标识
 */
public enum AuthIdentityEnum implements IBaseEnum<String> {

    USERNAME("username", "用户名密码"),

    MOBILE("mobile", "手机号验证码"),

    OPENID("openId", "开放式认证系统唯一标识");

    @Getter
    private String value;

    @Getter
    private String label;

    AuthIdentityEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
