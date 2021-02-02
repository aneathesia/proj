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


        ProjectDesign.PDLibary.pd.PrkSave(prkobj.toJSONString(),stat_Path+"\\"+ip+"\\"+ProjectName+"\\"+ProjectName+".PRK");

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        result.put("data",prkobj);
        return result.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/MapSave", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String CreateMapFromJson(@RequestBody JSONObject jsonParam) {
        // 直接将json信息打印出来
        System.out.println(jsonParam.toJSONString());

         String pointFile = jsonParam.getString("corner");
         String demFile = jsonParam.getString("dem");
        String prjFile = jsonParam.getString("prj");


         String ResPath = System.getProperty("user.dir") +"\\src\\main\\resources\\";

         System.out.println(pointFile);
         System.out.println(demFile);

        ProjectDesign.PDLibary.pd.MapSave(ResPath+pointFile,ResPath+demFile,ResPath+"2.Map");

        // 将获取的json数据封装一层，然后在给返回
        return "200";
    }


    @ResponseBody
    @RequestMapping(value = "/pwfsave", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String CreatePwfFromJson(@RequestBody JSONObject jsonParam) {
        // 直接将json信息打印出来
        System.out.println(jsonParam.toJSONString());
        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        ProjectDesign.PDLibary.pd.PwfSave(jsonParam.toJSONString(),resPath+"JAN.PWF","JAN.PRK");

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        result.put("data", jsonParam);
        return result.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/prjsave", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String CreatePrjFromJson(@RequestBody JSONObject jsonParam, HttpServletRequest request) {
        // 直接将json信息打印出来
        System.out.println(jsonParam.toJSONString());
        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        ProjectDesign.PDLibary.pd.PrjSave(jsonParam.toJSONString(),resPath+"JAN.PWF","JAN.PRK");

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        result.put("data", jsonParam);
        return result.toJSONString();
    }



    @GetMapping("/prkfile")
    public String prkfile(){
        //@Autowired
        final PointerByReference ptrRef = new PointerByReference();
        prkfile.CPRKLibary.cprk.ReadPrk("D:\\Springproject\\springmp\\json.PRK",ptrRef);
        // extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
        // extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(val);
        return val;
    }

    @GetMapping("/pwffile")
    public String pwffile(){
        //@Autowired
        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        final PointerByReference ptrRef = new PointerByReference();
        ProjectDesign.PDLibary.pd.PwfRead(resPath+"Jan.PWF",ptrRef);
        // extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
        // extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(val);
        return val;
    }

    @GetMapping("/prjfile")
    public String prjfile(){
        //@Autowired
        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        final PointerByReference ptrRef = new PointerByReference();
        ProjectDesign.PDLibary.pd.PrjRead(resPath+"Jan.PWF",ptrRef);
        // extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
        // extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(val);
        return val;
    }

    @GetMapping("/Mapfile")
    public String Mapfile(){
        //@Autowired
        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        final PointerByReference ptrRef = new PointerByReference();
        ProjectDesign.PDLibary.pd.MapRead(resPath+"Jan.Map",ptrRef);
        // extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
        // extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(val);
        return val;
    }
}