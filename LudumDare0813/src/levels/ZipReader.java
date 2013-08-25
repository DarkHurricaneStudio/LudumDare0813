package levels;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

public class ZipReader {

	static final int BUFFER = 2048;

	public static void read(String path, Level lvl) {
		
		try {
			//loading background.png
			byte[] data = ZipReader.load(path,"background.png");
            lvl.setBackground(ImageIO.read(new ByteArrayInputStream(data)));
			} catch (Exception e) {
			System.out.println("file not found : background");
		}

		try {
			//loading foreground.png
			byte[] data = ZipReader.load(path,"foreground.png");
            lvl.setForeground(ImageIO.read(new ByteArrayInputStream(data)));
			} catch (Exception e) {
			System.out.println("file not found : foreground");
		}
		

			//loading elements.txt
			String text = ZipReader.loadText(path,"elements.txt");
            lvl.loadTextFile(text);
			
		
		try {
			//loading cache.png
			byte[] data = ZipReader.load(path,"cache.png");
            lvl.setCache(ImageIO.read(new ByteArrayInputStream(data)));
			} catch (Exception e) {
			System.out.println("file not found : cache");
		}
		
	}

	public static byte[] load(String path, String filename) throws IOException {
		
			ByteArrayOutputStream out = new ByteArrayOutputStream();  
			ZipFile zf = new ZipFile(path);
			ZipEntry entry = zf.getEntry(filename);
        	InputStream in = zf.getInputStream(entry);  
        	byte[] buffer = new byte[BUFFER];  
        	for(int n; (n = in.read(buffer)) != -1; )  
        		out.write(buffer, 0, n);  
        	in.close();  
        	zf.close();  
        	out.close();  
        	byte[] bytes = out.toByteArray();
        	return bytes;
	}
	
	public static String loadText(String path, String filename) {

		String fileContent = "";
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();  
			ZipFile zf = new ZipFile(path);
			ZipEntry entry = zf.getEntry(filename);
			
        	InputStream in = zf.getInputStream(entry);
        	
        	BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        	
        	int i = 0;
        	String line = null;
        	while ((line = br.readLine()) != null)  {
        		fileContent += line+"/n";
        	}
        	
        	in.close();  
        	zf.close();  
        	out.close();  

        	
		} catch (Exception e) {
			System.out.println("Can not read text file.");
		}
		return fileContent;
	}
}
