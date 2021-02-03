package com.north.springmp.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.north.springmp.jna.ProjectDesign;
import com.north.springmp.jna.prkfile;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import org.springframework.web.bind.annotation.*;
import sun.security.ec.point.ProjectivePoint;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class transtr {

//    @RequestMapping("/test3")
//    public String str(String str) {
//        String getstr = str;
//        System.out.println(getstr);
//        return "success";
//    }
    @ResponseBody
    @RequestMapping(value = "/prksave", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String CreatePrkFromJson(HttpServletRequest request) { //RequestParam how to use
        // 直接将json信息打印出来
        String jsonParam =request.getParameter("prk");
        JSONObject prkobj = JSON.parseObject(jsonParam);
        System.out.println(jsonParam);
        String ProjectName = request.getParameter("ProjectName");
        String ip =request.getRemoteAddr();
        String stat_Path = System.getProperty("user.dir") +"\\src\\main\\resources\\";
        System.out.println(ProjectName);
        System.out.println(ip);
        System.out.println("PRKOBJJSON");
        System.out.println(prkobj.toJSONString());

        ProjectDesign.PDLibary.pd.PrkSave(jsonParam,stat_Path+"\\"+ip+"\\"+ProjectName+"\\"+ProjectName+".PRK");

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        result.put("data",prkobj);
        return result.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/MapSave", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String CreateMapFromJson(HttpServletRequest request) {
        // 直接将json信息打印出来
//        System.out.println(jsonParam.toJSONString());
//
//         String pointFile = jsonParam.getString("corner");
//         String demFile = jsonParam.getString("dem");
//        String prjFile = jsonParam.getString("prj");
        String ip = request.getRemoteAddr();
        String ProjectName = request.getParameter("ProjectName");
        String PointFile =request.getParameter("PointFile");
        String DemFile = request.getParameter("DemFile");
        String MapFile = request.getParameter("MapFile");

         String stat_Path = System.getProperty("user.dir") +"\\src\\main\\resources\\";

         System.out.println(PointFile);
         System.out.println(DemFile);

        ProjectDesign.PDLibary.pd.MapSave(stat_Path+ip+"\\"+ProjectName+"\\"+PointFile,stat_Path+ip+"\\"+ProjectName+"\\"+DemFile,stat_Path+ip+"\\"+ProjectName+"\\"+MapFile);

        // 将获取的json数据封装一层，然后在给返回
        return "200";
    }


    @ResponseBody
    @RequestMapping(value = "/pwfsave", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String CreatePwfFromJson(HttpServletRequest request) {
        // 直接将json信息打印出来
        String jsonParam =request.getParameter("pwf");
        JSONObject pwfobj = JSON.parseObject(jsonParam);
        System.out.println(jsonParam);
        String ProjectName = request.getParameter("ProjectName");
        System.out.println(ProjectName);
        String ip =request.getRemoteAddr();
        String stat_Path = System.getProperty("user.dir") +"\\src\\main\\resources\\";
        ProjectDesign.PDLibary.pd.PwfSave(jsonParam,stat_Path+ip+"\\"+ProjectName+"\\"+ProjectName+".PWF",stat_Path+ip+"\\"+ProjectName+"\\"+ProjectName+".PRK");
        System.out.println("PWFFILE");
        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        result.put("data", pwfobj);
        return result.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/projectsave", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String CreatePrjFromJson(HttpServletRequest request) {

        String stat_Path= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        String ProjectName = request.getParameter("ProjectName");
        System.out.println(ProjectName);
        String ip =request.getRemoteAddr();
        String PointFile =request.getParameter("PointFile");
        System.out.println(PointFile);
        String DemFile = request.getParameter("DemFile");
        System.out.println(DemFile);
        String prk = request.getParameter("prk");
        System.out.println(prk);
        String lao = request.getParameter("lao");
        System.out.println(lao);
        ProjectDesign.PDLibary.pd.PrjSave(stat_Path+ip+"\\"+ProjectName+"\\"+PointFile,stat_Path+"\\"+ip+"\\"+ProjectName+"\\"+DemFile,stat_Path+"\\"+ip+"\\"+ProjectName+"\\"+ProjectName);
        ProjectDesign.PDLibary.pd.PrkSave(prk,stat_Path+ip+"\\"+ProjectName+"\\"+ProjectName+".PRK");
        ProjectDesign.PDLibary.pd.LaoSave(lao,stat_Path+ip+"\\"+ProjectName+"\\"+ProjectName+".LAO");

        // 将获取的json数据封装一层，然后在给返回
        if (prk != null&&lao != null) {
            return "200";
        }
        return "error";
    }



    @PostMapping("/prkfile")
    public String prkfile(HttpServletRequest request){
        //@Autowired
        String stat_Path= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        String ProjectName = request.getParameter("ProjectName");
        String ip =request.getRemoteAddr();
        String FileName = request.getParameter("FileName");
        final PointerByReference ptrRef = new PointerByReference();
        prkfile.CPRKLibary.cprk.ReadPrk(stat_Path+ip+"\\"+ProjectName+"\\"+FileName,ptrRef);
        // extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
        // extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(val);
        return val;
    }

    @PostMapping("/pwffile")
    public String pwffile(HttpServletRequest request){
        //@Autowired
        String stat_Path= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        String ProjectName = request.getParameter("ProjectName");
        String ip =request.getRemoteAddr();
        String FileName = request.getParameter("FileName");

        final PointerByReference ptrRef = new PointerByReference();
        ProjectDesign.PDLibary.pd.PwfRead(stat_Path+ip+"\\"+ProjectName+"\\"+FileName,ptrRef);
        // extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
        // extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(val);
        return val;
    }

    @PostMapping("/prjfile")
    public String prjfile(HttpServletRequest request){
        //@Autowired

        String stat_Path= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        String ProjectName = request.getParameter("ProjectName");
        String ip =request.getRemoteAddr();
        String FileName = request.getParameter("FileName");
        final PointerByReference ptrRef = new PointerByReference();
        ProjectDesign.PDLibary.pd.PrjRead(stat_Path+ip+"\\"+ProjectName+"\\"+FileName,ptrRef);
        // extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
        // extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(val);
        return val;
    }

    @PostMapping("/Mapfile")
    public String Mapfile(HttpServletRequest request){
        //@Autowired
        String stat_Path= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        String ProjectName = request.getParameter("ProjectName");
        String ip =request.getRemoteAddr();
        String FileName = request.getParameter("FileName");
        final PointerByReference ptrRef = new PointerByReference();
        ProjectDesign.PDLibary.pd.MapRead(stat_Path+ip+"\\"+ProjectName+"\\"+FileName,ptrRef);
        // extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
        // extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(val);
        return val;
    }


    @PostMapping("/fileRead")  //根据后缀判断
    public String MapfileRead(HttpServletRequest request){
        //@Autowired
        String stat_Path= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        String ProjectName = request.getParameter("ProjectName");
        String ip =request.getRemoteAddr();
        String FileName = request.getParameter("FileName");

        final PointerByReference ptrRef = new PointerByReference();

        ProjectDesign.PDLibary.pd.MapRead(stat_Path+ip+"\\"+ProjectName+"\\"+FileName,ptrRef);
        // extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
        // extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(val);
        return val;
    }

}