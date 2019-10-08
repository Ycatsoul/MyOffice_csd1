package com.capgemini.cn.demo.manualSign.vo.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.Date;

@Data
public class ManualSignEditVo {

    @ApiModelProperty(value = "签卡ID")
    private Long manualSignId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "签卡时间")
    private Date signTime;

    @ApiModelProperty(value = "签卡备注")
    private String signDesc;

    @ApiModelProperty(value = "签卡标记")
    private Integer signTag;
}
