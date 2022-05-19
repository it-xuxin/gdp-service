package com.gdp.service.admin.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdp.service.admin.boot.mapper.SysOAuthClientMapper;
import com.gdp.service.admin.boot.pojo.entity.SysOAuthClient;
import com.gdp.service.admin.boot.service.ISysOAuthClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysOAuthClientServiceImpl extends ServiceImpl<SysOAuthClientMapper, SysOAuthClient> implements ISysOAuthClientService {

}
