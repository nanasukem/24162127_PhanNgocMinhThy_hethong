package sukem.vn.controller.web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import sukem.vn.dao.ProductDao;
import sukem.vn.dao.impl.ProductDaoImpl;
import sukem.vn.model.Product;

@WebServlet(urlPatterns = {"/","/home" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDao = new ProductDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy đúng 10 sản phẩm mới nhất từ CSDL
		List<Product> top10 = productDao.getTop10Recent();
		req.setAttribute("top10Products", top10);

		// Trỏ đến file view trang chủ (index.jsp hoặc home.jsp)
		req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
	}
}