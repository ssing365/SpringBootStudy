package com.edu.springboot.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

/*
 * 웹소켓 설정을 위한 자동 빈 생성(@Configuration),
 * 소켓 활성화(@EnableWebSocket),
 * 생성자 주입을 위한 인자 생성자(@RequiredArgsConstructor) 어노테이션 선언
 */
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
	
	private final WebSocketHandler webSocketHandler;
	/*
	 * 웹소켓에 접속할 수 있는 요청 URL생성
	 * 우리는 ws://localhost:포트번호/myChatserver 로 접속하게 된다.
	 * 웹소켓 사용시에는 http가 아니라 ws를 사용한다.
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/myChatServer")
			.setAllowedOrigins("*");
		/*
		 * 접속시 허용할 도메인 혹은 IP주소 지정
		 * 우리는 학습용이므로 모든 주소를 허용해준다.
		 * 실제 서비스에서는 *로 지정하면 위험할 수 있음
		 */
	}
	

}
