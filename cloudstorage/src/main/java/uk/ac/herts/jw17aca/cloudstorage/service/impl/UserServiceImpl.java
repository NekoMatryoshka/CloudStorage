package uk.ac.herts.jw17aca.cloudstorage.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herts.jw17aca.cloudstorage.mapper.*;
import uk.ac.herts.jw17aca.cloudstorage.pojo.*;
import uk.ac.herts.jw17aca.cloudstorage.service.*;

@Service("UserService")
public class UserServiceImpl implements UserService {

	// auto inject mapper for accessing database
	@Autowired
	UserMapper userMapper;
	@Autowired
	DiskMapper diskMapper;
	@Autowired
	FileMapper fileMapper;

	// implementation of login service.
	public User login(String email, String password) {
		// access database for user with same email, and inject it into an entity.
		User user = userMapper.selectByEmail(email);
		// if a user has been found and matches
		if (user != null && (user.getEmail().equals(email) && user.getPassword().equals(password)))
			return user;
		// else login fails
		return null;
	}

	@Transactional // unite multiply tables updating into a single transaction for roll back
	public User register(User input) {
		userMapper.add(input);
		// get the full info of new user, and create a root directory in table for him
		User newUser = userMapper.selectByEmail(input.getEmail());
		File rootDirectory = new File(-1, newUser.getUsername(), "/", true, newUser.getId(), 0, new Date(), "");
		fileMapper.add(rootDirectory);
		// get the full info of the root directory of new user, and create a disk in
		// table for him
		File newUserRootDirectory = fileMapper.selectRootDirectoryByUserId(newUser.getId());
		Disk newUserDisk = new Disk(newUser.getId(), newUserRootDirectory.getId());
		diskMapper.add(newUserDisk);
		return newUser;
	}

	public boolean checkEmail(String email) {
		User user = userMapper.selectByEmail(email);
		if (user != null)
			return false;
		return true;
	}

}
