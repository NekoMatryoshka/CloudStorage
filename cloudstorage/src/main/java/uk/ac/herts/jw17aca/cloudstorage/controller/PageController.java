package uk.ac.herts.jw17aca.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PageController {
	
    @RequestMapping("/registerPage")
    public String registerPage() {
        return "register";
    }
    
//    @RequestMapping("registerSuccessPage")
//    public String registerSuccessPage() {
//        return "registerSuccess";
//    }
    
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }
    
}
