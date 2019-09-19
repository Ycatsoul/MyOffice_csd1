package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hasaker
 * @since 2019/8/29 10:38
 * 角色类
 */
@Data
public class Role implements Serializable {

    /**
     * Id
     */
    private Long roleId;

    /**
     * 名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String roleDesc;
}
