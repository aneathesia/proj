package com.north.springmp.northwinddb.mapper;

import com.baomidou.mybatisplus.extension.api.R;
import com.north.springmp.northwinddb.entity.Route;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RouteMapperTest {
    @Autowired
    RouteMapper  routeMapper;
    @Test
    void test(){
        Route route=new Route();
        route.setRouteid(1);
        route.setRoute("132156,13213156,16516");
        route.setNum("13");
        routeMapper.insert(route);
    }
}