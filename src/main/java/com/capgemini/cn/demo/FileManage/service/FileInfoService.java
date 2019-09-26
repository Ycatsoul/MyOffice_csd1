package com.capgemini.cn.demo.FileManage.service;

import com.capgemini.cn.demo.FileManage.entity.FileInfo;
import com.capgemini.cn.demo.FileManage.vo.repuest.FileInfoAddVo;
import com.capgemini.cn.demo.FileManage.vo.response.FileInfoVo;
import com.capgemini.cn.demo.baseVo.RespVos;


public interface FileInfoService {

    Integer createNewFolder(FileInfoAddVo fileInfoAddVo);

    RespVos<FileInfoVo> listFileInfoTree();

    FileInfoVo convertToVo(FileInfo fileInfo);
}
