package com.gdp.service.admin.api.fallback;

import com.gdp.service.admin.api.SysUserFeignClient;
import com.gdp.service.admin.dto.AuthSysUserDTO;
import com.gdp.service.common.core.result.Result;
import com.gdp.service.common.core.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SysUserFeignFallbackClient implements SysUserFeignClient {

    @Override
    public Result<AuthSysUserDTO> getUserByUsername(String username) {
        log.error("feign远程调用系统用户服务异常后的降级方法");
        return Result.failed(ResultCode.DEGRADATION);    }
}
