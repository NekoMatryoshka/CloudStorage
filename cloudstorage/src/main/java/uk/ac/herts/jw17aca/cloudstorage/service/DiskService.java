package uk.ac.herts.jw17aca.cloudstorage.service;

import java.util.List;

import uk.ac.herts.jw17aca.cloudstorage.pojo.Disk;
import uk.ac.herts.jw17aca.cloudstorage.pojo.File;

public interface DiskService {
	
	public Disk loadDiskInfo(long userId);
	public boolean isEnoughSpace(File file);
	public List<File> loadFileList(long parentFileId);
	
}
