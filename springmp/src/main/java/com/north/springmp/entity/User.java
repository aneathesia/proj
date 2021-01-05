package com.north.springmp.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId
    public   Integer id;
    public String  username;
    public String  password;

}
