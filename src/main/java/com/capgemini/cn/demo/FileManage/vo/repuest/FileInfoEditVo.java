package com.capgemini.cn.demo.FileManage.vo.repuest;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FileInfoEditVo {

    private Long fileId;

    private String fileName;

    private Long fileType;

    private String remark;

    private Date updateDate;

    private Long updateUserId;

    private Long parentId;

    private String filePath;

    private List<Long> newAccessoryFileId;
}
