package com.north.springmp.northwinddb.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.north.springmp.northwinddb.entity.Pole;
import com.north.springmp.northwinddb.service.impl.PoleServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PoleMapperTest {
    @Autowired
    PoleMapper poleMapper;
    @Test
        void test(){
            poleMapper.selectList(null).forEach(System.out::println);
        }
    @Test
        void select_queryWrapper(){
        QueryWrapper queryWrapper = new QueryWrapper();  //using querywrapper
        queryWrapper.eq("PID",2);   //condition
        poleMapper.selectList(queryWrapper).forEach(System.out::println);  //system.out.print

    }
    @Test
        void select_map(){
        QueryWrapper queryWrapper=new QueryWrapper();   // condition map
        Map<String,Object> map=new HashMap<>();
        map.put("PID",1);
        map.put("coordx",2862167.0);
        queryWrapper.allEq(map);
        poleMapper.selectList(queryWrapper).forEach(System.out::println);
    }
}

