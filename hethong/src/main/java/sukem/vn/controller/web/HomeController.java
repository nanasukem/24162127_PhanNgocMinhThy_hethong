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
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			User account = (User) session.getAttribute("account");
			if (account.getRoleid() == 1) {
				resp.sendRedirect(req.getContextPath() + "/admin/products");
				return; 
			}
		}

		List<Product> top10 = productDao.getTop10Recent();
		req.setAttribute("top10Products", top10);

		req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
	}
}