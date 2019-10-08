package com.capgemini.cn.demo.fileManage.mapper;

import com.capgemini.cn.demo.fileManage.entity.AccessoryFile;
import com.capgemini.cn.demo.fileManage.entity.FileInfo;
import com.capgemini.cn.demo.fileManage.vo.repuest.FileInfoAddVo;
import com.capgemini.cn.demo.fileManage.vo.repuest.FileInfoSearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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

    @Update("update fileInfo set fileName=#{fileName},fileType=#{fileType},remark=#{remark}," +
            "updateDate=#{updateDate},updateUserId=#{updateUserId},filePath=#{filePath} where fileId=#{fileId}")
    Integer updateFileInfo(FileInfo fileInfo);

    @Update("update fileInfo set ifDelete=1 where fileId=#{fileId}")
    Integer returnFileInfo(Long fileId);
//======================================================================//
    @Select("select * from accessoryfile where fileId=#{fileId} and ifDelete=0")
    List<AccessoryFile> getAccessoryFileByparentId(Long parentId);

    @Select("select * from accessoryfile where accessoryId=#{accessoryId} and ifDelete=0")
    AccessoryFile getAccessoryFileByAccessoryId(Long accessoryId);

    @Delete("delete from accessoryfile where accessoryId=#{accessoryId}")
    Integer deleteAccessoryFile(Long accessoryId);

    @Update("update accessoryfile set ifDelete=1 where accessoryId=#{accessoryId}")
    Integer tempDeleteFile(Long accessoryId);

    @Insert("insert into accessoryfile(accessoryId, fileId, accessoryName, accessoryPath, accessoryTypeId, accessorySize, createDate,createUserId,remark)" +
            "value(#{accessoryId}, #{fileId}, #{accessoryName}, #{accessoryPath}, #{accessoryTypeId}, #{accessorySize}, #{createDate},#{createUserId},#{remark})")
    Integer addAccessoryFile(AccessoryFile accessoryFile);

    @Update("update accessoryfile set accessoryName=#{accessoryName},accessoryTypeId=#{accessoryTypeId}," +
            "remark=#{remark},accessoryPath=#{accessoryPath} where accessoryId=#{accessoryId}")
    Integer updateAccessoryFile(AccessoryFile accessoryFile);

    @Select("select accessoryPath from accessoryfile where accessoryId=#{accessoryId}")
    Integer getaccessoryPathById(Long accessoryId);

    List<FileInfo> searchFile(@Param("fileInfoSearchVo") FileInfoSearchVo fileInfoSearchVo);
}
