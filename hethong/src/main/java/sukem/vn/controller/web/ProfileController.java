package sukem.vn.controller.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import sukem.vn.model.User;
import sukem.vn.service.UserService;
import sukem.vn.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ProfileController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("account") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("account") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		User user = (User) session.getAttribute("account");

		// Sửa lại cho đúng name="fullName" bên JSP
		String fullname = req.getParameter("fullName");
		String phone = req.getParameter("phone");

		user.setFullName(fullname);
		user.setPhone(phone);

		// Xử lý Upload Ảnh Avatar
		try {
			Part part = req.getPart("image");
			if (part != null && part.getSize() > 0) {
				String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

				// Đổi tên file theo time millis để không trùng tên
				String ext = filename.substring(filename.lastIndexOf("."));
				String avatarFileName = System.currentTimeMillis() + ext;

				// Đường dẫn lưu file vào thư mục webapp/uploads
				String uploadPath = getServletContext().getRealPath("/uploads");
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs(); // Tự tạo thư mục nếu chưa có
				}

				// Lưu file vật lý
				part.write(uploadPath + File.separator + avatarFileName);

				// Gán tên file vào User
				user.setAvatar(avatarFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Cập nhật vào CSDL
		UserService service = new UserServiceImpl();
		boolean isSuccess = service.update(user);

		if (isSuccess) {
			session.setAttribute("account", user); // Cập nhật thông tin mới vào session
			req.setAttribute("message", "Cập nhật thông tin thành công!");
		} else {
			req.setAttribute("alert", "Cập nhật thất bại, vui lòng thử lại!");
		}

		req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
	}
}