package sukem.vn.controller.web;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import sukem.vn.constant.Constant;
import sukem.vn.model.Category;
import sukem.vn.service.CategoryService;
import sukem.vn.service.impl.CategoryServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {

	private CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		Category category = cateService.get(Integer.parseInt(id));

		req.setAttribute("category", category);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Category category = new Category();

		Category oldCategory = null;

		DiskFileItemFactory factory = DiskFileItemFactory.builder().get();

		JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

		upload.setHeaderCharset(StandardCharsets.UTF_8);

		try {

			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			List<FileItem> items = upload.parseRequest(req);

			for (FileItem item : items) {

				if (item.isFormField()) {

					if (item.getFieldName().equals("cateid")) {

						int id = Integer.parseInt(item.getString());

						category.setCateid(id);

						oldCategory = cateService.get(id);

					}

					else if (item.getFieldName().equals("catename")) {

						category.setCatename(item.getString(StandardCharsets.UTF_8));

					}

				}

				else {

					if (item.getFieldName().equals("icon")) {

						if (item.getSize() > 0) {

							String fileName = new File(item.getName()).getName();

							int index = fileName.lastIndexOf(".");

							String ext = fileName.substring(index);

							String newFileName = System.currentTimeMillis() + ext;

							File folder = new File(Constant.DIR + "/category");

							if (!folder.exists()) {

								folder.mkdirs();

							}

							File file = new File(folder, newFileName);

							item.write(file.toPath());

							category.setIcon("category/" + newFileName);

						}

					}

				}

			}

			if (category.getIcon() == null && oldCategory != null) {

				category.setIcon(oldCategory.getIcon());

			}

			cateService.edit(category);

			resp.sendRedirect(req.getContextPath() + "/admin/category/list");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}