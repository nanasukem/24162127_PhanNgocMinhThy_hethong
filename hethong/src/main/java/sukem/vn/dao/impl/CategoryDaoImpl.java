package sukem.vn.dao.impl;

import java.util.Collections;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sukem.vn.dao.CategoryDao;
import sukem.vn.entity.Category;
import sukem.vn.repository.JpaConfig;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public void insert(Category category) {
		EntityManager em = JpaConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(category);
			trans.commit();
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void edit(Category category) {
		update(category);
	}

	@Override
	public void update(Category category) {
		EntityManager em = JpaConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(category);
			trans.commit();
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int id) {
		EntityManager em = JpaConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			Category category = em.find(Category.class, id);
			if (category != null) {
				em.remove(category);
			}
			trans.commit();
		} catch (Exception e) {
			if (trans.isActive())
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public Category get(int id) {
		return findById(id);
	}

	@Override
	public Category findById(int id) {
		EntityManager em = JpaConfig.getEntityManager();
		try {
			return em.find(Category.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public Category get(String name) {
		return findByName(name);
	}

	@Override
	public Category findByName(String name) {
		EntityManager em = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT c FROM Category c WHERE c.categoryname = :name";
			TypedQuery<Category> query = em.createQuery(jpql, Category.class);
			query.setParameter("name", name);
			List<Category> list = query.getResultList();
			return list.isEmpty() ? null : list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public List<Category> getAll() {
		return findAll();
	}

	@Override
	public List<Category> findAll() {
		EntityManager em = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT c FROM Category c";
			TypedQuery<Category> query = em.createQuery(jpql, Category.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Category> search(String keyword) {
		EntityManager em = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT c FROM Category c WHERE c.categoryname LIKE :keyword";
			TypedQuery<Category> query = em.createQuery(jpql, Category.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		} finally {
			em.close();
		}
	}

	@Override
	public int count() {
		EntityManager em = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT count(c) FROM Category c";
			return ((Long) em.createQuery(jpql).getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			em.close();
		}
	}
}