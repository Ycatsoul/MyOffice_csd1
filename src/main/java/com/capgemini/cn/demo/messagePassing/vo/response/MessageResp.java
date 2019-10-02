package com.capgemini.cn.demo.messagePassing.vo.response;

import com.capgemini.cn.demo.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description:显示消息类
 * @Classname :MessageResp
 * @author: Skye Kong
 * @date: 2019/9/18
 */
@Data
public class MessageResp {

    @ApiModelProperty(value = "消息id", required = true)
    private Integer messageId;

    @ApiModelProperty(value = "消息标题", required = true)
    private String title;

    @ApiModelProperty(value = "消息类型", required = true)
    private String messageTypeName;

    @ApiModelProperty(value = "消息内容", required = true)
    private String content;

    @ApiModelProperty(value = "创建者（通过fromUserId查找）", required = true)
    private String username;

    @ApiModelProperty(value = "发送对象（查出的）", required = true)
    private Integer ifAll;

    @ApiModelProperty(value = "发送对象（前端要用的）", required = true)
    private String sendUser;

    @ApiModelProperty(value = "员工列表", required = true)
    private List<String> userList;

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
