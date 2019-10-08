package com.capgemini.cn.demo.messagePassing.entity;

import lombok.Data;

/**
 * @Description:发布消息实体类
 * @Classname :ReadCommonMessage
 * @author: Skye Kong
 * @date: 2019/9/22
 */
@Data
public class ReadCommonMessage {
    /**
     * 序号ID
     */
    private Integer readId;

    /**
     * 消息ID
     */
    private Integer messageId;

    /**
     * 消息读取者
     */
    private Long userId;
}
