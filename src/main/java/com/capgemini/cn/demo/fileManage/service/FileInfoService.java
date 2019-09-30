package com.capgemini.cn.demo.fileManage.service;

import com.capgemini.cn.demo.fileManage.vo.repuest.AccessoryFileAddVo;
import com.capgemini.cn.demo.fileManage.vo.repuest.FileInfoAddVo;
import com.capgemini.cn.demo.fileManage.vo.repuest.FileInfoEditVo;
import com.capgemini.cn.demo.fileManage.vo.response.FileInfoVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


public interface FileInfoService {

    Integer createNewFolder(FileInfoAddVo fileInfoAddVo);

    RespVos<FileInfoVo> listFileInfoTree();

    Integer uploadAccessoryFile(MultipartFile file,Long parentId, HttpServletRequest request);

    Integer creatNewAccessoryFile(AccessoryFileAddVo accessoryFileAddVo);

    Integer deleteAccessoryFile(Long accessorryId);

    Integer deleteFileInfo(Long fileInfoId);

    Integer destoryFile(Long accessoryId);

    Integer editFile(FileInfoEditVo fileInfoEditVo);

    Integer editUploadTempFile(MultipartFile file,Long parentId);

    void exit();

}
