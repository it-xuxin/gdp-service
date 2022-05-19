package com.gdp.service.auth.security.core.userdetails.app;

import com.gdp.service.common.core.constant.GlobalConstants;
import com.gdp.service.user.dto.UserAuthInfoDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@Data
public class AppUserDetails implements UserDetails {

    private Long userId;
    private String username;
    private Boolean enabled;

    private String authenticationIdentity;


    public AppUserDetails(UserAuthInfoDTO user){
        this.setUserId(user.getUserId());
        this.setUsername(user.getUsername());
        this.setEnabled(GlobalConstants.STATUS_YES.equals(user.getStatus()));
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        return collection;
    }


    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
