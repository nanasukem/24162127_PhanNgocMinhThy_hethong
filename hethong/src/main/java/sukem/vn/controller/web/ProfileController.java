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
import sukem.vn.constant.Constant;
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
		if (fullname == null || fullname.trim().isEmpty()) {
			req.setAttribute("alert", "Họ và tên không được để trống!");
			req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
			return;
		}

		if (phone == null || !phone.trim().matches("^0\\d{9}$")) {
			req.setAttribute("alert", "Số điện thoại không hợp lệ! Vui lòng nhập đúng 10 chữ số (VD: 0934385567).");
			req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
			return;
		}

		user.setFullName(fullname.trim());
		user.setPhone(phone.trim());

		// --- XỬ LÝ UPLOAD ẢNH AVATAR ---
		try {
			Part part = req.getPart("image");
			if (part != null && part.getSize() > 0) {
				String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

				// Validation định dạng file
				String lowerName = filename.toLowerCase();
				if (!lowerName.endsWith(".jpg") && !lowerName.endsWith(".jpeg") && !lowerName.endsWith(".png")
						&& !lowerName.endsWith(".gif") && !lowerName.endsWith(".webp")) {
					req.setAttribute("alert", "Chỉ chấp nhận file ảnh dạng JPG, PNG, GIF, WEBP!");
					req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
					return;
				}

				// Đổi tên file theo timestamp
				String ext = filename.substring(filename.lastIndexOf("."));
				String avatarFileName = System.currentTimeMillis() + ext;

				// LƯU CỐ ĐỊNH TẠI Constant.UPLOAD_DIRECTORY (Ví dụ: C:/upload)
				File uploadDir = new File(Constant.UPLOAD_DIR);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs();
				}

				// Ghi file vật lý vào thư mục dùng chung với ImageController
				part.write(Constant.UPLOAD_DIR + File.separator + avatarFileName);

				// Gán tên file vào User
				user.setAvatar(avatarFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Cập nhật CSDL
		UserService service = new UserServiceImpl();
		boolean isSuccess = service.update(user);

		if (isSuccess) {
			// Cập nhật lại session ngay lập tức
			session.setAttribute("account", user);
			req.setAttribute("message", "Cập nhật thông tin thành công!");
		} else {
			req.setAttribute("alert", "Cập nhật thất bại, vui lòng thử lại!");
		}

		req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
	}
}