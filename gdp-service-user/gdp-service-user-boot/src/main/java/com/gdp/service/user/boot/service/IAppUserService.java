package com.gdp.service.user.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdp.service.user.boot.pojo.entity.User;
import com.gdp.service.user.dto.UserAuthInfoDTO;

public interface IAppUserService extends IService<User> {

    UserAuthInfoDTO getByOpenid(String openid);

    /**
     * 根据手机号获取会员认证信息
     *
     * @param mobile
     * @return
     */
    UserAuthInfoDTO getByMobile(String mobile);
}
