package sukem.vn.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import sukem.vn.email.EmailService;
import sukem.vn.model.User;
import sukem.vn.service.UserService;
import sukem.vn.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/resend-otp" })
public class ResendOtpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String email = (session != null) ? (String) session.getAttribute("verifyEmail") : null;

		if (email == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		User user = userService.findByEmail(email);
		if (user != null) {
			// Sinh mã OTP mới
			String newOtp = EmailService.generateOtp();
			user.setCode(newOtp);
			userService.update(user);

			// Gửi lại email
			String content = "<h3>Yêu cầu gửi lại mã kích hoạt</h3>"
					+ "<p>Mã OTP kích hoạt mới của bạn là: <b style='color:#0099dd; font-size:22px;'>" + newOtp
					+ "</b></p>";
			EmailService.sendEmail(email, "Gửi lại mã kích hoạt tài khoản", content);

			req.setAttribute("message", "Mã OTP mới đã được gửi lại vào email của bạn!");
		}

		req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
	}
}