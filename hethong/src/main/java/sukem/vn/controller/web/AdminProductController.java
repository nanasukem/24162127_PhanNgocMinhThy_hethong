package sukem.vn.controller.web;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import sukem.vn.constant.Constant;
import sukem.vn.dao.CategoryDao;
import sukem.vn.dao.ProductDao;
import sukem.vn.dao.impl.CategoryDaoImpl;
import sukem.vn.dao.impl.ProductDaoImpl;
import sukem.vn.entity.Category;
import sukem.vn.model.Product; // Sử dụng đúng Entity Product

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/products", "/admin/product/add", "/admin/product/edit", "/admin/product/delete" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AdminProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDao = new ProductDaoImpl();
	private CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();

		if (uri.contains("/admin/product/add")) {
			req.setAttribute("categories", categoryDao.findAll());
			req.getRequestDispatcher("/views/admin/product-form.jsp").forward(req, resp);
		} else if (uri.contains("/admin/product/edit")) {
			String idStr = req.getParameter("id");
			if (idStr != null && !idStr.trim().isEmpty()) {
				int id = Integer.parseInt(idStr);
				req.setAttribute("product", productDao.findById(id));
			}
			req.setAttribute("categories", categoryDao.findAll());
			req.getRequestDispatcher("/views/admin/product-form.jsp").forward(req, resp);
		} else if (uri.contains("/admin/product/delete")) {
			String idStr = req.getParameter("id");
			if (idStr != null && !idStr.trim().isEmpty()) {
				int id = Integer.parseInt(idStr);
				productDao.delete(id);
			}
			resp.sendRedirect(req.getContextPath() + "/admin/products");
		} else {
			req.setAttribute("products", productDao.findAll());
			req.getRequestDispatcher("/views/admin/product-list.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		try {
			String idStr = req.getParameter("id");
			String name = req.getParameter("name");
			String priceStr = req.getParameter("price");
			String description = req.getParameter("description");
			String categoryIdStr = req.getParameter("categoryId");

			if (name == null || name.trim().isEmpty() || priceStr == null || priceStr.trim().isEmpty()) {
				req.setAttribute("alert", "Vui lòng nhập đầy đủ tên và giá bánh!");
				req.setAttribute("categories", categoryDao.findAll());
				req.getRequestDispatcher("/views/admin/product-form.jsp").forward(req, resp);
				return;
			}

			BigDecimal price = new BigDecimal(priceStr);
			int categoryId = Integer.parseInt(categoryIdStr);
			Category category = categoryDao.findById(categoryId);

			Product product;
			if (idStr == null || idStr.trim().isEmpty()) {
				product = new Product();
				product.setCreatedDate(new Date());
			} else {
				product = productDao.findById(Integer.parseInt(idStr));
			}

			product.setName(name);
			product.setPrice(price);
			product.setDescription(description);
			product.setCategory(category);

			Part filePart = req.getPart("imageFile");
			if (filePart == null) {
				filePart = req.getPart("image");
			}

			if (filePart != null && filePart.getSize() > 0) {
				String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				if (!originalFileName.isEmpty()) {
					String uploadPath = Constant.UPLOAD_DIR;
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						uploadDir.mkdirs();
					}

					String savedFileName = System.currentTimeMillis() + "_" + originalFileName;
					filePart.write(uploadPath + File.separator + savedFileName);
					product.setImage(savedFileName);
				}
			}

			if (idStr == null || idStr.trim().isEmpty()) {
				productDao.insert(product);
			} else {
				productDao.update(product);
			}

			resp.sendRedirect(req.getContextPath() + "/admin/products");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("alert", "Lỗi khi xử lý sản phẩm: " + e.getMessage());
			req.setAttribute("categories", categoryDao.findAll());
			req.getRequestDispatcher("/views/admin/product-form.jsp").forward(req, resp);
		}
	}
}