package myjpa3;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UseMember02_select {
	
public static void main(String[] args) {
		
		// 영속성 생성
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		// select는 트랜잭션 생성x
//		EntityTransaction transaction = em.getTransaction();
		Member3 member3 = em.find(Member3.class, "hong@spring.com");
		System.out.println("member3=" + member3);
		
		if(member3 != null) {
			System.out.println("이름 :" + member3.getName());
			System.out.println("날짜 :" + member3.getCreateDate());
		}
		else {
			System.out.println("존재하지 않습니다.");
		}
		
		/*
		 * 실행하기전 persistence.xml에서 
		 * hibernate.hbm2ddl.auto속성을 none으로 변경해야한다.
		 * 그렇지 않으면 실행시 테이블을 drop후 create하기 때문에 레코드가 삭제된다.
		 */
		emf.close();
		em.close();
		
	}

}
