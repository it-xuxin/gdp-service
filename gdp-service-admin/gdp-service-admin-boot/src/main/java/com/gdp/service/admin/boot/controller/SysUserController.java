package com.gdp.service.admin.boot.controller;

import com.gdp.service.admin.boot.service.ISysUserService;
import com.gdp.service.admin.dto.AuthSysUserDTO;
import com.gdp.service.common.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class SysUserController {

    private final ISysUserService iSysUserService;


    @ApiOperation(value = "根据用户名获取认证信息", notes = "提供用于用户登录认证信息")
    @GetMapping("/username/{username}")
    public Result<AuthSysUserDTO> getAuthInfoByUsername(
            @ApiParam("用户名") @PathVariable String username) {
        AuthSysUserDTO user = iSysUserService.getAuthInfoByUsername(username);
        return Result.success(user);
    }
}
