package com.capgemini.cn.demo.FileManage.service;

import com.capgemini.cn.demo.FileManage.entity.FileInfo;
import com.capgemini.cn.demo.FileManage.entity.FileType;
import com.capgemini.cn.demo.FileManage.mapper.FileInfoMapper;
import com.capgemini.cn.demo.FileManage.mapper.FileTypeMapper;
import com.capgemini.cn.demo.FileManage.vo.repuest.FileInfoAddVo;
import com.capgemini.cn.demo.FileManage.vo.response.FileInfoVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.User;
import com.capgemini.cn.demo.userSystem.mapper.UserMapper;
import com.capgemini.cn.demo.utils.DateUtils;
import com.capgemini.cn.demo.utils.IdWorker;
import com.capgemini.cn.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    FileInfoMapper fileInfoMapper;
    @Autowired
    FileTypeMapper fileTypeMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public RespVos<FileInfoVo> listFileInfoTree() {
        RespVos<FileInfoVo> respVos=new RespVos<>();
        List<FileInfo> fileInfoList=fileInfoMapper.listFileInfo();
        respVos.setSize(fileInfoList.size());
        respVos.setVos(fileInfoList.stream().map(this::convertToVo).collect(Collectors.toList()));

        return respVos;
    }

    @Override
    public Integer createNewFolder(FileInfoAddVo fileInfoAddVo) {

        FileInfo p_fileInfo=fileInfoMapper.getFileByfileId(fileInfoAddVo.getParentId());
        String filePath="";
        Integer res=0;
        if(p_fileInfo != null){

            //判断是否有同名文件夹
            List<FileInfo> childfileInfoList = fileInfoMapper.getChildFileByFileId(p_fileInfo.getFileId());
            for (FileInfo fileInfo : childfileInfoList) {
                //查找同名文件夹
                if(fileInfo.getFileName().equals(fileInfoAddVo.getFileName())){
                    return -1;
                }
            }

            //无重名，在磁盘创建文件夹
            filePath=p_fileInfo.getFilePath()+"\\\\"+fileInfoAddVo.getFileName();
            File file=new File(filePath);
            file.mkdir();

            //创建文件夹对象
            Long fileTypeid=fileTypeMapper.getFileTypeIdByFileTypeSuffix("");
            User user= UserUtils.getCurrentUser();
            fileInfoAddVo.setFileId(IdWorker.get().nextId());
            fileInfoAddVo.setCreateDate(DateUtils.getNowDate());
            fileInfoAddVo.setFileOwner(user.getUserId());
            fileInfoAddVo.setFilePath(filePath);
            fileInfoAddVo.setFileType(fileTypeid);


            res=fileInfoMapper.addFileInfo(fileInfoAddVo);
        }

        return res>0?1:-1;
    }

    @Override
    public FileInfoVo convertToVo(FileInfo fileInfo) {
        FileInfoVo fileInfoVo=new FileInfoVo();

        fileInfoVo.setFileId(fileInfo.getFileId());
        fileInfoVo.setFileName(fileInfo.getFileName());
        fileInfoVo.setFilePath(fileInfo.getFilePath());
        fileInfoVo.setParentId(fileInfo.getParentId());
        fileInfoVo.setRemark(fileInfo.getRemark());
        fileInfoVo.setCreateDate(fileInfo.getCreatDate());

        User user=userMapper.getUser(fileInfo.getFileOwner());
        fileInfoVo.setFileOwnerId(user.getUserId());
        fileInfoVo.setFileOwnerName(user.getUsername());

        FileType fileType=fileTypeMapper.getFileTypeByFileTypeId(fileInfo.getFileType());
        fileInfoVo.setFileType(fileType);

        return fileInfoVo;

    }
}
