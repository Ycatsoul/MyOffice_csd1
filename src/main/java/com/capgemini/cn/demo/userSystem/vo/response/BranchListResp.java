package com.capgemini.cn.demo.userSystem.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:机构信息列表返回体
 * @Classname :BranchListResp
 * @author: Skye Kong
 * @date: 2019/9/25
 */
@Data
public class BranchListResp {

    @ApiModelProperty(value = "机构列表", required = true)
    private List<BranchResp> branchList;
}
