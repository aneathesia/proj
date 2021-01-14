package com.north.springmp.entity;

public class SECTION {
    char[] name=new char[32];								//分段名称
    char[] starJ=new char[16];								//起始转角号
    double from;								//起始累距
    char[] endJ=new char[16];								//结束转角号
    double to;									//结束累距
    char[] powerLine=new char[32];							//导线名称
    char[] groundLine=new char[32];						//地线名称
    char[] wertherName=new char[32];						//气象条件名称
    double pLineK;								//导线新线系数0.95
    double pLineF;								//正常设计安全系数    Ｆ
    double pLineC;								//年平均运行张力上限    Ｃ ≤ 25 %Ｔp   Ｎ
    double pSMax ;								//导线的最大应力
    double pSAve ;								//导线的平均使用应力

    double gLineK;								//地线新线系数1.00
    double gLineF;								//正常设计安全系数    Ｆ
    double gLineC;								//年平均运行张力上限    Ｃ ≤ 25 %Ｔp   Ｎ
    double gSMax ;								//地线的最大应力
    double gSAve ;								//地线的平均使用应力

    int  circuit;								//回路数量
    int[]  unusedInt=new int[7];
    double[] unusedDouble = new double[8];
}
