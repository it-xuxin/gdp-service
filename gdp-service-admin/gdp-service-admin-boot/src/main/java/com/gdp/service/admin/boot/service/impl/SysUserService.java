package com.gdp.service.admin.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdp.service.admin.boot.mapper.SysUserMapper;
import com.gdp.service.admin.boot.pojo.entity.SysUser;
import com.gdp.service.admin.boot.service.ISysUserService;
import com.gdp.service.admin.dto.AuthSysUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public AuthSysUserDTO getAuthInfoByUsername(String username) {
        return this.baseMapper.getAuthInfoByUsername(username);
    }
}
