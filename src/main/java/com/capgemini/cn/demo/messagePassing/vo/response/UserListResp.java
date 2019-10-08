package com.capgemini.cn.demo.messagePassing.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:员工列表返回体
 * @Classname :UserListResp
 * @author: Skye Kong
 * @date: 2019/9/25
 */
@Data
public class UserListResp {
    @ApiModelProperty(value = "员工列表", required = true)
    private List<UserResp> userList;
}
