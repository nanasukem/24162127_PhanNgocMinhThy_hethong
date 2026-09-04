package sukem.vn.repository;

import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import jakarta.persistence.PersistenceContext;
import sukem.vn.entity.Category;
import sukem.vn.entity.Video;

@PersistenceContext

public class JpaConfig {

	public static EntityManager getEntityManager() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");

		return factory.createEntityManager();

	}

	public static void main(String[] args) {

		EntityManager enma = JpaConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		Category cate = new Category();

		cate.setCategoryname("Iphone");

		cate.setImages("abc.jpg");

		cate.setStatus(1);

		Video video = new Video();

		video.setVideoId("v01");

		video.setTitle("test");

		video.setCategory(cate);

		try {

			trans.begin();

			enma.persist(cate);

			enma.persist(video);

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}

	}

}