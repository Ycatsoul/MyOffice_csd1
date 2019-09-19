package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/4 17:02
 */
@Data
public class Menu {

    private Long menuId;

    private String menuUrl;

    private String menuPath;

    private String menuComponent;

    private String menuName;

    private String menuIcon;

    private Integer menuOrder;

    private Long parentMenuId;

    private List<Menu> children;

    private Boolean requireAuth;

    private List<Role> roles;

    private Boolean disabled;
}
