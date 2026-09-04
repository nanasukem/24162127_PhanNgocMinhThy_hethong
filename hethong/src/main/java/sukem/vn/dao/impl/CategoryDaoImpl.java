package sukem.vn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sukem.vn.dao.CategoryDao;
import sukem.vn.model.Category;
import sukem.vn.repository.SQLServerConnect;

public class CategoryDaoImpl extends SQLServerConnect implements CategoryDao {

	@Override
	public void insert(Category category) {

		String sql = "INSERT INTO category(cate_name, icons) VALUES (?, ?)";

		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, category.getCatename());

			ps.setString(2, category.getIcon());

			ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public void edit(Category category) {

		String sql = "UPDATE category SET cate_name=?, icons=? WHERE cate_id=?";

		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, category.getCatename());

			ps.setString(2, category.getIcon());

			ps.setInt(3, category.getCateid());

			ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public void delete(int id) {

		String sql = "DELETE FROM category WHERE cate_id=?";

		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public Category get(int id) {

		String sql = "SELECT * FROM category WHERE cate_id=?";

		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Category category = new Category();

				category.setCateid(rs.getInt("cate_id"));

				category.setCatename(rs.getString("cate_name"));

				category.setIcon(rs.getString("icons"));

				return category;

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	@Override
	public Category get(String name) {

		String sql = "SELECT * FROM category WHERE cate_name=?";

		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Category category = new Category();

				category.setCateid(rs.getInt("cate_id"));

				category.setCatename(rs.getString("cate_name"));

				category.setIcon(rs.getString("icons"));

				return category;

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	@Override
	public List<Category> getAll() {

		List<Category> categories = new ArrayList<>();

		String sql = "SELECT * FROM category";

		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Category category = new Category();

				category.setCateid(rs.getInt("cate_id"));

				category.setCatename(rs.getString("cate_name"));

				category.setIcon(rs.getString("icons"));

				categories.add(category);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return categories;

	}

	@Override
	public List<Category> search(String keyword) {

		List<Category> categories = new ArrayList<>();

		String sql = "SELECT * FROM category WHERE cate_name LIKE ?";

		try (Connection con = super.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, "%" + keyword + "%");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Category category = new Category();

				category.setCateid(rs.getInt("cate_id"));

				category.setCatename(rs.getString("cate_name"));

				category.setIcon(rs.getString("icons"));

				categories.add(category);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return categories;

	}

}