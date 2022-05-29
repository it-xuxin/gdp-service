package com.gdp.service.auth.security.core.userdetails.system;

import cn.hutool.core.collection.CollectionUtil;
import com.gdp.service.admin.dto.AuthSysUserDTO;
import com.gdp.service.auth.common.enums.PasswordEncoderTypeEnum;
import com.gdp.service.common.core.constant.GlobalConstants;
import com.gdp.service.common.core.enums.AuthIdentityEnum;
import com.gdp.service.user.dto.UserAuthInfoDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class SysUserDetails implements UserDetails {

    private Long userId;

    /**
     * 扩展字段：认证身份标识，枚举值如下：
     *
     * @see com.gdp.service.common.core.enums.AuthIdentityEnum
     */
    private String authenticationIdentity;

    /**
     * 扩展字段：部门ID
     */
    private Long deptId;

    private String username;
    private String password;
    private Boolean enabled;
    private Collection<SimpleGrantedAuthority> authorities;


    /**
     * 系统管理用户
     */
    public SysUserDetails(AuthSysUserDTO user) {
        this.setUserId(user.getUserId());
        this.setUsername(user.getUsername());
        this.setDeptId(user.getDeptId());
        this.setPassword(PasswordEncoderTypeEnum.BCRYPT.getPrefix() + user.getPassword());
        this.setEnabled(GlobalConstants.STATUS_YES.equals(user.getStatus()));
        if (CollectionUtil.isNotEmpty(user.getRoles())) {
            authorities = new ArrayList<>();
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
