package com.gdp.service.auth.security.core.userdetails.app;

import com.gdp.service.common.core.enums.AuthIdentityEnum;
import com.gdp.service.common.core.result.Result;
import com.gdp.service.common.core.result.ResultCode;
import com.gdp.service.user.api.UserFeignClient;
import com.gdp.service.user.dto.UserAuthInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsServiceImpl implements UserDetailsService {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByMobile(String mobile) {

        Result<UserAuthInfoDTO> result = userFeignClient.loadUserByMobile(mobile);
        return setUserDetailIdentity(result, AuthIdentityEnum.MOBILE);
    }

    public UserDetails loadUserByOpenId(String openId) {
        Result<UserAuthInfoDTO> result = userFeignClient.loadUserByOpenId(openId);
        return setUserDetailIdentity(result, AuthIdentityEnum.OPENID);
    }


    private UserDetails setUserDetailIdentity(Result<UserAuthInfoDTO> result, AuthIdentityEnum identity) {
        AppUserDetails userDetails = null;
        if (Result.isSuccess(result)) {
            UserAuthInfoDTO user = result.getData();
            if (null != user) {
                userDetails = new AppUserDetails(user);
                //设置用户登录身份
                userDetails.setAuthenticationIdentity(identity.getValue());
            }
        }
        if (userDetails == null) {
            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
        } else if (!userDetails.isEnabled()) {
            throw new DisabledException(ResultCode.USER_ACCOUNT_DISABLED.getMsg());
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException(ResultCode.USER_ACCOUNT_LOCKED.getMsg());
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException(ResultCode.USER_ACCOUNT_INVALID.getMsg());
        }
        return userDetails;
    }
}
