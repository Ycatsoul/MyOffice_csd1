package com.capgemini.cn.demo.messagePassing.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:部门列表返回体
 * @Classname :DepartListResp
 * @author: Skye Kong
 * @date: 2019/9/25
 */
@Data
public class DepartListResp {

    @ApiModelProperty(value = "部门列表", required = true)
    private List<DepartResp> departList;

    @ApiModelProperty(value = "机构名", required = true)
    private String branchName;
}
