package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

/**
 * @Description:用户消息表（包含是否已读）
 * @Classname :MessageToUser
 * @author: Skye Kong
 * @date: 2019/9/18
 */
@Data
public class MessageToUser {
    //序号id
    private Integer id;
    //消息id（外-message-messageid）
    private Integer messageId;
    //发送对象id（外-userinfo-userid）
    private Long touserId;
    //是否已读（1：已读；0：未读）
    private Integer ifRead;
}
