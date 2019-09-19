package com.capgemini.cn.demo.userSystem.vo.request;

import lombok.Data;

/**
 * @author hasaker
 * @since 2019/9/12 10:10
 */
@Data
public class UserRoleEditVo {

    private Long userRoleId;

    private Long userId;

    private Long roleId;
}
