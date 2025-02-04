package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/naverSmartEditor.do")
	public String naverSmartEditor() {
		return "naverSmartEditor";
		
	}
	
	@RequestMapping(value="/naverSmartEditor.do", method=RequestMethod.POST)
	public String naverSmartEditorSubmit(HttpServletRequest req, Model model) {
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		System.out.println("title="+title);
		System.out.println("contents="+contents);
		
		model.addAttribute("submit","폼값전송됨");
		return "naverSmartEditor";
	}

}
