package com.capgemini.cn.demo.messagePassing.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:模糊查询请求体
 * @Classname :UserReq
 * @author: Skye Kong
 * @date: 2019/9/30
 */
@Data
public class UserReq {

    @ApiModelProperty(value = "机构名", required = true)
    private String branchName;

    @ApiModelProperty(value = "机构ID", required = true)
    private Long branchId;

    @ApiModelProperty(value = "部门ID", required = true)
    private Long departmentId;

    @ApiModelProperty(value = "部门名", required = true)
    private String departName;

    @ApiModelProperty(value = "员工ID", required = true)
    private Long userId;

    @ApiModelProperty(value = "员工名", required = true)
    private String name;


}
