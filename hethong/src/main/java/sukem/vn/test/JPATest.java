package sukem.vn.test;

import jakarta.persistence.EntityManager;

import sukem.vn.config.JpaConfig;

public class JPATest {

	public static void main(String[] args) {

		EntityManager em = JpaConfig.getEntityManager();

		System.out.println("JPA CONNECT SUCCESS");

		em.close();

	}

}