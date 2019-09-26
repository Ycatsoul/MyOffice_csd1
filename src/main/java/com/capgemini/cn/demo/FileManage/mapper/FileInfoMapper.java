package com.capgemini.cn.demo.FileManage.mapper;

import com.capgemini.cn.demo.FileManage.entity.AccessoryFile;
import com.capgemini.cn.demo.FileManage.entity.FileInfo;
import com.capgemini.cn.demo.FileManage.vo.repuest.FileInfoAddVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface FileInfoMapper {

    @Insert("insert into fileInfo" +
            "(fileId,fileName,fileType,remark,fileOwner,parentId,filePath,createDate)" +
            "value(#{fileId},#{fileName},#{fileType},#{remark},#{fileOwner},#{parentId},#{filePath},#{createDate})")
    Integer addFileInfo(FileInfoAddVo fileInfoAddVo);


    @Select("select * from fileInfo where fileId=#{fileId} and ifDelete=0")
    FileInfo getFileByfileId(Long fileId);

    @Select("select * from fileInfo where parentId=#{fileId} and ifDelete=0")
    List<FileInfo> getChildFileByFileId(Long fileId);

    @Select("select * from fileInfo where ifDelete=0")
    List<FileInfo> listFileInfo();

    @Select("select * from accessoryfile where parentId=#{parentId} and ifDelete=0")
    List<AccessoryFile> getAccessoryFileByparentId(Long parentId);

    @Select("select * from accessoryfile where accessoryId=#{accessoryId} and ifDelete=0")
    AccessoryFile getAccessoryFileByAccessoryId(Long accessoryId);


}
