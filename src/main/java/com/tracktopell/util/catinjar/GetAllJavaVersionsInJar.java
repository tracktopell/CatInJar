package com.tracktopell.util.catinjar;

import static com.tracktopell.util.catinjar.GetJavaVersion.getJavaVersionsNames;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;

/**
 * com.tracktopell.util.catinjar.GetAllJavaVersionsInJar
 *
 * @author alfredo estrada
 */
public class GetAllJavaVersionsInJar {

    public static void main(String[] args) {
        try {
            File jarFile = new File(args[0]);
            JarFile jar = new JarFile(jarFile);
            Enumeration enumEntries = jar.entries();
            while (enumEntries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumEntries.nextElement();

                if(jarEntry.getName().endsWith(".class")){
                    InputStream is = jar.getInputStream(jarEntry); // get the input stream
                    DataInputStream dis = null;
                    dis = new DataInputStream(is);
                    int magic = dis.readInt();
                    PrintStream ps = System.out;
                    int minor = dis.readUnsignedShort();
                    int major = dis.readUnsignedShort();
                    
                    ps.print(getJavaVersionsNames().get(major));
                    ps.print("|");
                    ps.println(jarEntry);

                    is.close();
                }
            }
            jar.close();

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
