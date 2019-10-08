package com.capgemini.cn.demo.schedule.service.impl;

import com.capgemini.cn.demo.schedule.bean.Schedule;
import com.capgemini.cn.demo.schedule.bean.SubDateAndCreateUser;
import com.capgemini.cn.demo.schedule.mapper.DepartScheduleMapper;
import com.capgemini.cn.demo.schedule.service.DepartScheduleService;
import com.capgemini.cn.demo.utils.NameToBeJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartScheduleServiceImpl implements DepartScheduleService{
	@Autowired
	DepartScheduleMapper mapper;
	@Override
	public List<Schedule> selectScheduleByDepartId(SubDateAndCreateUser subDateAndCreateUser) {
		return mapper.selectScheduleByDepartId(subDateAndCreateUser);
	}

	@Override
	public List<String> selectUserNameByDepartId(long id) {
		return mapper.selectUserNameByDepartId(id);
	}

	@Override
	public List<Schedule> selectScheduleByCreateName(NameToBeJson name) {
		return mapper.selectScheduleByCreateName(name);
	}
}
