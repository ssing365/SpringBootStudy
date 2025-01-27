package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.smtp.EmailSending;
import com.edu.springboot.smtp.InfoDTO;

@Controller
public class MainController {
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/emailSendMain.do")
	public String emailSendMain() {
		return "emailSendMain";
	}
	
	@Autowired
	EmailSending email;
	
	@PostMapping("/emailSendProcess.do")
	public String emailSendProcess(InfoDTO infoDTO) {
		email.myEmailSender(infoDTO);
		return "home";
	}

}
