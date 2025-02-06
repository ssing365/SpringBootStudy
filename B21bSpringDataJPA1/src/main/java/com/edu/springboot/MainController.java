package com.edu.springboot;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.springboot.jpa.Member;
import com.edu.springboot.jpa.MemberService;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
//	DAO 자동주입
	@Autowired
	MemberService memberService;
	
//	입력
	@GetMapping("/insert.do")
	public String insert(@RequestParam("username") String name, 
			Model model) {
		// 빌더패턴으로 인스턴스를 생성한 후 멤버변수 초기화
		Member member = Member.builder()
				.username(name)
				.createDate(LocalDate.now())
				.build();
		// DAO 호출 및 insert 처리
		Member result = memberService.insert(member);
		// Model 객체에 결과 저장 후 View 포워드
		model.addAttribute("member",result);
		return "insert";
	}
	
//	조회
	@GetMapping("/select.do")
	public String select(@RequestParam("id") Long p_id,
				Model model	) {
		Optional<Member> result = memberService.select(p_id);
		if(result.isPresent()) {
			model.addAttribute("member", result.get());
		}
		else {
			model.addAttribute("member", null);
		}
		return "select";
	}
	@GetMapping("/selectAll.do")
	public String selectAll(Model model) {
		List<Member> result = memberService.selectAll();
		model.addAttribute("members", result);
		return "selectAll";
	}
	
	@GetMapping("/delete.do")
	public String delete(@RequestParam("id") Long pid) {
		memberService.delete(pid);
		return "delete";
	}
	
	@GetMapping("/update.do")
	public String update(Member member, Model model) {
		member.setCreateDate(LocalDate.now());
		Member result = memberService.update(member);
		model.addAttribute("member",result);
		return "update";
	}
}
