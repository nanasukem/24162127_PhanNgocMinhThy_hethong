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

		String fullname = req.getParameter("fullName");
		String phone = req.getParameter("phone");

		// --- SERVER-SIDE VALIDATION ---
		// 1. Kiểm tra Họ và Tên không được để trống
		if (fullname == null || fullname.trim().isEmpty()) {
			req.setAttribute("alert", "Họ và tên không được để trống!");
			req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
			return;
		}

		// 2. Kiểm tra Số điện thoại (Bắt buộc đúng 10 số, bắt đầu bằng 0)
		if (phone == null || !phone.trim().matches("^0\\d{9}$")) {
			req.setAttribute("alert", "Số điện thoại không hợp lệ! Vui lòng nhập đúng 10 chữ số (VD: 0934385567).");
			req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
			return;
		}

		user.setFullName(fullname.trim());
		user.setPhone(phone.trim());

		// --- XỬ LÝ UPLOAD ÁNH AVATAR ---
		try {
			Part part = req.getPart("image");
			if (part != null && part.getSize() > 0) {
				String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

				// Validation định dạng file phía Server
				String lowerName = filename.toLowerCase();
				if (!lowerName.endsWith(".jpg") && !lowerName.endsWith(".jpeg") && !lowerName.endsWith(".png")
						&& !lowerName.endsWith(".gif")) {
					req.setAttribute("alert", "Chỉ chấp nhận file ảnh dạng JPG, PNG, GIF!");
					req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
					return;
				}

				// Đổi tên file theo time millis để không trùng
				String ext = filename.substring(filename.lastIndexOf("."));
				String avatarFileName = System.currentTimeMillis() + ext;

				// Đường dẫn lưu file
				String uploadPath = getServletContext().getRealPath("/uploads");
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs();
				}

				// Lưu file vật lý
				part.write(uploadPath + File.separator + avatarFileName);

				// Gán tên file vào User
				user.setAvatar(avatarFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Cập nhật vào CSDL qua JPA
		UserService service = new UserServiceImpl();
		boolean isSuccess = service.update(user);

		if (isSuccess) {
			session.setAttribute("account", user);
			req.setAttribute("message", "Cập nhật thông tin thành công!");
		} else {
			req.setAttribute("alert", "Cập nhật thất bại, vui lòng thử lại!");
		}

		req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
	}
}