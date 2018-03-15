package uk.ac.herts.jw17aca.cloudstorage.interceptor;

import javax.servlet.http.*;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import uk.ac.herts.jw17aca.cloudstorage.pojo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// get user from session
		User user = (User) request.getSession().getAttribute("user");
		// get requested url
		String url = request.getRequestURL().toString();
		// allowed url without login
		String[] allowedURLs = new String[] { "/registerPage", "/loginPage", };
		System.out.println("interceptor: requested URL: " + url);
		// for allowed URLs, permit the access		
		for (String allowedURL : allowedURLs) {
			if (url.contains(allowedURL))
				return true;
		}
		// for URLs needing login, if user does not exist in session, deny the access
		if (user == null) {
			System.out.println("interceptor: user: null");
			// send un-login user to login page
			response.sendRedirect("loginPage");
			return false;
		}
		// if already login, permit the access
		//System.out.println("interceptor: user: yes");
		return true;
	}

}
