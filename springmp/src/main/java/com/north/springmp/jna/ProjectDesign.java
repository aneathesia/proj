package com.north.springmp.jna;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class ProjectDesign {
    public interface PDLibary extends Library {
        ProjectDesign.PDLibary pd = (ProjectDesign.PDLibary) Native.load(("D:\\Springproject\\springmp\\src\\main\\resources\\TL.dll"), ProjectDesign.PDLibary.class);
        void GenerateCoord(String FilePath, PointerByReference ResPath);
        void GeoAndSectionPoint(String pointPath,String demPath,PointerByReference resJson);
        void PrkRead(String FilePath, PointerByReference ResPrk);
        void PrkSave(String json, String ResPath);
        void PwfRead(String FilePath, PointerByReference ResPwf);
        void PwfSave(String json, String ResPath, String PrkFile);
        void LaoRead(String FilePath, PointerByReference ResLao);
        void LaoSave(String json, String ResPath);
        void MapSave(String pointFile, String demFile, String ResPath);
        void MapRead(String FilePath, PointerByReference ResMap);
        void PrjSave(String pointFile,String demFile, String ResPath);
        void PrjRead(String FilePath, PointerByReference ResPrj);
        void Prj(String pointFile, String demFile, String ResPath);
    }
    public  static void main(String[] args){
//        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";
////        final String outFile = "J1-J18.txt";
////        final String outFile2 = "1.MAP";
////        PDLibary.pd.MapSave(resPath+"J1-J18.txt",resPath+"DEM.EGX",outFile2);
//        final PointerByReference ptrRef = new PointerByReference();
//        PDLibary.pd.PwfRead(resPath+"Jan.PWF",ptrRef);
//        final Pointer p = ptrRef.getValue();
//// extract the null-terminated string from the Pointer
//        final String val = p.getString(0);
//        System.out.println(val);


        String resPath= System.getProperty("user.dir") +"\\src\\main\\resources\\";

        final PointerByReference ptrRef = new PointerByReference();
// call the C function
        ProjectDesign.PDLibary.pd.PrjRead(resPath+"10.130.224.138\\test\\"+"test.PRJ",ptrRef);
        //ProjectDesign.PDLibary.pd.PrkRead(resPath+"statics\\"+"SIMPLE.PRK",ptrRef);
        // extract the void* that was allocated in C
        final Pointer p = ptrRef.getValue();
// extract the null-terminated string from the Pointer
        final String val = p.getString(0);

        System.out.println(val);
    }


}
