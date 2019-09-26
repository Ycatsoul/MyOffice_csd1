package com.capgemini.cn.demo.FileManage.vo.repuest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class FileInfoAddVo {

    @ApiModelProperty("文件ID")
    private Long fileId;

    @ApiModelProperty("文件夹名称")
    private String fileName;

    @ApiModelProperty("类型-文件夹")
    private Long fileType;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("文件创建者")
    private Long fileOwner;

    @ApiModelProperty("父级目录")
    private Long parentId;

    @ApiModelProperty("文件路径")
    private String filePath;

    @ApiModelProperty("创建日期")
    private Date createDate;
}
