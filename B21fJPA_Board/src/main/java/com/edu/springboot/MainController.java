package com.edu.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.edu.springboot.jpaboard.BoardEntity;
import com.edu.springboot.jpaboard.BoardService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MainController {

	@Autowired
	BoardService boardService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/list.do")
	public String list(Model model){
		
		List<BoardEntity> rows = boardService.selectList();
		
		model.addAttribute("rows", rows);
		
		return "boardList";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "boardWrite";
	}
	
	@PostMapping("/writeProc.do")
	public String writeProc(BoardEntity boardTable) {
		boardService.insertPost(boardTable);
		return "redirect:list.do";
	}
	
	@GetMapping("/view.do")
	public String view(Model model,
			HttpServletRequest req) {
		String idx = req.getParameter("idx");
		Optional<BoardEntity> result = boardService.selectPost(
				Long.parseLong(idx));
		if(result.isPresent()) {
			BoardEntity be = result.get();
			be.setContents(be.getContents().replaceAll("\r\n", "<br>"));
			model.addAttribute("row", be);
			
		}
		else {
			model.addAttribute("row", null);
		}		
		return "boardView";
	}
	
	@GetMapping("/edit.do")
	public String edit(
			Model model,
			HttpServletRequest req) {
		String idx = req.getParameter("idx");
		Optional<BoardEntity> result = boardService.selectPost(
				Long.parseLong(idx));
		if(result.isPresent()) {
			
			model.addAttribute("row", result.get());
			
		}
		else {
			model.addAttribute("row", null);
		}		
		return "boardEdit";
	}
	
	@PostMapping("/editProc.do")
	public String editProc(BoardEntity boardTable) {
		boardService.updatePost(boardTable);
		return "redirect:view.do?idx=" + boardTable.getIdx();
	}
	
	@GetMapping("/delete.do")
	public String delete(BoardEntity boardTable) {
		boardService.deletePost(boardTable.getIdx());
		return "redirect:list.do";
	}
	
	
	
}


