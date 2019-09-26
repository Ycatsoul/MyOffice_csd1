package com.capgemini.cn.demo.userSystem.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class ManualSignVo {

    /**
     * 签卡ID
     */
    private Long manualSignId ;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 所属部门
     */
    private String departmentName;

    /**
     * 所属机构
     */
    private String branchName;

    /**
     * 签卡时间
     */
    private Date signTime;

    /**
     * 签卡备注
     */
    private String signDesc;

    /**
     * 签卡标记
     */
    private Integer signTag;
}


