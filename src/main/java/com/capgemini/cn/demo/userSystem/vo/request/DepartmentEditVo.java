package com.capgemini.cn.demo.userSystem.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author WYQ
 * @description:一人做事一人当
 * @description:小丁做事小叮当
 * @date 2019/9/24 16:09
 */

@Data
@ApiModel
public class DepartmentEditVo {

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    @ApiModelProperty(value = "联系电话")
    private String connectPhone;

    @ApiModelProperty(value = "联系电话（座机）")
    private String connectTelephone;

    @ApiModelProperty(value = "部门主管ID")
    private Long principalUserId;

    @ApiModelProperty(value = "所属机构ID")
    private Long branchId;
}