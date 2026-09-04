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
		// 1. Nếu đã đăng nhập rồi -> chuyển thẳng sang trang Home
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/home");
			return;
		}

		// 2. Kiểm tra Cookie Remember Me
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
					String username = cookie.getValue();
					UserService service = new UserServiceImpl();
					User user = service.get(username);
					if (user != null) {
						session = req.getSession(true);
						session.setAttribute("account", user);
						resp.sendRedirect(req.getContextPath() + "/home");
						return;
					}
				}
			}
		}

		// 3. Chưa đăng nhập -> Hiện trang login.jsp
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
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);

			if (isRememberMe) {
				saveRememberMe(resp, username);
			}

			// Đăng nhập thành công -> Chuyển hướng sang trang Home
			resp.sendRedirect(req.getContextPath() + "/home");
		} else {
			req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
	}

	private void saveRememberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}