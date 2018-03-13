package uk.ac.herts.jw17aca.cloudstorage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.herts.jw17aca.cloudstorage.mapper.DiskMapper;
import uk.ac.herts.jw17aca.cloudstorage.pojo.Disk;
import uk.ac.herts.jw17aca.cloudstorage.pojo.File;
import uk.ac.herts.jw17aca.cloudstorage.service.DiskService;

@Service("DiskService")
public class DiskServiceImpl implements DiskService {

	@Autowired
	DiskMapper mapper;

	public Disk loadDiskInfo(long userId) {
		return mapper.selectByUserId(userId);
	}

	public boolean isEnoughSpace(File file) {
		Disk disk = mapper.selectByUserId(file.getdiskId());
		return file.getSize() < (disk.getSize() - disk.getUsedSize());
	}

}
