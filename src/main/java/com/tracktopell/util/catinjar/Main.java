package com.tracktopell.util.catinjar;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;

/**
 * com.tracktopell.util.catinjar.Main
 *
 * @author alfredo estrada
 */
public class Main {
	public static void main(String[] args) {
		try{
			File jarFile         = new File(args[0]);
			String targetResource = args[1];
			JarFile jar          = new JarFile(jarFile);
			Enumeration enumEntries = jar.entries();
			while (enumEntries.hasMoreElements()) {
    				JarEntry jarEntry = (JarEntry) enumEntries.nextElement();

				if(jarEntry.getName().contains(targetResource)){
					System.err.print("-->");
					System.err.println(jarEntry);

	    				InputStream is = jar.getInputStream(jarEntry); // get the input stream
  	  				OutputStream os = System.out;
    					while (is.available() > 0) {  // write contents of 'is' to 'os'
        					os.write(is.read());
						os.flush();
    					}
    					is.close();
    				//	os.close();
				} else {
					System.err.print("   ");
					System.err.println(jarEntry);
				}
			}
			jar.close();
			
		} catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
}
