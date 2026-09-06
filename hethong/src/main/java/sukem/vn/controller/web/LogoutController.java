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

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. Xóa Session
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.removeAttribute("account");
			session.invalidate(); // Hủy session để giải phóng bộ nhớ
		}

		// 2. BỔ SUNG: Xóa Cookie Remember Me (để không bị auto-login lại)
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
					cookie.setMaxAge(0); // Đặt thời gian sống = 0 để xóa cookie khỏi browser
					cookie.setPath("/");
					resp.addCookie(cookie);
					break;
				}
			}
		}

		// 3. Chuyển hướng về trang đăng nhập
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}