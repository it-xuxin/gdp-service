package com.gdp.service.user.boot.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gdp.service.common.core.base.BaseEntity;
import com.gdp.service.user.boot.pojo.enums.GradeEnum;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 */
@Data
@TableName(value = "user")
public class User extends BaseEntity{

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer gender;

    private String nickName;

    private String mobile;

    private LocalDate birthday;

    private String avatarUrl;

    private String openid;

    private String sessionKey;

    private String city;

    private String country;

    private String language;

    private String province;

    private Integer status;

    private Long balance;

    @TableLogic(delval = "1", value = "0")
    private Integer deleted;

//    @TableField(exist = false)
//    private List<UmsAddress> addressList;

    private Integer point;

    @TableField("aa")
    private GradeEnum gradeEnum;
}
