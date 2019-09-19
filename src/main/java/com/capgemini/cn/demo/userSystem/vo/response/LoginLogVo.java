package com.capgemini.cn.demo.userSystem.vo.response;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author hasaker
 * @since 2019/9/4 15:18
 */
@Data
public class LoginLogVo {

    /**
     * ID
     */
    private Long loginLogId;

    /**
     * 是否登录成功
     */
    private String isSuccess;

    /**
     * 登录用户ID
     */
    private Long loginUserId;

    /**
     * 登录用户名
     */
    private String loginUserName;

    /**
     * 登录用户IP
     */
    private String loginIp;

    /**
     * 登录描述
     */
    private String loginDesc;

    /**
     * 登录时间
     */
    private Timestamp loginTime;
}
