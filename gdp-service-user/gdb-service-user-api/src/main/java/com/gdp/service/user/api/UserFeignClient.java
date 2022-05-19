package com.gdp.service.user.api;

import com.gdp.service.common.core.result.Result;
import com.gdp.service.user.dto.UserAuthInfoDTO;
import com.gdp.service.user.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "gdp-service-user-boot")
public interface UserFeignClient {

    /**
     * 根据手机号获取会员认证信息
     */
    @GetMapping("/app-api/v1/members/mobile/{mobile}")
    Result<UserAuthInfoDTO> loadUserByMobile(@PathVariable String mobile);

    /**
     * 根据openId获取会员认证信息
     */
    @GetMapping("/app-api/v1/members/openid/{openid}")
    Result<UserAuthInfoDTO> loadUserByOpenId(@PathVariable String openid);

    /**
     * 获取会员的 openid
     */
    @GetMapping("/app-api/v1/members/{memberId}/openid")
    Result<String> getMemberOpenId(@PathVariable Long memberId);

}
