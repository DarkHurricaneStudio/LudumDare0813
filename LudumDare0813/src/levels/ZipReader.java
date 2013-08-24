package levels;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipReader {

	static final int BUFFER = 2048;

	public static void read(String path) {
		try {
			byte data[] = new byte[BUFFER];
			// destination file
			BufferedOutputStream dest = null;
			// open zip archive
			FileInputStream file;

			file = new FileInputStream(path);

			BufferedInputStream buffi = new BufferedInputStream(file);

			ZipInputStream zis = new ZipInputStream(buffi);

			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				FileOutputStream fos = new FileOutputStream(entry.getName());
				dest = new BufferedOutputStream(fos, ZipReader.BUFFER);

				int count;
				while ((count = zis.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				
				System.out.println(dest);
			}
			
			
			zis.close();
			/*
			buffi.close();
			dest.close();
			file.close();
			*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
