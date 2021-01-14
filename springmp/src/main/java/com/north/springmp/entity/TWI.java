package com.north.springmp.entity;

public class TWI {
    public
    TWI()
    {
        name[0] = '\0';
        t = 0;
        v =0;
        b =0;
        P1=0;
        P2 =0;
        P3 =0;
        P4 =0;
        P5 =0;
        P6=0;
        P7 =0;
        Fmx =0;
        angle=0;
    };
    public char[] name=new char[32];						//气象名称
    public double t;							//温度 ℃
    public double v;							//风速 m/s
    public double b;							//覆冰厚度	mm

    public double P1;							//自  荷  载
    public double P2;							//冰  荷  载
    public double P3;							//自荷载加冰荷载
    public double P4;							//无冰时的风荷载
    public double P5;							//覆冰时的风荷载
    public double P6;							//无冰时综合荷载
    public double P7;							//覆冰时综合荷载
    public double Fmx;							//控制条件判别值
    public double angle;						//风偏角 θ
    public double[] unused=new double[16];
}
