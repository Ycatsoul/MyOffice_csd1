package com.capgemini.cn.demo.userSystem.vo.response;

import lombok.Data;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/4 23:17
 * 获取机构-部门-用户目录树
 */
@Data
public class BraDepUserVo {

    @Data
    public static class Department {

        @Data
        public static class User {

            Long userId;

            String name;
        }

        Long departmentId;

        String departmentName;

        /**
         * 该部门下的所有用户
         */
        List<User> users;
    }

    Long branchId;

    String branchShortName;

    /**
     * 该机构下的部门
     */
    List<Department> departments;
}
