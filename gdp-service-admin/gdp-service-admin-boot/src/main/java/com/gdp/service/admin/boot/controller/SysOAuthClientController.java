package com.gdp.service.admin.boot.controller;

import com.gdp.service.admin.boot.pojo.entity.SysOAuthClient;
import com.gdp.service.admin.boot.service.ISysOAuthClientService;
import com.gdp.service.common.core.result.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
