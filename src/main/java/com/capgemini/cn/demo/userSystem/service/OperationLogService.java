package com.capgemini.cn.demo.userSystem.service;


import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.OperationLog;
import com.capgemini.cn.demo.userSystem.vo.request.OperationLogSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.OperationLogVo;


public interface OperationLogService {

    RespVos<OperationLogVo> listOperationLogs(OperationLogSearchVo operationLogSearchVo);

    Integer insertOperationLog(OperationLog operationLog);

    Integer deleteOperationLogs(DeleteVo deleteVo);
}
