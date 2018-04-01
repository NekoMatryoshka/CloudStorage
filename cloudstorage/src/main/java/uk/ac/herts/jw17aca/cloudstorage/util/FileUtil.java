package uk.ac.herts.jw17aca.cloudstorage.util;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.*;

import uk.ac.herts.jw17aca.cloudstorage.pojo.File;

public class FileUtil {

	// path to save files;
	public static final String FILE_PATH = "/home/disk/files/";

	// get all files from request
	public static List<MultipartFile> getFiles(HttpServletRequest req) {

		MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> fileMap = mreq.getFileMap();
		List<MultipartFile> fileList = new ArrayList<>();
		for (MultipartFile file : fileMap.values()) {
			fileList.add(file);
		}
		return fileList;

	}

	// get certain file from request
	public static List<MultipartFile> getFile(String fileName, HttpServletRequest req) {
		MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) req;
		return mreq.getFiles(fileName);
	}

	// upload file to certain path
	public static void upload(MultipartFile file, String path) throws IOException {
		FileOutputStream out = new FileOutputStream(path);
		FileCopyUtils.copy(file.getBytes(), out);
	}

	// download file
	public static void download(HttpServletResponse res, File file) {
		// simulate a html response head
		res.reset();
		// set file type to non-type byte stream file for downloading
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Length", file.getSize() + "");
		// activate file download process and provide a default file name
		res.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
		// try to write to output stream of client
		FileInputStream in = null;
		PrintWriter out = null;
		try {
			in = new FileInputStream(file.getServerLocation());
			out = res.getWriter();
			int temp;
			while ((temp = in.read()) != -1)
				out.write(temp);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
		
}
