package com.edu.springboot.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// getter, setter, 기본생성자, toString 등 추가
@Data
// 인자생성자 추가(위에서 생성된 기본생성자가 없어짐)
@AllArgsConstructor
// 기본생성자를 protected로 선언
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/*
 * 빌더 패턴을 적용하여 메서드 체이닝을 통해 멤버변수를 초기화할 수 있다.
 * 디자인패턴 중 하나로, 싱글톤과 같이 학습해보도록한다.
 */
@Builder
// 엔티티 설정
@Entity(name = "JPAMEMBER01")
public class Member {
	// PK, 시퀀스 설정
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	// name속성에 지정한 이름으로 컬럼 생성
	@Column(name="create_date")
	private LocalDate createDate;
}
