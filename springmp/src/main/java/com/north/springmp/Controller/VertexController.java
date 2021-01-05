package com.north.springmp.Controller;

import com.north.springmp.pojo.Vertex;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class VertexController {
    @GetMapping("/dem/caculate")
    @ResponseBody
    public Vertex getVertex(){
        Vertex vertice=new Vertex();
        vertice.setId(2);
        vertice.setCoordx(0.000);
        vertice.setCoordy(1.000);
        vertice.setCoordz(2.005);

        return vertice;
    }
}
