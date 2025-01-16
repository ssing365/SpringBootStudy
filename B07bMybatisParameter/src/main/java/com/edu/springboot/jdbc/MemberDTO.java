package com.edu.springboot.jdbc;

import java.util.List;

import lombok.Data;

// education계정의 member 테이블 컬럼과 동일하게 변수 생성
@Data
public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private String regidate;
	
	/*
	 * 검색을 위한 멤버변수 추가
	 * 필드명은 체크박스이므로 2개 이상의 항목을 저장하기 위해 List,
	 * 검색어는 단일항목이므로 String으로 정의. 
	 */
	private List<String> searchField;
	private String searchKeyword;
}
