package com.edu.springboot.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// getter, setter, 기본 생성자, Object 클래스의 메서드까지 생성됨. 이거면 보통 다 커버 가능
@Data
// 아래는 개별적으로 생성.
//@Setter
//@Getter
//@AllArgsConstructor : 인수 생성자
//@NoArgsConstructor : 기본 생성자(인수x)
public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private String regdate;
}
