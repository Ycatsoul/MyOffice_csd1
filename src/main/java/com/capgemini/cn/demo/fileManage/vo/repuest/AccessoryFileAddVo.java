package com.capgemini.cn.demo.fileManage.vo.repuest;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@ApiModel
@Data
public class AccessoryFileAddVo {

    //父级文件夹ID
    private Long fileId;

    private String accessoryName;

    private Long accessoryTypeId;

    private String remark;
}
