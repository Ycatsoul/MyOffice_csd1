package com.capgemini.cn.demo.userSystem.service;


import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.OperationLog;
import com.capgemini.cn.demo.userSystem.vo.request.OperationLogSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.OperationLogVo;

/**
 * @author hasaker
 * @since 2019/9/4 22:12
 */
public interface OperationLogService {

    RespVos<OperationLogVo> listOperationLogs(OperationLogSearchVo operationLogSearchVo);

    Integer insertOperationLog(OperationLog operationLog);

    Integer deleteOperationLogs(DeleteVo deleteVo);
}
