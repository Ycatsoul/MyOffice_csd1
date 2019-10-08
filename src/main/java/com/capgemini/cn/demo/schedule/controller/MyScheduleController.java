/**
 * 
 */
package com.capgemini.cn.demo.schedule.controller;

import com.capgemini.cn.demo.schedule.bean.Precontract;
import com.capgemini.cn.demo.schedule.bean.Schedule;
import com.capgemini.cn.demo.schedule.bean.SubDateAndCreateUser;
import com.capgemini.cn.demo.schedule.service.MyScheduleService;
import com.capgemini.cn.demo.schedule.vo.ScheduleVo;
import com.capgemini.cn.demo.utils.DateToBeJson;
import com.capgemini.cn.demo.utils.IdToBeJson;
import com.capgemini.cn.demo.utils.RespBean;
import com.capgemini.cn.demo.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ft
 *
 */
@RestController
@Api(value = "日程管理")
public class MyScheduleController {
	
	@Autowired
	MyScheduleService myScheduleService;
	
	@PutMapping("addSchedule")
	@ApiOperation(value = "添加我的日程,不需要给我scheduleId")
	public RespBean addSchedule(@Valid @RequestBody ScheduleVo scheduleVo, HttpServletRequest request) {
		System.out.println(scheduleVo.toString());
		String createName = UserUtils.getCurrentUser().getUsername();
		Schedule schedule = new Schedule();
		schedule.setAddress(scheduleVo.getAddress());
		schedule.setBeginTime(scheduleVo.getBeginTime());
		schedule.setCreateTime(new Date());
		schedule.setCreateUser(createName);
		schedule.setEndTime(scheduleVo.getEndTime());
		schedule.setIfPrivate(0);
		schedule.setMeetingId(schedule.getMeetingId());
		schedule.setSchContent(scheduleVo.getSchContent());
		schedule.setTitle(scheduleVo.getTitle());
		myScheduleService.insertSchedule(schedule);

		Precontract precontract = new Precontract();
		//把得到的预约人名字和日程id保存在预约表里
		precontract.setScheduleId(schedule.getScheduleId());
		List<String> lists =scheduleVo.getList();
		for(String userName: lists) {
			//根据预约人的名字找到预约人的Id
			String userId = myScheduleService.selectUserIdByUserName(userName);
			precontract.setUserId(userId);
			myScheduleService.insertPrecontract(precontract);
		}
		return RespBean.ok("添加日程成功");
	}
	@PutMapping("modifyScheduleById")
	@ApiOperation(value = "修改我的日程")
	public RespBean modifyScheduleById(@Valid @RequestBody ScheduleVo scheduleVo) {
		System.out.println(scheduleVo.toString());
		Schedule schedule = new Schedule();
		schedule.setScheduleId(scheduleVo.getScheduleId());
		schedule.setAddress(scheduleVo.getAddress());
		schedule.setBeginTime(scheduleVo.getBeginTime());
		schedule.setEndTime(scheduleVo.getEndTime());
		schedule.setMeetingId(schedule.getIfPrivate());
		schedule.setSchContent(scheduleVo.getSchContent());
		schedule.setTitle(scheduleVo.getTitle());
		myScheduleService.updateScheduleById(schedule);
		IdToBeJson id = new IdToBeJson();
		id.setId(schedule.getScheduleId());
		//删除所有预约人
		myScheduleService.deletePrecontractByscheduleId(id);
		//再添加预约人
		Precontract precontract = new Precontract();
		precontract.setScheduleId(schedule.getScheduleId());
		List<String> lists =scheduleVo.getList();
		for(String userName: lists) {
			//根据预约人的名字找到预约人的Id
			String userId = myScheduleService.selectUserIdByUserName(userName);
			precontract.setUserId(userId);
			//把得到的预约人名字和日程id保存在预约表里
			myScheduleService.insertPrecontract(precontract);
		}
		return RespBean.ok("修改日程成功");
	}
	@PutMapping("deleteScheduleById")
	@ApiOperation(value = "删除我的日程")
	public RespBean deleteScheduleById(@Valid @RequestBody IdToBeJson id) {
		myScheduleService.deleteScheduleById(id);
		myScheduleService.deletePrecontractByscheduleId(id);
		return RespBean.ok("删除成功");
	}
	@PutMapping("selectScheduleByBeginTime")
	@ApiOperation(value = "根据日期查找个人简约日程")
	public RespBean selectScheduleByBeginTime(@Valid @RequestBody DateToBeJson date) {
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		String strTime = formatter1.format(date.getDate());
		
		String createUser = UserUtils.getCurrentUser().getUsername();
		SubDateAndCreateUser subDateAndCreateUser = new SubDateAndCreateUser();
		subDateAndCreateUser.setCreateUser(createUser);
		subDateAndCreateUser.setSubDate(strTime);
		
		List<Schedule> lists = myScheduleService.selectScheduleByDate(subDateAndCreateUser);
		
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
		List<String> list = new ArrayList();
		for(Schedule schedule : lists) {
			Date date1 = schedule.getBeginTime();
			String str = formatter.format(date1);
			String shortTitle = schedule.getTitle().substring(0, 3);
			String result = "@"+str+shortTitle+"...";
			list.add(result);
		}
		return RespBean.ok("成功", list);
	}
	@PutMapping("selectScheduleByBeginTime2")
	@ApiOperation(value = "根据日期查找个人完整日程")
	public RespBean selectScheduleByBeginTime2(@Valid @RequestBody DateToBeJson date) {
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		String strTime = formatter1.format(date.getDate());
		
		String createUser = UserUtils.getCurrentUser().getUsername();
		SubDateAndCreateUser subDateAndCreateUser = new SubDateAndCreateUser();
		subDateAndCreateUser.setCreateUser(createUser);
		subDateAndCreateUser.setSubDate(strTime);
		
		List<Schedule> lists = myScheduleService.selectScheduleByDate(subDateAndCreateUser);
		return RespBean.ok("成功", lists);
	}
	
}
