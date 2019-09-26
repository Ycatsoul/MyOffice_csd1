package com.capgemini.cn.demo.userSystem.vo.request;

import com.capgemini.cn.demo.baseVo.BaseSearchVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author WYQ
 * @description:一人做事一人当
 * @description:小丁做事小叮当
 * @date 2019/9/24 16:07
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class BranchSearchVo extends BaseSearchVo {

    @ApiModelProperty(value = "机构名称")
    private String branchName;
}