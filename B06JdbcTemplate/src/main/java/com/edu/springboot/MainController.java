package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.IMemberService;
import com.edu.springboot.jdbc.MemberDTO;

@Controller
public class MainController {

	/*
	 * Service 역할의 인터페이스(컨테이너에 자동 빈 생성)의 빈을 자동으로 주입받음
	 * 이를 통해 DAO의 메서드를 호출할 수 있다. 
	 */
	@Autowired
	IMemberService dao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	// 회원 목록
	@RequestMapping("/list.do")
	public String member2(Model model) {
		model.addAttribute("memberList", dao.select());
		return "list";
	}
	
	/*
	 * @RequestMapping 어노테이션을 통해 매핑할 때 
	 * 전송방식과 상관없이 설정하려면 프로퍼티없이 요청명만 기술하면 된다.
	 * 아래와 같이 value에 요청명, method에 전송방식 명시
	 */
	// @RequestMapping(value="/regist.do", method=RequestMethod.GET)
	@GetMapping(value="/regist.do")
	public String member1() {
		return "regist";
	}
	
	/*
	 * SpringBoot 3.x에선 매핑시 @GetMapping, @PostMapping의 사용을 권고한다.
	 */
	// @RequestMapping(value="/regist.do", method=RequestMethod.POST)
	@PostMapping(value="/regist.do")
	public String member6(MemberDTO memberDTO) {
		// 전송된 폼값을 DTO로 한 번에 받은 후 DAO를 호출		
		int result = dao.insert(memberDTO);
		// insert의 결과이므로 1은 성공, 0은 실패로 판단 가능		
		if(result==1) System.out.println("입력되었습니다.");
		/*
		 * 컨트롤러에서 String을 반환하면 포워드가 되지만,
		 * redirect:를 사용하면 특정 요청명으로 이동한다. 
		 */
		return "redirect:list.do";
	}
	
	// 회원 수정1 : 기존 회원정보 불러오기
	@GetMapping(value="/edit.do")
	public String member3(MemberDTO memberDTO, Model model) {
		// 수정할 아이디를 파라미터로 받은 후 회원정보 인출
		memberDTO = dao.selectOne(memberDTO);
		model.addAttribute("dto", memberDTO);
		return "edit";
	}
	
	// 회원 수정2 : 수정한 내용을 DB에 적용
	@PostMapping(value="/edit.do")
	public String member7(MemberDTO memberDTO) {
		int result = dao.update(memberDTO);
		System.out.println(result);
		if(result == 1) System.out.println("수정되었습니다.");
		// 수정이 완료되면 리스트로 이동
		return "redirect:list.do";
	}
	
	// 회원 삭제
	@GetMapping("/delete.do")
	public String member4(MemberDTO memberDTO) {
		int result = dao.delete(memberDTO);
		if(result==1) System.out.println("삭제되었습니다.");
		return "redirect:list.do";
	}	
	@PostMapping("/delete.do")
	public String member9(MemberDTO memberDTO) {
		int result = dao.delete(memberDTO);
		if(result==1) System.out.println("삭제되었습니다.");
		return "redirect:list.do";
	}	
}
