package com.capgemini.cn.demo.utils;


import com.capgemini.cn.demo.userSystem.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Bowen Du
 * @since 2019-08-29 14:32
 */
public class UserUtils {
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
