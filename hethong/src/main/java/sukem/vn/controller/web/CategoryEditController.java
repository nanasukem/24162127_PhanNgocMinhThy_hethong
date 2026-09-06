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
@WebServlet(urlPatterns = { "/admin/category/edit" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class CategoryEditController extends HttpServlet {

	private CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id != null && !id.trim().isEmpty()) {
			Category category = cateService.get(Integer.parseInt(id));
			req.setAttribute("category", category);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		try {
			// 1. Nhận dữ liệu ID từ form
			String idStr = req.getParameter("categoryid");
			if (idStr == null || idStr.trim().isEmpty()) {
				idStr = req.getParameter("cateid");
			}
			int id = Integer.parseInt(idStr);

			// 2. Nhận tên danh mục
			String categoryName = req.getParameter("categoryname");
			if (categoryName == null || categoryName.trim().isEmpty()) {
				categoryName = req.getParameter("catename");
			}

			// 3. Lấy đối tượng cũ từ CSDL dùng đúng kiểu sukem.vn.entity.Category
			Category category = cateService.get(id);
			if (category != null) {
				category.setCategoryname(categoryName);

				// 4. Nhận file ảnh qua Jakarta Part
				Part part = req.getPart("images");
				if (part == null) {
					part = req.getPart("icon");
				}

				// Nếu người dùng chọn tải ảnh mới lên
				if (part != null && part.getSize() > 0) {
					String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

					if (!originalFileName.isEmpty()) {
						int index = originalFileName.lastIndexOf(".");
						String ext = (index > 0) ? originalFileName.substring(index) : ".png";
						String newFileName = System.currentTimeMillis() + ext;

						// Lưu vào thư mục C:/upload/category
						File uploadFolder = new File(Constant.UPLOAD_DIR + File.separator + "category");
						if (!uploadFolder.exists()) {
							uploadFolder.mkdirs();
						}

						File fileSave = new File(uploadFolder, newFileName);
						part.write(fileSave.getAbsolutePath());

						// Gán đường dẫn ảnh mới
						category.setImages("category/" + newFileName);
					}
				}
				// Nếu không chọn ảnh mới thì giữ nguyên ảnh cũ

				cateService.edit(category);
			}

			// Sửa dòng redirect cuối cùng:
			resp.sendRedirect(req.getContextPath() + "/admin/products");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("alert", "Lỗi khi cập nhật danh mục: " + e.getMessage());
			doGet(req, resp);
		}
	}
}