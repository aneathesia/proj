package com.north.springmp.Controller;

import com.north.springmp.entity.CONDUCTOR;
import com.north.springmp.entity.HEADINFO;
import com.north.springmp.entity.TWI;
import com.north.springmp.entity.WEATHER;
import com.north.springmp.generate.Endiantransfrom;

import java.io.*;

public class prkFileRead {
    //prk文件读写保存
    //DataInputStream  FileInputStream
    //public  static void main(String[] args){
            //File file = new File("C:\\Users\\bwg_666\\Desktop\\TLCAD2010\\TLCAD2010\\Release\\Templates\\SIMPLE.PRK");
        public CONDUCTOR[] GetCONDUCTOR(){
        File file = new File("C:\\Users\\bwg_666\\Desktop\\TLCAD2010\\springmp\\GEN.PRK");

        CONDUCTOR[] m_conductor= new CONDUCTOR[10];
        HEADINFO m_headinfo = new HEADINFO();
        WEATHER[] m_weather = new WEATHER[10];

            if(file.exists()){
                System.out.println("file input");
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    DataInputStream dataInputStream = new DataInputStream(inputStream);
                    Endiantransfrom endiantransfrom=new Endiantransfrom();
                    byte[] doubletemp = new byte[8];
                    byte[] inttemp = new byte[4];
                    //                    char[] prkFileFlag = new char[32];
//                    int num = 0;
//                    //int i;
//                    System.out.println(dataInputStream);
//                    byte[] prkfile = new byte[32];
//                    byte[] headinfo = new byte[120];
//                    byte[] number = new byte[4];
////                    byte a = dataInputStream.readByte();
//                    dataInputStream.read(prkfile,0,32);
//                    dataInputStream.read(headinfo,0,120);
//                    dataInputStream.read(number,0,4);
//                    dataInputStream.readInt();
//                    String prkfileinfo =  new String(prkfile,0,32);
//                    String infores = new String(headinfo,0,120);
//                    String numres = new String(number,0,4);
//                    System.out.println(prkfileinfo);
//                    System.out.println(infores);
//                    System.out.println(numres);
                    //prkfileinfo  char[32]
//                    for(int i=0;i<32;i++) {
//                        byte ch1 = dataInputStream.readByte();
//                        System.out.println((char)ch1);
//                    }

                    byte[] prkfile = new byte[32];
                    dataInputStream.read(prkfile,0,32);
                    String prkfileinfo=endiantransfrom.ByteToString(prkfile);
                    System.out.println(prkfileinfo);
                    //headinfo
                    byte[] ver = new byte[120];
                    dataInputStream.read(ver,0,120);




                    //CONDUCTOR
                    dataInputStream.read(inttemp,0,4);
                    int conductor_num = endiantransfrom.bytesToInt_Little(inttemp);
                    System.out.println(conductor_num);
                    //CONDUCTOR[] m_conductor=new CONDUCTOR[conductor_num];
                    for(int j=0;j<conductor_num;j++) {
                        m_conductor[j]=new CONDUCTOR();
                        byte[] name = new byte[32];
                        dataInputStream.read(name, 0, 32);
                        m_conductor[j].name = endiantransfrom.ByteToString(name).toCharArray();
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].A = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].p1 = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].d = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].E = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].a = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].Tp = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(inttemp, 0, 4);
                        m_conductor[j].num = endiantransfrom.bytesToInt_Little(inttemp);
                        //
                        dataInputStream.read(inttemp, 0, 4);

                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].jgbang = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].k = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].F = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].C = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].S_p = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].S_m = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_conductor[j].S_a = endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(inttemp, 0, 4);
                        m_conductor[j].type = endiantransfrom.bytesToInt_Little(inttemp);
                        //
                        dataInputStream.read(doubletemp, 0, 4);

                        for (int i = 0; i < 8; i++) {
                            dataInputStream.read(doubletemp, 0, 8);
                            //m_conductor[j].unuesedDouble[i] = endiantransfrom.bytesToDouble(doubletemp, true);
                        }
                        for (int i = 0; i < 8; i++) {
                            dataInputStream.read(inttemp, 0, 4);
                            //m_conductor[j].unuesedInt[i] = endiantransfrom.bytesToInt_Little(inttemp);
                        }

                    }

                    //WEATHER
                    dataInputStream.read(inttemp,0,4);
                    int weather_num= endiantransfrom.bytesToInt_Little(inttemp);
                    System.out.println(weather_num);
                    //WEATHER[] m_weather = new WEATHER[weather_num];
                    for(int j=0;j<weather_num;j++){
                        m_weather[j] = new WEATHER();
                        byte[] name = new byte[32];
                        dataInputStream.read(name, 0, 32);
                        //TWI[16]
                        for(int ii=0;ii<16;ii++){
                            m_weather[j].twi[ii]=new TWI();
                            byte[] twi_name = new byte[32];
                            dataInputStream.read(twi_name,0,32);
                            int ptr=0;
                            while(twi_name[ptr]!=0){
                                ptr++;
                            }
                            String strres =  new String(twi_name,0,ptr);
                            System.out.println(strres);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].t = endiantransfrom.bytesToDouble(doubletemp, true);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].v= endiantransfrom.bytesToDouble(doubletemp, true);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].b= endiantransfrom.bytesToDouble(doubletemp, true);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].P1= endiantransfrom.bytesToDouble(doubletemp, true);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].P2= endiantransfrom.bytesToDouble(doubletemp, true);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].P3= endiantransfrom.bytesToDouble(doubletemp, true);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].P4= endiantransfrom.bytesToDouble(doubletemp, true);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].P5= endiantransfrom.bytesToDouble(doubletemp, true);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].P6= endiantransfrom.bytesToDouble(doubletemp, true);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].P7= endiantransfrom.bytesToDouble(doubletemp, true);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].Fmx= endiantransfrom.bytesToDouble(doubletemp, true);
                            dataInputStream.read(doubletemp, 0, 8);
                            m_weather[j].twi[ii].angle= endiantransfrom.bytesToDouble(doubletemp, true);
                            // unsed[16]
                            for(int jj=0;jj<16;jj++){
                                dataInputStream.read(doubletemp, 0, 8);
                            }
                        }

                        dataInputStream.read(inttemp, 0, 4);
                        m_weather[j].num = endiantransfrom.bytesToInt_Little(inttemp);
                        //skip 4
                        dataInputStream.read(inttemp, 0, 4);

                        dataInputStream.read(doubletemp, 0, 8);
                        m_weather[j].h= endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_weather[j].hs= endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_weather[j].alfa= endiantransfrom.bytesToDouble(doubletemp, true);
                        dataInputStream.read(doubletemp, 0, 8);
                        m_weather[j].B= endiantransfrom.bytesToDouble(doubletemp, true);

                        for(int jj=0;jj<14;jj++){
                            dataInputStream.read(doubletemp, 0, 4);
                        }

                    }


                }
                catch (IOException e){
                    System.out.println("file read unavaliable");
                }
            }
            return m_conductor;
        }
    //}
}
