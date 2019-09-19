package com.capgemini.cn.demo.userSystem.vo.response;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author hasaker
 * @since 2019/9/4 15:24
 */
@Data
public class OperationLogVo {

    /**
     * ID
     */
    private Long operationLogId;

    /**
     * 执行操作的用户的ID
     */
    private Long operationUserId;

    /**
     * 执行操作的用户的名字
     */
    private String operationUserName;

    /**
     * 操作名称
     */
    private String operationName;

    /**
     * 操作描述
     */
    private String operationDesc;

    /**
     * 操作时间
     */
    private Timestamp operationTime;
}
