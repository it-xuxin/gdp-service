package com.gdp.service.auth.controller;

import com.gdp.service.auth.service.CertifiedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = "认证中心")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth")
public class AuthController {

    private final CertifiedService certifiedService;


    @ApiOperation(value = "OAuth2认证", notes = "手机验证码登录入口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile",  value = "手机号", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", required = true)
    })
    @PostMapping("/login/mobile/code")
    public Object loginByCode(@RequestParam String mobile, @RequestParam String code) {
        return certifiedService.loginByCode(mobile, code);
    }

    @ApiOperation(value = "OAuth2认证", notes = "用户名密码和验证码登录入口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true),
            @ApiImplicitParam(name = "code", value = "图片验证码文案", required = true),
            @ApiImplicitParam(name = "uuid", value = "图片验证码uuid", required = true)
    })
    @PostMapping("/login/username/password")
    public Object loginByUsernamePassword(@RequestParam String username,
                                          @RequestParam String password,
                                          @RequestParam String code,
                                          @RequestParam String uuid) {
        return certifiedService.loginByUsernamePassword(username, password, code, uuid);
    }
}
