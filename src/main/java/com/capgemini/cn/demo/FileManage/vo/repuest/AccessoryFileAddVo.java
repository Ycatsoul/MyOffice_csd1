package com.capgemini.cn.demo.FileManage.vo.repuest;

import lombok.Data;

import java.util.Date;


@Data
public class AccessoryFileAddVo {

    private Long accessoryId;

    //父级文件夹ID
    private Long fileId;

    private String accessoryName;

    private int accessorySize;

    private Long accessoryTypeId;

    private Date createDate;

    private String accessoryPath;
}
