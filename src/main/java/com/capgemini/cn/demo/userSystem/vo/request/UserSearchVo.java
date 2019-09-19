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
public class UserSearchVo extends BaseSearchVo {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "用户性别")
    private String gender;

    @ApiModelProperty(value = "所属部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "所属机构ID")
    private Long branchId;
}
