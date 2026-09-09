package sukem.vn.dao;

import java.util.List;
import sukem.vn.entity.Category;

public interface CategoryDao {
	void insert(Category category);

	void edit(Category category);

	void update(Category category);

	void delete(int id);

	Category get(int id);

	Category findById(int id);

	Category get(String name);

	Category findByName(String name);

	List<Category> getAll();

	List<Category> findAll();

	List<Category> search(String keyword);

	int count();
}