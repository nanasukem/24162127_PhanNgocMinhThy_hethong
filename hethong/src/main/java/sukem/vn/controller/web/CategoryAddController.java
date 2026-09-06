package sukem.vn.controller.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import sukem.vn.constant.Constant;
import sukem.vn.entity.Category;
import sukem.vn.service.CategoryService;
import sukem.vn.service.impl.CategoryServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class CategoryAddController extends HttpServlet {

	private CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		try {
			// 1. Lấy và kiểm tra tên danh mục (Validation)
			String cateName = req.getParameter("categoryname");
			if (cateName == null || cateName.trim().isEmpty()) {
				cateName = req.getParameter("catename");
			}

			if (cateName == null || cateName.trim().isEmpty()) {
				req.setAttribute("alert", "Tên danh mục không được để trống!");
				req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);
				return;
			}

			Category category = new Category();
			category.setCategoryname(cateName.trim());
			category.setStatus(1); // 1 là hoạt động

			// 2. Lấy file ảnh upload qua Jakarta Part
			Part part = req.getPart("images");
			if (part == null) {
				part = req.getPart("icon");
			}

			if (part != null && part.getSize() > 0) {
				String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

				if (!originalFileName.isEmpty()) {
					int index = originalFileName.lastIndexOf(".");
					String ext = (index > 0) ? originalFileName.substring(index) : ".png";

					String newFileName = System.currentTimeMillis() + ext;

					File uploadFolder = new File(Constant.UPLOAD_DIR + File.separator + "category");
					if (!uploadFolder.exists()) {
						uploadFolder.mkdirs();
					}

					File fileSave = new File(uploadFolder, newFileName);
					part.write(fileSave.getAbsolutePath());

					category.setImages("category/" + newFileName);
				}
			}

			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/products");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("alert", "Lỗi khi thêm danh mục: " + e.getMessage());
			req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);
		}
	}
}