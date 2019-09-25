package com.capgemini.cn.demo.userSystem.vo.request;

import lombok.Data;

import java.util.List;

/**
 * @Description:删除消息请求体
 * @Classname :MessageDeleteReq
 * @author: Skye Kong
 * @date: 2019/9/19
 */
@Data
public class MessageDeleteReq {

    private List<Integer> messageIds;
}
