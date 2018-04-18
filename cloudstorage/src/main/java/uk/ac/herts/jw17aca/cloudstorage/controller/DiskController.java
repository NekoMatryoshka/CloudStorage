package uk.ac.herts.jw17aca.cloudstorage.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import uk.ac.herts.jw17aca.cloudstorage.service.*;
import uk.ac.herts.jw17aca.cloudstorage.util.FileUtil;
import uk.ac.herts.jw17aca.cloudstorage.pojo.*;

@Controller
@RequestMapping("/home")
public class DiskController {

	@Autowired
	UserService userService;
	@Autowired
	DiskService diskService;

	// list subfiles
	@RequestMapping("/listSubfiles")
	@ResponseBody
	public String listSubFiles(long id) {
		System.out.println("/listSubfiles: " + id);
		List<File> files = diskService.loadFileList(id);
		return JSON.toJSONString(files);
	}
	
	// log out
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// invalidate session when log out
		session.invalidate();
		return "redirect:/";
	}

	// upload file
	@RequestMapping("/upload/{folderId}")
	@ResponseBody
	public String uploadFile(HttpServletRequest req, @PathVariable long folderId, HttpSession session) {
		System.out.println("/uploadFile: " + folderId);
		java.io.File filePath = new java.io.File(FileUtil.FILE_PATH);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		MultipartFile uploadedFile = FileUtil.getFiles(req).get(0);
		File folder = diskService.getFile(folderId);

		File file = new File();
		file.setUserId(folder.getUserId());
		file.setSize(uploadedFile.getSize() / 1024); // multipart file getSize in bytes, convert into KB.
		file.setName(uploadedFile.getOriginalFilename());
		file.setCreateDate(new Date());
		file.setDir(false);
		file.setDirectory(folder.getDirectory() + folder.getName() + "/");
		file.setParentId(folderId);
		String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
		file.setServerLocation(FileUtil.FILE_PATH + new Date().getTime() + "." + fileType);

		try {
			FileUtil.upload(uploadedFile, file.getServerLocation());
			if (!diskService.uploadFile(file)) {
				System.out.println("uploadFile: diskService.uploadFile(file) fails");
				return "false";
			}
			List<File> subFiles = diskService.loadFileList(folderId);
			File res = subFiles.get(subFiles.size() - 1);
			Disk disk = diskService.loadDiskInfo(folder.getUserId());
			System.out.println("uploadFile: " + disk.toString() + " userID: " + folder.getUserId());
			Map<String, Object> result = new HashMap<>();
			result.put("file", res);
			result.put("usedSize", disk.getUsedSize());
			session.setAttribute("disk", disk);
			return JSON.toJSONString(result);
		} catch (IOException e) {
			e.printStackTrace();
			return "false";
		}
	}

	// download file
	@RequestMapping("/download/{fileId}")
	public void downloadFile(HttpServletResponse res, @PathVariable long fileId) {
		FileUtil.download(res, diskService.getFile(fileId));
	}

	// make new folder
	@RequestMapping("/mkdir/{folderId}")
	@ResponseBody
	public String mkDir(String name, @PathVariable long folderId) {
		return diskService.mkDir(folderId, name);
	}

	// rename file or folder
	@RequestMapping("/rename/{fileId}")
	@ResponseBody
	public void renameFile(@PathVariable long fileId, String newFileName) {
		System.out.println("DiskController: renameFile:" + newFileName);
		diskService.renameFile(fileId, newFileName);
	}

	// move file or folder
	@RequestMapping("/move")
	@ResponseBody
	public void moveFile(long fromFileId, long toParentFileId) {
		diskService.moveFile(fromFileId, toParentFileId);
	}

	// delete file or folder and return the new disk size
	@RequestMapping("/delete/{fileId}")
	@ResponseBody
	public String deleteFile(@PathVariable long fileId) {
		return diskService.deleteFile(fileId);
	}

}
