package com.north.springmp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@MapperScan("com.north.springmp.mapper")
@MapperScan("com.north.springmp.northwind.mapper")  //scan all the mapper implement

public class SpringmpApplication {

    public static void main(String[] args) {
//        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";
//        //System.out.println(resPath);
//        helloworld.CLibrary.INSTANCE.GenrateCoord(resPath+"coordination.txt",resPath+"result.txt");
//

      SpringApplication.run(SpringmpApplication.class, args);

    }

}

