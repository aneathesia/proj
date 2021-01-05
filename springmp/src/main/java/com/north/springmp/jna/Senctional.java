package com.north.springmp.jna;


import com.sun.jna.*;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import org.apache.catalina.connector.ClientAbortException;

import java.awt.*;


public  class Senctional {
    public interface CLibrary extends Library{
        CLibrary INSTANCE = (CLibrary) Native.load(("D:\\Springproject\\springmp\\src\\main\\resources\\SectionMap.dll"),CLibrary.class);
        void GenrateCoord(String FilePath,PointerByReference val);
    }
    public  static void main(String[] args){
       String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";
// allocate a void**
        final PointerByReference ptrRef = new PointerByReference();
// call the C function
        CLibrary.INSTANCE.GenrateCoord(resPath+"DEM.EGX",ptrRef);
// extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
// extract the null-terminated string from the Pointer
        final String val = p.getString(0);
        System.out.println(val);
    }
}