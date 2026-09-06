package sukem.vn.dao;

import java.util.List;
import sukem.vn.entity.Category;

public interface CategoryDao {
	// Thêm, sửa, xóa
	void insert(Category category);

	void edit(Category category);

	void update(Category category);

	void delete(int id);

	// Tìm kiếm theo ID
	Category get(int id);

	Category findById(int id);

	// Tìm kiếm theo tên
	Category get(String name);

	Category findByName(String name);

	// Lấy toàn bộ danh sách
	List<Category> getAll();

	List<Category> findAll();

	// Tìm kiếm theo từ khóa
	List<Category> search(String keyword);

	// Đếm tổng số lượng danh mục
	int count();
}