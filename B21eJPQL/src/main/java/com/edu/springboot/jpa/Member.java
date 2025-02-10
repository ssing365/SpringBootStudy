package com.edu.springboot.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게터, 세터, 인자생성자, 기본생성자, 빌더패턴 적용
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder

// 엔티티(테이블)명 설정
@Entity(name="JPAMEMBER03")
public class Member {
	// PK지정 및 기본 시퀀스 생성
	@Id
	@SequenceGenerator(
			name = "mySequence03",
			sequenceName = "JPAMEMBER03_SEQ",
			initialValue = 1,
			allocationSize = 1
	)
	@GeneratedValue (generator = "mySequence03")
	private Long id;
	private String name;
	private String email;
}
