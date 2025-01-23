package com.edu.springboot;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	// 커스텀 로그인 페이지 매핑
	@RequestMapping("/myLogin.do")
	public String login1(Principal principal, Model model){
		/*
		 * Spring Security는 세션을 사용해 로그인 정보를 저장하지만
		 * 개발자가 직접 접근할 수 없어
		 * Principal 객체를 통해 로그인 아이디를 얻어올 수 있다.
		 */
		try {
			String user_id = principal.getName();
			model.addAttribute("user_id",user_id);
		}
		catch(Exception e) {
			/*
			 * 최초 접근시 로그인 정보가 없으므로 NullPointer예외가 발생된다.
			 * 따라서 예외처리 해야함
			 */
			e.printStackTrace();
			System.out.println("로그인 전입니다.");	
		}
		return "auth/login";
	}
	
	@RequestMapping("/myError.do")
	public String login2(){
		return "auth/error";
	}
	@RequestMapping("/denied.do")
	public String login3(){
		return "auth/denied";
	}
}
