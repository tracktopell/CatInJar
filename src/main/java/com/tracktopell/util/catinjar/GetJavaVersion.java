package com.tracktopell.util.catinjar;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * com.tracktopell.util.catinjar.GetJavaVersion
 * @author Alfredo Estrada
 */
public class GetJavaVersion {
    public static Map<Integer,String> javaVersionsNames;

    public static Map<Integer, String> getJavaVersionsNames() {
        if(javaVersionsNames == null){
            javaVersionsNames=new LinkedHashMap<Integer,String>();
            
            javaVersionsNames.put(0x2D,"JDK 1.1");
            javaVersionsNames.put(0x2E,"JDK 1.2");
            javaVersionsNames.put(0x2F,"JDK 1.3");
            javaVersionsNames.put(0x30,"JDK 1.4");
            javaVersionsNames.put(0x31,"Java SE 5.0");
            javaVersionsNames.put(0x32,"Java SE 6.0");
            javaVersionsNames.put(0x33,"Java SE 7.0");
            javaVersionsNames.put(0x34,"Java SE 8.0");
            javaVersionsNames.put(0x35,"Java SE 9.0");
            javaVersionsNames.put(0x36,"Java SE 10.0");
            javaVersionsNames.put(0x37,"Java SE 11.0");
            javaVersionsNames.put(0x38,"Java SE 12.0");
            javaVersionsNames.put(0x40,"Java SE 13.0");
            javaVersionsNames.put(0x41,"Java SE 14.0");
            javaVersionsNames.put(0x42,"Java SE 15.0");
            javaVersionsNames.put(0x43,"Java SE 16.0");
            javaVersionsNames.put(0x44,"Java SE 17.0");
        }
        return javaVersionsNames;
    }

    public static void main(String[] args) {
        String fileName = null;
        FileInputStream fis = null;
        DataInputStream dis = null;
        
        if(args.length != 1) {
            System.err.println("options: classFile");
            System.exit(1);
        } 
        fileName = args[0];
        try{
            fis = new FileInputStream(fileName);
            dis = new DataInputStream(fis);
            
            int magic = dis.readInt();
            System.out.println("       Magic:"+Integer.toHexString(magic));
            int minor = dis.readUnsignedShort();
            int major = dis.readUnsignedShort();
            System.out.println("     Version:"+Integer.toHexString(major)+"."+Integer.toHexString(minor));
            System.out.println("Java Version:"+getJavaVersionsNames().get(major));
        } catch(IOException ioe){
            ioe.printStackTrace(System.err);
            System.exit(2);
        } finally{
            if(dis != null){
                try{
                    System.err.println("...clossing DataInputStream");
                    dis.close();
                    System.err.println("...clossing FileInputStream");
                    fis.close();
                }catch(IOException ioe2){
                    ioe2.printStackTrace(System.err);
                    System.exit(3);
                }
            }
        }
    }
}
