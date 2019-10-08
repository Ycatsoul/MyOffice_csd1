package com.capgemini.cn.demo.fileManage.vo.repuest;

import lombok.Data;

@Data
public class FileInfoEditVo {

    private Long fileId;

    private String fileName;

    private Long fileType;

    private String remark;
}
