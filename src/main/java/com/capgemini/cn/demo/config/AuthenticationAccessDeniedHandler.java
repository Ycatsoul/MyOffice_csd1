package com.capgemini.cn.demo.config;

import com.capgemini.cn.demo.baseVo.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author hasaker
 * @since 2019/9/8 10:38
 * 处理权限不足
 * 自定义403响应的内容
 */
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();
        RespBean error = RespBean.error("权限不足, 请联系管理员!");

        out.write(new ObjectMapper().writeValueAsString(error));
        out.flush();
        out.close();
    }
}
