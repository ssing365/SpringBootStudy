package com.edu.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
//	DAO역할의 인터페이스 자동주입
	@Autowired
	private MemberRepository memberRepository;
	
//	입력 : 디폴트 메서드
	public void insert() {

		Member member;
		
		member = Member.builder().name("이순신")
				.email("test1@test.com").build();
		memberRepository.save(member);
		
		member = Member.builder().name("강감찬")
				.email("test2@test.com").build();
		memberRepository.save(member);
		
		member = Member.builder().name("을지문덕")
				.email("test3@test.com").build();
		memberRepository.save(member);
		
		member = Member.builder().name("계백")
				.email("test4@test.com").build();
		memberRepository.save(member);
		
		member = Member.builder().name("김유신")
				.email("test5@test.com").build();
		memberRepository.save(member);
		
		member = Member.builder().name("연개소문")
				.email("test6@test.com").build();
		memberRepository.save(member);
		
		member = Member.builder().name("김종서")
				.email("test7@test.com").build();
		memberRepository.save(member);
		
		member = Member.builder().name("최영")
				.email("test8@test.com").build();
		memberRepository.save(member);
		
		member = Member.builder().name("신사임당")
				.email("test9@test.com").build();
		memberRepository.save(member);
		
		member = Member.builder().name("성삼문")
				.email("test10@test.com").build();
		memberRepository.save(member);
		
		member = Member.builder().name("이방원")
				.email("test23@test.com").build();
		memberRepository.save(member);
	}
		
//	전체 조회 : 디폴트 메서드
	public List<Member> selectAll(){
		return memberRepository.findAll();
	}
	
//	아이디로 검색 : 디폴트 메서드
	/*
	 * 반환타입이 Optional<엔티티 빈>인 경우 결과가 1개만 인출되어야한다.
	 * 2개 이상 인출되면 예외가 발생된다.
	 */
	public Optional<Member> selectId(Long search){
		Optional<Member> member = memberRepository.findById(search);
		return member;
	}
	
//	이름으로 검색 : 사용자 정의 메서드
	public Optional<Member> selectName(String search){
		Optional<Member> member = memberRepository.findByName(search);
		return member;
	}
	
//	이메일로 검색 : 사용자 정의 메서드
	public Optional<Member> selectEmail(String search){
		Optional<Member> member = memberRepository.findByEmail(search);
		return member;
	}
	
	// 이름을 like로 검색 : 사용자 정의 메서드
	public List<Member> selectNameLike(String search){
		List<Member> member = memberRepository.findByNameLike(search);
		return member;
	}
	
	// 이름을 like 검색 후 정렬 : 사용자 정의 메서드
	public List<Member> selectNameLikeNameDesc(String search){
		List<Member> member = memberRepository
				.findByNameLikeOrderByNameDesc(search);
		return member;
	}
	
	// 위와 동일하지만 Sort 클래스로 정렬
	public List<Member> selectNameLike(String search, Sort sort){
		List<Member> member = memberRepository
				.findByNameLike(search, sort);
		return member;
	}
	
//	삭제
//	public void delete(Long id) {
//		dao.deleteById(id);
//	}
//	
//	수정
//	public Member update(Member member) {
//		/*
//		 * save() 
//		 * 	: 동일한 키 값이 있으면 update, 없으면 insert 쿼리가 실행된다.
//		 */
//		Member resultMember = dao.save(member);
//		return resultMember;
//	}

}
