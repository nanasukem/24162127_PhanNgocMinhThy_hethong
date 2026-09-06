package sukem.vn.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import sukem.vn.email.EmailService;
import sukem.vn.model.User;
import sukem.vn.service.UserService;
import sukem.vn.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/forgot-password" })
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");

		User user = userService.findByEmail(email);

		if (user == null) {
			req.setAttribute("alert", "Email này không tồn tại trong hệ thống!");
			req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
			return;
		}

		// Sinh OTP mới
		String otp = EmailService.generateOtp();
		user.setCode(otp);
		userService.update(user);

		// Gửi thư OTP
		String emailContent = "<h3>Yêu cầu lấy lại mật khẩu</h3>" + "<p>Mã xác thực OTP để đổi mật khẩu của bạn là:</p>"
				+ "<h2 style='color: #dc3545; letter-spacing: 3px;'>" + otp + "</h2>"
				+ "<p>Tuyệt đối không chia sẻ mã này cho bất kỳ ai.</p>";
		EmailService.sendEmail(email, "Mã OTP đặt lại mật khẩu", emailContent);

		// Ghi nhận email vào session và chuyển sang trang đổi pass
		req.getSession().setAttribute("resetEmail", email);
		resp.sendRedirect(req.getContextPath() + "/reset-password");
	}
}