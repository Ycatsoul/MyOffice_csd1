package com.capgemini.cn.demo.baseVo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hasaker
 * @since 2019-08-25 23:44
 */
@Getter
@Setter
public class RespBean {

    private Integer status;
    private String msg;
    private Object obj;

    private RespBean(Integer status, Object obj) {
        this.status = status;
        this.obj = obj;
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean ok(Object obj) {
        return new RespBean(200, obj);
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }


}
