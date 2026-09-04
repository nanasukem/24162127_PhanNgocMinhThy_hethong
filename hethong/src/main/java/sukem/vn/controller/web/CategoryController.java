package sukem.vn.controller.web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import sukem.vn.model.Category;
import sukem.vn.service.CategoryService;
import sukem.vn.service.impl.CategoryServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/admin/category/list")

public class CategoryController extends HttpServlet {

	private CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Category> cateList = cateService.getAll();

		req.setAttribute("cateList", cateList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-category.jsp");

		dispatcher.forward(req, resp);

	}

}