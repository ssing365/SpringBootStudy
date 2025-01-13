package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/write.do")
	public String insert1(){
		return "write";
	}
	
	/**
	 * Validator 인터페이스를 통한 폼값 유효성 검증
	 * Spring에서 사용하는 커맨드객체는 전송된 폼값을 한번에 받아 Model에 저장해준다.
	 * 만약 저장되는 속성명을 변경하고 싶다면 @ModelAttribute 어노테이션을 사용하면 된다.
	 * 이 코드의 경우 boardDTO가 아닌 dto라는 이름으로 Model에 저장된다.
	 */
	@RequestMapping("/writeAction1.do")
	public String writeAction1(
			@ModelAttribute("dto") BoardDTO boardDTO,
			BindingResult result) {
		
		// 폼값 검증에 성공한 경우 포워드할 View 경로 설정
		String page = "result";
		System.out.println(boardDTO);
		
		// 폼값 검증을 위한 인스턴스 생성
		BoardValidator validator = new BoardValidator();
		/*
		 * 폼값을 저장한 DTO 및 검증 결과 전달을 위한 객체를 인수로 전달
		 * 여기서 validate()를 호출하여 검증을 진행하게 된다. 
		 */
		validator.validate(boardDTO, result);
		
		// 폼값 검증에 실패한 경우 if문 블럭 실행됨
		if(result.hasErrors()) {
			// 실패한 경우 쓰기 페이지로 포워드
			page = "write";
			
			// 폼값 검증에 대한 전체 내용 출력
			System.out.println("검증 실패 반환값 1 : "+result.toString());
			System.out.println("================================");
			
			// 제목 검증 실패시 개별 메시지 출력
			if(result.getFieldError("title")!=null) {
				System.out.println("제목 검증1(에러코드):"
						+result.getFieldError("title").getCode());
			}
			// 내용 검증 실패시 개별 메시지 출력
			if(result.getFieldError("content")!=null) {
				System.out.println("내용 검증1(디폴트메시지):"
						+result.getFieldError("content").getDefaultMessage());
			}			
		}
		return page;
	}
}