package com.capgemini.cn.demo.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author hasaker
 * @since 2019/9/8 11:22
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    /**
     * 判断是否具有足够的权限
     * decide方法接收三个参数，
     * 其中第一个参数中保存了当前登录用户的角色信息，
     * 第三个参数则是UrlFilterInvocationSecurityMetadataSource中的getAttributes方法传来的，
     * 表示当前请求需要的角色（可能有多个）
     * @param auth Authentication
     * @param o Object
     * @param cas cas
     */
    @Override
    public void decide(Authentication auth, Object o, Collection<ConfigAttribute> cas){
        for (ConfigAttribute ca : cas) {
            //当前请求需要的权限
            String needRole = ca.getAttribute();

            // 如果当前请求需要的权限为ROLE_LOGIN则表示登录即可访问，和角色没有关系
            // 此时我需要判断authentication是不是AnonymousAuthenticationToken的一个实例，
            // 如果是，则表示当前用户没有登录，没有登录就抛一个BadCredentialsException异常，
            // 登录了就直接返回，则这个请求将被成功执行。
            if ("ROLE_LOGIN".equals(needRole)) {
                if (auth instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录!");
                } else {
                    return;
                }
            }

            // 当前用户所具有的权限
            // 遍历collection，同时查看当前用户的角色列表中是否具备需要的权限，如果具备就直接返回，否则就抛异常
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("权限不足!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
