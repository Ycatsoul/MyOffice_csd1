package com.capgemini.cn.demo.FileManage.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AccessoryFile {

    private Long accessoryId;

    private Long fileId;

    private String accessoryName;

    private int accessorySize;

    private Long accessoryTypeId;

    private Date createDate;

    private String accessoryPath;
}
