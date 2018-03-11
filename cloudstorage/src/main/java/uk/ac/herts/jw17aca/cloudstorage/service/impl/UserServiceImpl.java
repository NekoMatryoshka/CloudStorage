package uk.ac.herts.jw17aca.cloudstorage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.herts.jw17aca.cloudstorage.mapper.UserMapper;
import uk.ac.herts.jw17aca.cloudstorage.pojo.User;
import uk.ac.herts.jw17aca.cloudstorage.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

	// auto inject mapper for accessing database
	@Autowired
	UserMapper mapper;

	// implementation of login service.
	public User login(String email, String password) {
		// access database for user with same email, and inject it into an entity.
		User user = mapper.selectByEmail(email);
		// if a user has been found and matches
		if (user != null && (user.getEmail().equals(email) && user.getPassword().equals(password)))
			return user;
		// else login fails
		return null;
	}

	public boolean register(User input) {
		User user = mapper.selectByEmail(input.getEmail());
		if (user != null)
			return false;
		mapper.add(input);
		return true;
	}

}
