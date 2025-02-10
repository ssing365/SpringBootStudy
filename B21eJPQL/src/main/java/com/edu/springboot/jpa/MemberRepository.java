
package com.edu.springboot.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
		/*
		 * JPQL 쿼리문. from 뒤는 엔티티명으로 소문자로 기술하면 에러 발생됨
		 * 파라미터로 전달된 name1을 like절에 사용하여 검색. id로 내림차순 정렬
		 */
		@Query("select m from JPAMEMBER03 m where m.name like "
				+ " :name1 order by m.id desc")
		List<Member> findMembers(@Param("name1") String name2);
		
		// 정렬을 위한 Sort 사용
		@Query("select m from JPAMEMBER03 m where m.name like :name1")
		List<Member> findMembers(@Param("name1") String name2, Sort sort);
		
		// 페이징을 위한 Pageable
		@Query("select m from JPAMEMBER03 m where m.name like :name1")
		Page<Member> findMembers(@Param("name1") String name2
				, Pageable pageable);

		// 일반적인 SQL문 사용. 테이블 등 대소문자를 구분하지 않는다.
		@Query(value = "select * from JPAMEMBER03 where name like :name1"
				+ " order by id desc",
				nativeQuery = true)
		List<Member> findMembersNative(@Param("name1") String name2);
}

