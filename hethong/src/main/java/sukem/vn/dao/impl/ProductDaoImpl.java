package sukem.vn.dao.impl;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sukem.vn.dao.ProductDao;
import sukem.vn.model.Product; // Sửa dòng này
import sukem.vn.repository.JpaConfig;

public class ProductDaoImpl implements ProductDao {

	@Override
	public void insert(Product product) {
		EntityManager em = JpaConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(product);
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
	public void update(Product product) {
		EntityManager em = JpaConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(product);
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
			Product p = em.find(Product.class, id);
			if (p != null)
				em.remove(p);
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
	public Product findById(int id) {
		EntityManager em = JpaConfig.getEntityManager();
		try {
			return em.find(Product.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> findAll() {
		EntityManager em = JpaConfig.getEntityManager();
		try {
			return em.createQuery("SELECT p FROM Product p ORDER BY p.id DESC", Product.class).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> getTop10Recent() {
		EntityManager em = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT p FROM Product p ORDER BY p.createdDate DESC";
			TypedQuery<Product> query = em.createQuery(jpql, Product.class);
			query.setMaxResults(10);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> findWithPaging(int pageIndex, int pageSize) {
		EntityManager em = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT p FROM Product p ORDER BY p.id DESC";
			TypedQuery<Product> query = em.createQuery(jpql, Product.class);
			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		} finally {
			em.close();
		}
	}

	@Override
	public int countTotal() {
		EntityManager em = JpaConfig.getEntityManager();
		try {
			String jpql = "SELECT COUNT(p) FROM Product p";
			Long count = em.createQuery(jpql, Long.class).getSingleResult();
			return count.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			em.close();
		}
	}
}