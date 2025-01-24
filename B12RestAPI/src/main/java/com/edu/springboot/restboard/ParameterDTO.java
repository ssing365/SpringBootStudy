package com.edu.springboot.restboard;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ParameterDTO {
//	일련번호
	private String num;
//	페이지 번호
	private String pageNum;
//	검색필드
	private String searchField;
//	검색어
	private ArrayList<String> searchWord;
//	각 페이지 구간
	private int start;
	private int end;
}
