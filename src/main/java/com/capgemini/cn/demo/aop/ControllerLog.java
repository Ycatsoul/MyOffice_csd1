package com.capgemini.cn.demo.aop;

import java.lang.annotation.*;

/**
 * @author hasaker
 * @since 2019/9/11 09:40
 */
@Target({ElementType.PARAMETER, ElementType.METHOD}) // 作用在参数和方法上
@Retention(RetentionPolicy.RUNTIME) //运行时注解
@Documented  //表示该注解应该被doc工具记录
public @interface ControllerLog {
    String name() default "";
}
