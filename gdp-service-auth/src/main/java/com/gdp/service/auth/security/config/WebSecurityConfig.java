package com.gdp.service.auth.security.config;

import com.gdp.service.auth.security.core.point.JsonAuthenticationEntryPoint;
import com.gdp.service.auth.security.core.userdetails.app.AppUserDetailsServiceImpl;
import com.gdp.service.auth.security.extension.mobile.SmsCodeAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService sysUserDetailsService;
    private final UserDetailsService appUserDetailsService;
    private final StringRedisTemplate redisTemplate;
    private final JsonAuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/oauth/**", "/fegin/**", "/sms-code").permitAll()
                // @link https://gitee.com/xiaoym/knife4j/issues/I1Q5X6 (接口文档knife4j需要放行的规则)
                .antMatchers("/webjars/**", "/doc.html", "/swagger-resources/**", "/v2/api-docs").permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).disable()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
    }

    /**
     * 认证管理对象
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(smsCodeAuthenticationProvider())
                .authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * 手机验证码认证授权提供者
     *
     * @return
     */
    @Bean
    public SmsCodeAuthenticationProvider smsCodeAuthenticationProvider() {
        SmsCodeAuthenticationProvider provider = new SmsCodeAuthenticationProvider();
        provider.setUserDetailsService(appUserDetailsService);
        provider.setRedisTemplate(redisTemplate);
        return provider;
    }

//    /**
//     * 微信认证授权提供者
//     *
//     * @return
//     */
//    @Bean
//    public WechatAuthenticationProvider wechatAuthenticationProvider() {
//        WechatAuthenticationProvider provider = new WechatAuthenticationProvider();
//        provider.setUserDetailsService(memberUserDetailsService);
//        provider.setWxMaService(wxMaService);
//        provider.setMemberFeignClient(memberFeignClient);
//        return provider;
//    }


    /**
     * 用户名密码认证授权提供者
     *
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(sysUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(false); // 是否隐藏用户不存在异常，默认:true-隐藏；false-抛出异常；
        return provider;
    }


    /**
     * 密码编码器
     * <p>
     * 委托方式，根据密码的前缀选择对应的encoder，例如：{bcypt}前缀->标识BCYPT算法加密；{noop}->标识不使用任何加密即明文的方式
     * 密码判读 DaoAuthenticationProvider#additionalAuthenticationChecks
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
