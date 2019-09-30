package com.capgemini.cn.demo.fileManage.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FileInfo {

    private Long fileId;

    private String fileName;
    //数据库字段的类型改为BIGINT(20)
    private Long fileType;

    //数据库字段的类型改为BIGINT(20)
    private Long fileOwner;

    private Date creatDate;

    private Long parentId;

    private String filePath;

    private String remark;

    private Date updateDate;

    private Long updateUserId;

}
