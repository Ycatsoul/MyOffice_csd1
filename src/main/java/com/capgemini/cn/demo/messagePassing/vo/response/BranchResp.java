package com.capgemini.cn.demo.messagePassing.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:机构信息返回他体
 * @Classname :BranchResp
 * @author: Skye Kong
 * @date: 2019/9/25
 */
@Data
public class BranchResp {

    @ApiModelProperty(value = "机构id",required = true)
    private Long branchId;

    @ApiModelProperty(value = "机构名",required = true)
    private String branchName;
}
