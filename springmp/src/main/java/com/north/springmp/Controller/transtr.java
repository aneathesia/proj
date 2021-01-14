package com.north.springmp.Controller;

import com.alibaba.fastjson.JSONObject;
import com.north.springmp.jna.prkfile;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class transtr {

    @RequestMapping("/test2")
    public String str(String str) {
        String getstr = str;
        System.out.println(getstr);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/test1", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getByJSON(@RequestBody JSONObject jsonParam) {
        // 直接将json信息打印出来
        System.out.println(jsonParam.toJSONString());

        prkfile.CPRKLibary.cprk.JNASaveJsonPRK(jsonParam.toJSONString());

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        result.put("data", jsonParam);
        return result.toJSONString();
    }

}