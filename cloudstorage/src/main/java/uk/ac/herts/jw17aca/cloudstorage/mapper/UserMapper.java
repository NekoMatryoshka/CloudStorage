package uk.ac.herts.jw17aca.cloudstorage.mapper;

import uk.ac.herts.jw17aca.cloudstorage.pojo.User;

public interface UserMapper {
	
	  public User selectById(int id);
	  public User selectByEmail(String email);
	  public void add(User user);
	  public void delete(int id);
	  public void update(User user);
	  
}
