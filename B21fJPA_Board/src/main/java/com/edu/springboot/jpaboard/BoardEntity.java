package com.edu.springboot.jpaboard;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
@Entity(name="jpaboard")
public class BoardEntity {
	// PK지정 및 기본 시퀀스 생성
	@Id
	@SequenceGenerator(
			name = "mySequence",
			sequenceName = "jpaboard_seq",
			initialValue = 1,
			allocationSize = 1
	)
	@GeneratedValue (generator = "mySequence")
	private Long idx;
	private String name;
	private String title;
	private String contents;
	private Long hits;
	@Column(columnDefinition = "DATE DEFAULT SYSDATE")
	private LocalDateTime postdate;
	@PrePersist
	protected void onPrePersist() {
		this.postdate = LocalDateTime.now();
		if(this.hits == null) {
			this.hits = 0L;
		}
	}
}