package com.capgemini.cn.demo.FileManage.entity;

import lombok.Data;

@Data
public class FileInfo {

    private Long fileId;

    private String fileName;
    //数据库字段的类型改为BIGINT(20)
    private Long fileType;

    //数据库字段的类型改为BIGINT(20)
    private Long fileOwner;

    private Data creatDate;

    private Long parentId;

    private String filePath;

    private String remark;

}
