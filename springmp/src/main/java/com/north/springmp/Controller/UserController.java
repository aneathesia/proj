package com.north.springmp.Controller;

import com.north.springmp.jna.Senctional;
import com.north.springmp.mapper.UserMapper;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "User manage")
@RestController
@RequestMapping(value = "")
public class UserController {
    @GetMapping("")
    public  String begin(){
        return "Springboot";
    }
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/User")
    @ApiOperation(value="UserInfo",notes="user id name password")
    public  String test(){
        String sout;
        sout=userMapper.selectList(null).toString();

        return sout;
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(String name,String pwd){
        String s1=name;
        String s2=pwd;
//
//        User muser = new User();
//         //primary key  usename   unique id generate
//
//        muser.setUsername(s1);
//        muser.setPassword(s2);

//        muser.setId(7);
//        userMapper.insert(muser);
        System.out.println(userMapper.selectById(5).username);

        return  s1+s2;
    }

//    @GetMapping("/caculate")
//    public int addres(){
//
////        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";
////        //System.out.println(resPath);
////        helloworld.CLibrary.INSTANCE.GenrateCoord(resPath+"coordination.txt",resPath+"result.txt");
//      return  1;
//    }

    @GetMapping("/caculate")
    public String addres(){
        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";
        final PointerByReference ptrRef = new PointerByReference();
// call the C function
        Senctional.CLibrary.INSTANCE.GenrateCoord(resPath+"DEM.EGX",ptrRef);
// extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
// extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        return  val;

    }



}
