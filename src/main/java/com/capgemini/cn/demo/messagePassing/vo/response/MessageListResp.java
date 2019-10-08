package com.capgemini.cn.demo.messagePassing.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:消息列表
 * @Classname :MessageListResp
 * @author: Skye Kong
 * @date: 2019/9/18
 */
@Data
public class MessageListResp {

    @ApiModelProperty(value = "列表长度", required = true)
    private Long listCount;

    @ApiModelProperty(value = "消息列表", required = true)
    private List<MessageResp> messageList;

    @ApiModelProperty(value = "员工列表", required = true)
    private List<UserResp> userList;

}
