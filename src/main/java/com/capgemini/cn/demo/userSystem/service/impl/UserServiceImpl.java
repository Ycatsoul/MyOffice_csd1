package com.capgemini.cn.demo.userSystem.service.impl;

import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.BranchInfo;
import com.capgemini.cn.demo.userSystem.entity.DepartInfo;
import com.capgemini.cn.demo.userSystem.entity.Role;
import com.capgemini.cn.demo.userSystem.entity.User;
import com.capgemini.cn.demo.userSystem.mapper.BranchInfoMapper;
import com.capgemini.cn.demo.userSystem.mapper.DepartMapper;
import com.capgemini.cn.demo.userSystem.mapper.UserMapper;
import com.capgemini.cn.demo.userSystem.mapper.UserRoleMapper;
import com.capgemini.cn.demo.userSystem.service.UserService;
import com.capgemini.cn.demo.userSystem.vo.request.UserEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.UserSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.BraDepUserVo;
import com.capgemini.cn.demo.userSystem.vo.response.UserVo;
import com.capgemini.cn.demo.utils.IdToBeJson;
import com.capgemini.cn.demo.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    DepartMapper departmentMapper;
    @Autowired
    BranchInfoMapper branchMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }

        List<Role> roles = userRoleMapper.getRolesByUserId(user.getUserId());
        user.setRoles(roles);

        return user;
    }

    @Override
    public List<Long> getAllUserIds() {
        return userMapper.getAllUserIds();
    }

    @Override
    public RespVos<UserVo> getUser(Long userId) {
        User user = userMapper.getUser(userId);

        if (user != null) {
            RespVos<UserVo> respVos = new RespVos<>();
            respVos.setSize(1);
            respVos.setVos(new ArrayList<UserVo>(){{
                add(convertToVo(user));
            }});

            return respVos;
        }

        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public RespVos<UserVo> listUsers(UserSearchVo userSearchVo) {
        List<User> users = userMapper.listUsers(userSearchVo);

        if (users != null && users.size() > 0) {
            RespVos<UserVo> respVos = new RespVos<>();
            respVos.setSize(userMapper.countUsers(userSearchVo));
            respVos.setVos(users.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public RespVos<BraDepUserVo> getBraDepUserTree() {
        List<BranchInfo> branches = branchMapper.selectAllBranchInfo();
        List<DepartInfo> departments = departmentMapper.selectAllDepart();
        List<User> users = userMapper.listUsers(new UserSearchVo(){{setSize(1000);}});
        List<BraDepUserVo> braDepUserVos = new ArrayList<>();
        RespVos<BraDepUserVo> respVos = new RespVos<>();

        for (BranchInfo branch : branches) {
            List<BraDepUserVo.Department> convertedDepartments = new ArrayList<>();
            for (DepartInfo department : departments) {
                if (department.getBranchId()==branch.getBranchId()) {
                    List<BraDepUserVo.Department.User> convertedUsers = new ArrayList<>();
                    for (User user : users) {
                        if (user.getDepartmentId()==department.getDepartId()) {
                            convertedUsers.add(new BraDepUserVo.Department.User() {{
                                setUserId(user.getUserId());
                                setName(user.getName());
                            }});
                        }
                    }
                    convertedDepartments.add(new BraDepUserVo.Department(){{
                        setDepartmentId(Long.valueOf(department.getDepartId()));
                        setDepartmentName(department.getDepartName());
                        setUsers(convertedUsers);
                    }});
                }
            }
            braDepUserVos.add(new BraDepUserVo(){{
                setBranchId(Long.valueOf(branch.getBranchId()));
                setBranchShortName(branch.getBranchShortName());
                setDepartments(convertedDepartments);
            }});
        }

        respVos.setSize(braDepUserVos.size());
        respVos.setVos(braDepUserVos);

        return respVos;
    }

    @Override
    public Integer addUser(UserEditVo userEditVo) {
        // 用户名已存在, 返回-1
        if (userMapper.getUserByUsername(userEditVo.getUsername()) != null) {
            return -1;
        }
        //id生成器，
        userEditVo.setUserId(IdWorker.get().nextId());
        userEditVo.setPassword(new BCryptPasswordEncoder().encode(userEditVo.getPassword()));

//        // 新注册用户默认为ROLE_USER
//        Role role = roleMapper.getRoleByRoleName("ROLE_USER");

        Integer res = userMapper.insertUser(userEditVo);

        userRoleMapper.addUserRole(IdWorker.get().nextId(), userEditVo.getUserId(), userEditVo.getRoleId());

        return res;
    }

    @Override
    public Integer updateUser(UserEditVo userEditVo) {
        return userMapper.updateUser(userEditVo);
    }

    @Override
    public Integer blockUsers(DeleteVo deleteVo) {
        return userMapper.blockUsers(deleteVo.getIds());
    }

    @Override
    public Integer deleteUsers(DeleteVo deleteVo) {
        // 删除用户的同时删除相关的权限分配记录
        for (Long userId : deleteVo.getIds()) {
            userRoleMapper.deleteUserRolesByUserId(userId);
        }

        return userMapper.deleteUsers(deleteVo.getIds());
    }

    /**
     * 将User装换为UserVo
     */
    @Override
    public UserVo convertToVo(User user) {
        UserVo userVo = new UserVo();
        IdToBeJson id=new IdToBeJson();
        id.setId(Integer.valueOf(user.getDepartmentId().toString()));
        DepartInfo department = departmentMapper.getDepartment(user.getDepartmentId());
        List<Role> roles = userRoleMapper.getRolesByUserId(user.getUserId());

        userVo.setUserId(user.getUserId());
        userVo.setUsername(user.getUsername());
        userVo.setName(user.getName());
        userVo.setGender(user.getGender());
        userVo.setAvatar(user.getAvatar());
        userVo.setDepartmentId(user.getDepartmentId());
        userVo.setDepartmentName(department == null ? null : department.getDepartName());
        userVo.setRoles(roles);
        userVo.setIsBlocked(user.getIsBlocked());

        return userVo;
    }
}
