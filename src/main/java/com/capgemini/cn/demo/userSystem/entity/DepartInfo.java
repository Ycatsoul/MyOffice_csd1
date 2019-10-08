package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

@Data
public class DepartInfo {
	
	private int departId;
	private String departName;
	private String principalUser;
	private long connectTelNo;
	private long connectMobileTelNo;
	private int faxes;
	private int branchId;
	private String branchName;
}
