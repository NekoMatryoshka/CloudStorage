package uk.ac.herts.jw17aca.cloudstorage.service;

import java.util.List;

import uk.ac.herts.jw17aca.cloudstorage.pojo.Disk;
import uk.ac.herts.jw17aca.cloudstorage.pojo.File;

public interface DiskService {
	
	public Disk loadDiskInfo(long userId);
	public boolean isEnoughSpace(File file);
	public File getFile(long fileId);
	public List<File> loadFileList(long parentFileId);
	public void mkDir(long parentFileId, String folderName);
	public void renameFile(long fileId, String newFileName);
	public void moveFile(long fromFileId, long toFileId);
	public void deleteFile(long fileId);
	
}
