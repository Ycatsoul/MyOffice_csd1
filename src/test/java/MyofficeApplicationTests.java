import com.capgemini.cn.demo.MyofficeApplication;
import com.capgemini.cn.demo.userSystem.entity.Role;
import com.capgemini.cn.demo.userSystem.entity.User;
import com.capgemini.cn.demo.userSystem.mapper.MenuMapper;
import com.capgemini.cn.demo.userSystem.mapper.RoleMapper;
import com.capgemini.cn.demo.userSystem.mapper.UserMapper;
import com.capgemini.cn.demo.userSystem.vo.request.UserEditVo;
import com.capgemini.cn.demo.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyofficeApplication.class)
public class MyofficeApplicationTests {

    @Resource
    UserMapper userMapper;
    @Resource
    MenuMapper menuMapper;
    @Resource
    RoleMapper roleMapper;


    @Test
    public void test1(){
       BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
       User user=new User();
       user.setName("yrx");
       user.setUsername("yrx");
       user.setPassword(passwordEncoder.encode("123456"));
        UserEditVo userEditVo=new UserEditVo();
        userEditVo.setName("姚若栩");
        userEditVo.setUserId(IdWorker.get().nextId());
        userEditVo.setPassword(user.getPassword());
        userEditVo.setUsername(user.getUsername());
        userEditVo.setGender(0);
        userEditVo.setDepartmentId(Long.valueOf(1231));
        userEditVo.setAvatar("sads");
        userEditVo.setIsBlocked(false);
       userMapper.insertUser(userEditVo);
    }
}