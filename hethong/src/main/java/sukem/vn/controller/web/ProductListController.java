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
import sukem.vn.model.Product; // Dùng entity Product

@WebServlet(urlPatterns = { "/product" })
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDao = new ProductDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int pageSize = 6; // Yêu cầu 6 sp / trang

		String pageParam = req.getParameter("page");
		if (pageParam != null && !pageParam.trim().isEmpty()) {
			try {
				page = Integer.parseInt(pageParam);
			} catch (NumberFormatException ignored) {
				page = 1;
			}
		}

		int totalCount = productDao.countTotal();
		int endPage = (int) Math.ceil((double) totalCount / pageSize);

		List<Product> list = productDao.findWithPaging(page, pageSize);

		// Đổi tên đúng khớp 100% với JSP
		req.setAttribute("products", list);
		req.setAttribute("currentPage", page);
		req.setAttribute("endPage", endPage);

		// Nếu file JSP của bạn nằm ở thư mục views/web thì đổi thành
		// /views/web/product-list.jsp
		req.getRequestDispatcher("/views/product-list.jsp").forward(req, resp);
	}
}