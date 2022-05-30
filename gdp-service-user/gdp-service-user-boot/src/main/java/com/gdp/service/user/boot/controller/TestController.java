package com.gdp.service.user.boot.controller;

import com.gdp.service.common.core.result.Result;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/t")
    @PreAuthorize("hasAnyRole('ROLE_APP')")
    public Result t(){
        return Result.success("test");
    }

    @GetMapping("/f")
    public Result f(){
        return Result.success("fffffff");
    }
}
