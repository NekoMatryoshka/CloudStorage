package uk.ac.herts.jw17aca.cloudstorage.mapper;

import uk.ac.herts.jw17aca.cloudstorage.pojo.Disk;

public interface DiskMapper {
	
	  public Disk selectByUserId(long userId);
	  public void add(Disk disk);
	  public void deleteByUserId(long userId);
	  public void updateByUserId(Disk disk);
	  
}
