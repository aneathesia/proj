package com.north.springmp.entity;

public class WEATHER {
//    public WEATHER()
//    {
//        name[0] = '\0';
//        num = 11;
//        int i =0;
////        strcpy(twi[i++].name="平均气温");
////        strcpy(twi[i++].name,"最高气温");
////        strcpy(twi[i++].name,"最低气温");
////        strcpy(twi[i++].name,"基本风速");
////        strcpy(twi[i++].name,"最大覆冰");
////
////        strcpy(twi[i++].name,"安装情况");
////        strcpy(twi[i++].name,"操作情况");
////        strcpy(twi[i++].name,"大气有风");
////        strcpy(twi[i++].name,"大气无风");
////        strcpy(twi[i++].name,"最高线温");
////
////        strcpy(twi[i++].name,"检修工况");
////        strcpy(twi[i++].name,"事故工况");
//        num = i;
//        h = 15;
//        hs = 15;
//        alfa = 0.16;
//        B = 0;
//    }
    public char[] name=new char[32];
    public TWI[] twi=new TWI[16];
    public int num;
    public double h;			//设计计算风速高度
    public double hs;			//风速基准高度
    public double alfa;		//地形粗糙系数 B类地形为 0.16
    public double B;			//风荷载增加系数
    public int[] unusedInt=new int[14];

}
