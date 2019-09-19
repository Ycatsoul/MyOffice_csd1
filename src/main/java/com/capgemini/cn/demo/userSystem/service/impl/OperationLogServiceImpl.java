package com.capgemini.cn.demo.userSystem.service.impl;
import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.OperationLog;
import com.capgemini.cn.demo.userSystem.entity.User;
import com.capgemini.cn.demo.userSystem.mapper.OperationLogMapper;
import com.capgemini.cn.demo.userSystem.mapper.UserMapper;
import com.capgemini.cn.demo.userSystem.service.OperationLogService;
import com.capgemini.cn.demo.userSystem.vo.request.OperationLogSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.OperationLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hasaker
 * @since 2019/9/4 22:27
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Autowired
    OperationLogMapper operationLogMapper;
    @Autowired
    UserMapper userMapper;



    @Override
    public RespVos<OperationLogVo> listOperationLogs(OperationLogSearchVo operationLogSearchVo) {
        List<OperationLog> operationLogs = operationLogMapper.listOperationLogs(operationLogSearchVo);

        if (operationLogs != null && operationLogs.size() > 0) {
            RespVos<OperationLogVo> respVos = new RespVos<>();
            respVos.setSize(operationLogMapper.countOperationLogs(operationLogSearchVo));
            respVos.setVos(operationLogs.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public Integer insertOperationLog(OperationLog operationLog) {
        return operationLogMapper.insertOperationLog(operationLog);
    }

    @Override
    public Integer deleteOperationLogs(DeleteVo deleteVo) {
        return operationLogMapper.deleteOperationLogs(deleteVo.getIds());
    }

    private OperationLogVo convertToVo(OperationLog operationLog) {
        OperationLogVo operationLogVo = new OperationLogVo();

        User user = userMapper.getUser(operationLog.getOperationUserId());

        operationLogVo.setOperationLogId(operationLog.getOperationId());
        operationLogVo.setOperationUserId(operationLog.getOperationUserId());
        operationLogVo.setOperationUserName(user == null ? null : user.getName());
        operationLogVo.setOperationName(operationLog.getOperationName());
        operationLogVo.setOperationDesc(operationLog.getOperationDesc());
        operationLogVo.setOperationTime(operationLog.getOperationTime());

        return operationLogVo;
    }
}
