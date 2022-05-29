package com.gdp.service.admin.boot.controller;

import com.gdp.service.admin.boot.service.ICaptchaService;
import com.gdp.service.common.core.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/captcha")
@AllArgsConstructor
public class CaptchaController {

    private final ICaptchaService captchaService;

    /**
     * 获取图片二维码
     * <img src="data:image/png;base64,   >
     *
     * @return
     */
    @GetMapping("/code")
    public Result code() {
        return Result.success(captchaService.getCaptcha());
    }
}
