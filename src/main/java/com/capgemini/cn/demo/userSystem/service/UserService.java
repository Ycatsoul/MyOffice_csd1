package com.capgemini.cn.demo.userSystem.service;

import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.User;
import com.capgemini.cn.demo.userSystem.vo.request.UserEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.UserSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.BraDepUserVo;
import com.capgemini.cn.demo.userSystem.vo.response.UserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    List<Long> getAllUserIds();

    RespVos<UserVo> getUser(Long userId);

    User getUserByUsername(String username);

    RespVos<UserVo> listUsers(UserSearchVo userSearchVo);

    //RespVos<BraDepUserVo> getBraDepUserTree();

    Integer addUser(UserEditVo userEditVo);

    Integer updateUser(UserEditVo userEditVo);

    Integer blockUsers(DeleteVo deleteVo);

    Integer deleteUsers(DeleteVo deleteVo);

    //UserVo convertToVo(User user);
}
