package com.capgemini.cn.demo.schedule.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Schedule {

	private int scheduleId;
	private String title;
	private String address;
	private int meetingId;
	//@JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
	private Date beginTime;
	private Date endTime;
	private String schContent;
	private String createUser;
	private Date createTime;
	private int ifPrivate;
}
