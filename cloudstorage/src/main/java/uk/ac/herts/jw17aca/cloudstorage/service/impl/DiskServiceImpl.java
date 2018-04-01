package uk.ac.herts.jw17aca.cloudstorage.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herts.jw17aca.cloudstorage.mapper.*;
import uk.ac.herts.jw17aca.cloudstorage.pojo.*;
import uk.ac.herts.jw17aca.cloudstorage.service.*;

@Service("DiskService")
public class DiskServiceImpl implements DiskService {

	@Autowired
	DiskMapper diskMapper;
	@Autowired
	FileMapper fileMapper;

	public Disk loadDiskInfo(long userId) {
		return diskMapper.selectByUserId(userId);
	}

	public boolean isEnoughSpace(File file) {
		Disk disk = diskMapper.selectByUserId(file.getUserId());
		return file.getSize() < (disk.getSize() - disk.getUsedSize());
	}

	public List<File> loadFileList(long parentFileId) {
		return fileMapper.selectByParentId(parentFileId);
	}

	public File getFile(long fileId) {
		return fileMapper.selectById(fileId);
	}

	public void mkDir(long parentFileId, String folderName) {
		File parentDir = fileMapper.selectById(parentFileId);
		File dir = new File(parentFileId, folderName, parentDir.getDirectory() + parentDir.getName() + "/", true,
				parentDir.getUserId(), 0, new Date(), "");
		fileMapper.add(dir);
	}

	public void renameFile(long fileId, String newFileName) {
		File file = fileMapper.selectById(fileId);
		file.setName(newFileName);
		fileMapper.update(file);
	}

	@Transactional
	public void deleteFile(long fileId) {
		File file = fileMapper.selectById(fileId);
		Disk disk = diskMapper.selectByUserId(file.getUserId());
		// if it is a file, delete it from db and hard disk directly, and update disk info
		if (!file.isDir()) {
			fileMapper.delete(fileId);
			disk.setFileNumber(disk.getFileNumber() - 1);
			disk.setSize(fileMapper.getTotalUsedSizeByUserId(disk.getUserId()));
			diskMapper.updateByUserId(disk);
			new java.io.File(file.getServerLocation()).delete();
		// if it is a folder, delete every file/folder under it, and then delete itself
		} else {
			List<File> subfiles = fileMapper.selectByParentId(fileId);
			for(File subfile : subfiles) {
				deleteFile(subfile.getId());
			}
			fileMapper.delete(fileId);
		}
	}

	@Transactional
	public void moveFile(long fromFileId, long toParentFileId) {
		File fromFile = fileMapper.selectById(fromFileId);
		File toParentFile = fileMapper.selectById(toParentFileId);
		fileMapper.delete(fromFileId);
		fromFile.setParentId(toParentFileId);
		fromFile.setDirectory(toParentFile.getDirectory() + toParentFile.getName() + "/");
		fileMapper.add(fromFile);
	}

	@Transactional
	public void uploadFile(User user, File uploadFile) {

	}

}
