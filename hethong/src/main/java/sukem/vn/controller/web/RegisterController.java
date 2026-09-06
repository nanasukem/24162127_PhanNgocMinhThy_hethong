package sukem.vn.controller.web;

import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import sukem.vn.email.EmailService;
import sukem.vn.model.User;
import sukem.vn.service.UserService;
import sukem.vn.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// Lấy đúng tham số theo name trong register.jsp
		String username = req.getParameter("username");
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");

		// 1. Kiểm tra username đã tồn tại chưa
		if (userService.checkExistUsername(username)) {
			req.setAttribute("alert", "Tên tài khoản này đã được sử dụng!");
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
			return;
		}

		// 2. Kiểm tra email đã tồn tại chưa
		if (userService.checkExistEmail(email)) {
			req.setAttribute("alert", "Email này đã được sử dụng!");
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
			return;
		}

		// 3. Tạo mã OTP ngẫu nhiên 6 chữ số
		String otp = EmailService.generateOtp();

		// 4. Tạo đối tượng User với trạng thái chưa kích hoạt (status = false)
		User user = new User();
		user.setUserName(username);
		user.setFullName(fullname);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassWord(password);
		user.setRoleid(5); // Quyền người dùng mặc định
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setStatus(false); // Chưa kích hoạt
		user.setCode(otp); // Lưu OTP vào DB để đối chiếu

		userService.insert(user);

		// 5. Gửi email chứa mã OTP kích hoạt
		String subject = "Mã xác thực kích hoạt tài khoản";
		String body = "<h3>Xin chào " + fullname + ",</h3>"
				+ "<p>Cảm ơn bạn đã đăng ký tài khoản hệ thống. Mã OTP kích hoạt của bạn là:</p>"
				+ "<h2 style='color: #0099dd; letter-spacing: 4px;'>" + otp + "</h2>"
				+ "<p>Vui lòng không chia sẻ mã xác thực này cho người khác.</p>";
		EmailService.sendEmail(email, subject, body);

		// 6. Lưu email vào Session để trang verify-otp biết cần xác thực user nào
		req.getSession().setAttribute("verifyEmail", email);

		// 7. Chuyển sang trang nhập OTP
		resp.sendRedirect(req.getContextPath() + "/verify-otp");
	}
}