package com.gdp.service.user.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdp.service.user.boot.mapper.UserMapper;
import com.gdp.service.user.boot.pojo.entity.User;
import com.gdp.service.user.boot.service.IAppUserService;
import com.gdp.service.user.dto.UserAuthInfoDTO;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl extends ServiceImpl<UserMapper, User> implements IAppUserService {

//    private final RedisTemplate redisTemplate;
//    @Autowired
//    private UserMapper userMapper;

    @Override
    public UserAuthInfoDTO getByOpenid(String openid) {
        User user = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getOpenid, openid)
                .select(User::getId,
                        User::getOpenid,
                        User::getStatus
                )
        );
        UserAuthInfoDTO memberAuth = null;
        if (user != null) {
            memberAuth = new UserAuthInfoDTO()
                    .setUserId(user.getId())
                    .setUsername(user.getOpenid())
                    .setStatus(user.getStatus());
        }
        return memberAuth;
    }


    @Override
    public UserAuthInfoDTO getByMobile(String mobile) {
        User user = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getMobile, mobile)
                .select(User::getId,
                        User::getOpenid,
                        User::getStatus
                )
        );

        UserAuthInfoDTO memberAuth = null;
        if (user != null) {
            memberAuth = new UserAuthInfoDTO()
                    .setUserId(user.getId())
                    .setUsername(user.getMobile())
                    .setStatus(user.getStatus());
        }
        return memberAuth;
    }
}
