package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

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
	
	/*
	 * 1. request 내장객체를 통해 폼값 받기
	 * : JSP에서 사용하던 방식으로 request내장객체를 통해 폼값을 받는다.
	 * 내장객체 사용을 위해 이 함수의 매개변수로 선언한 후 getParameter()
	 * 메서드를 호출하면 된다.
	 * */
	
	@RequestMapping("/form1.do")
	public String form1(HttpServletRequest httpServletRequest,
			Model model) {
		// 폼값 받아 변수 저장
		String name = httpServletRequest.getParameter("name");
		String age = httpServletRequest.getParameter("age");
		/*
		 * View로 전달한 데이터가 있는 경우 서블릿에서는
		 * request영역에 저장한 후 포워드했다.
		 * SpringBoot에서는 Model객체에 저장한 후 
		 * 포워드 대신 View의 경로를 반환한다.
		 * */
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "form/submit1";
	}
	
	/*
	 * 2. 어노테이션을 통해 파라미터를 받은 후 변수에 저장
	 * @RequestParam에 지정된 파라미터명으로 값을 받은 후 우측에 정의한
	 * 매개변수에 저장한다.
	 * */
	@RequestMapping("/form2.do")
	public String form2(@RequestParam("name") String name,
			@RequestParam("age") String age, Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "form/submit2";
	}
	
	/*
	 * 3. 커맨드객체를 통해 파라미터를 한꺼번에 받아 DTO에 저장
	 * 	 : 사용시 매개변수명은 클래스명의 첫 글자를 소문자로 바꾼 이름을 사용해야한다.
	 * 	 매개변수명을 바꾸고 싶다면 @ModelAttribute 어노테이션을 사용해야한다. 
	 * */
	@RequestMapping("/form3.do")
	public String form3(PersonDTO personDTO) {
		/*
		 * 요청시 사용되는 파라미터명과 DTO의 멤버변수명을 동일하게 정의하면
		 * 개수에 상관없이 setter를 통해 한꺼번에 폼값을 받아 저장할 수 있다.
		 * 또한 Model객체에 자동으로 저장되므로
		 * View로 전달하기 위해 별도의 저장 로직을 작성할 필요가 없다.*/
		return "form/submit3";
	}
	
	/*
	 * 4. @PathVariable 어노테이션으로 경로 형태로 전달되는 파라미터를 순서대로 변수에 저장한다.
	 * 단 이 때 파라미터를 경로로 인식하므로 정적 리소스 사용시 경로 설정에 주의해야한다.
	 * */
	@RequestMapping("/form4/{name}/{age}")
	public String form4(@PathVariable("name") String name,
			@PathVariable("age") String age, Model model) {
		
		model.addAttribute("name",name);
		model.addAttribute("age",age);
		
		return "form/submit4";
	}
	
	@RequestMapping("/memberRegist.do")
	public String memberRegist() {
		return "member/regist";
	}
	@RequestMapping("/memberLogin.do")
	public String memberLogin() {
		return "member/login";
	}
	
	@RequestMapping("/registProcess.do")
	public String registProcess(HttpServletRequest httpServletRequest,
			Model model) {

		String id = httpServletRequest.getParameter("id");
		String pwd = httpServletRequest.getParameter("pass1");
		String name = httpServletRequest.getParameter("name");
		String sex = httpServletRequest.getParameter("sex");
		String email = httpServletRequest.getParameter("email1");
		String emailAgree = httpServletRequest.getParameter("mailing");
		String mail = httpServletRequest.getParameter("zipcode");
		String address = httpServletRequest.getParameter("addr1");
		String phone = httpServletRequest.getParameter("phone1");
		String SMS = httpServletRequest.getParameter("sms");
		String hobby = httpServletRequest.getParameter("etc_no1");
		String route = httpServletRequest.getParameter("etc_no2");
		
		
		model.addAttribute("id", id);
		model.addAttribute("pwd", pwd);
		model.addAttribute("name", name);
		model.addAttribute("sex", sex);
		model.addAttribute("email", email);
		model.addAttribute("emailAgree", emailAgree);
		model.addAttribute("mail", mail);
		model.addAttribute("address", address);
		model.addAttribute("phone", phone);
		model.addAttribute("SMS", SMS);
		model.addAttribute("hobby", hobby);
		model.addAttribute("route", route);
		
		return "member/registProcess";
	}
	@RequestMapping("/loginProcess.do")
	public String loginProcess(HttpServletRequest httpServletRequest,
			Model model) {
		
		String id = httpServletRequest.getParameter("id");
		String pwd = httpServletRequest.getParameter("passwd");
		
		model.addAttribute("id", id);
		model.addAttribute("pwd", pwd);
		
		return "member/loginProcess";
	}
}