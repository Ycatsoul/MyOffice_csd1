package com.capgemini.cn.demo.fileManage.controller;

import com.capgemini.cn.demo.fileManage.service.FileInfoService;
import com.capgemini.cn.demo.fileManage.vo.repuest.AccessoryFileAddVo;
import com.capgemini.cn.demo.fileManage.vo.repuest.FileInfoAddVo;
import com.capgemini.cn.demo.fileManage.vo.repuest.FileInfoEditVo;
import com.capgemini.cn.demo.fileManage.vo.response.FileInfoVo;
import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.baseVo.RespVos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/file")
@Api(value = "文件管理")
public class FileInfoController {

    @Autowired
    FileInfoService fileInfoService;

    /**
     * 文件目录
     * @return
     */
    @ApiOperation("获取文件目录树")
    @GetMapping("/listFile")
    public RespBean listFile(){

        RespVos<FileInfoVo> respVos=fileInfoService.listFileInfoTree();

        if(respVos!=null&&respVos.getSize()>=0){
            return  RespBean.ok("查询成功",respVos);
        }
        return RespBean.error("查询失败");

    }

    /**
     * 新增文件夹
     * @param fileInfoAddVo
     * @return
     */
    @ApiOperation("新增文件夹")
    @PostMapping("/addFile")
    public RespBean addFile(@RequestBody FileInfoAddVo fileInfoAddVo){
        Integer res=fileInfoService.createNewFolder(fileInfoAddVo);
        if (res<0){
            return RespBean.error("创建文件夹失败");
        }
        return RespBean.ok("创建成功");
    }

    /**
     * 新增文件
     * @param accessoryFileAddVo
     * @return
     */
    @ApiOperation("新增附件")
    @PostMapping("/addAccessoryFile")
    public RespBean addAccessoryFile(@RequestBody AccessoryFileAddVo accessoryFileAddVo){
        Integer res=fileInfoService.creatNewAccessoryFile(accessoryFileAddVo);
        if(res==-2){
            return RespBean.error("请上传附件");
        }else if(res==-1){
            return RespBean.error("文件上传错误");
        }
        return RespBean.error("新增文件成功");
    }

    /**
     * 新增文件-上传单文件
     * @param file
     * @param parentId
     * @param request
     * @return
     */
    @ApiOperation("新增文件时上传临时文件")
    @PostMapping("/addFile/uploadFile/{parentId}")
    public RespBean upload(@RequestParam("file") MultipartFile file,@PathVariable("parentId") Long parentId,HttpServletRequest request){
        Integer res=fileInfoService.uploadAccessoryFile(file,parentId,request);

        return res>0? RespBean.ok("上传成功"):RespBean.error("上传失败");
    }

    /**
     * 修改文件-临时上传附件
     * @param accessoryId
     * @return
     */
    @PostMapping("/editFile/uploadFile/{parentId}")
    @ApiOperation("修改文件夹时上传临时附件")
    public RespBean editUpload(@RequestParam("file") MultipartFile file,@PathVariable("parentId") Long parentId){
        Integer res=fileInfoService.editUploadTempFile(file,parentId);
        return res>0? RespBean.ok("上传成功"):RespBean.error("上传失败");
    }

    /**
     * 确认修改文件夹
     * @param fileInfoEditVo
     * @return
     */
    @ApiOperation("修改文件夹")
    @PostMapping("/editFile")
    public RespBean editFile(@RequestBody FileInfoEditVo fileInfoEditVo){
        Integer res=fileInfoService.editFile(fileInfoEditVo);
        return res>0? RespBean.ok("修改成功"):RespBean.error("修改失败");
    }

    /**
     * 删除文件到回收箱
     * @param accessoryId
     * @return
     */
    @ApiOperation("临时删除附件")
    @PostMapping("/delete/{accessoryId}")
    public RespBean delete(@PathVariable("accessoryId") Long accessoryId){
        Integer res=fileInfoService.deleteAccessoryFile(accessoryId);
        if(res>0){return RespBean.ok("删除成功，回收箱可找回文件");}
        return RespBean.error("删除失败，服务器错误");
    }

    @ApiOperation("彻底删除附件")
    @PostMapping("/destoryFile/{accessoryId}")
    public RespBean destoryFile(@PathVariable("accessoryId") Long accessoryId){
        Integer res=fileInfoService.destoryFile(accessoryId);
        if(res>0){return RespBean.ok("文件已彻底删除成功");}
        return RespBean.error("删除失败，服务器错误");
    }

    /**
     * 修改文件夹或者新增文件时退出
     */
    @GetMapping("/exit")
    public void exit(){
        fileInfoService.exit();
    }

    /**
     * 删除一个文件夹及其子文件夹
     * @param fileInfoId
     * @return
     */
    @ApiOperation("删除文件夹")
    @PostMapping("/deleteFile/{fileInfoId}")
    public RespBean deleteFile(@PathVariable("fileInfoId") Long fileInfoId){
        Integer res=fileInfoService.deleteFileInfo(fileInfoId);
        return res>0?RespBean.ok("删除文件夹成功"):RespBean.error("fail");

    }
}