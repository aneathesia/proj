package com.north.springmp.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class JNATest {
    public interface JNACLibrary extends Library {
         int Getarea(int w,int h);
    }
        public  static void main(String[] args){
            //JNACLibrary.JANC_INS.Getarea(1,1);
            JNACLibrary JANC_INS= (JNACLibrary) Native.load(("C:\\Users\\bwg_666\\source\\repos\\JNATest\\x64\\Release\\JNATest.dll"), JNACLibrary.class);
            JANC_INS.Getarea(1,1);
        }
}
