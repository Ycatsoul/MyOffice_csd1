package com.capgemini.cn.demo.config;
import com.capgemini.cn.demo.userSystem.entity.Menu;
import com.capgemini.cn.demo.userSystem.entity.Role;
import com.capgemini.cn.demo.userSystem.service.MenuRoleService;
import com.capgemini.cn.demo.userSystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 10:40
 * 通过当前的请求地址，获取该地址需要的用户角色
 */
@Component
public class DeemoMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;
    @Autowired
    MenuRoleService menuRoleService;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public DeemoMetadataSource(MenuService menuService, MenuRoleService menuRoleService) {
        this.menuService = menuService;
        this.menuRoleService = menuRoleService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> allMenus = menuService.getAllMenus();

        // 如果地址是/login_page，这个是登录页，不需要任何角色即可访问，直接返回null
        if ("login_page".equals(requestUrl)) {
            return null;
        }

        // 需要相应权限才能访问的路径
        for (Menu menu : allMenus) {
            menu.setRoles(menuRoleService.getRolesByMenuId(menu.getMenuId()));
            if (antPathMatcher.match(menu.getMenuUrl(), requestUrl) && menu.getRoles().size() > 0) {
                List<Role> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getRoleName();
                }

                return SecurityConfig.createList(values);
            }
        }

        // 所有未匹配到的路径，都是认证(登录)后就可以访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
