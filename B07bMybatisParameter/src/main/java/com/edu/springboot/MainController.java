package com.edu.springboot;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.IMemberService;
import com.edu.springboot.jdbc.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	@Autowired
	IMemberService dao;
	
	@RequestMapping("/")
	public String home(){
		return "home";
	}
	
	// 회원 목록
	@RequestMapping("/list.do")
	public String member2(Model model, MemberDTO memberDTO) {
		
		// 학습용 로그
		System.out.println("Model model : "+model);
		System.out.println("MemberDTO memberDTO : "+memberDTO);
		System.out.println(memberDTO.getSearchField());
		System.out.println(memberDTO.getSearchKeyword());
		
		// 검색 기능 없음
		// model.addAttribute("memberList", dao.select());
		// 검색기능 추가
		model.addAttribute("memberList", dao.select(memberDTO));
		
		// 학습용 로그
		System.out.println("Model model : "+model);
		System.out.println("MemberDTO memberDTO : "+memberDTO);
		
		return "list";
	}
	
	// 회원 등록
	@RequestMapping(value="/regist.do", method=RequestMethod.GET)
	public String member1() {
		return "regist";
	}
	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	public String member6(HttpServletRequest req) {
		/*
		 * 전송된 폼값은 request내장객체를 통해 전달받는다.
		 * 이 경우 폼값은 개별적으로 받아야한다.
		 */
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		
		// 함수 호출 시 개별적으로 전달한다.
		int result = dao.insert(id, pass, name);
		if(result == 1 )System.out.println("입력되었습니다.");
		return "redirect:list.do";
	}
	
	// 회원정보 인출하기(수정1) : 기존 회원정보를 수정페이지에 설정
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String member3(HttpServletRequest req,
			MemberDTO memberDTO, Model model) {		
		// request내장객체로 id를 받는다.
		memberDTO = dao.selectOne(req.getParameter("id"));
		model.addAttribute("dto", memberDTO);
		return "edit";
	}
	// 수정처리
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String member7(HttpServletRequest req) {
		//request내장객체로 폼값을 받는다.
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("m_id", id);
		paramMap.put("m_pass", pass);
		paramMap.put("m_name", name);
		
		int result = dao.update(paramMap);
		if(result == 1 )System.out.println("수정되었습니다.");
		else System.out.println("수정 실패");
		
		return "redirect:list.do";
	}
	
	// 회원 삭제
	@RequestMapping("/delete.do")
	public String member4(HttpServletRequest req) {
		String id = req.getParameter("id");
		int result = dao.delete(id);
		if(result == 1 )System.out.println("삭제되었습니다.");
		else System.out.println("삭제 실패");
		
		return "redirect:list.do";
	}
	
}