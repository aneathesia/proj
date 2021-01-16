package com.north.springmp.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;


public class JNATest {
    public interface JNACLibrary extends StdCallLibrary {
         int Getarea(int w,int h);
    }
        public  static void main(String[] args){
            //JNACLibrary.JANC_INS.Getarea(1,1);
            JNACLibrary JANC_INS= (JNACLibrary) Native.load(("C:\\Users\\bwg_666\\source\\repos\\JNATest\\Release\\JNATest.dll"), JNACLibrary.class);
            int a=JANC_INS.Getarea(1,1);
            System.out.println(a);
        }
}
