package com.capgemini.cn.demo.messagePassing.entity;

import lombok.Data;

/**
 * @Description:消息类型实体类
 * @Classname :MessageType
 * @author: Skye Kong
 * @date: 2019/9/18
 */
@Data
public class MessageType {
    //消息类型id
    private Integer messageTypeId;
    //消息类型名称
    private String messageTypeName;
    //消息类型描述
    private String messageDesc;
}
