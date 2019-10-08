package com.capgemini.cn.demo.schedule.vo;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

@Data
public class DepartIdAndDateVo {
	@ApiParam(value = "部门Id")
	private long id;
	private  Date date;
}
