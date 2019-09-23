package com.capgemini.cn.demo.schedule.bean;

import lombok.Data;

import java.util.Date;

@Data
public class MyNote {
	private int noteId;
	private String noteTitle;
	private String noteContent;
	private Date createTime;
	private String createUser;
}
