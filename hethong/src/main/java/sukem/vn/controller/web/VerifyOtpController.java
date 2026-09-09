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

@WebServlet(urlPatterns = { "/verify-otp" })
public class VerifyOtpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String otpInput = req.getParameter("otp");
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("verifyEmail");

		System.out.println("=== KIEM TRA XAC THUC OTP ===");
		System.out.println("1. Email lay tu Session: " + email);
		System.out.println("2. OTP form gui len: [" + otpInput + "]");

		if (email == null) {
			System.out.println("=> LOI: Session 'verifyEmail' bi null!");
			resp.sendRedirect(req.getContextPath() + "/register");
			return;
		}

		User user = userService.findByEmail(email);
		System.out.println("3. Tim User: " + (user != null ? "Thay user " + user.getUserName() : "NULL"));

		if (user != null) {
			System.out.println("4. OTP trong DB: [" + user.getCode() + "]");
		}

		if (user != null && otpInput != null && user.getCode() != null
				&& otpInput.trim().equals(user.getCode().trim())) {

			System.out.println("=> KET QUA: Khop OTP thanh cong!");
			user.setStatus(true); 
			user.setCode(null); 
			userService.update(user);

			session.removeAttribute("verifyEmail");
			resp.sendRedirect(req.getContextPath() + "/login?alert=active_success");
		} else {
			System.out.println("=> KET QUA: OTP khong khop!");
			req.setAttribute("alert", "Mã OTP không chính xác. Vui lòng kiểm tra lại email!");
			req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
		}
	}
}