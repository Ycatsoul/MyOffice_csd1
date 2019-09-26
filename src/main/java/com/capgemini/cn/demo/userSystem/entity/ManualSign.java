package com.capgemini.cn.demo.userSystem.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author WYQ
 * @description:一人做事一人当
 * @description:小丁做事小叮当
 * @date 2019/9/23 15:58
 */
@Data
public class ManualSign {

    /**
     * 签卡id
     */
    private Long manualSignId ;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 签卡时间
     */
    private Date signTime;

    /**
     * 签卡备注
     */
    private String signDesc;

    /**
     * 签卡标记
     */
    private Integer signTag;
}
