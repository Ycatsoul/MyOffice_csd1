package com.capgemini.cn.demo.userSystem.vo.request;

import com.capgemini.cn.demo.baseVo.BaseSearchVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019/9/4 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class LoginLogSearchVo extends BaseSearchVo {

    @ApiModelProperty("登录用户的ID")
    private Long loginUserId;

    @ApiModelProperty("登录起始时间")
    private Date startTime;

    @ApiModelProperty("登录截止时间")
    private Date endTime;
}
