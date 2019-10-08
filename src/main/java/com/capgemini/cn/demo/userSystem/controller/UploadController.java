package com.capgemini.cn.demo.userSystem.controller;

import com.capgemini.cn.demo.utils.WaterMarkUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;


@Controller
@RequestMapping("file")
public class UploadController {
    /*
     * 获取file.html页面
     */
    @RequestMapping("file")
    public String file(){
        return "/file";
    }

    /**
     * 实现文件上传
     * */
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }
        /*Resource resource = file.getResource();
        System.out.println(resource);*/
        System.out.println(file.getName());
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "D:/test" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
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
    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("fffffffffffffffffffffffffffffffffffffff");
        String filename="test.jpg";
        String filePath = "D:/test" ;
        System.out.println(filePath + "/" + filename);
        File file = new File(filePath + "/" + filename);
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
        return "file";
    }
    @RequestMapping("/test")
    @ResponseBody
    public void test(){
        Font font = new Font("微软雅黑", Font.PLAIN, 35);                     //水印字体
        String srcImgPath="D:/test/test.jpg"; //源图片地址
        String tarImgPath="D:/test/test2.jpg"; //待存储的地址
        String waterMarkContent="MY OFFICE";  //水印内容
        Color color=new Color(162, 164, 165);                               //水印图片色彩以及透明度
        new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath, waterMarkContent,color ,font);
    }


}
