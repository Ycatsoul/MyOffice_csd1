package com.capgemini.cn.demo.FileManage.service;

import com.capgemini.cn.demo.FileManage.entity.AccessoryFile;
import com.capgemini.cn.demo.FileManage.entity.FileInfo;
import com.capgemini.cn.demo.FileManage.entity.FileType;
import com.capgemini.cn.demo.FileManage.mapper.FileInfoMapper;
import com.capgemini.cn.demo.FileManage.mapper.FileTypeMapper;
import com.capgemini.cn.demo.FileManage.vo.repuest.FileInfoAddVo;
import com.capgemini.cn.demo.FileManage.vo.response.AccessoryFileVo;
import com.capgemini.cn.demo.FileManage.vo.response.FileInfoVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.User;
import com.capgemini.cn.demo.userSystem.mapper.UserMapper;
import com.capgemini.cn.demo.utils.DateUtils;
import com.capgemini.cn.demo.utils.IdWorker;
import com.capgemini.cn.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        respVos.setVos(fileInfoList.stream().map(this::convertFileToVo).collect(Collectors.toList()));

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
    public List<Long> uploadAccessoryFile(MultipartFile[] files) {
        return null;
    }

    public FileInfoVo convertFileToVo(FileInfo fileInfo) {
        FileInfoVo fileInfoVo=new FileInfoVo();

        fileInfoVo.setFileId(fileInfo.getFileId());
        fileInfoVo.setFileName(fileInfo.getFileName());
        fileInfoVo.setFilePath(fileInfo.getFilePath().replace("\\\\","\\"));
        fileInfoVo.setParentId(fileInfo.getParentId());
        fileInfoVo.setRemark(fileInfo.getRemark());
        fileInfoVo.setCreateDate(fileInfo.getCreatDate());

        User user=userMapper.getUser(fileInfo.getFileOwner());
        fileInfoVo.setFileOwnerId(user.getUserId());
        fileInfoVo.setFileOwnerName(user.getUsername());

        FileType fileType=fileTypeMapper.getFileTypeByFileTypeId(fileInfo.getFileType());
        fileInfoVo.setFileType(fileType);

        List<AccessoryFile> accessoryFiles=fileInfoMapper.getAccessoryFileByparentId(fileInfo.getFileId());

        fileInfoVo.setAccessoryFileList(accessoryFiles.stream().map(this::convertAccessoryToVo)
        .collect(Collectors.toList()));

        return fileInfoVo;
    }


    public AccessoryFileVo convertAccessoryToVo(AccessoryFile accessoryFile) {
        AccessoryFileVo accessoryFileVo=new AccessoryFileVo();

        User user=userMapper.getUser(accessoryFile.getCreateUserId());
        accessoryFileVo.setCreateUserId(user.getUserId());
        accessoryFileVo.setCreateUserName(user.getUsername());

        FileType fileType=fileTypeMapper.getFileTypeByFileTypeId(accessoryFile.getAccessoryTypeId());
        accessoryFileVo.setAccessoryType(fileType);

        accessoryFileVo.setAccessoryId(accessoryFile.getAccessoryId());
        accessoryFileVo.setAccessoryName(accessoryFile.getAccessoryName());
        accessoryFileVo.setAccessoryPath(accessoryFile.getAccessoryPath());
        accessoryFileVo.setCreateDate(accessoryFile.getCreateDate());
        accessoryFileVo.setAccessorySize(accessoryFile.getAccessorySize());
        accessoryFileVo.setFileId(accessoryFile.getFileId());

        return accessoryFileVo;
    }


}
