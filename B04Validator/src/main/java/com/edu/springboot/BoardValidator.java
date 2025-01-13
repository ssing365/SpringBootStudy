package com.edu.springboot;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 폼값 검증을 위한 클래스 제작을 위해 먼저 Validator 인터페이스를 구현한다.
 * 그리고 추상메서드 2개를 필수로 오버라이딩 해야한다.
 */
public class BoardValidator implements Validator {

	/*
	 * 검증을 위한 커맨드 객체의 클래스 타입 확인을 위한 메서드 
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("supports() 호출됨");
		// 폼값을 받을 때 사용하는 커맨드객체의 타입만 명시하면 된다.
		return BoardDTO.class.isAssignableFrom(clazz);
	}

	/*
	 * 폼값 검증 방법 세 가지
	 * 1. if문
	 * 2. ValidationUtils 클래스 이용
	 * 3. 사용자 정의 메서드 이용
	 */
	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("validate() 호출됨");
		
		// 폼값 전체를 저장한 DTO객체는 Object타입으로 전달되므로 형변환한다.
		BoardDTO boardDTO = (BoardDTO) target;
		
		// 폼값을 저장한 클래스가 검증에 적합한 커맨드객체인지 검사
		// (필요한 경우 사용)
		if(supports(boardDTO.getClass())== true) {
			System.out.println("폼값 검증에 적합한 인스턴스");
		}else {
			System.out.println("폼값 검증을 위한 인스턴스가 아님");
		}
		
		// 1. 아이디 검증
		String userid = boardDTO.getUserid();
		// 만약 아이디가 null 또는 빈값이라면 아래 코드 실행
		if(userid == null || userid.trim().isEmpty()) {
			System.out.println("아이디를 입력해주세요");
			/**
			 * 오류 있는 경우 처리 형식
			 * -> errors.rejectValue(폼의 name속성, 에러 객체명, 디폴트메시지) 
			 */
			errors.rejectValue("userid", "idError", "아이디 검증 실패");
		}
		
		// 2. 제목검증 : ValidationUtils 클래스의 정적메서드를 호출해 빈값or널값 검증
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"title", "titleError", "제목 검증 실패");
		
		// 3. 내용검증 : 개발자가 정의한 메서드를 통해 검증
		boolean contentValidate = myEmptyOrWhiteSpace(boardDTO.getContent());
		if(contentValidate == false) {
			System.out.println("내용을 입력해주세요.");
			errors.rejectValue("content", "contentError", "내용 검증 실패");
		}
	}
	
	public boolean myEmptyOrWhiteSpace(String value) {
		// 내용이 null 또는 빈값이면 false 반환
		if(value == null || value.trim().length()==0) {
			return false;
		}
		else {
			return true;
		}
	}

}
