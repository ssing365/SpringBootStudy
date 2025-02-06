package myjpa2;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UseMember2 {
	
	public static void main(String[] args) {
		
		// 영속성 생성
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			// 트랜잭션 시작
			transaction.begin();
			Member2 member2 = new Member2("홍길동2", "1234");	
			em.persist(member2);
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
