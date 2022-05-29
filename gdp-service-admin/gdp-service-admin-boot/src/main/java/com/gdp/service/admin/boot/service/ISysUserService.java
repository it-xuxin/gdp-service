package com.gdp.service.admin.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdp.service.admin.boot.pojo.entity.SysUser;
import com.gdp.service.admin.dto.AuthSysUserDTO;

public interface ISysUserService  extends IService<SysUser> {

    AuthSysUserDTO getAuthInfoByUsername(String username);

}
