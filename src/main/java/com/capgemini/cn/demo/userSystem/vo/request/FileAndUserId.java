package com.capgemini.cn.demo.userSystem.vo.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author WildFather
 * @description:代码千万条，注释第一条
 * @description:注释不规范，亲人两行泪
 * @description:
 * @date 2019/9/24 16:51
 */
@Data
public class FileAndUserId {
    private MultipartFile file;
    private long userId;
}
