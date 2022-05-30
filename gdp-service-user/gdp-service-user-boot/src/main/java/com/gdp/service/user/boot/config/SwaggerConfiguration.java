package com.gdp.service.user.boot.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Kni4j配置类
 */
@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@Slf4j
public class SwaggerConfiguration {

    @Value("${knife4j.password_token_url:http://127.0.0.1:9999/auth/oauth/token}")
    private String passwordTokenUrl;


    @Bean(value = "defaultApi2")
    public Docket restApi() {
        //schema
        List<GrantType> grantTypes = new ArrayList<>();
        //密码模式
        ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(passwordTokenUrl);
        grantTypes.add(resourceOwnerPasswordCredentialsGrant);
        OAuth oAuth = new OAuthBuilder().name("oauth2")
                .grantTypes(grantTypes).build();
        //context
        //scope方位
        List<AuthorizationScope> scopes = new ArrayList<>();
        scopes.add(new AuthorizationScope("read", "read  resources"));
        scopes.add(new AuthorizationScope("write", "write resources"));
        scopes.add(new AuthorizationScope("reads", "read all resources"));
        scopes.add(new AuthorizationScope("writes", "write all resources"));

        SecurityReference securityReference = new SecurityReference("oauth2", scopes.toArray(new AuthorizationScope[]{}));
        SecurityContext securityContext = new SecurityContext(Lists.newArrayList(securityReference), PathSelectors.ant("/**"));
        //schemas
        List<SecurityScheme> securitySchemes = Lists.newArrayList(oAuth);
        //securyContext
        List<SecurityContext> securityContexts = Lists.newArrayList(securityContext);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // .apis(RequestHandlerSelectors.basePackage("com.youlai.admin.controller"))
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts)
                .securitySchemes(securitySchemes)
                .apiInfo(apiInfo());

//        Docket docket=new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                //分组名称
//                .groupName("测试分组")
//                .select()
//                //这里指定Controller扫描包路径
//                .apis(RequestHandlerSelectors.basePackage("com.gdp.service.user.boot.controller"))
//                .paths(PathSelectors.any())
//                .build();
//        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("系统管理")
                .description("<div style='font-size:14px;color:red;'>gdp-service-user-boot</div>")
                .termsOfServiceUrl("http://127.0.0.1:9999/u/doc.html")
                .contact(new Contact("果丹皮", "https://www.g.cn", "g@163.com"))
                .license("Open Source")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0.0")
                .build();
    }

}
