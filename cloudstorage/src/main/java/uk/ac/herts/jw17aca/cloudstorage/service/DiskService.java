package uk.ac.herts.jw17aca.cloudstorage.service;

import java.util.List;

import uk.ac.herts.jw17aca.cloudstorage.pojo.Disk;
import uk.ac.herts.jw17aca.cloudstorage.pojo.File;
import uk.ac.herts.jw17aca.cloudstorage.pojo.User;

public interface DiskService {
	
	public Disk loadDiskInfo(long userId);
	public boolean isEnoughSpace(File file);
	public File getFile(long fileId);
	public List<File> loadFileList(long parentFileId);
	public String mkDir(long parentFileId, String folderName);
	public void renameFile(long fileId, String newFileName);
	public void moveFile(long fromFileId, long toFileId);
	public String deleteFile(long fileId);
	public boolean uploadFile(File uploadFile);
	
}
