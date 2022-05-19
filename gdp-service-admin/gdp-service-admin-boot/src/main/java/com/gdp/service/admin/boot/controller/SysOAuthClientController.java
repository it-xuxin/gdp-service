package com.gdp.service.admin.boot.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.gdp.service.admin.boot.pojo.entity.SysOAuthClient;
import com.gdp.service.admin.boot.service.ISysOAuthClientService;
import com.gdp.service.admin.dto.AuthClientDTO;
import com.gdp.service.common.core.result.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/oauth-clients")
public class SysOAuthClientController {

    private ISysOAuthClientService iSysOAuthClientService;

    @ApiOperation(value = "客户端详情")
    @ApiImplicitParam(name = "clientId", value = "客户端id", required = true, paramType = "path", dataType = "String")
    @GetMapping("/{clientId}")
    public Result detail(@PathVariable String clientId) {
        SysOAuthClient client = iSysOAuthClientService.getById(clientId);
        return Result.success(client);
    }

    @ApiOperation(hidden = true, value = "获取 OAuth2 客户端认证信息", notes = "Feign 调用")
    @GetMapping("/getOAuth2ClientById")
    public Result<AuthClientDTO> getOAuth2ClientById(@RequestParam String clientId) {
        SysOAuthClient client = iSysOAuthClientService.getById(clientId);
        Assert.isTrue(client != null, "OAuth2 客户端不存在");
        AuthClientDTO authClientDTO = new AuthClientDTO();
        BeanUtil.copyProperties(client, authClientDTO);
        return Result.success(authClientDTO);
    }
}
