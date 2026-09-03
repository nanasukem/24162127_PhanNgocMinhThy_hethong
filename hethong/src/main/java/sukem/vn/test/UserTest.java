package sukem.vn.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import sukem.vn.config.JpaConfig;
import sukem.vn.entity.User;

public class UserTest {

	public static void main(String[] args) {

		EntityManager em = JpaConfig.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {

			trans.begin();

			User user = new User();

			user.setUsername("admin");

			user.setPassword("123456");

			user.setFullname("Nguyen Van A");

			user.setPhone("0909999999");

			user.setImage("avatar.png");

			em.persist(user);

			trans.commit();

			System.out.println("INSERT USER SUCCESS");

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

		} finally {

			em.close();

		}

	}

}