package com.capgemini.cn.demo.userSystem.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author WYQ
 * @description:一人做事一人当
 * @description:小丁做事小叮当
 * @date 2019/9/24 16:06
 */

@Data
@ApiModel(description = "机构(Branch)添加和修改VO")
public class BranchEditVo {

    @ApiModelProperty(notes = "机构ID", example = "000001", position = 1)
    private Long branchId;

    @ApiModelProperty(notes = "机构名称", example = "Customer System Development", required = true, position = 2)
    private String branchName;

    @ApiModelProperty(notes = "机构简称", example = "CSD", position = 3)
    private String branchShortName;
}
