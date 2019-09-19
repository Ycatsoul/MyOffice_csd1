package com.capgemini.cn.demo.userSystem.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hasaker
 * @since 2019/9/2 19:17
 */
@Data
public class UserEditVo {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "所属部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "是否被屏蔽")
    private Boolean isBlocked;
}
