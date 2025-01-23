package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String home(){
		return "home";
	}

	@RequestMapping("/guest/index.do")
	public String welcome1(){
		return "guest";
	}
	
	@RequestMapping("/member/index.do")
	public String welcome2(){
		return "member";
	}
	
	@RequestMapping("/admin/index.do")
	public String welcome3(){
		return "admin";
	}
}
