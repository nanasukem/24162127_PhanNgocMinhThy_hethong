package sukem.vn.service;

import java.util.List;
import sukem.vn.entity.Category;

public interface CategoryService {
	void insert(Category category);

	void edit(Category category);

	void delete(int id);

	Category get(int id);

	Category get(String name);

	List<Category> getAll();

	List<Category> search(String keyword);

	Category findById(int id);

	List<Category> findAll();
}