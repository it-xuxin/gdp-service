package com.gdp.service.admin.dto;

import lombok.Data;

import java.util.List;

/**
 * OAuth2认证用户信息传输层对象
 */
@Data
public class AuthSysUserDTO {

    private Long userId;

    private String username;

    private String password;

    /**
     * 用户状态(1:正常;0:禁用)
     */
    private Integer status;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户角色编码集合 ["ROOT","ADMIN"]
     */
    private List<String> roles;
}
