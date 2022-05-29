package com.gdp.srevice.gateway.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Configuration
@ConfigurationProperties("spring.security")
public class AuthProperties {
    private List<String> whites = new ArrayList();
    private List<String> blocks = new ArrayList();
    private List<String> resource = new ArrayList();
}
