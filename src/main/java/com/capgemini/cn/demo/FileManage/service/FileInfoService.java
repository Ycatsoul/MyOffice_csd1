package com.capgemini.cn.demo.FileManage.service;

import com.capgemini.cn.demo.FileManage.entity.AccessoryFile;
import com.capgemini.cn.demo.FileManage.entity.FileInfo;
import com.capgemini.cn.demo.FileManage.vo.repuest.FileInfoAddVo;
import com.capgemini.cn.demo.FileManage.vo.response.AccessoryFileVo;
import com.capgemini.cn.demo.FileManage.vo.response.FileInfoVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileInfoService {

    Integer createNewFolder(FileInfoAddVo fileInfoAddVo);

    RespVos<FileInfoVo> listFileInfoTree();

    List<Long> uploadAccessoryFile(MultipartFile[] files);

}
