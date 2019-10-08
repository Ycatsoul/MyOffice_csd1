package com.capgemini.cn.demo.messagePassing.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Classname :
 * @author: Skye Kong
 * @date: 2019/9/20
 */
@Data
public class MessagePublishReq {
    @ApiModelProperty(value = "消息id", required = true)
    private Integer messageId;
}
