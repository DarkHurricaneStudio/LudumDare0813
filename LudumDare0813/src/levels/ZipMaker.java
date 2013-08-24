package levels;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipMaker {
	
	
	static final int BUFFER = 2048;
	public static void make(String path,String[] files){
		try {
			 
			byte data[] = new byte[BUFFER];
			FileOutputStream dest = new FileOutputStream(path);
			BufferedOutputStream buff = new BufferedOutputStream(dest);
			ZipOutputStream out = new ZipOutputStream(buff);
			out.setMethod(ZipOutputStream.DEFLATED);
			out.setLevel(9);
			
			for(int i=0; i<files.length; i++) { 
				FileInputStream fi = new FileInputStream(files[i]);
				BufferedInputStream buffi = new BufferedInputStream(fi, BUFFER);
				ZipEntry entry = new ZipEntry(files[i]);
				out.putNextEntry(entry);
				
				int count; 
				while((count = buffi.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count); 
					out.closeEntry();
					buffi.close();
				}
				out.close();
					
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
