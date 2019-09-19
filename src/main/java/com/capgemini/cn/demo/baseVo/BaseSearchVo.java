package com.capgemini.cn.demo.baseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hasaker
 * @since 2019-08-22 15:05
 */
@Data
@ApiModel(description = "搜索基础类")
public class BaseSearchVo {

    @ApiModelProperty(value = "分页起始位置")
    private Integer start = 0;
    
    @ApiModelProperty(value = "分页大小")
    private Integer size = 15;
}
