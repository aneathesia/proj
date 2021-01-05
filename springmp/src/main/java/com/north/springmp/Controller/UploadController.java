package com.north.springmp.Controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@ResponseBody
@Api(value = "file upload")
public class UploadController {
//    @RequestMapping(value="/uploadPre",method = RequestMethod.GET)
//    public String uploadPre(){
//        return "upload";
//    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Object upload( @RequestParam("name") String name,@RequestParam(value = "file")MultipartFile multipartFile)
        throws IllegalStateException, IOException{
        Map<String,Object> map = new HashMap<String,Object>();
        if(multipartFile!=null) {
            // 设置文件名称
            map.put("nameParam", name);
            // 设置文件名称
            map.put("filename", multipartFile.getName());
            // 设置文件类型
            map.put("contentType", multipartFile.getContentType());
            //设置文件大小
            map.put("fileSize", multipartFile.getSize());
            String fileName = UUID.randomUUID() + "." + multipartFile.getContentType().substring(multipartFile.getContentType().lastIndexOf("/") + 1);
            //获取到文件的路径信息
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            String filePath = servletRequestAttributes.getRequest().getServletContext().getRealPath("/") + fileName;
            //print save path
            System.out.println(filePath);
            //save pathInfo
            map.put("filePath", filePath);
            //创建文件
            File saveFile = new File(filePath);
            //文件保存
            multipartFile.transferTo(saveFile);
            //返回信息
            return map;
        }
        else{
            return "no file";
        }


        }
    }

