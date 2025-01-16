package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.IMemberService;
import com.edu.springboot.jdbc.MemberDTO;

@Controller
public class MainController {
	
	/*
	 * 서비스 인터페이스를 통해 Mapper의 메서드를 호출하므로 여기서 자동주입 받아 준비한다.
	 * 해당 인터페이스는 @Mapper 어노테이션이 부착되어 있으므로
	 * 컨테이너가 시작될 때 자동으로 빈이 생성된다.
	 */
	@Autowired
	IMemberService dao;
	
	@RequestMapping("/")
	public String home(){
		return "home";
	}
	
	// 회원 목록
	@RequestMapping("/list.do")
	public String member2(Model model) {
		/*
		 * dao.select() : 매퍼 인터페이스의 추상메서드를 호출한다.
		 * 그러면 인터페이스와 연결된 Mapper파일에 정의된 특정 엘리먼트가 호출되어 실행되고
		 * 결과를 반환한다.
		 */
		model.addAttribute("memberList", dao.select());
		return "list";
	}
	
	// 회원 등록
	@RequestMapping(value="/regist.do", method=RequestMethod.GET)
	public String member1() {
		return "regist";
	}
	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	public String member6(MemberDTO memberDTO) {
		int result = dao.insert(memberDTO);
		if(result == 1 )System.out.println("입력되었습니다.");
		return "redirect:list.do";
	}
	
	// 회원 수정1 : 기존 회원정보를 수정페이지에 설정
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String member3(MemberDTO memberDTO, Model model) {
		// 파라미터로 전달된 id를 DTO로 받은 후 전달
		memberDTO = dao.selectOne(memberDTO);
		// 매퍼에서 인출한 회원정보를 View로 전달
		model.addAttribute("dto", memberDTO);
		return "edit";
	}
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String member7(MemberDTO memberDTO) {
		
		// 수정할 내용을 DTO로 받은 후 전달
		int result = dao.update(memberDTO);
		// update 결과는 1이면 성공 0이면 실패
		if(result == 1 )System.out.println("수정되었습니다.");
		else System.out.println("수정 실패");
		
		return "redirect:list.do";
	}
	
	// 회원 삭제
	@RequestMapping("/delete.do")
	public String member4(MemberDTO memberDTO) {
		int result = dao.delete(memberDTO);
		if(result == 1 )System.out.println("삭제되었습니다.");
		else System.out.println("삭제 실패");
		
		return "redirect:list.do";
	}
	
}