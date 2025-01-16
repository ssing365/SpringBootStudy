package com.edu.springboot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.jdbc.BoardDTO;
import com.edu.springboot.jdbc.IBoardService;
import com.edu.springboot.jdbc.ParameterDTO;

import jakarta.servlet.http.HttpServletRequest;
import utils.PagingUtil;

@Controller
public class MainController {
	
	// DAO호출을 위한 빈 자동주입. 이 인터페이스를 통해 Mapper를 호출한다.
	@Autowired
	IBoardService dao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	// 게시판 목록 페이징 처리
	@RequestMapping("/list.do")
		/*
		 * 매개변수는 View로 전달할 데이터 저장을 위한 Model, 
		 * 요청을 받기 위한 request 내장객체, 커맨드객체로 사용할 DTO 추가
		 */
	public String boardList(Model model, HttpServletRequest req,
			ParameterDTO parameterDTO) {
//		게시물 개수 카운트(검색어가 있는 경우 DTO객체에 자동으로 저장)
		int totalCount = dao.getTotalCount(parameterDTO);
		
//		페이징 처리를 위한 설정값(하드코딩)
//		페이지 당 출력할 게시물 개수
		int pageSize = 2;		
//		한 블록당 출력할 페이지 번호 개수
		int blockPage = 2;
		/*
		 * 목록에 첫 진입 시 페이지 번호가 없으므로 1로 설정하고
		 * 파라미터로 전달된 페이지 번호가 있다면 정수로 변환한다.
		 */
		int pageNum = (req.getParameter("pageNum")==null
				|| req.getParameter("pageNum").equals(""))
				? 1: Integer.parseInt(req.getParameter("pageNum"));
//		페이지에 출력할 게시물 구간 계산
		int start = (pageNum-1) * pageSize + 1;
		int end = pageNum * pageSize;
//		계산의 결과는 DTO에 저장
		parameterDTO.setStart(start);
		parameterDTO.setEnd(end);
		
		
//		View에서 게시물의 가상번호 계산을 위한 값을 Map에 저장
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("totalCount", totalCount);
		maps.put("pageSize", pageSize);
		maps.put("pageNum", pageNum);
		model.addAttribute("maps",maps);
		
//		DB에서 인출한 게시물의 목록을 Model에 저장
		ArrayList<BoardDTO> lists = dao.listPage(parameterDTO);
		model.addAttribute("lists", lists);
		
//		게시판 하단에 출력할 페이지 번호를 String으로 저장 후 Model에 저장
		String pagingImg =
				PagingUtil.pagingImg(totalCount, pageSize,
						blockPage, pageNum,
						req.getContextPath()+"/list.do?");
		model.addAttribute("pagingImg", pagingImg);
		
		return "list";
	}
	
//	글쓰기1 : 작성폼 진입
	@GetMapping("/write.do")
	public String boardWriteGet(Model model) {
		return "write";
	}
//	글쓰기2 : 입력 처리
	@PostMapping("/write.do")
	public String boardWritePost(Model model, HttpServletRequest req) {
//		request내장객체로 폼값 받기
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
//		파라미터를 개별적으로 전달
		int result = dao.write(name, title, content);
		System.out.println("글쓰기 결과 : " + result);
		return "redirect:list.do";
	}
}
