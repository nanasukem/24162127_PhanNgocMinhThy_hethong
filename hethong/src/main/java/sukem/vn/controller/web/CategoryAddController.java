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

		Category category = new Category();

		try {
			// 1. Lấy tên danh mục
			String cateName = req.getParameter("categoryname");
			if (cateName == null || cateName.trim().isEmpty()) {
				cateName = req.getParameter("catename"); // dự phòng form dùng name="catename"
			}
			category.setCategoryname(cateName);
			category.setStatus(1); // int status: 1 là hoạt động

			// 2. Lấy file ảnh upload qua Jakarta Part
			Part part = req.getPart("images");
			if (part == null) {
				part = req.getPart("icon"); // dự phòng form dùng name="icon"
			}

			if (part != null && part.getSize() > 0) {
				String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

				if (!originalFileName.isEmpty()) {
					int index = originalFileName.lastIndexOf(".");
					String ext = (index > 0) ? originalFileName.substring(index) : ".png";

					// Tạo tên file mới kèm timestamp để tránh trùng lặp
					String newFileName = System.currentTimeMillis() + ext;

					// Tạo thư mục C:/upload/category nếu chưa có
					File uploadFolder = new File(Constant.UPLOAD_DIR + File.separator + "category");
					if (!uploadFolder.exists()) {
						uploadFolder.mkdirs();
					}

					// Lưu file vào đĩa cứng
					File fileSave = new File(uploadFolder, newFileName);
					part.write(fileSave.getAbsolutePath());

					// Cập nhật đường dẫn ảnh vào entity
					category.setImages("category/" + newFileName);
				}
			}

			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("alert", "Lỗi khi thêm danh mục: " + e.getMessage());
			req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);
		}
	}
}