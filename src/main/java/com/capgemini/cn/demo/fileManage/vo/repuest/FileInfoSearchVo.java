package com.capgemini.cn.demo.fileManage.vo.repuest;
import com.capgemini.cn.demo.baseVo.BaseSearchVo;
import com.capgemini.cn.demo.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019-08-22 11:02
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class FileInfoSearchVo extends BaseSearchVo {

    @ApiModelProperty(value = "文件名搜索字段")
    private String fileName;

    @ApiModelProperty(value = "文件创建人搜索字段")
    private String createUserName;

    @ApiModelProperty(value = "起始时间")
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone = DateUtils.DEFAULT_ZONE)
    private Date startTime;

    @ApiModelProperty(value = "截止时间")
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone = DateUtils.DEFAULT_ZONE)
    private Date endTime;

    @ApiModelProperty(value = "父文件夹ID")
    private Long parentId;
}
