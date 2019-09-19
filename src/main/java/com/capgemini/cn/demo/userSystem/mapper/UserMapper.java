package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.userSystem.entity.User;
import com.capgemini.cn.demo.userSystem.vo.request.UserEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.UserSearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:DAO层UserMapper类
 * @Classname :UserMapper
 * @author: GuoBingjun
 * @date:2019-8-21 16:13
 */
@Mapper
@Repository
public interface UserMapper {

    List<Long> getAllUserIds();

    User getUser(@Param("userId") Long userId);

    User getUserByUsername(@Param("username") String username);

    List<User> listUsers(@Param("userSearchVo") UserSearchVo userSearchVo);

    Integer countUsers(@Param("userSearchVo") UserSearchVo userSearchVo);

    Integer insertUser(@Param("userEditVo") UserEditVo userEditVo);

    Integer updateUser(@Param("userEditVo") UserEditVo userEditVo);

    Integer blockUsers(@Param("userIds") List<Long> userIds);

    Integer deleteUsers(@Param("userIds") List<Long> userIds);
}
