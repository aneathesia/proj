package com.north.springmp.Controller;

import com.north.springmp.jna.JNATest;
import com.north.springmp.jna.ProjectDesign;
import com.north.springmp.jna.Senctional;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@ResponseBody
public class DemCaculate {
    @CrossOrigin
    @RequestMapping(value = "/EGXCaculate",method = RequestMethod.POST)
    public String EGXFileToSection(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        //绝对路径
        String path = "E:\\Spring\\data\\";
        //相对dir src/main/resource/static

        String app_path = System.getProperty("user.dir");//程序当前路径
        String stat_path = app_path+"\\src\\main\\resources";
        System.out.println(stat_path);

        //System.out.println(path);
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);

        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        File dir = new File(stat_path, originalFilename);
        String ImgPath = stat_path + originalFilename;
        File filepath = new File(stat_path);
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
        file.transferTo(dir);

//创建文件夹
//        String ip = request.getRemoteAddr();
//        String projname = file.getOriginalFilename().substring(0,originalFilename.lastIndexOf("."));
//        System.out.println(projname);
//        File testfilepath = new File(stat_path+"\\"+ip);
//        System.out.println(testfilepath.exists());
//        if (!testfilepath.exists()) {
//            testfilepath.mkdirs();
//            System.out.println(testfilepath.toString());
//        }

        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";

        final PointerByReference ptrRef = new PointerByReference();
// call the C function
        ProjectDesign.PDLibary.pd.GenerateCoord(resPath+originalFilename,ptrRef);
// extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
// extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(request.getParameter("ProjectName"));
        System.out.println(request.getRemoteAddr());
        System.out.println(response);

        return val;
    }


    @CrossOrigin
    @RequestMapping(value = "/EGXGEOCaculate",method = RequestMethod.POST)
    public String EGXGEOcaculate(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        //resources根目录  组合对应ip的文件夹
        String app_path = System.getProperty("user.dir");
        String stat_path = app_path+"\\src\\main\\resources";
        String ip = request.getRemoteAddr();
        String ProjectName = request.getParameter("ProjectName");
        String PointFile =request.getParameter("PointFile");
        String DemFile = request.getParameter("DemFile");
        final PointerByReference ptrRef = new PointerByReference();
        System.out.println(ProjectName);
        System.out.println(PointFile);
        System.out.println(DemFile);
// call the C function
        ProjectDesign.PDLibary.pd.GeoAndSectionPoint(stat_path+"\\"+ip+"\\"+ProjectName+"\\"+PointFile,stat_path+"\\"+ip+"\\"+ProjectName+"\\"+DemFile,ptrRef);
// extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
// extract the null-terminated string from the Pointer
        final String val = p.getString(0);

        return val;
    }
}


