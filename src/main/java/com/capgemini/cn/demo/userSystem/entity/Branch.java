package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

/**
 * @Description:机构管理实体类
 * @Classname :Branch
 * @author: Cola Guo
 * @date:2019-8-26 16:47
 */
@Data
public class Branch {

    /**
     * 机构Id
     */
    private Long branchId;

    /**
     * 机构名称
     */
    private String branchName;

    /**
     * 机构简称
     */
    private String branchShortName;

}
