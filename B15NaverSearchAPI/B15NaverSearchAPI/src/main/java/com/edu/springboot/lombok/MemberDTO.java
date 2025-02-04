package com.edu.springboot.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//getter, setter, 기본생성자, Object클래스의 메서드까지 생성됨
@Data

//아래는 게터, 세터를 개별적으로 생성할 때 사용
//@Setter
//@Getter
//인수 생성자 추가 
//@AllArgsConstructor
//기본 생성자 추가 
//@NoArgsConstructor
public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private String regidate;
}
