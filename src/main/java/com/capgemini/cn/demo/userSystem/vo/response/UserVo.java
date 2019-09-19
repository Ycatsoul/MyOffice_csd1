package com.capgemini.cn.demo.userSystem.vo.response;


import com.capgemini.cn.demo.userSystem.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class UserVo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户性别
     */
    private String gender;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 所属部门ID
     */
    private Long departmentId;

    /**
     * 所属部门名称
     */
    private String departmentName;

    /**
     * 角色ID
     */
    private List<Role> roles;

    /**
     * 是否被屏蔽
     */
    private Boolean isBlocked;
}
