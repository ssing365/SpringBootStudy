package com.edu.springboot.jsontype4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @RestController
 *  : @ResponseBody + @Controller를 합친 것
 *  RestAPI를 만들기 위한 컨트롤러에서 사용된다.
 *  내부 메서드에서 반환되는 값은 웹브라우저에 출력된다.
 *  (일반적인 컨트롤러에서는 반환값이 View의 경로로 사용된다.)
 */
@RestController
public class MyRestController {
	
	// JSON객체
	// Map은 Key와 Value를 가진 구조이므로 JSON 객체와 동일
	@GetMapping("/restApi01.do")
	public Map<String, Object> restApi01(){
		Map<String, Object> maps = new HashMap<>();
		
		maps.put("key01", "홍길동");
		maps.put("key02", "유비");
		maps.put("key03", "손오공");
		maps.put("key04", "강백호");
		maps.put("key05", "둘리");
		
		return maps;
	}
	
	// JSON배열
	@GetMapping("/restApi02.do")
	public List<String> restApi02(){
		List<String> lists = new ArrayList<>();
		
		lists.add("홍길동");
		lists.add("유비");
		lists.add("손오공");
		lists.add("강백호");
		lists.add("둘리");
		
		return lists;
	}
	
	// **JSON배열을 포함한 객체**
	// 실무에서 가장 많이 쓰임
	@GetMapping("/restApi03.do")
	public Map<String, Object> restApi03(){
		Map<String, Object> maps = new HashMap<>();
		
		// 각 Key에는 list 인스턴스가 포함된다.
		List<String> lists1 
			= new ArrayList<>(Arrays.asList("이순신", "세종대왕", "신사임당"));
		// Key의 Value로 list
		maps.put("한국의 위인", lists1);
		
		List<String> lists2
			= new ArrayList<>(Arrays.asList("유비", "관우", "장비"));
		maps.put("삼국지", lists2);
		
		List<String> lists3 
			= new ArrayList<>(Arrays.asList("손오공", "저팔계", "사오정"));
		maps.put("서유기", lists3);
		
		return maps;
	}
	
	// JSON객체를 포함한 배열
	// 게시판 목록에서 흔히 사용됨
	@GetMapping("/restApi04.do")
	public List<PersonVO> restApi04(){
		List<PersonVO> lists = new ArrayList<>();
		
		PersonVO vo1 = new PersonVO("강백호", 21, "파워포워드");
		PersonVO vo2 = new PersonVO("서태웅", 21, "파워포워드");
		PersonVO vo3 = new PersonVO("송태섭", 22, "포인트가드");
		PersonVO vo4 = new PersonVO("정대만", 23, "슈팅가드");
		PersonVO vo5 = new PersonVO("채치수", 24, "센터");
		
		lists.add(vo1);
		lists.add(vo2);
		lists.add(vo3);
		lists.add(vo4);
		lists.add(vo5);
		
		return lists;
	}
}
