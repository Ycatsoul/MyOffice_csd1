package com.capgemini.cn.demo.userSystem.service;


import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.LoginLog;
import com.capgemini.cn.demo.userSystem.vo.request.LoginLogSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.LoginLogVo;


public interface LoginLogService {

    RespVos<LoginLogVo> listLoginLogs(LoginLogSearchVo loginLogSearchVo);

    Integer insertLoginLog(LoginLog loginLog);

    Integer deleteLoginLogs(DeleteVo deleteVo);
}
