package com.edu.springboot.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/* 컴포넌트 어노테이션으로 인해 자동으로 빈이 생성된다.
 * 핸들러 클래스를 상속받아 정의 후, 3개의 메서드를 오버라이딩하는 클래스*/
@Component
public class WebSocketHandler extends TextWebSocketHandler{
	
//	채팅 접속자의 정보를 저장하기 위한 Map형식 컬렉션 생성
	private static final ConcurrentHashMap<String, WebSocketSession>
		CLIENTS = new ConcurrentHashMap<String, WebSocketSession>();
	
	/*
	 * 클라이언트가 웹소켓 서버에 접속했을 때 호출되는 메서드.
	 * 접속 시 웹소켓 session값이 자동 생성되는데, 이 값을 멤버변수인 Map에 저장한다.
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
		throws Exception{
		/*
		 * Key로는 웹소켓 세션 아이디를, Value로는 세션 인스턴스 자체를 저장한다.
		 */
		CLIENTS.put(session.getId(), session);
		System.out.println("접속(세션아디) ::" + session.getId() + ":(세션):" + session);
	}
	
	/*
	 * 웹소켓 서버에서 클라가 접속을 종료했을 때 호출되는 메서드
	 * Map에서 해당 session값을 제거
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status)
				throws Exception {
		CLIENTS.remove(session.getId());
	}
	
	/*
	 * 클라가 웹소켓 서버로 메시지를 보내면 호출되는 메서드
	 * Map에 저장된 클라 수만큼 반복한다.
	 * 발신자를 제외한 나머지한테만 메시지를 보낸다.
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message)
				throws Exception{
//		메시지를 보낸 사용자의 웹소켓 세션 아이디를 얻어온다.
		String id = session.getId();
//		Map에 저장된 클라 수만큼 반복
		CLIENTS.entrySet().forEach(arg->{
			
//			발신자 아이디를 제외한 나머지에게만 전송. (Echo)
			if(!arg.getKey().equals(id)) {
				try {
					arg.getValue().sendMessage(message);
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		});
	}
}
