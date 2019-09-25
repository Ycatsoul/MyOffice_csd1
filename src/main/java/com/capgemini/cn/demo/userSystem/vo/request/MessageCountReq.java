package com.capgemini.cn.demo.userSystem.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:消息计数请求体
 * @Classname :MessageCountReq
 * @author: Skye Kong
 * @date: 2019/9/23
 */
@Data
public class MessageCountReq {
    @ApiModelProperty(value = "当前操作用户ID", required = true)
    private Long operationId;
}
