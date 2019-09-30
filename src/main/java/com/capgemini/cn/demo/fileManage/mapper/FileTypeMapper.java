package com.capgemini.cn.demo.fileManage.mapper;

import com.capgemini.cn.demo.fileManage.entity.FileType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-21 15:05
 */
@Service
public interface FileTypeMapper {
    Long getFileTypeIdByFileTypeSuffix(@Param("fileTypeSuffix") String fileTypeSuffix);

    FileType getFileTypeByFileTypeId(@Param("fileTypeId") Long fileTypeId);

    List<String> getAllFileTypeSuffixes();

    List<FileType> getAllFileType();

    Integer insertFileType(@Param("fileType") FileType fileType);

    Integer deleteFileType(@Param("fileTypeId") Long fileTypeId);
}
