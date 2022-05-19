package com.gdp.srevice.gateway;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

@SpringBootTest
public class SimpleTest {

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
        String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkjxlZHSjD8SHoGcv6H+H\n" +
                "7gadSSPz5j4Wy1KE4wO9fzzvR057FSK7CDzh5UAZGOG8XLQ4lmJ3KValFsrTvxKS\n" +
                "S+eMX04XwW17vQM73CYaezkdq18J4ysX+arJDKaQIHRdEkdMl4Bwow4qYLKgVucF\n" +
                "gD9FPF0lBBToc31GrhhvCbmDuVJIdcUoCWbPDNq/1LQSrJfDJtxpIJQmBN/SCQxm\n" +
                "fWHs7/4+OXz3tlTXGlmYVYPLfLaNz/3JvrI5DzdcW2KDcCfx6bkQc9RWjn22DzZ6\n" +
                "bsC2dWY5ATAT63EFUsiYKqXMujbfyKn4+nEg3K5bAtbAtG4fuVw2cXQ4uwJJL62K\n" +
                "rQIDAQAB\n" +
                "-----END PUBLIC KEY-----\n";
        RsaVerifier verifier = new RsaVerifier(publicKey);
        String token = "kjxlZHSjD8SHoGcv6H-H7gadSSPz5j4Wy1KE4wO9fzzvR057FSK7CDzh5UAZGOG8XLQ4lmJ3KValFsrTvxKSS-eMX04XwW17vQM73CYaezkdq18J4ysX-arJDKaQIHRdEkdMl4Bwow4qYLKgVucFgD9FPF0lBBToc31GrhhvCbmDuVJIdcUoCWbPDNq_1LQSrJfDJtxpIJQmBN_SCQxmfWHs7_4-OXz3tlTXGlmYVYPLfLaNz_3JvrI5DzdcW2KDcCfx6bkQc9RWjn22DzZ6bsC2dWY5ATAT63EFUsiYKqXMujbfyKn4-nEg3K5bAtbAtG4fuVw2cXQ4uwJJL62KrQ";

        //String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbnZpcm9ubWVudCI6ImRlZmF1bHQiLCJncmFudF90eXBlIjoic21zX2NvZGUiLCJ1c2VyX25hbWUiOiJuaWtlIiwic2NvcGUiOlsiYWxsIl0sImF1dGhlbnRpY2F0aW9uSWRlbnRpdHkiOiJtb2JpbGUiLCJleHAiOjE2NTA0NTYwNjMsImF1dGhvcml0aWVzIjpbIlJPTEVfQVBQIiwiUk9MRV9VU0VSIl0sImp0aSI6IjBlNWI4MWZiLWVkNDctNDUyYy1hMjdlLTY3MzU5ODZmMzRiZSIsImVuYWJsZWQiOnRydWUsImNsaWVudF9pZCI6ImFydHdvcmxkLWFwcCIsIm1lbWJlcklkIjoyMzc5OTY5NTU4MTc1MzM0NH0.STQHH6pSyOXbJkY8hkKBtOVg_RIWHI_nYp-Mg-ANkaEYUvQIKrIeoUtL3cPfKu6iNvnGPVtAsWY60glqYHN6BwBMWHa-AjPyjQpV8Zh1ifpS-V5GSPf_Mf9kW7OvdJ2mx4JrV4Bs23wjXn5TnZrdEBlNQKiOZimcR4pbzcDCbnyBdxd7mbdWeGwDOj6_Oum58EdqxDTg_95_-9pgA96jzWOLVrmCaIAN98vPzLVlDrxVo4zXWxx3RvFEEGnrN_zp-Vp77ipXrhj_RzKtuQaqynKN30piDM9mDadQJufqEYGLgBH-g0Ar5wig-kKLgg1qNh1iDG3YDykEsNopYnpOog";
        Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
        System.out.println(jwt);
    }
}
