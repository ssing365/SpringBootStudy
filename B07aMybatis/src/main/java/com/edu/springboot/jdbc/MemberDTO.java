package com.edu.springboot.jdbc;

import lombok.Data;

// education계정의 member 테이블 컬럼과 동일하게 변수 생성
@Data
public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private String regidate;
}
