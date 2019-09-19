package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.userSystem.entity.LoginLog;
import com.capgemini.cn.demo.userSystem.vo.request.LoginLogSearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/4 15:09
 */
@Repository
@Mapper
public interface LoginLogMapper {

    List<LoginLog> listLoginLogs(@Param("loginLogSearchVo") LoginLogSearchVo loginLogSearchVo);

    Integer countLoginLogs(@Param("loginLogSearchVo") LoginLogSearchVo loginLogSearchVo);

    Integer insertLoginLog(@Param("loginLog") LoginLog loginLog);

    Integer deleteLoginLogs(@Param("loginIds") List<Long> loginIds);
}
