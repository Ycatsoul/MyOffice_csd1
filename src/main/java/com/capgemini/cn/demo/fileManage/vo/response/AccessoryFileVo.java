package com.capgemini.cn.demo.fileManage.vo.response;

import com.capgemini.cn.demo.fileManage.entity.FileType;
import lombok.Data;

import java.util.Date;

@Data
public class AccessoryFileVo {

    private Long accessoryId;

    //父级文件夹ID
    private Long fileId;

    private String accessoryName;

    private Long accessorySize;

    private FileType accessoryType;

    private Date createDate;

    private String accessoryPath;

    private Long createUserId;

    private String createUserName;
}
