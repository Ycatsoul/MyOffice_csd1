package com.capgemini.cn.demo.userSystem.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: Skye Kong
 * @date: 2019/9/20
 */
@Data
public class MessageInsertReq {

    @ApiModelProperty(value = "消息id", required = true)
    private Integer messageId;

    @ApiModelProperty(value = "消息标题", required = true)
    private String title;

    @ApiModelProperty(value = "开始有效时间", required = true)
    private Date beginTime;

    @ApiModelProperty(value = "结束有效时间", required = true)
    private Date endTime;

    @ApiModelProperty(value = "发送对象", required = true)
    private Long userId;

    @ApiModelProperty(value = "消息内容", required = true)
    private String content;

    @ApiModelProperty(value = "是否发布", required = true)
    private Integer ifPublish;

    @ApiModelProperty(value = "记录创建时间", required = true)
    private Date recordTime;

    @ApiModelProperty(value = "创建人", required = true)
    private Long fromUserId;

    @ApiModelProperty(value = "是否已读", required = true)
    private Integer ifRead;

    @ApiModelProperty(value = "消息类型名字", required = true)
    private String messageTypeName;

    @ApiModelProperty(value = "是否给所有人发送", required = true)
    private Integer ifAll;
}
