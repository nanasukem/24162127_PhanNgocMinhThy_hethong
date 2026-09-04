package sukem.vn.service.impl;

import java.util.List;

import sukem.vn.dao.CategoryDao;
import sukem.vn.dao.impl.CategoryDaoImpl;
import sukem.vn.model.Category;
import sukem.vn.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {

		categoryDao.insert(category);

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
	public void edit(Category newCategory) {

		Category oldCategory = categoryDao.get(newCategory.getCateid());

		if (oldCategory != null) {

			oldCategory.setCatename(newCategory.getCatename());

			// nếu có ảnh mới thì cập nhật ảnh
			if (newCategory.getIcon() != null && !newCategory.getIcon().isEmpty()) {

				oldCategory.setIcon(newCategory.getIcon());

			}

			categoryDao.edit(oldCategory);

		}

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

}