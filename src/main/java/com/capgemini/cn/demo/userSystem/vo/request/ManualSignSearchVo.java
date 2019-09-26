package com.capgemini.cn.demo.userSystem.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author WYQ
 * @description:一人做事一人当
 * @description:小丁做事小叮当
 * @date 2019/9/24 15:51
 */

@Data
@ApiModel
public class ManualSignSearchVo {

    @ApiModelProperty(value = "机构ID")
    private Long branchId;

    @ApiModelProperty (value = "部门ID")
    private Long departmentId;

    @ApiModelProperty (value = "用户ID")
    private Long userId;

    @ApiModelProperty (value = "用户姓名")
    private String userName;

    @ApiModelProperty (value ="签卡起始时间")
    private Date startTime;

    @ApiModelProperty (value ="签卡结束时间")
    private Date endTime;

}
