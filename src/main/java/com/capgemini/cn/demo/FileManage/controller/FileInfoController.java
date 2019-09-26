package com.capgemini.cn.demo.FileManage.controller;

import com.capgemini.cn.demo.FileManage.service.FileInfoService;
import com.capgemini.cn.demo.FileManage.vo.repuest.FileInfoAddVo;
import com.capgemini.cn.demo.FileManage.vo.repuest.FileInfoEditVo;
import com.capgemini.cn.demo.baseVo.RespBean;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/file")
@Api(value = "文件管理")
public class FileInfoController {

    @Autowired
    FileInfoService fileInfoService;
    @PostMapping("/addFile")
    public RespBean addFile(@RequestBody FileInfoAddVo fileInfoAddVo){
        Integer res=fileInfoService.createNewFolder(fileInfoAddVo);
        if (res<0){
            return RespBean.error("创建文件夹失败");
        }
        return RespBean.ok("创建成功");
    }

    @PostMapping("/edit")
    public RespBean editFile(@RequestBody FileInfoEditVo fileInfoEditVo){
        return RespBean.ok("接口测试ok");

    }
}
