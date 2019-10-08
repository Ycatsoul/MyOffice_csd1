package com.capgemini.cn.demo.manualSign.entity;

import lombok.Data;

import java.util.Date;

@Data
public class WorkTime {

    private Long WorkTimeId;

    private Date onDutyTime;

    private Date offDutyTime;
}
