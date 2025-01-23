package com.edu.springboot.auth;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 자동으로 생성되는 '빈'임을 명시
@Configuration
public class MyAuthFailureHandler 
	implements AuthenticationFailureHandler{
	// 핸들러 제작을 위해 인터페이스를 구현한 후 클래스를 정의한다. 
	
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception)
				throws IOException, ServletException {
		String errorMsg = "";
		
		/*
		 * 시큐리티 환경에서 인증시 발생되는 여러 케이스를 각각의 조건으로 생성한다.
		 * 이미 생성된 예외객체를 이용해 적당한 메시지를 출력하게 한다.
		 */
		if(exception instanceof BadCredentialsException) {
			loginFailureCnt(request.getParameter("my_id"));
			errorMsg = "아이디나 비번이 맞지 않습니다. 다시 확인해주세요.(1)";
		}
		else if(exception instanceof InternalAuthenticationServiceException) {
			// 로그인 실패시 잠금과 같은 기능이 필요하다면 메서드를 별도로 정의 후 호출한다.
			loginFailureCnt(request.getParameter("my_id"));
			errorMsg = "아이디나 비번이 맞지 않습니다. 다시 확인해주세요.(2)";
		}
		else if(exception instanceof DisabledException) {
			errorMsg = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.(3)";
		}
		else if(exception instanceof CredentialsExpiredException) {
			errorMsg = "비번 유효기간이 만료되었습니다. 관리자에게 문의하세요.(4)";
		}
		
		/* request 영역에 에러메시지 저장 후 포워드. */ 
		request.setAttribute("errorMsg", errorMsg);
		//이때 error를 파라미터로 전달한다.
		request.getRequestDispatcher("/myLogin.do?error").forward(request, response);
	}
	
	public void loginFailureCnt(String username) {
		System.out.println("요청 아이디 : "+username);
		/*
		 * 틀린 횟수 업데이트
		 * 틀린 횟수 조회
		 * 만약 3회 이상 실패했다면 계정 잠금처리
		 */
	}
}
