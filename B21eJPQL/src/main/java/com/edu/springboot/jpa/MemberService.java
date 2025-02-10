package com.edu.springboot.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	// DAO 역할의 리포지토리 인터페이스에 대한 빈 주입
	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> selectMembers1(String search){
		List<Member> member = memberRepository
				.findMembers(search);
		return member;
	}
	
	public List<Member> selectMembers2(String search, Sort sort){
		List<Member> member = memberRepository
				.findMembers(search, sort);
		return member;
	}
	
	public Page<Member> selectMembers3(String search, Pageable pageable){
		Page<Member> member = memberRepository
				.findMembers(search, pageable);
		return member;
	}
	
	public List<Member> selectMembers4(String search){
		List<Member> member = memberRepository.findMembersNative(search);
		return member;
	}

}
