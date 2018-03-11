package uk.ac.herts.jw17aca.cloudstorage.service;

import uk.ac.herts.jw17aca.cloudstorage.pojo.User;

public interface UserService {
	
	public User login(String email, String password);
	public boolean register(User user); 
	
}
