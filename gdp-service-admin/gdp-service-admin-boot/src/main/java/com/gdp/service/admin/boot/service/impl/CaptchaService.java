package com.gdp.service.admin.boot.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.util.IdUtil;
import com.gdp.service.admin.boot.service.ICaptchaService;
import com.gdp.service.common.core.constant.SecurityConstants;
import com.google.code.kaptcha.Producer;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class CaptchaService implements ICaptchaService {

    private final Producer producer;
    private final StringRedisTemplate redisTemplate;

    @Override
    public Object getCaptcha() {
        String capText = producer.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage image = producer.createImage(capStr);
        // 缓存验证码至Redis
        String uuid = IdUtil.simpleUUID();

        redisTemplate.opsForValue().set(SecurityConstants.VALIDATE_CODE_PREFIX + uuid, code, 60, TimeUnit.SECONDS);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return e;
        }

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("uuid", uuid);
        resultMap.put("img", Base64.encode(os.toByteArray()));
        return resultMap;
    }
}
