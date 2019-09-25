package com.capgemini.cn.demo.userSystem.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:部门返回体
 * @Classname :DepartResp
 * @author: Skye Kong
 * @date: 2019/9/25
 */
@Data
public class DepartResp {
    @ApiModelProperty(value = "部门id",required = true)
    private Long departId;

    @ApiModelProperty(value = "机构名",required = true)
    private String departName;
}

