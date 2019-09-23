package com.capgemini.cn.demo.schedule.service;

import com.capgemini.cn.demo.schedule.bean.Precontract;
import com.capgemini.cn.demo.schedule.bean.Schedule;
import com.capgemini.cn.demo.schedule.bean.SubDateAndCreateUser;
import com.capgemini.cn.demo.utils.IdToBeJson;

import java.util.List;

public interface MyScheduleService {

	void insertSchedule(Schedule schedule);
	void updateScheduleById(Schedule schedule);
	String selectUserIdByUserName(String userName);
	void insertPrecontract(Precontract precontract);
	void updatePrecontractById(Precontract precontract);
	void deleteScheduleById(IdToBeJson id);
	void deletePrecontractByscheduleId(IdToBeJson id);
	List<Schedule> selectScheduleByDate(SubDateAndCreateUser subDateAndCreateUser);
	


}
