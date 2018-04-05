package uk.ac.herts.jw17aca.cloudstorage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import uk.ac.herts.jw17aca.cloudstorage.pojo.*;
import uk.ac.herts.jw17aca.cloudstorage.service.*;

@Controller // mark this class as view controller
@Scope("prototype") // create a new springmvc instance everytime
@RequestMapping("/user")
public class UserController {

	// auto inject service for service implementation methods
	@Autowired
	UserService userService;
	@Autowired
	DiskService diskService;

	// map this class to give url /login
	@RequestMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {
		// encode name to avoid code injection
		email = HtmlUtils.htmlEscape(email);
		// try to login with given user data
		User user = userService.login(email, password);
		System.out.println("logincontroller: email " + email + " password " + password);
		if (user != null) {
			System.out.println("logincontroller: user: " + user.toString());
			session.setAttribute("user", user);
			Disk disk = diskService.loadDiskInfo(user.getId());
			System.out.println("logincontroller: disk: " + disk.toString());
			session.setAttribute("disk", disk);
			System.out.println("logincontroller: rootDirectoryID: " + disk.getRootDirectoryId());
			session.setAttribute("rootDirectoryID", disk.getRootDirectoryId());
			return "redirect:/home/disk";
		} else {
			return "redirect:/";
		}
	}

	// ajax check login
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	@ResponseBody
	public String checkLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
		User user = userService.login(email, password);
		if (user != null)
			return "true";
		return "false";
	}

	// log out
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// invalidate session when log out
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("/register")
	public String register(HttpSession session, User user) {
		String email = HtmlUtils.htmlEscape(user.getEmail());
		user.setEmail(email);
		System.out.println("registercontroller: registering");
		User newUser = userService.register(user);
		// login if success
		session.setAttribute("user", newUser);
		Disk disk = diskService.loadDiskInfo(newUser.getId());
		session.setAttribute("disk", disk);
		session.setAttribute("rootDirectoryID", disk.getRootDirectoryId());
		return "redirect:/home/disk";
	}

	// check if email already exists when register by ajax
	@RequestMapping(value = "/checkRegister", method = RequestMethod.POST)
	@ResponseBody
	public String confirmEmail(User user) {
		return userService.checkEmail(user.getEmail()) ? "true" : "false";
	}

}
