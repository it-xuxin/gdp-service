package com.gdp.service.auth.controller;

import com.gdp.service.auth.service.CertifiedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth")
public class AuthController {

    private final CertifiedService certifiedService;


    @PostMapping("/loginByCode")
    public Object loginByCode(@RequestParam String mobile, @RequestParam String code) {
        return certifiedService.loginByCode(mobile, code);
    }
}
