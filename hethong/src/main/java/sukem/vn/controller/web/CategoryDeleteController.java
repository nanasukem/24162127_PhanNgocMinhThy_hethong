package sukem.vn.controller.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import sukem.vn.service.CategoryService;
import sukem.vn.service.impl.CategoryServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/admin/category/delete")

public class CategoryDeleteController extends HttpServlet {

	private CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		cateService.delete(Integer.parseInt(id));

		resp.sendRedirect(req.getContextPath() + "/admin/category/list");

	}

}