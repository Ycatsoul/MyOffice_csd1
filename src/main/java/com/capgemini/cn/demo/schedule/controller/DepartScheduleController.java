package com.capgemini.cn.demo.schedule.controller;

import com.capgemini.cn.demo.schedule.bean.ListHaveArrayList;
import com.capgemini.cn.demo.schedule.bean.Schedule;
import com.capgemini.cn.demo.schedule.bean.SubDateAndCreateUser;
import com.capgemini.cn.demo.schedule.service.DepartScheduleService;
import com.capgemini.cn.demo.schedule.vo.DepartIdAndDateVo;
import com.capgemini.cn.demo.utils.NameToBeJson;
import com.capgemini.cn.demo.utils.RespBean;
import com.capgemini.cn.demo.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "部门日程")
public class DepartScheduleController {
	@Autowired
	DepartScheduleService departScheduleService;
	@PutMapping("selectScheduleByDepartId")
	@ApiOperation(value = "根据部门Id查找部门日程")
	public RespBean selectScheduleByDepartName(@Valid @RequestBody DepartIdAndDateVo departIdAndDateVo) {
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		String strTime = formatter1.format(departIdAndDateVo.getDate());
		
		SubDateAndCreateUser subDateAndCreateUser = new SubDateAndCreateUser();
		subDateAndCreateUser.setSubDate(strTime);
		List<String> lists = departScheduleService.selectUserNameByDepartId(departIdAndDateVo.getId());
		List<Schedule> resultList = new ArrayList<>();
		List<ArrayList<Schedule>> relList1 = new ArrayList<>();
		ListHaveArrayList relList = new ListHaveArrayList();

		
		for(String createUser :lists) {
			subDateAndCreateUser.setCreateUser(createUser);
			resultList = departScheduleService.selectScheduleByDepartId(subDateAndCreateUser);
			relList1.add((ArrayList<Schedule>) resultList);
			relList.setMyList(relList1);
		}
		return RespBean.ok("成功", relList);
	}
	@PutMapping("selectScheduleByCreateUser")
	@ApiOperation(value = "根据创建人模糊查找部门日程")
	public RespBean selectScheduleByCreateUser(@Valid @RequestBody NameToBeJson name) {
		List<Schedule> lists = departScheduleService.selectScheduleByCreateName(name);
		//如果要查询的人没有日程，就查询自己的日程
		if(lists.size()==0) {
			name.setName(UserUtils.getCurrentUser().getUsername());
			lists = departScheduleService.selectScheduleByCreateName(name);
		}
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
}
