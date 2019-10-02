package com.capgemini.cn.demo.userSystem.vo.response;

import lombok.Data;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class DepartmentVo {
    /**
     * 部门Id
     */
    private Long departmentId;

    /**
     * 部门名称
     */
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
     * 法人ID
     */
    private Long principalUserId;

    /**
     * 主管姓名
     */
    private String principalUserName;

    /**
     * 所属机构ID
     */
    private Long branchId;

    /**
     * 所属机构名称
     */
    private String branchName;

    /**
     * 所属机构简称
     */
    private String branchShortName;
}
