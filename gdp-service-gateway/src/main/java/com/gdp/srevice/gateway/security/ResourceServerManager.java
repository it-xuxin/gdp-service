package com.gdp.srevice.gateway.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.gdp.service.common.core.constant.GlobalConstants;
import com.gdp.service.common.core.constant.SecurityConstants;
import com.gdp.srevice.gateway.config.AuthProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.*;

@Component
@AllArgsConstructor
@Slf4j
public class ResourceServerManager implements ReactiveAuthorizationManager<AuthorizationContext> {

//    private final RedisTemplate redisTemplate;

    private final AuthProperties authProperties;
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        String url = authorizationContext.getExchange().getRequest().getURI().getPath();
        // 跳过不需要验证的资源路径
        for (String resource : authProperties.getResource()) {
            if (ANT_PATH_MATCHER.match(resource, url)) {
                log.info("Match resource = {}, url = {}", resource, url);
                return Mono.just(new AuthorizationDecision(false));
            }
        }
        return Mono.just(new AuthorizationDecision(true));
    }

//    @Override
//    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
//        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
//        String path = request.getURI().getPath();
//        PathMatcher pathMatcher = new AntPathMatcher();
//
//        // 1. 对应跨域的预检请求直接放行
//        if (request.getMethod() == HttpMethod.OPTIONS) {
//            return Mono.just(new AuthorizationDecision(true));
//        }
//
//        // 2. token为空拒绝访问
//        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
//        if (StrUtil.isBlank(token)) {
//            return Mono.just(new AuthorizationDecision(false));
//        }
//
//
//        // 3.缓存取资源权限角色关系列表
//        Map<Object, Object> resourceRolesMap = new HashMap<>();
//        //redisTemplate.opsForHash().entries(GlobalConstants.URL_PERM_ROLES_KEY);
//        Iterator<Object> iterator = resourceRolesMap.keySet().iterator();
//
//        // 4.请求路径匹配到的资源需要的角色权限集合authorities
//        List<String> authorities = new ArrayList<>();
//        while (iterator.hasNext()) {
//            String pattern = (String) iterator.next();
//            if (pathMatcher.match(pattern, path)) {
//                authorities.addAll(Convert.toList(String.class, resourceRolesMap.get(pattern)));
//            }
//        }
//        Mono<AuthorizationDecision> authorizationDecisionMono = mono
//                .filter(Authentication::isAuthenticated)
//                .flatMapIterable(Authentication::getAuthorities)
//                .map(GrantedAuthority::getAuthority)
//                .any(roleId -> {
//                    // 5. roleId是请求用户的角色(格式:ROLE_{roleId})，authorities是请求资源所需要角色的集合
//                    log.info("访问路径：{}", path);
//                    log.info("用户角色roleId：{}", roleId);
//                    log.info("资源需要权限authorities：{}", authorities);
//                    return authorities.contains(roleId);
//                })
//                .map(AuthorizationDecision::new)
//                .defaultIfEmpty(new AuthorizationDecision(false));
//        return authorizationDecisionMono;
//    }
}
