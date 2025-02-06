package myjpa3;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UseMember01_insert {

	public static void main(String[] args) {
		
		// 영속성 생성
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			// 트랜잭션 시작
			transaction.begin();
			Member3 member3 = 
					new Member3("hong@spring.com", "홍길동3", LocalDate.now());	
			em.persist(member3);
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
