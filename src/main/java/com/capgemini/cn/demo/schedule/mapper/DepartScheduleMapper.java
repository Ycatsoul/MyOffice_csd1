package com.capgemini.cn.demo.schedule.mapper;

import com.capgemini.cn.demo.schedule.bean.Schedule;
import com.capgemini.cn.demo.schedule.bean.SubDateAndCreateUser;
import com.capgemini.cn.demo.utils.NameToBeJson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartScheduleMapper {
	List<String>  selectUserNameByDepartId(long id);
	List<Schedule> selectScheduleByDepartId(SubDateAndCreateUser subDateAndCreateUser);
	List<Schedule> selectScheduleByCreateName(NameToBeJson name);
}
