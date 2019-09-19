package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author hasaker
 * @since 2019/9/4 14:58
 */
@Data
public class OperationLog {

    private Long operationId;

    private Long operationUserId;

    private String operationName;

    private String operationDesc;

    private Timestamp operationTime;
}
