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

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name="JPAMEMBER03")
public class Member {
	//PK 및 시퀀스 생성
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
