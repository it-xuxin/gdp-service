package com.gdp.service.user.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gdp.service.common.core.result.Result;
import com.gdp.service.common.core.result.ResultCode;
import com.gdp.service.user.boot.pojo.entity.User;
import com.gdp.service.user.boot.service.impl.AppUserServiceImpl;
import com.gdp.service.user.dto.UserAuthInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "「移动端」会员管理")
@RestController
@RequestMapping("/app-api/v1/members")
public class UserController {

    @Autowired
    private AppUserServiceImpl appUserService;

    @ApiOperation(value = "根据会员ID获取openid")
    @GetMapping("/{memberId}/openid")
    public Result<String> getMemberOpenId(
            @ApiParam("会员ID") @PathVariable Long memberId
    ) {
        User member = appUserService.getOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getId, memberId)
                        .select(User::getOpenid)
        );
        String openid = member.getOpenid();
        return Result.success(openid);
    }


    @ApiOperation(value = "根据 openid 获取会员认证信息")
    @GetMapping("/openid/{openid}")
    public Result<UserAuthInfoDTO> getByOpenid(
            @ApiParam("微信身份标识") @PathVariable String openid
    ) {
        UserAuthInfoDTO memberAuthInfo = appUserService.getByOpenid(openid);
        if (memberAuthInfo == null) {
            return Result.failed(ResultCode.USER_NOT_EXIST);
        }
        return Result.success(memberAuthInfo);
    }


    /**
     * 根据手机号获取会员认证信息
     *
     * @param mobile
     * @return
     */
    @GetMapping("/mobile/{mobile}")
    public Result<UserAuthInfoDTO> getByMobile(
            @ApiParam("手机号码") @PathVariable String mobile
    ) {
        UserAuthInfoDTO memberAuthInfo = appUserService.getByMobile(mobile);
        if (memberAuthInfo == null) {
            return Result.failed(ResultCode.USER_NOT_EXIST);
        }
        return Result.success(memberAuthInfo);
    }

}


