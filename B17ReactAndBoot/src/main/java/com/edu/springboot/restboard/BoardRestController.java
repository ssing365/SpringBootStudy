package com.edu.springboot.restboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BoardRestController {
	
//	JDBC 작업을 위한 인터페이스 자동주입
	@Autowired
	IBoardService dao;
	
	@GetMapping("/restBoardList.do")
	public List<BoardDTO> restBoardList(ParameterDTO parameterDTO){
//		한 페이지에 출력할 게시물 수(하드코딩)
		int pageSize = 10;
//		페이지 번호
		int pageNum = parameterDTO.getPageNum() == null ? 1:
			Integer.parseInt(parameterDTO.getPageNum());
//		게시물의 구간 계산
		int start = (pageNum - 1) * pageSize + 1;
		int end = pageNum * pageSize;
//		계산결과 DTO에 저장
		parameterDTO.setStart(start);
		parameterDTO.setEnd(end);
//		dao의 메서드 호출
		List<BoardDTO> boardList = dao.list(parameterDTO);
//		List를 반환하므로 JSON 배열로 화면에 출력된다.
		return boardList;
	}
	
	@GetMapping("/restBoardSearch.do")
	public List<BoardDTO> restBoardSearch(HttpServletRequest req,
			ParameterDTO parameterDTO){
//		입력된 검색어가 있는 경우...
		if(req.getParameter("searchWord")!=null) {
//			검색어를 스페이스바를 기준으로 split
			String[] sTxtArray = req.getParameter("searchWord").split("");
//			저장된 모든 데이터 삭제
			parameterDTO.getSearchWord().clear();
//			앞에서 반환된 String 배열의 크기만큼 반복
			for(String str : sTxtArray) {
				System.out.println(str);
//				각 검색어를 하나씩 List에 추가
				parameterDTO.getSearchWord().add(str);
			}
		}
//		Mapper의 search 메서드 호출
		List<BoardDTO> searchList = dao.search(parameterDTO);
		return searchList;
	}
	
	// 게시물 열람
	@GetMapping("/restBoardView.do")
	public BoardDTO restBoardView(ParameterDTO parameterDTO){
		// 일련번호는 DTO로 받음
		BoardDTO boardDTO = dao.view(parameterDTO);
		return boardDTO;
	}
	
	@PostMapping("/restBoardWrite.do")
	public Map restBoardWrite(BoardDTO boardDTO){
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int result = dao.write(boardDTO);
			if (result == 1) {
				resultMap.put("result", result);
				System.out.println("정상 입력됨");
			} 
			else resultMap.put("result", result);
		} catch (Exception e) {
	        // 예외를 잡아서 처리
	        resultMap.put("result", 0);
	        resultMap.put("message", e.getMessage());
	        System.err.println("Exception 발생: " + e.getMessage());
	    }
		return resultMap;
	}
}