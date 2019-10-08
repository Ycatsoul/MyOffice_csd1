package com.capgemini.cn.demo.fileManage.service;

import com.capgemini.cn.demo.fileManage.entity.AccessoryFile;
import com.capgemini.cn.demo.fileManage.entity.FileInfo;
import com.capgemini.cn.demo.fileManage.entity.FileType;
import com.capgemini.cn.demo.fileManage.mapper.FileInfoMapper;
import com.capgemini.cn.demo.fileManage.mapper.FileTypeMapper;
import com.capgemini.cn.demo.fileManage.vo.repuest.AccessoryFileAddVo;
import com.capgemini.cn.demo.fileManage.vo.repuest.FileInfoAddVo;
import com.capgemini.cn.demo.fileManage.vo.repuest.FileInfoEditVo;
import com.capgemini.cn.demo.fileManage.vo.repuest.FileInfoSearchVo;
import com.capgemini.cn.demo.fileManage.vo.response.AccessoryFileVo;
import com.capgemini.cn.demo.fileManage.vo.response.FileInfoVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.User;
import com.capgemini.cn.demo.userSystem.mapper.UserMapper;
import com.capgemini.cn.demo.utils.DateUtils;
import com.capgemini.cn.demo.utils.IdWorker;
import com.capgemini.cn.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public RespVos<FileInfoVo> searchFile(FileInfoSearchVo fileInfoSearchVo) {
        RespVos<FileInfoVo> respVos = new RespVos<>();
        List<FileInfo> fileInfos = fileInfoMapper.searchFile(fileInfoSearchVo);

        if (fileInfos != null && fileInfos.size() > 0) {
            respVos.setSize(fileInfos.size());
            respVos.setVos(fileInfos.stream().map(this::convertFileToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public Integer createNewFolder(FileInfoAddVo fileInfoAddVo){

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
    public Integer uploadAccessoryFile(MultipartFile file,Long parentId, HttpServletRequest request) {

        HttpSession session=request.getSession();

        //创建对象
        AccessoryFile accessoryFile=new AccessoryFile();
        Long accessoryFileId=IdWorker.get().nextId();
        String originalFileName=file.getOriginalFilename();
        String accessoryFileName=originalFileName.substring(0,originalFileName.lastIndexOf("."));
        String fileSuffix=originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        Long fileTypeId=fileTypeMapper.getFileTypeIdByFileTypeSuffix(fileSuffix);
        FileInfo fileInfo=fileInfoMapper.getFileByfileId(parentId);
        String parentPath=fileInfo.getFilePath().replace("\\\\","\\");
        String filePath=parentPath+"\\"+originalFileName;
        try {
            file.transferTo(new File(filePath));
            accessoryFile.setAccessoryId(accessoryFileId);
            accessoryFile.setFileId(parentId);
            accessoryFile.setAccessoryName(accessoryFileName);
            accessoryFile.setAccessoryTypeId(fileTypeId);
            accessoryFile.setAccessorySize(file.getSize());
            accessoryFile.setAccessoryPath(filePath);
            accessoryFile.setCreateDate(new Date());
            Long userId=(Long)session.getAttribute("currentUserId");
            accessoryFile.setCreateUserId(userId);
            //插入新对象
            fileInfoMapper.addAccessoryFile(accessoryFile);

            //是否存在临时上传文件
            Long tempUploadFileId=(Long) session.getAttribute("tempUploadFileId");

            if(tempUploadFileId != null){
                //删除旧对象，
                // 删除磁盘的文件失败
                AccessoryFile oldAccessoryFile=fileInfoMapper.getAccessoryFileByAccessoryId(tempUploadFileId);
                File oldFile=new File(oldAccessoryFile.getAccessoryPath());
                Integer res=fileInfoMapper.deleteAccessoryFile(tempUploadFileId);
                if(res!=1&&!oldFile.delete()){
                    System.out.println("临时文件删除失败");
                    return -1;
                }
            }
            //更新临时ID
            session.setAttribute("tempUploadFileId",accessoryFileId);
        } catch (IOException e) {
            //上传失败
            return -1;
        }

        return 1;


    }

    @Override
    public Integer creatNewAccessoryFile(AccessoryFileAddVo accessoryFileAddVo) {

        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();

        Long tempUploadFileId=(Long) session.getAttribute("tempUploadFileId");

        if(tempUploadFileId==null){
            //没有上传附件
            return -2;
        }
        AccessoryFile accessoryFile=fileInfoMapper.getAccessoryFileByAccessoryId(tempUploadFileId);
        accessoryFile.setAccessoryName(accessoryFileAddVo.getAccessoryName());
        accessoryFile.setRemark(accessoryFileAddVo.getRemark());
        accessoryFile.setAccessoryTypeId(accessoryFileAddVo.getAccessoryTypeId());
        accessoryFile.setCreateDate(new Date());

        String olaPath=accessoryFile.getAccessoryPath();
        String fileSuffix=olaPath.substring(olaPath.lastIndexOf("."));
        String newPath=olaPath.substring(0,olaPath.lastIndexOf("\\")+1)+accessoryFile.getAccessoryName()+fileSuffix;
        accessoryFile.setAccessoryPath(newPath);
        //System.out.println("newPatn----->"+newPath);

        //更新磁盘附件路径名称
        File file=new File(olaPath);
        File newFile=new File(newPath);
        file.renameTo(newFile);

        if(fileInfoMapper.updateAccessoryFile(accessoryFile)>0&&file.renameTo(newFile)){
            session.removeAttribute("tempUploadFileId");
            return 1;
        }

        return -1;
    }

    @Override
    public Integer deleteAccessoryFile(Long accessoryId) {
        Integer res=fileInfoMapper.tempDeleteFile(accessoryId);
        return res;
    }

    @Override
    public Integer destoryFile(Long accessoryId) {

        AccessoryFile accessoryFile=fileInfoMapper.getAccessoryFileByAccessoryId(accessoryId);
        File file=new File(accessoryFile.getAccessoryPath());

        Integer res=fileInfoMapper.deleteAccessoryFile(accessoryId);
        if(res>0&&file.delete()){
            return res;
        }
        return -1;
    }

    public FileInfoVo convertFileToVo(FileInfo fileInfo) {
        FileInfoVo fileInfoVo=new FileInfoVo();

        fileInfoVo.setFileId(fileInfo.getFileId());
        fileInfoVo.setFileName(fileInfo.getFileName());
        fileInfoVo.setFilePath(fileInfo.getFilePath().replace("\\\\","\\"));
        fileInfoVo.setParentId(fileInfo.getParentId());

        String filePath=fileInfoVo.getFilePath();
        fileInfoVo.setLocation(filePath.substring(0,filePath.lastIndexOf("\\")));
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

    @Override
    public Integer editFile(FileInfoEditVo fileInfoEditVo) {
        FileInfo fileInfo=fileInfoMapper.getFileByfileId(fileInfoEditVo.getFileId());

        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();

        if (fileInfo != null) {
            fileInfo.setUpdateDate(new Date());
            fileInfo.setUpdateUserId((Long) session.getAttribute("currentUserId"));
            fileInfo.setFileType(fileInfoEditVo.getFileType());
            fileInfo.setFileName(fileInfoEditVo.getFileName());
            fileInfo.setRemark(fileInfoEditVo.getRemark());

            String oldPath=fileInfo.getFilePath();
            String newPath=oldPath.substring(0,oldPath.lastIndexOf("\\")+1)+fileInfo.getFileName();
            fileInfo.setFilePath(newPath);

            File file=new File(oldPath);
            File newfile=new File(newPath);
            file.renameTo(newfile);

            fileInfoMapper.updateFileInfo(fileInfo);
            session.removeAttribute("editTempUploadFile");

            return 1;
        }
        return -1;
    }

    @Override
    public Integer editUploadTempFile(MultipartFile file, Long parentId) {

        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();

        //创建对象
        AccessoryFile accessoryFile=new AccessoryFile();
        Long accessoryFileId=IdWorker.get().nextId();
        String originalFileName=file.getOriginalFilename();
        String accessoryFileName=originalFileName.substring(0,originalFileName.lastIndexOf("."));
        String fileSuffix=originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        Long fileTypeId=fileTypeMapper.getFileTypeIdByFileTypeSuffix(fileSuffix);
        FileInfo fileInfo=fileInfoMapper.getFileByfileId(parentId);
        String parentPath=fileInfo.getFilePath().replace("\\\\","\\");
        String filePath=parentPath+"\\"+originalFileName;
        try {
            file.transferTo(new File(filePath));
            accessoryFile.setAccessoryId(accessoryFileId);
            accessoryFile.setFileId(parentId);
            accessoryFile.setAccessoryName(accessoryFileName);
            accessoryFile.setAccessoryTypeId(fileTypeId);
            accessoryFile.setAccessorySize(file.getSize());
            accessoryFile.setAccessoryPath(filePath);
            accessoryFile.setCreateDate(new Date());
            Long userId=(Long)session.getAttribute("currentUserId");
            accessoryFile.setCreateUserId(userId);
            //插入新对象
            fileInfoMapper.addAccessoryFile(accessoryFile);

            //是否存在临时上传文件
            List<Long> editTempUploadFile;
            editTempUploadFile=(List<Long>) session.getAttribute("editTempUploadFile");
            if(editTempUploadFile!=null){
                editTempUploadFile.add(accessoryFile.getAccessoryId());
                session.setAttribute("editTempUploadFile",accessoryFileId);
            }else {
                editTempUploadFile=new ArrayList<Long>();
                editTempUploadFile.add(accessoryFile.getAccessoryId());
                session.setAttribute("editTempUploadFile",accessoryFileId);

            }
            return 1;
        } catch (IOException e) {
            //上传失败
            return -1;
        }
    }

    @Override
    public void exit() {
        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();

        Long tempId=(Long) session.getAttribute("tempUploadFileId");
        List<Long> editIds=(List<Long>)session.getAttribute("editTempUploadFile");
        if(tempId!=null){
            fileInfoMapper.deleteAccessoryFile(tempId);
            session.removeAttribute("tempUploadFileId");
            //File file=new File(fileInfoMapper.getaccessoryPathById(tempId));

        }else if(editIds!=null){
            for (Long id:editIds
                 ) {
                fileInfoMapper.deleteAccessoryFile(id);
            }
            session.removeAttribute("editTempUploadFile");
        }
    }

    @Override
    public Integer deleteFileInfo(Long fileInfoId) {
        //递归
        boolean res=DeleteIfHaveChild(fileInfoId);
        return res?1:-1;

    }

    public boolean DeleteIfHaveChild(Long fileInfoId){
        List<FileInfo> fileInfoList=fileInfoMapper.getChildFileByFileId(fileInfoId);
        if(!fileInfoList.isEmpty()){
            for (FileInfo f:fileInfoList) {
                DeleteIfHaveChild(f.getFileId());
            }
        }
        Integer res=fileInfoMapper.returnFileInfo(fileInfoId);

        return res>0?true:false;

    }
}
