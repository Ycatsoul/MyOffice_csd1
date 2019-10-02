package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

/**
 * @Description:部门管理实体类
 * @Classname :Department
 * @author: GuoBingjun
 * @date:2019-8-26 11:35
 */
@Data
public class Department {
    /**
     * 部门ID
     */
    private Long departmentId;

    /*
     * 部门名称
     * */
    private String departmentName;

    /**
     * 联系电话
     */
    private String connectPhone;

    /**
     * 联系电话（座机）
     */
    private String connectTelephone;

    /**
     * 部门负责人ID
     */
    private Long principalUserId;

    /**
     * 所属机构ID
     */
    private Long branchId;

}
