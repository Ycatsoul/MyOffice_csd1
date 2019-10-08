package com.capgemini.cn.demo.userSystem.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hasaker
 * @since 2019/9/2 17:10
 */
@Data
@ApiModel(description = "机构(Branch)添加和修改VO")
public class BranchEditVo {

    @ApiModelProperty(notes = "机构ID", example = "4717142704390144", position = 1)
    private Long branchId;

    @ApiModelProperty(notes = "机构名称", example = "Customer System Development", required = true, position = 2)
    private String branchName;

    @ApiModelProperty(notes = "机构简称", example = "CSD", position = 3)
    private String branchShortName;
}
