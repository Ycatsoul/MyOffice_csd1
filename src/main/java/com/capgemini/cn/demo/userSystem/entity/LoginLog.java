package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author hasaker
 * @since 2019/9/4 14:56
 */
@Data
public class LoginLog {

    private Long loginId;

    private Boolean isSuccess;

    private Long loginUserId;

    private String loginIp;

    private String loginDesc;

    private Timestamp loginTime;
}
