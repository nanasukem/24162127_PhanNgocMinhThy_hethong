package sukem.vn.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import sukem.vn.config.JpaConfig;
import sukem.vn.entity.User;

public class UserRepository {

	public User findById(Long id) {

		EntityManager em = JpaConfig.getEntityManager();

		User user = em.find(User.class, id);

		em.close();

		return user;

	}

	public User save(User user) {

		EntityManager em = JpaConfig.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {

			trans.begin();

			if (user.getId() == null) {

				em.persist(user);

			} else {

				em.merge(user);

			}

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

		} finally {

			em.close();

		}

		return user;

	}

	public void delete(Long id) {

		EntityManager em = JpaConfig.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {

			trans.begin();

			User user = em.find(User.class, id);

			if (user != null) {

				em.remove(user);

			}

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

		} finally {

			em.close();

		}

	}

}