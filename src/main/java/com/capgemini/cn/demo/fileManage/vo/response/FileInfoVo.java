package com.capgemini.cn.demo.fileManage.vo.response;

import com.capgemini.cn.demo.fileManage.entity.FileType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FileInfoVo {

    /**
     * 文件ID
     */
    private Long fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 位置
     */
    private String location;

    /**
     * 文件类型
     */
    private FileType fileType;

    /**
     * 文件拥有者ID
     */
    private Long fileOwnerId;

    /**
     * 文件拥有者名字
     */
    private String fileOwnerName;

    /**
     * 父文件夹ID
     */
    private Long parentId;

    /**
     * 文件备注
     */
    private String remark;

    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 附件列表
     */
    private List<AccessoryFileVo> accessoryFileList;
}
