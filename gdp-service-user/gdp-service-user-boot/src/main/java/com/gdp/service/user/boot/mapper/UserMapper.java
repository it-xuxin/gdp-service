package com.gdp.service.user.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdp.service.user.boot.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User> {
//
//    @Select("<script>" +
//            " SELECT * from ums_member " +
//            " <if test ='nickname !=null and nickname.trim() neq \"\" ' >" +
//            "       WHERE nick_name like concat('%',#{nickname},'%')" +
//            " </if>" +
//            " ORDER BY gmt_modified DESC, gmt_create DESC" +
//            "</script>")
//    @Results({
//            @Result(id = true, column = "id", property = "id"),
//            @Result(property = "addressList", column = "id", many = @Many(select = "com.youlai.mall.ums.mapper.UmsAddressMapper.listByUserId"))
//    })
//    List<User> list(Page<User> page, String nickname);

//    public List<User> findAllUser();

}
