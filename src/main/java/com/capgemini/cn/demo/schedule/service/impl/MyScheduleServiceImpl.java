package com.capgemini.cn.demo.schedule.service.impl;

import com.capgemini.cn.demo.schedule.bean.Precontract;
import com.capgemini.cn.demo.schedule.bean.Schedule;
import com.capgemini.cn.demo.schedule.bean.SubDateAndCreateUser;
import com.capgemini.cn.demo.schedule.mapper.MyScheduleMapper;
import com.capgemini.cn.demo.schedule.service.MyScheduleService;
import com.capgemini.cn.demo.utils.IdToBeJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyScheduleServiceImpl implements MyScheduleService{
	@Autowired
	MyScheduleMapper mapper;

	@Override
	public void insertSchedule(Schedule schedule) {
		mapper.insertSchedule(schedule);
	}

	@Override
	public void updateScheduleById(Schedule schedule) {
		mapper.updateScheduleById(schedule);
	}


	@Override
	public void insertPrecontract(Precontract precontract) {
		mapper.insertPrecontract(precontract);
	}

	@Override
	public String selectUserIdByUserName(String userName) {
		return mapper.selectUserIdByUserName(userName);
	}

	@Override
	public void updatePrecontractById(Precontract precontract) {
		mapper.updatePrecontractById(precontract);
	}

	@Override
	public void deleteScheduleById(IdToBeJson id) {
		mapper.deleteScheduleById(id);
	}

	@Override
	public void deletePrecontractByscheduleId(IdToBeJson id) {
		mapper.deletePrecontractByscheduleId(id);
	}

	@Override
	public List<Schedule> selectScheduleByDate(SubDateAndCreateUser subDateAndCreateUser) {
		return mapper.selectScheduleByDate(subDateAndCreateUser);
	}

	
}
