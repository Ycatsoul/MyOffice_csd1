package com.capgemini.cn.demo.baseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-25 23:44
 */
@Data
@ApiModel(description = "返回数据")
public class RespVos<T> {

    @ApiModelProperty(value = "数据总长度")
    private Integer size;

    @ApiModelProperty(value = "返回数据数组")
    private List<T> vos;
}
