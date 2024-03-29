package com.gdp.service.user.boot.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 会员视图层对象
 *
 */
@ApiModel("会员视图层对象")
@Data
public class UserVO {

    @ApiModelProperty("会员ID")
    private Long id;

    @ApiModelProperty("会员昵称")
    private String nickName;

    @ApiModelProperty("会员头像地址")
    private String avatarUrl;

    @ApiModelProperty("会员手机号")
    private String mobile;

    @ApiModelProperty("会员余额(单位:分)")
    private Long balance;

}
