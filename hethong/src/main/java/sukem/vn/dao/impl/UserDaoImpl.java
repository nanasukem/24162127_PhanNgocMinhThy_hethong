package sukem.vn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import sukem.vn.dao.UserDao;
import sukem.vn.model.User;
import sukem.vn.repository.JpaConfig;

public class UserDaoImpl implements UserDao {

	@Override
	public User get(String username) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT u FROM User u WHERE u.userName = :username";
			TypedQuery<User> query = enma.createQuery(jpql, User.class);
			query.setParameter("username", username);

			List<User> list = query.getResultList();
			if (!list.isEmpty()) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			enma.close();
		}
		return null;
	}

	@Override
	public User findByEmail(String email) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT u FROM User u WHERE u.email = :email";
			TypedQuery<User> query = enma.createQuery(jpql, User.class);
			query.setParameter("email", email);

			List<User> list = query.getResultList();
			if (!list.isEmpty()) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			enma.close();
		}
		return null;
	}

	@Override
	public void insert(User user) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
		} finally {
			enma.close();
		}
	}

	@Override
	public boolean update(User user) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(user);
			trans.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
		} finally {
			enma.close();
		}
		return false;
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
			TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
			query.setParameter("email", email);
			return query.getSingleResult() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			enma.close();
		}
		return false;
	}

	@Override
	public boolean checkExistUsername(String username) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT COUNT(u) FROM User u WHERE u.userName = :username";
			TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
			query.setParameter("username", username);
			return query.getSingleResult() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			enma.close();
		}
		return false;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT COUNT(u) FROM User u WHERE u.phone = :phone";
			TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
			query.setParameter("phone", phone);
			return query.getSingleResult() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			enma.close();
		}
		return false;
	}
}