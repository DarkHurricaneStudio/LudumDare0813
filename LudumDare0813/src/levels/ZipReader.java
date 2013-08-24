package levels;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
			//loading background.png
			byte[] data = ZipReader.load(path,"foreground.png");
            lvl.setForeground(ImageIO.read(new ByteArrayInputStream(data)));
			} catch (Exception e) {
			System.out.println("file not found : foreground");
		}
		try {
			//loading background.png
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
}
