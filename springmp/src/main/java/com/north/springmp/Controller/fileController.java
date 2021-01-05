package com.north.springmp.Controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@Api(value = "about file")
public class fileController {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd");

    @RequestMapping(value = "/import",method = RequestMethod.POST)
    //@RequestMapping(value = "/import",method = RequestMethod.GET)
    public void importData(MultipartFile file, HttpServletRequest req) throws IOException{
        String format = simpleDateFormat.format(new Date());
        String realPath =req.getServletContext().getRealPath("/resources")+format;
        System.out.println(realPath);
        File folder =new File(realPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        if (file != null) {
            String oldName = file.getOriginalFilename();
            String newName = UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."));
            file.transferTo(new File(folder,newName));
            String url =req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/upload"+format+newName;
            System.out.println(url);
        }

        //RespBean   Resp.ok
    }
}
