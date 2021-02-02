package com.north.springmp.Controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@Api(tags = "不可使用")
public class fileController {
    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public void importfile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        //相对dir src/main/resource/static

        String app_path = System.getProperty("user.dir");//程序当前路径
        String stat_path = app_path+"\\src\\main\\resources";
        System.out.println(stat_path);
        String ip = request.getRemoteAddr();
        String ProjectName = request.getParameter("ProjectName");
        String ProjPath = stat_path+"\\"+ip+"\\"+ProjectName;

        //System.out.println(path);
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);

        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        File dir = new File(ProjPath, originalFilename);

        File filepath = new File(ProjPath);
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
        file.transferTo(dir);
    }
}
