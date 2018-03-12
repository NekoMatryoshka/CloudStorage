package uk.ac.herts.jw17aca.cloudstorage.util;

import java.io.*;

public class FileUtil {

	public static void copyFile(String filePath, String targetPath) throws FileNotFoundException, IOException {

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(targetPath)));

		int len = 0;
		while ((len = bis.read()) != -1) {
			bos.write(len);
		}

		bis.close();
		bos.close();

	}

}
