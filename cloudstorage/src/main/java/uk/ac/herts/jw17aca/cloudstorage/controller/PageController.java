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
        
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }
    
    @RequestMapping("/home/disk")
    public String home0() {
        return "home";
    }
    
    @RequestMapping("/home/")
    public String home1() {
        return "redirect:/home/disk";
    }
    
    @RequestMapping("/home")
    public String home2() {
        return "redirect:/home/disk";
    }
    
}
