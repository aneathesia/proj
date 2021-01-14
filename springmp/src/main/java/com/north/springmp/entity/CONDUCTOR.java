package com.north.springmp.entity;

public class CONDUCTOR {
    public char[] name=new char[32];
    public double A;								//电线截面积   mm^2
    public double p1;								//电线单位质量 kg/m
    public double d;								//电线直径		mm
    public double E;								//电线弹性系数 N/mm^2
    public double a;								//电线膨胀系数 /℃
    public double Tp;								//拉断力 Ｔp  Ｎ
    public int  num;								//分裂数
    public double  jgbang;							// 平均次档距

    public double k;								//新线系数0.95
    public double F;								//设计安全系数    Ｆ
    public double C;								//平均运行张力    Ｃ ≤ 25 %Ｔp   Ｎ

    public double S_p;								//电线的破坏应力
    public double S_m;								//电线的最大应力
    public double S_a;								//电线的平均使用应力

    public int  type;								//0导线 1 地线 2 OPGW
    public double[] unuesedDouble=new double[8];
    public int[]    unuesedInt=new int[8];

}
