package com.capgemini.cn.demo.userSystem.vo.request;

import com.capgemini.cn.demo.baseVo.BaseSearchVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * @author hasaker
 * @since 2019/9/4 15:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class OperationLogSearchVo extends BaseSearchVo {

    private Long operationUserId;

    private Timestamp startTime;

    private Timestamp endTime;
}
