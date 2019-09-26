package com.capgemini.cn.demo.userSystem.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author WYQ
 * @description:一人做事一人当
 * @description:小丁做事小叮当
 * @date 2019/9/24 15:47
 */
@Data
public class ManualSignEditVo {

        @ApiModelProperty(value = "签卡ID")
        private Long manualSignId;

        @ApiModelProperty(value = "用户ID")
        private Long userId;

        @ApiModelProperty(value = "签卡备注")
        private String signDesc;

        @ApiModelProperty(value = "签卡标记")
        private Integer signTag;
    }
