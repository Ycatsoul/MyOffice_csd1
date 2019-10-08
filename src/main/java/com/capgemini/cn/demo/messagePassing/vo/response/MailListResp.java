package com.capgemini.cn.demo.messagePassing.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:收件箱消息列表
 * @Classname :MailListResp
 * @author: Skye Kong
 * @date: 2019/9/23
 */
@Data
public class MailListResp {
    @ApiModelProperty(value = "列表长度", required = true)
    private Long listCount;

    @ApiModelProperty(value = "收件箱消息列表", required = true)
    private List<MailResp> mailMessageList;
}
