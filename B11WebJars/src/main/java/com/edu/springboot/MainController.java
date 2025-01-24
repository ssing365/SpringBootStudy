package com.edu.springboot;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	/*
	 * 컨트폴러에서 매핑을 위한 메서드에 @ResponseBody 어노테이션을 부착하면
	 * 반환되는 값 그대로 웹브라우저에 출력할 수 있다.
	 */
	@RequestMapping("/json.do")
	@ResponseBody
	public String json() {
		/*
		 * 외부라이브러리인 simple-json을 통해
		 * 사용할 수 있는 클래스로 JSON배열과 객체를 생성해준다.
		 */
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		
		// JSON배열에 데이터 추가
		arr.add("손오공");
		arr.add("저팔계");
		arr.add("사오정");
		
		// JSON객체에 데이터 추가
		obj.put("서유기", arr);
		obj.put("result", 1);
		
		// JSON을 String 형식으로 웹브라우저에 출력
		return obj.toJSONString();
	}
	
	@RequestMapping("/jsonQuiz.do")
	@ResponseBody
	public String jsonQuiz() {
		JSONObject obj = new JSONObject();
		
		JSONArray hobbys = new JSONArray();
		hobbys.add("자전거");
		hobbys.add("수영");
		hobbys.add("축구");
		
		JSONArray sinseo = new JSONArray();
		sinseo.add("손오공");
		sinseo.add("저팔계");
		sinseo.add("사오정");
		
		JSONArray samguk = new JSONArray();
		samguk.add("유비");
		samguk.add("관우");
		samguk.add("장비");
		
		JSONArray chunhyang = new JSONArray();
		chunhyang.add("이몽룡");
		chunhyang.add("성춘향");
		
		JSONObject a = new JSONObject();
		a.put("circle", samguk);
		a.put("class", chunhyang);
		
		JSONObject friends = new JSONObject();
		friends.put("mid", sinseo);
		friends.put("high", a);

		obj.put("name", "홍길동");
		obj.put("age", 99);
		obj.put("hobby", hobbys);
		obj.put("friend", friends);
		
		return obj.toJSONString();
	}
}