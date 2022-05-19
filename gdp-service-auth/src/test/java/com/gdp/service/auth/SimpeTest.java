package com.gdp.service.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest
public class SimpeTest {

    @Test
    public void encode() {
        String code = "gdp-service:LKg7icFR/nbIWATvAoqYEyyoO4nBUf52";
        System.out.println((Base64.getEncoder().encodeToString(code.getBytes(StandardCharsets.UTF_8))));
    }

    //Z2RwLXNlcnZpY2U6TEtnN2ljRlIvbmJJV0FUdkFvcVlFeXlvTzRuQlVmNTI=
    @Test
    public void decode() {
        String code = "Z2RwLXNlcnZpY2U6TEtnN2ljRlIvbmJJV0FUdkFvcVlFeXlvTzRuQlVmNTI=";
        String basicPlainText = new String(Base64.getDecoder().decode(code.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);

        System.out.println(basicPlainText);
    }
}
