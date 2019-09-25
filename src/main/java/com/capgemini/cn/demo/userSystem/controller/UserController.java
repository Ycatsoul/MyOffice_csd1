package com.capgemini.cn.demo.userSystem.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.demo.aop.ControllerLog;
import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.service.UserService;
import com.capgemini.cn.demo.userSystem.vo.request.UserEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.UserSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.BraDepUserVo;
import com.capgemini.cn.demo.userSystem.vo.response.UserVo;
import com.capgemini.cn.demo.utils.IdToBeJson;
import com.capgemini.cn.demo.utils.WaterMarkUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hasaker
 * @create_date 2019-08-19 22:32
 * @description
 */
@Slf4j
@Api
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    private long g =0L;
    @Autowired
    UserService userService;

    @ApiOperation("为了让我知道是在修改谁")
    @PostMapping("/forGiveMeUserId")
    public void UsforGiveMeUserIder(@Valid @RequestBody IdToBeJson idToBeJson) {
        g = idToBeJson.getId();
    }

    @ApiOperation("查询用户")
    @GetMapping("/{userId}")
    public RespBean getUser(@PathVariable Long userId) {
        RespVos<UserVo> respVos = userService.getUser(userId);

        if (respVos != null && respVos.getSize() == 1) {
            return RespBean.ok("查询成功!", respVos);
        }

        return RespBean.error("查询失败!");
    }

    @ApiOperation("获取User列表")
    @PostMapping("/list")
    public RespBean listUsers(@RequestBody UserSearchVo userSearchVo){
        RespVos<UserVo> respVos = userService.listUsers(userSearchVo);

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功!", respVos);
        }

        return RespBean.error("查询失败！");
    }

    @ApiOperation("获取机构-部门-用户树")
    @GetMapping("/braDepUserTree")
    public RespBean getBraDepUserTree() {
        RespVos<BraDepUserVo> respVos = userService.getBraDepUserTree();

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功!", respVos);
        }

        return RespBean.error("查询失败！");
    }

    @ControllerLog(name = "添加用户")
    @ApiOperation("添加User")
    @PostMapping("/")
    public RespBean addUser(@RequestBody UserEditVo userEditVo) {
        Integer res = userService.addUser(userEditVo);

        return res > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

    @ControllerLog(name = "更新用户信息")
    @ApiOperation("更新User信息")
    @PutMapping("/")
    public RespBean updateUser(@RequestBody UserEditVo userEditVo) {
        Integer res = userService.updateUser(userEditVo);

        return res > 0 ? RespBean.ok("更新成功!") : RespBean.error("更新失败!");
    }

    @ControllerLog(name = "屏蔽用户")
    @ApiOperation("屏蔽User")
    @PutMapping("/block")
    public RespBean blockUsers(@RequestBody DeleteVo deleteVo) {
        Integer res = userService.blockUsers(deleteVo);

        return res > 0 ? RespBean.ok("成功屏蔽" + res + "个用户!") : RespBean.error("屏蔽失败!");
    }

    @ControllerLog(name = "删除用户")
    @ApiOperation("删除User")
    @PostMapping("/delete")
    public RespBean deleteUsers(@RequestBody DeleteVo deleteVo) {
        Integer res = userService.deleteUsers(deleteVo);

        return res > 0 ? RespBean.ok("成功删除" + res + "个用户!") : RespBean.error("删除失败!");
    }
    @PutMapping("/fileUpload")
    @ApiOperation(value="实现文件上传")
    public String fileUpload(@Valid @RequestBody MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }
        System.out.println(file.getName());
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        String path = "D:/test" ;
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Date date=new Date();
        String str = String.valueOf(g);
        File dest = new File(path + "/" +fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            dest.renameTo(new File(path+"/"+str+".png"));

            Font font = new Font("华文彩云", Font.BOLD, 40);                     //水印字体
            String srcImgPath=path+"/"+str+".png"; //源图片地址
            String tarImgPath="D:/test/test1/"+str+".png"; //待存储的地址
            String waterMarkContent="我的OFFICE系统";  //水印内容
            Color color=new Color(64, 148, 220);                               //水印图片色彩以及透明度
            new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath, waterMarkContent,color ,font);

            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }
    @PutMapping("/download")
    @ApiOperation(value="实现无水印文件下载")
    public RespBean downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        String str = String.valueOf(g);
        String filename=str+".png";
        String filePath = "D:/test/test1" ;
        System.out.println(filePath + "/" + filename);
        File file = new File(filePath + "/" + filename);
        System.out.println(file.exists());
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            System.out.println("======================================");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return RespBean.ok("下载成功");
    }
    @PutMapping("/downLoadNoWater")
    @ApiOperation(value="实现无水印文件下载")
    public RespBean downLoadNoWater(HttpServletResponse response) throws UnsupportedEncodingException {
        String str = String.valueOf(g);
        String filename=str+".png";
        String filePath = "D:/test" ;
        System.out.println(filePath + "/" + filename);
        File file = new File(filePath + "/" + filename);
        System.out.println(file.exists());
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            System.out.println("======================================");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return RespBean.ok("下载成功");
    }

}
