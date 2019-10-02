package com.capgemini.cn.demo.messagePassing.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description:消息实体类
 * @Classname :Message
 * @author: Skye Kong
 * @date: 2019/9/18
 */
@Data
public class Message {
    //消息id
    private Integer messageId;
    //消息标题
    private String title;
    //消息内容
    private String content;
    //消息类型
    private Integer type;
    //开始有效时间
    private Date beginTime;
    //有效结束时间
    private Date endTime;
    //发送者
    private Long fromUserId;
    //是否已发布（发布：1；未发布：0）
    private Integer ifPublish;
    //发送时间
    private Date recordTime;
    //删除标志位（1：已删除；0：未删除）
    private Integer ifDelete;
    //更新时间
    private Date updateTime;
    //更新人id
    private Long updateUserId;
    //是否是全部
    private Integer ifAll;
}
