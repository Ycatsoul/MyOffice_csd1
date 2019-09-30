package com.capgemini.cn.demo.fileManage.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AccessoryFile {

    private Long accessoryId;

    //父级文件夹ID
    private Long fileId;

    private String accessoryName;

    private Long accessorySize;

    private Long accessoryTypeId;

    private Date createDate;

    private String accessoryPath;

    private Long createUserId;

    private String remark;
}
