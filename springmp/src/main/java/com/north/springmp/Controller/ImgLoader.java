package com.north.springmp.Controller;

import com.north.springmp.jna.JNATest;
import com.north.springmp.jna.Senctional;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@ResponseBody
public class ImgLoader {
    @CrossOrigin
    @RequestMapping(value = "/uploadimg",method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
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

        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";

        final PointerByReference ptrRef = new PointerByReference();
// call the C function
        Senctional.CLibrary.INSTANCE.GenrateCoord(resPath+originalFilename,ptrRef);
// extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
// extract the null-terminated string from the Pointer
        final String val = p.getString(0);

        return val;
    }

}


