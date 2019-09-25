package com.capgemini.cn.demo.userSystem.vo.request;

import com.capgemini.cn.demo.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:从后台查询的消息
 * @Classname :MessageReq
 * @author: Skye Kong
 * @date: 2019/9/18
 */
@Data
public class MessageReq {

    @ApiModelProperty(value = "消息id", required = true)
    private Integer messageId;

    @ApiModelProperty(value = "消息标题", required = true)
    private String title;

    @ApiModelProperty(value = "消息类型", required = true)
    private Integer type;

    @ApiModelProperty(value = "消息内容", required = true)
    private String content;

    @ApiModelProperty(value = "发送者", required = true)
    /**
     * 通过id和userinfo的userid对应，查找username，对应MessageResp的创建者
     * （username）
     */
    private String fromUserId;

    @ApiModelProperty(value = "发送对象", required = true)
    private Integer ifAll;

    @ApiModelProperty(value = "开始有效时间", required = true)
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone= DateUtils.DEFAULT_ZONE)
    private Date beginTime;

    @ApiModelProperty(value = "有效结束时间", required = true)
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone= DateUtils.DEFAULT_ZONE)
    private Date endTime;

    @ApiModelProperty(value = "创建时间", required = true)
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone= DateUtils.DEFAULT_ZONE)
    private Date recordTime;

}
