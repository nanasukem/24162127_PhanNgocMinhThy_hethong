package sukem.vn.dao;

import java.util.List;
import sukem.vn.model.Product;

public interface ProductDao {
	void insert(Product product);

	void update(Product product);

	void delete(int id);

	Product findById(int id);

	List<Product> findAll();

	List<Product> getTop10Recent();

	List<Product> findWithPaging(int page, int pageSize);

	int countTotal();
}