package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * JSP에서는 컨트롤러로 정의하기 위해 Servlet 클래스를 정의한 후 매핑정보를 입력했다.
 * SpringBoot에서는 아래와 같이 @Controller 어노테이션을 부착하면
 * 자동으로 컨트롤러 클래스로 정의된다.
 * 또한 사용을 위해 별도로 인스턴스를 생성할 필요 없이
 * 스프링 컨테이너가 시작시 자동으로 인스턴스(자바빈)를 생성해준다.
 */

@Controller
public class MainController {
	/*
	 * 요청명에 대한 매핑 처리.
	 * 프로젝트를 처음 실행했을 때 home화면에 대한 view처리를 하고있다. 
	 */ 
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	/*
	 * 컨트롤러에 매핑된 각 메서드에서
	 * String을 반환하면 application.properties의 JSP설정에의해
	 * View의 경로가 조립된다.
	 * "Prefix경로 + 메서드 반환값 + suffix경로"에 해당하는 
	 * JSP파일을 찾아 포워드한다.
	 * 즉 이 함수는 index.jsp파일을 찾아 포워드한다. 
	 */
	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/sub/sub.do")
	public String sub() {
		return "sub/sub";
	}
}