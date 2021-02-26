package com.north.springmp.Controller;

import ch.qos.logback.core.util.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.north.springmp.jna.ProjectDesign;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import io.swagger.annotations.Api;
import org.apache.tomcat.jni.Directory;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimeType;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@Api(tags = "CreateProject、fileUpload")
public class Project {


    @CrossOrigin
    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    public List<FileInfo> fileController(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        ArrayList<FileInfo> filelist = new ArrayList<FileInfo>();
        //resources根目录  组合对应ip的文件夹
        String app_path = System.getProperty("user.dir");
        String stat_path = app_path+"\\src\\main\\resources";
        String ip = request.getRemoteAddr();
        String ProjectName = request.getParameter("ProjectName");

        //创建文件夹 Proj
        File filepath = new File(stat_path+"\\"+ip+"\\"+ProjectName,file.getOriginalFilename());
        System.out.println(filepath.exists());
        if (!filepath.exists()) {
            filepath.mkdirs();
            System.out.println(filepath.toString());
            //file.transferTo(filepath);
        }

        file.transferTo(filepath);


        //return "200";
        File fileDir = new File(stat_path+"\\"+ip+"\\"+ProjectName);
        if (!fileDir.exists()) {
            System.out.println("directory not exists");
        }
        File[] allFiles =fileDir.listFiles();
        for (File f:allFiles){
            FileInfo sf = new FileInfo();
            sf.name=f.getName();
            sf.csize =f.length();
            //sf.fname = f.getAbsolutePath();
            filelist.add(sf);
        }
        return filelist;
    }

    @CrossOrigin
    @RequestMapping(value = "/UploadfileToJson",method = RequestMethod.POST)
    public JSONObject UploadfileToJson(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
        String app_path = System.getProperty("user.dir");
        String stat_path = app_path+"\\src\\main\\resources";
        String ip = request.getRemoteAddr();
        String ProjectName = request.getParameter("ProjectName");
        System.out.println(ProjectName);


//        System.out.println(ProjectName);
//        ProjectName="Jan";
//        System.out.println(ProjectName);

        //文件名中取出 文件名 文件后缀
        String filename = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
        System.out.println(file.getOriginalFilename());
        String filesuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1,file.getOriginalFilename().length());

        System.out.println(filesuffix);
        //创建文件夹 Proj
        File filepath = new File(stat_path+"\\"+ip+"\\"+ProjectName,file.getOriginalFilename());
        System.out.println(filepath.exists());
        if (!filepath.exists()) {
            filepath.mkdirs();
            System.out.println(filepath.toString());
        }
        file.transferTo(filepath);


        System.out.println(stat_path + "\\" + ip + "\\" + ProjectName + "\\" + file.getOriginalFilename());
        final PointerByReference ptrRef = new PointerByReference();
// call the C function  according to filesuffix
        switch (filesuffix){
            case "PRK":
                ProjectDesign.PDLibary.pd.PrkRead(stat_path+"\\"+ip+"\\"+ProjectName+"\\"+file.getOriginalFilename(),ptrRef);
                break;
            case "PRJ":
                ProjectDesign.PDLibary.pd.PrjRead(stat_path+"\\"+ip+"\\"+ProjectName+"\\"+file.getOriginalFilename(),ptrRef);
                break;
            case "PWF":
                ProjectDesign.PDLibary.pd.PwfRead(stat_path+"\\"+ip+"\\"+ProjectName+"\\"+file.getOriginalFilename(),ptrRef);
                break;
            case "MAP":
                ProjectDesign.PDLibary.pd.MapRead(stat_path+"\\"+ip+"\\"+ProjectName+"\\"+file.getOriginalFilename(),ptrRef);
                break;
            case "LAO":
                ProjectDesign.PDLibary.pd.LaoRead(stat_path+"\\"+ip+"\\"+ProjectName+"\\"+file.getOriginalFilename(),ptrRef);
                break;
            default:
                System.out.println("file miss match");
        }

// extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
// extract the null-terminated string from the Pointer  p need not null
        final String val = p.getString(0);
        JSONObject jsonObject = JSON.parseObject(val);

        JSONObject res=new JSONObject();
        switch (filesuffix){
            case "MAP" :
                res = jsonObject.getJSONObject("DEM");
                break;
            case "PRJ":
                JSONArray jsonArray=jsonObject.getJSONArray("m_jPile");  // dist z
                JSONArray GEO = new JSONArray();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject geo =new JSONObject();
                    geo.put("x", jsonArray.getJSONObject(i).getDouble("dist"));
                    geo.put("z", jsonArray.getJSONObject(i).getDouble("z"));
                    GEO.add(i,geo);
                }
                res.put("GEO",GEO);
                break;
            default:
                res=jsonObject;
        }
        return res;

    }

    @CrossOrigin
    @RequestMapping(value = "/CreateProject",method = RequestMethod.POST)
    public String projController(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        //resources根目录  组合对应ip的文件夹
        String app_path = System.getProperty("user.dir");
        String stat_path = app_path+"\\src\\main\\resources";
        String ip = request.getRemoteAddr();
        String ProjectName = request.getParameter("ProjectName");

        System.out.println(ProjectName);
        //文件名中取出 文件名 文件后缀
//        String filename = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
//        String filesuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());

        //创建文件夹 Proj
        File filepath = new File(stat_path+"\\"+ip+"\\"+ProjectName);
        System.out.println(filepath.exists());
        if (!filepath.exists()) {
            filepath.mkdirs();
            System.out.println(filepath.toString());
        }

        //将模板文件JSON传回

        final PointerByReference ptrRef = new PointerByReference();
// call the C function
        ProjectDesign.PDLibary.pd.PrkRead(app_path+"\\src\\main\\resources\\statics\\"+"SIMPLE.PRK",ptrRef);
// extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
// extract the null-terminated string from the Pointer
        final String val = p.getString(0);

        //SIMPLE.XXX create JSON

        final PointerByReference ptrRefLao = new PointerByReference();
// call the C function
        ProjectDesign.PDLibary.pd.LaoRead(app_path+"\\src\\main\\resources\\statics\\"+"SIMPLE.LAO",ptrRefLao);
// extract the void* that was allocated in C
        final Pointer pLao = ptrRefLao.getValue();
// extract the null-terminated string from the Pointer
        final String Lao = pLao.getString(0);


        final PointerByReference ptrRefMap = new PointerByReference();
// call the C function
        ProjectDesign.PDLibary.pd.MapRead(app_path+"\\src\\main\\resources\\statics\\"+"SIMPLE.MAP",ptrRefMap);
// extract the void* that was allocated in C
        final Pointer pMap = ptrRefMap.getValue();
// extract the null-terminated string from the Pointer
        final String Map = pMap.getString(0);

        final PointerByReference ptrRefPrj = new PointerByReference();
// call the C function
        ProjectDesign.PDLibary.pd.PrjRead(app_path+"\\src\\main\\resources\\statics\\"+"SIMPLE.PRJ",ptrRefPrj);
// extract the void* that was allocated in C
        final Pointer pPrj = ptrRefPrj.getValue();
// extract the null-terminated string from the Pointer
        final String Prj = pPrj.getString(0);

        JSONObject prkobj = JSON.parseObject(val);
        JSONObject laoobj = JSON.parseObject(Lao);
        JSONObject mapobj = JSON.parseObject(Map);
        JSONObject prjobj = JSON.parseObject(Prj);
        JSONObject result = new JSONObject();
        result.put("prk",prkobj);
        result.put("Lao",laoobj);
        result.put("Map",mapobj);
        result.put("prj",prjobj);
        System.out.println("simple return");
        return  result.toJSONString();
    }

    @CrossOrigin
    @RequestMapping(value = "/directory/{ProjectName}",method = RequestMethod.GET)
    public List<FileInfo> dirController(HttpServletRequest request, HttpServletResponse response,@PathVariable String ProjectName) throws IllegalStateException, IOException {
        ArrayList<FileInfo> filelist = new ArrayList<FileInfo>();
        //resources根目录  组合对应ip的文件夹
        String app_path = System.getProperty("user.dir");
        String stat_path = app_path+"\\src\\main\\resources";
        String ip = request.getRemoteAddr();
        //String ProjectName = request.getParameter("ProjectName");

        File fileDir = new File(stat_path+"\\"+ip+"\\"+ProjectName);
        if (!fileDir.exists()) {
            System.out.println("directory not exists");
        }
        File[] allFiles =fileDir.listFiles();
        for (File f:allFiles){
            FileInfo sf = new FileInfo();
            sf.name=f.getName();
            sf.csize =f.length();
            sf.fname = f.getPath();
            filelist.add(sf);
        }

        System.out.println("directory return");
        return filelist;
    }


    @CrossOrigin
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("filename");// 设置文件名，根据业务需要替换成要下载的文件名
        System.out.println(fileName);
        if (fileName != null) {
            //设置文件路径
            String app_path = System.getProperty("user.dir");
            String stat_path = app_path+"\\src\\main\\resources";
            String ip = request.getRemoteAddr();
            String ProjectName = request.getParameter("ProjectName");
            String realPath = stat_path+"\\"+ip+"\\"+ProjectName;
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/octet-stream;charset=utf-8");//  application/zip
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" +fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    System.out.println(file.getAbsolutePath());
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int read = bis.read(buffer);
                    while (read != -1) {
                        os.write(buffer, 0, buffer.length);
                        os.flush();
                        read = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/Pathdownload/Project={Path}&filename={filename}",method = RequestMethod.GET)
    public ResponseEntity downloadtest(@PathVariable String filename,@PathVariable String Path,HttpServletRequest request) throws Exception{
        String app_path = System.getProperty("user.dir");
        String stat_path = app_path+"\\src\\main\\resources";
        String ip = request.getRemoteAddr();
        FileSystemResource file = new FileSystemResource(stat_path+"\\"+ip+"\\"+Path+"\\"+filename);
        System.out.println(file.getPath());
        HttpHeaders Headers = new HttpHeaders();
        Headers.add("Content-Disposition", "attachment;fileName=" +URLEncoder.encode(filename,"UTF-8"));
        return ResponseEntity.ok()
                .headers(Headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }



}


