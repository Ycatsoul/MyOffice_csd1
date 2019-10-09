package com.capgemini.cn.demo.schedule.mapper;

import com.capgemini.cn.demo.schedule.bean.Precontract;
import com.capgemini.cn.demo.schedule.bean.Schedule;
import com.capgemini.cn.demo.schedule.bean.SubDateAndCreateUser;
import com.capgemini.cn.demo.utils.IdToBeJson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyScheduleMapper {
	void insertSchedule(Schedule schedule);
	void updateScheduleById(Schedule schedule);
	String selectUserIdByUserName(String userName);
	void insertPrecontract(Precontract precontract);
	void updatePrecontractById(Precontract precontract);
	void deleteScheduleById(IdToBeJson id);
	void deletePrecontractByscheduleId(IdToBeJson id);
	List<Schedule> selectScheduleByDate(SubDateAndCreateUser subDateAndCreateUser);
}
