/**
 * 
 */
package com.capgemini.cn.demo.schedule.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ft
 *
 */
@Data
public class ScheduleVo {

	private int scheduleId;
	private String title;
	private String address;
	private int meetingId;
	//@JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
	private Date beginTime;
	private Date endTime;
	private String schContent;
	List<String> list;
	
	
	
}
