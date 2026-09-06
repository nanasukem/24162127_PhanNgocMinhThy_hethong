package sukem.vn.service.impl;

import java.util.List;

import sukem.vn.dao.CategoryDao;
import sukem.vn.dao.impl.CategoryDaoImpl;
import sukem.vn.entity.Category;
import sukem.vn.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(Category category) {
		categoryDao.edit(category);
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public Category get(int id) {
		return categoryDao.get(id);
	}

	@Override
	public Category get(String name) {
		return categoryDao.get(name);
	}

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	@Override
	public List<Category> search(String keyword) {
		return categoryDao.search(keyword);
	}

	@Override
	public Category findById(int id) {
		return categoryDao.get(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.getAll();
	}
}