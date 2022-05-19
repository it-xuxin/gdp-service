package com.gdp.service.auth.controller;

import com.gdp.service.common.core.result.Result;
import com.gdp.service.user.api.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fegin")
public class TestFeginController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping(value = "/test")
    public Result test() {
        Result<String> userDTOResult = userFeignClient.getMemberOpenId(2L);
        return Result.success(userDTOResult.getData());
    }

}
