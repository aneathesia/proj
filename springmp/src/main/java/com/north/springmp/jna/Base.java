package com.north.springmp.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

public class Base {
    @Structure.FieldOrder({"wYear","wMonth","wDayOfWeek","wDay","wHour","wMinute","wSecond","wMilliseconds"})
    public static class SYSTEMTIME extends Structure{
        public short wYear;
        public short wMonth;
        public short wDayOfWeek;
        public short wDay;
        public short wHour;
        public short wMinute;
        public short wSecond;
        public short wMilliseconds;
    }
    public  interface Kernel32 extends StdCallLibrary {

        void GetLocalTime(SYSTEMTIME result);
    }
    public  static void main(String[] args){
        Kernel32 lib = (Kernel32) Native.load(("C:\\Windows\\System32\\kernel32.dll"), Kernel32.class);
        SYSTEMTIME time =new SYSTEMTIME();

        lib.GetLocalTime(time);

        System.out.println("Year is "+time.wYear);

    }
}
