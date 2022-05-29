package com.gdp.service.auth.security.core.userdetails.system;

import com.gdp.service.admin.api.SysUserFeignClient;
import com.gdp.service.admin.dto.AuthSysUserDTO;
import com.gdp.service.common.core.result.Result;
import com.gdp.service.common.core.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("sysUserDetailsService")
@Slf4j
@RequiredArgsConstructor
public class SysUserDetailsServiceImpl implements UserDetailsService {

    private final SysUserFeignClient sysUserFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDetails sysUserDetails = null;
        Result<AuthSysUserDTO> result = sysUserFeignClient.getUserByUsername(username);
        if (Result.isSuccess(result)){
            AuthSysUserDTO user = result.getData();
            if (null != user){
                sysUserDetails = new SysUserDetails(user);
            }
        }
        if (sysUserDetails == null) {
            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
        } else if (!sysUserDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!sysUserDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!sysUserDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return sysUserDetails;
    }
}
