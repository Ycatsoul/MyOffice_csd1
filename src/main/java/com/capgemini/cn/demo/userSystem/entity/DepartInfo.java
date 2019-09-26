package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

@Data
public class DepartInfo {
	
	private Long departId;
	private String departName;
	private String principalUser;
	private Long connectTelNo;
	private Long connectMobileTelNo;
	private Long faxes;
	private Long branchId;
	private String branchName;
}
