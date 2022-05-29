package com.gdp.service.admin.boot.service.impl;

import com.gdp.service.admin.boot.pojo.entity.SysOAuthClient;
import com.gdp.service.admin.boot.service.ISysOAuthClientService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SysOAuthClientServiceImplTest {

    @Autowired
    private ISysOAuthClientService sysOAuthClientService;

    @Test
    public void findById(){
        SysOAuthClient client = sysOAuthClientService.getById("gdp-service-user-boot");
        log.info(client.toString());
    }
}