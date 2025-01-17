package com.edu.springboot.jdbc;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
 * Controller와 Mybatis Mapper 사이에서 매개역할을 하는 인터페이스
 * @Mapper 어노테이션 부착
 * 컨트롤러는 인터페이스의 추상메서드를 호출하고,
 * 이를 통해 Mapper(xml)의 특정 엘리먼트가 호출되어 쿼리문이 실행되는 구조
 */
@Mapper
public interface IBoardService {
//	목록 : 게시물 개수 카운트(커맨드 객체 사용)
	public int getTotalCount(ParameterDTO parameterDTO);
//	목록 : 각 페이지에 출력할 게시물을 인출하여 반환(커맨드 객체 사용)
	public ArrayList<BoardDTO> listPage(ParameterDTO parameterDTO);
//	작성 : 어노테이션을 통해 폼값을 받은 후 이름을 변경하여 Mapper로 전달
	public int write(@Param("_name") String name,
			@Param("_title") String title,
			@Param("_content") String content);
//	열람
	public BoardDTO view(BoardDTO boardDTO);
//	수정
	public int edit(BoardDTO boardDTO);
//	삭제
	public int delete(String idx);
//	조회수 증가
	public int visitCountPlus(BoardDTO boardDTO);
}
