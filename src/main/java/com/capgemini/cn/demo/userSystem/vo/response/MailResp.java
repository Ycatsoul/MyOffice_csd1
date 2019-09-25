package com.capgemini.cn.demo.userSystem.vo.response;

import com.capgemini.cn.demo.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:收件箱信息类型
 * @Classname :MailListResp
 * @author: Skye Kong
 * @date: 2019/9/23
 */
@Data
public class MailResp {
    @ApiModelProperty(value = "消息id", required = true)
    private Integer messageId;

    @ApiModelProperty(value = "发件人", required = true)
    private String name;

    @ApiModelProperty(value = "收件人", required = true)
    private String sendUser;

    @ApiModelProperty(value = "是否已读", required = true)
    private Integer ifRead;

    @ApiModelProperty(value = "消息主题", required = true)
    private String title;

    @ApiModelProperty(value = "发送时间", required = true)
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone= DateUtils.DEFAULT_ZONE)
    private Date recordTime;

    @ApiModelProperty(value = "紧急程度", required = true)
    private String messageTypeName;

    @ApiModelProperty(value = "消息内容", required = true)
    private String content;

}
