package com.capgemini.cn.demo.userSystem.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:员工返回体
 * @Classname :UserResp
 * @author: Skye Kong
 * @date: 2019/9/25
 */
@Data
public class UserResp {
    @ApiModelProperty(value = "员工id",required = true)
    private Long userId;

    @ApiModelProperty(value = "员工名",required = true)
    private String name;
}
