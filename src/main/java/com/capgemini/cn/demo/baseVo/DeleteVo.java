package com.capgemini.cn.demo.baseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/6 20:41
 */
@Data
@ApiModel
public class DeleteVo {

    @ApiModelProperty("所要删除的Id集合")
    List<Long> ids;
}
