package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.userSystem.entity.OperationLog;
import com.capgemini.cn.demo.userSystem.vo.request.OperationLogSearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/4 16:35
 */
@Mapper
@Repository
public interface OperationLogMapper {

    List<OperationLog> listOperationLogs(@Param("operationLogSearchVo") OperationLogSearchVo operationLogSearchVo);

    Integer countOperationLogs(@Param("operationLogSearchVo") OperationLogSearchVo operationLogSearchVo);

    Integer insertOperationLog(@Param("operationLog") OperationLog operationLog);

    Integer deleteOperationLogs(@Param("operationIds") List<Long> operationIds);
}
