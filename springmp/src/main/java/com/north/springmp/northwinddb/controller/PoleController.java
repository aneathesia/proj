package com.north.springmp.northwinddb.controller;


import com.north.springmp.northwinddb.entity.Pole;
import com.north.springmp.northwinddb.mapper.PoleMapper;
import com.north.springmp.northwinddb.service.impl.PoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WHJ
 * @since 2021-01-05
 */
@RestController
@RequestMapping("/northwinddb/pole")
public class PoleController {
    @Autowired
    PoleMapper poleMapper;
    @GetMapping("")
    public List<Pole> test(){
        return poleMapper.selectList(null);
    }
}

