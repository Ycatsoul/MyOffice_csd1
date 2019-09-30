package com.capgemini.cn.demo.manualSign.vo.request;

import com.capgemini.cn.demo.baseVo.BaseSearchVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class ManualSignSearchVo extends BaseSearchVo {

    @ApiModelProperty(value = "机构ID")
    private Long branchId;

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "签卡起始时间")
    private Date startTime;

    @ApiModelProperty(value = "签卡结束时间")
    private Date endTime;
}