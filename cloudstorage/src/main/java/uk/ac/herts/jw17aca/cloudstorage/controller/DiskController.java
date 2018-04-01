package uk.ac.herts.jw17aca.cloudstorage.controller;

import java.util.List;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	public String listSubFiles(long parentFileId) {
		List<File> files = diskService.loadFileList(parentFileId);
		return JSON.toJSONString(files);
	}

	// upload file
	@RequestMapping("/upload/{folderId}")
	@ResponseBody
	public String uploadFile(HttpServletRequest req, @PathVariable long folderId) {

	}

	// download file
	@RequestMapping("/download/{fileId}")
	public void downloadFile(HttpServletResponse res, @PathVariable long fileId) {
		FileUtil.download(res, diskService.getFile(fileId));
	}

	// make new folder
	@RequestMapping("/mkdir/{folderId}")
	@ResponseBody
	public void mkDir(String name, @PathVariable long folderId) {
		diskService.mkDir(folderId, name);
	}

	// rename file or folder
	@RequestMapping("/rename/{fileId}")
	@ResponseBody
	public void renameFile(@PathVariable long fileId, String newFileName) {
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
	public String deleteFile(@PathVariable long fileId, HttpSession session) {
		diskService.deleteFile(fileId);
		return diskService.loadDiskInfo(((User) session.getAttribute("user")).getId()).getUsedSize() + "";
	}

}
