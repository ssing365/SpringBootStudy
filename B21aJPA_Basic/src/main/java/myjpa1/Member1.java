package myjpa1;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * JPA에서는 클래스명을 통해 테이블을 생성한다.
 * 멤버변수는 컬럼을 생성하게 되는데, 여러가지 어노테이션을 통해 세부 설정이 가능하다.
 */

/*
 * @Entity : 클래스명으로 테이블 생성
 * @Table : 테이블 생성시 이름을 지정할 수 있다.
 * 			즉, 별도의 지정이 없으면 클래스명이 default 테이블명이 된다.
 */
@Entity
@Table(name="JpaMember1")
public class Member1 {
	/*
	 * @Id : 컬럼 생성 시 primary key로 지정됨
	 * @GeneratedValue : 기본키에서 사용할 시퀀스 생성
	 * 		- 별도의 설정이 없으면 증가치 50
	 * 		- 시퀀스명은 "테이블명_seq"
	 * 		- MySQL을 사용한다면 auto_increment컬럼으로 지정된다.
	 */
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	
	/*
	 * @Column : 컬럼명 지정
	 * 컬럼 생성시 별도 지정이 없으면 변수명으로 생성된다.
	 */
	@Column(name="create_data")
	private LocalDate createDate;
	
	/*
	 * JPA가 디폴트생성자를 사용하므로 반드시 추가해야한다. (없으면 에러 발생)
	 * 만약 인수생성자를 정의하지 않았다면 자동으로 생성되므로 생략 가능
	 */
	public Member1() {}
	public Member1(String username, LocalDate createDate) {
		this.username = username;
		this.createDate = createDate;
	}

}
