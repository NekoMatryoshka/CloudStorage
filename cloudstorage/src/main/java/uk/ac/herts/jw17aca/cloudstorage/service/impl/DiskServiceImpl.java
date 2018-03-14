package uk.ac.herts.jw17aca.cloudstorage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
