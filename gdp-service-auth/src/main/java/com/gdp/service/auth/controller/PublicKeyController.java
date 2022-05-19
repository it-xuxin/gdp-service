package com.gdp.service.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * RSA公钥开放接口
 */
@RestController
@RequestMapping("/oauth")
@AllArgsConstructor
public class PublicKeyController {

    private KeyPair keyPair;

    @GetMapping("/getPublicKey")
    public Map<String, Object> getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        var key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }


}
