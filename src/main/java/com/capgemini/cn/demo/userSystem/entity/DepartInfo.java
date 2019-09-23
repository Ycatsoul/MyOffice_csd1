package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

@Data
public class DepartInfo {
	
	private long departId;
	private String departName;
	private String principalUser;
	private long connectTelNo;
	private long connectMobileTelNo;
	private int faxes;
	private long branchId;
	private String branchName;
}
