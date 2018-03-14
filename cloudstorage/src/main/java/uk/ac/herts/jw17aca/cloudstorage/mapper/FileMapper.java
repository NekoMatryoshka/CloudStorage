package uk.ac.herts.jw17aca.cloudstorage.mapper;

import java.util.List;

import uk.ac.herts.jw17aca.cloudstorage.pojo.File;

public interface FileMapper {
	
	public void add(File file);
	public void delete(long id);
	public void update(File file);
	
	public List<File> selectByParentId(long parentId);
	public List<File> selectByFuzzyName(String fuzzyFileName);
	
	public File selectById(long fileId);
	public File selectRootDirectoryByUserId(long userId);
				
}
