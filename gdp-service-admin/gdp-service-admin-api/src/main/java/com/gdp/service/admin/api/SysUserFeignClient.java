package com.gdp.service.admin.api;

import com.gdp.service.admin.api.fallback.SysUserFeignFallbackClient;
import com.gdp.service.admin.dto.AuthSysUserDTO;
import com.gdp.service.common.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "gdp-service-admin-boot", fallback = SysUserFeignFallbackClient.class)
public interface SysUserFeignClient {

    @GetMapping("/api/v1/users/username/{username}")
    Result<AuthSysUserDTO> getUserByUsername(@PathVariable String username);
}
