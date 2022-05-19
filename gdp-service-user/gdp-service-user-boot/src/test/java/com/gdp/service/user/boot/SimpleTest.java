package com.gdp.service.user.boot;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gdp.service.user.boot.mapper.UserMapper;
import com.gdp.service.user.boot.pojo.entity.User;
import com.gdp.service.user.boot.pojo.enums.GradeEnum;
import com.gdp.service.user.boot.service.IAppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

import java.util.List;

@SpringBootTest
public class SimpleTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IAppUserService appUserService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<User>()
        .eq(User::getId, 45L).select(User::getId, User::getGradeEnum);
        List<User> userList = userMapper.selectList(query);
        //Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setMobile("15313151721");
        user.setNickName("cc");
        user.setGradeEnum(GradeEnum.PRIMARY);
        boolean flag = appUserService.save(user);
        //int insert = userMapper.insert(user);
        System.out.println("insert = " + flag);
        System.out.println(user.getId() + "," + user.getNickName());
    }

    @Test
    public void testDelete() {
        boolean flag = appUserService.removeById(45L);
        //int insert = userMapper.insert(user);
        System.out.println("delete = " + flag);
    }

    @Test
    public void testUpdate() {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId, 55L)
                .set(User::getDeleted, 1);
        boolean flag = appUserService.update(updateWrapper);
        //int insert = userMapper.insert(user);
        System.out.println("update = " + flag);
    }

    @Test
    public void aa(){
        String token = "kjxlZHSjD8SHoGcv6H-H7gadSSPz5j4Wy1KE4wO9fzzvR057FSK7CDzh5UAZGOG8XLQ4lmJ3KValFsrTvxKSS-eMX04XwW17vQM73CYaezkdq18J4ysX-arJDKaQIHRdEkdMl4Bwow4qYLKgVucFgD9FPF0lBBToc31GrhhvCbmDuVJIdcUoCWbPDNq_1LQSrJfDJtxpIJQmBN_SCQxmfWHs7_4-OXz3tlTXGlmYVYPLfLaNz_3JvrI5DzdcW2KDcCfx6bkQc9RWjn22DzZ6bsC2dWY5ATAT63EFUsiYKqXMujbfyKn4-nEg3K5bAtbAtG4fuVw2cXQ4uwJJL62KrQ";
        String claims = JwtHelper.decode(token).getClaims();
        System.out.println(claims);
        JSONObject jsonPayload = JSONUtil.parseObj(claims);
        System.out.println(jsonPayload);
    }

    @Test
    public void rsaKeyCreatesValidRsaSignedTokens() {
        String publickey ="-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA15w2T+DOwQzFxWa26bFq\n" +
                "1xhYdy49INE7vPC1D6+09P/c1byY6U7N3AStO3eYhT/v5TUwCfXobXZ98e2Gf1/O\n" +
                "KO/TmLFXOcoea9MP0kVcVV/80O6g19tzWDIkBbphO9g5E0lq/VwQYC0Cc3/4jNO0\n" +
                "87d5BwMdlSasqbJjA8MVDXjzupPDl+hw6Wr48motbJGgSzrOpNPZMJK25ylwlqac\n" +
                "TCetcjQXL4tpKcPXOaCEUFwWWxBUtFfwP+nU0PSDTUhwe6UsKtugnkAd1TYn9+bh\n" +
                "78J77CFwpFPxtfutSbP2E8/yxYKufUg6NYo+F3PRQQElTb/aH8zi4n+RIHvec2Zw\n" +
                "HwIDAQAB\n" +
                "-----END PUBLIC KEY-----";
        RsaVerifier verifier = new RsaVerifier(publickey);
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6IlJPTEVfVklQLFJPTEVfVVNFUiIsIm5hbWUiOiJpdGhlaW1hIiwiaWQiOiIxIn0.oW1h0Xza7vUEgi0rzOlgMLdLicnJ7kFXg6pBjW640sxzeeiPC0cUfXEYAAmmTYEMQtLeivbZeiar-p5ydupUVhTVF8evEzqt64p31DJQ5tsIQ2-moXE8Q6W9JP2MY1sylBdN8rmVmIW6pqBh4KuLkFFalWd851HAzdQg95SvIbyUu6F6M-iZvL4-qcTdakYjpnWKOTWNBighB-hMlaAs6REMXyMsU0wo8DRHyNypUb4vILb_NPjd69ubFhOTUvLkYRtImEk8QNnQ8-l68GhAGmqifxDkNAz3I0f0Mcgfu3OdT056qcR6FG_o1thzOaXRZQliA1B2ZnZ3tepHv6ahxw";


        //String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbnZpcm9ubWVudCI6ImRlZmF1bHQiLCJncmFudF90eXBlIjoic21zX2NvZGUiLCJ1c2VyX25hbWUiOiJuaWtlIiwic2NvcGUiOlsiYWxsIl0sImF1dGhlbnRpY2F0aW9uSWRlbnRpdHkiOiJtb2JpbGUiLCJleHAiOjE2NTA0NTYwNjMsImF1dGhvcml0aWVzIjpbIlJPTEVfQVBQIiwiUk9MRV9VU0VSIl0sImp0aSI6IjBlNWI4MWZiLWVkNDctNDUyYy1hMjdlLTY3MzU5ODZmMzRiZSIsImVuYWJsZWQiOnRydWUsImNsaWVudF9pZCI6ImFydHdvcmxkLWFwcCIsIm1lbWJlcklkIjoyMzc5OTY5NTU4MTc1MzM0NH0.STQHH6pSyOXbJkY8hkKBtOVg_RIWHI_nYp-Mg-ANkaEYUvQIKrIeoUtL3cPfKu6iNvnGPVtAsWY60glqYHN6BwBMWHa-AjPyjQpV8Zh1ifpS-V5GSPf_Mf9kW7OvdJ2mx4JrV4Bs23wjXn5TnZrdEBlNQKiOZimcR4pbzcDCbnyBdxd7mbdWeGwDOj6_Oum58EdqxDTg_95_-9pgA96jzWOLVrmCaIAN98vPzLVlDrxVo4zXWxx3RvFEEGnrN_zp-Vp77ipXrhj_RzKtuQaqynKN30piDM9mDadQJufqEYGLgBH-g0Ar5wig-kKLgg1qNh1iDG3YDykEsNopYnpOog";
        Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
        System.out.println(jwt);
    }

    @Test
    public void test(){
        String s = "S1L1U1Lesson1";
        System.out.println(s.substring(2, 4));
    }
}
