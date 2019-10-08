package com.capgemini.cn.demo.userSystem.vo.request;

import com.capgemini.cn.demo.baseVo.BaseSearchVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class DepartmentSearchVo extends BaseSearchVo {

    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    @ApiModelProperty(value = "所属机构ID")
    private Long branchId;
}
