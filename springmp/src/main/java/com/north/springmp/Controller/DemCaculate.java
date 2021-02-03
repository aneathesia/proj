package com.north.springmp.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    @RequestMapping(value = "/EGXGEOCaculate",method = RequestMethod.POST)
    public String EGXGEOCaculate(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
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


