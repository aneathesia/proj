package com.north.springmp.entity;

public class TOWER {
    char[]   name=new char[32];			// 串图号
    int    type;				//0导线悬垂 1导线耐张 2导线跳线 3地线悬垂 4地线耐张


    double avergeK;				// 串常年荷载安全系数
    double maxK;				// 串最大荷载安全系数
    double angleV;				// 中相V串夹角

    int	   Join;				// 1单联2双联
    int	   UpDown;				// 1上扛2下垂或3直跳4绕跳
    int	   Num;					// 每联片数
    int	   material;			// 材料1瓷2玻璃3合成
    double Strength;			// 串机械强度
    char[]   Condition=new char[32];		// 选用条件

    int	   Clamp;				// 1单2双线夹
    double ClampAngle;			// 线夹允许悬垂角
    double Length;				// 串长
    double Weight;				// 无冰串重
    double IceWeight;			// 单片覆冰
    double area;				// 单片面积
    double k;					//金具面积折算折算系数
    int    metrialTpye;			//材质
    int    dirtTpye;			//污秽分区
    double WindPressure;		// 大风串风压
    double IcePressure;			// 覆冰串风压
    double OperationPressure;	// 操作串风压
    double AtomspherePressure;	// 大气串风压
    double ElecPressure;		// 带电串风压
    double Weights;				// 每片重锤重量
    int	   MaxNum;				// 最多可加重锤片数
    double SteelWeight;			// 等值钢重
    int metrialNum;				//材料数量
    MATERIAL[] metrial=new MATERIAL[50];  //MAX_MATERIAL 50
    double lineBrokeK;			//串断线工况安全系数
    double jjmaxK;				//金具最大使用荷载安全系数
    double jjlineBrokeK;		//金具断线工况安全系数
    double jjStrength;			//金具破坏荷载
    double[]  unuesed=new double[12];
}
