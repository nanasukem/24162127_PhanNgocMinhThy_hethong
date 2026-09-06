package sukem.vn.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import sukem.vn.dao.ProductDao;
import sukem.vn.dao.impl.ProductDaoImpl;
import sukem.vn.model.Product; // Sửa dòng này

@WebServlet(urlPatterns = { "/product/detail" })
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDao = new ProductDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		if (idStr != null) {
			int id = Integer.parseInt(idStr);
			Product product = productDao.findById(id);
			req.setAttribute("product", product);
			req.getRequestDispatcher("/views/product-detail.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/product");
	}
}