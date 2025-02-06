package myjpa1;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UseMember1 {
	
	public static void main(String[] args) {
		
		// 영속성 생성
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		/*
		 * 순수 JPA를 사용할 때 이정도 코드가 있다는 것만 확인하고 넘어가면 된다.
		 * 차후 Spring data JPA로 넘어가면 아래 부분은 모두 추상화되어 직접 작성할 일이 없기 때문
		 */
		try {
			// 트랜잭션 시작
			transaction.begin();
			// 테이블 및 시퀀스 생성을 위한 인스턴스 생성
			// 인자생성자를 통해 초기화된 값이 테이블에 레코드로 입력된다.
			Member1 member1 = new Member1("홍길동1", LocalDate.now());
			// 영속성 개체로 			
			em.persist(member1);
			transaction.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		finally {
			em.close();
		}
		
		emf.close();
		
	}

}
