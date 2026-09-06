package sukem.vn.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import sukem.vn.constant.Constant;
import sukem.vn.model.User;
import sukem.vn.service.UserService;
import sukem.vn.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Kiểm tra thông báo từ các trang khác chuyển về
		String alertParam = req.getParameter("alert");
		if ("active_success".equals(alertParam)) {
			req.setAttribute("message", "Tài khoản đã được kích hoạt thành công! Vui lòng đăng nhập.");
		} else if ("reset_success".equals(alertParam)) {
			req.setAttribute("message", "Đổi mật khẩu thành công! Vui lòng đăng nhập lại với mật khẩu mới.");
		}

		// 2. Nếu đã đăng nhập rồi -> phân quyền chuyển trang
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			User currentUser = (User) session.getAttribute("account");
			redirectByRole(req, resp, currentUser);
			return;
		}

		// 3. Kiểm tra Cookie Remember Me
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
					String username = cookie.getValue();
					UserService service = new UserServiceImpl();
					User user = service.get(username);
					// Kiểm tra tồn tại và bắt buộc đã kích hoạt status
					if (user != null && user.isStatus()) {
						session = req.getSession(true);
						session.setAttribute("account", user);
						redirectByRole(req, resp, user);
						return;
					}
				}
			}
		}

		// 4. Chưa đăng nhập -> Hiện trang login.jsp
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		boolean isRememberMe = "on".equals(remember);

		if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
			req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
			return;
		}

		UserService service = new UserServiceImpl();
		User user = service.login(username, password);

		if (user != null) {
			// KIỂM TRA TRẠNG THÁI KÍCH HOẠT
			if (!user.isStatus()) {
				HttpSession session = req.getSession(true);
				session.setAttribute("verifyEmail", user.getEmail());

				req.setAttribute("notActivated", true);
				req.setAttribute("alert", "Tài khoản chưa được kích hoạt OTP qua Email!");
				req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
				return;
			}

			// Lưu Session khi đã kích hoạt
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);

			if (isRememberMe) {
				saveRememberMe(resp, username);
			}

			// ĐĂNG NHẬP THÀNH CÔNG -> Phân quyền Admin và Khách
			redirectByRole(req, resp, user);

		} else {
			req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
	}

	/**
	 * Phân quyền điều hướng: Admin vào /admin/products, Khách hàng vào /home
	 */
	private void redirectByRole(HttpServletRequest req, HttpServletResponse resp, User user) throws IOException {
		// Kiểm tra roleid = 1 (hoặc user.getRoleid() == 1 tuỳ hàm getter trong model
		// User)
		int roleId = user.getRoleid();
		if (roleId == 1) {
			resp.sendRedirect(req.getContextPath() + "/admin/products");
		} else {
			resp.sendRedirect(req.getContextPath() + "/home");
		}
	}

	private void saveRememberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60); // 30 phút
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}