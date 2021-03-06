package com.capgemini.cn.demo.messagePassing.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:修改消息的请求体
 * @Classname :MailBoxEditReq
 * @author: Skye Kong
 * @date: 2019/9/24
 */
@Data
public class MailBoxEditReq {

    @ApiModelProperty(value = "messagetouser表id",required = true)
    private Integer id;

    @ApiModelProperty(value = "消息id", required = true)
    private Integer messageId;

    @ApiModelProperty(value = "消息标题", required = true)
    private String title;

    @ApiModelProperty(value = "消息类型名字", required = true)
    private String messageTypeName;

    @ApiModelProperty(value = "开始有效时间", required = true)
    private Date beginTime;

    @ApiModelProperty(value = "结束有效时间", required = true)
    private Date endTime;

    @ApiModelProperty(value = "消息内容", required = true)
    private String content;
}
