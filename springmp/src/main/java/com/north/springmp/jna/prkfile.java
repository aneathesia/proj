package com.north.springmp.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;

public class prkfile {
    public interface CPRKLibary extends Library{
        CPRKLibary cprk = (CPRKLibary) Native.load(("C:\\Users\\bwg_666\\source\\repos\\prkfile\\x64\\Release\\prkfile.dll"), CPRKLibary.class);
        void JNASaveJsonPRK(String FilePath);
        void ReadPrk(String FilePath,PointerByReference val);
    }


    public  static void main(String[] args){

        final PointerByReference ptrRef = new PointerByReference();
        CPRKLibary.cprk.ReadPrk("D:\\Springproject\\springmp\\json.PRK",ptrRef);
        // extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
// extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(val);


        //kernel32

    }
}
