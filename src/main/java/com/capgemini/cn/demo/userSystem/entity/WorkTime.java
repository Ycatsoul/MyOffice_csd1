package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

import java.sql.Date;


@Data
public class WorkTime {

    private Long WorkTimeId;

    private Date onDutyTime;

    private Date offDutyTime;
}