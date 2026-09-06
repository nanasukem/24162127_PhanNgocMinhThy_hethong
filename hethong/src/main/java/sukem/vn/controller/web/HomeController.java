package sukem.vn.controller.web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import sukem.vn.dao.ProductDao;
import sukem.vn.dao.impl.ProductDaoImpl;
import sukem.vn.model.User; // Hoặc sukem.vn.model.User tuỳ theo vị trí class User của bạn
import sukem.vn.model.Product;

@WebServlet(urlPatterns = { "/", "/home" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDao = new ProductDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Kiểm tra session xem có tài khoản Admin đang đăng nhập không
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			User account = (User) session.getAttribute("account");
			// Nếu là Admin (roleid == 1), tự động nhảy vào trang quản trị Admin
			if (account.getRoleid() == 1) {
				resp.sendRedirect(req.getContextPath() + "/admin/products");
				return; // Dừng lại, không chạy code hiển thị trang khách bên dưới
			}
		}

		// 2. Nếu chưa đăng nhập hoặc là khách bình thường, nạp 10 bánh mới nhất và hiển
		// thị trang chủ
		List<Product> top10 = productDao.getTop10Recent();
		req.setAttribute("top10Products", top10);

		req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
	}
}