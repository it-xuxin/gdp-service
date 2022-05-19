package com.gdp.service.admin.api;

import com.gdp.service.admin.dto.AuthClientDTO;
import com.gdp.service.common.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "gdp-service-admin-boot", contextId = "oauth-client")
public interface OAuthClientFeignClient {

    @GetMapping("/api/v1/oauth-clients/getOAuth2ClientById")
    Result<AuthClientDTO> getOAuth2ClientById(@RequestParam String clientId);
}
