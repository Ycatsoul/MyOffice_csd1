package com.capgemini.cn.demo.fileManage.vo.repuest;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 附件搜索
 */
@Data
@ApiModel
public class AccessoryFileSearchVo {

    //搜索文件名
    private String fileName;

    //附件包含的文字
    //private String containsWord;

    //创建者姓名
    private String fileOwnerName;
}
