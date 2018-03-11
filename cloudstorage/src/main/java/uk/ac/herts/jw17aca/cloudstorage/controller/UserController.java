package uk.ac.herts.jw17aca.cloudstorage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import uk.ac.herts.jw17aca.cloudstorage.pojo.User;
import uk.ac.herts.jw17aca.cloudstorage.service.UserService;

@Controller // mark this class as view controller
@Scope("prototype") // create a new springmvc instance everytime
public class UserController {

	// auto inject service for service implementation methods
	@Autowired
	UserService service;

	// map this class to give url /login
	@RequestMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session) {
		// encode name to avoid code injection
		email = HtmlUtils.htmlEscape(email);
		// try to login with given user data
		User user = service.login(email, password);
		if (user != null) {
			session.setAttribute("user", user);
			return "main";
		} else {
			model.addAttribute("message", "Wrong username and password.");
			return "error";
		}
	}
	
	@RequestMapping("/register")
	public String register(Model model, User user) {
		String email = HtmlUtils.htmlEscape(user.getEmail());
		user.setEmail(email);
		if(!service.register(user)) {
			model.addAttribute("message", "Account with this email already exists, please login.");
			model.addAttribute("user", null);
			return "error";
		}else {
			model.addAttribute("email", user.getEmail());
			return "login";
		}
	}
	
}
