package com.capgemini.cn.demo;

import com.capgemini.cn.core.commons.BaseBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created at 2017/12/20
 *
 * @author Andriy
 */
@SpringBootApplication
public class MyofficeApplication extends BaseBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyofficeApplication.class, args);
    }
}
