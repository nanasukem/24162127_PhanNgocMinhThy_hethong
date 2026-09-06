package sukem.vn.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import sukem.vn.model.User;
import sukem.vn.service.UserService;
import sukem.vn.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/reset-password" })
public class ResetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String inputOtp = req.getParameter("otp");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");

		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("resetEmail");

		if (email == null) {
			resp.sendRedirect(req.getContextPath() + "/forgot-password");
			return;
		}

		// Kiểm tra mật khẩu xác nhận
		if (!newPassword.equals(confirmPassword)) {
			req.setAttribute("alert", "Mật khẩu xác nhận không trùng khớp!");
			req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
			return;
		}

		User user = userService.findByEmail(email);

		// Kiểm tra OTP
		if (user != null && inputOtp != null && inputOtp.trim().equals(user.getCode())) {
			user.setPassWord(newPassword);
			user.setCode(null); // Xóa OTP
			userService.update(user);

			session.removeAttribute("resetEmail");
			resp.sendRedirect(req.getContextPath() + "/login?alert=reset_success");
		} else {
			req.setAttribute("alert", "Mã OTP không chính xác hoặc đã hết hiệu lực!");
			req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
		}
	}
}