package sukem.vn.controller.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;

import sukem.vn.constant.Constant;
import sukem.vn.model.Category;
import sukem.vn.service.CategoryService;
import sukem.vn.service.impl.CategoryServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/category/add" })

public class CategoryAddController extends HttpServlet {

	private CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		Category category = new Category();

		try {

			DiskFileItemFactory factory = DiskFileItemFactory.builder().get();

			JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload(factory);

			upload.setHeaderCharset(java.nio.charset.StandardCharsets.UTF_8);

			List<DiskFileItem> items = upload.parseRequest(req);

			for (FileItem item : items) {

				if (item.isFormField()) {

					if (item.getFieldName().equals("catename")) {

						category.setCatename(item.getString(java.nio.charset.StandardCharsets.UTF_8));

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

			cateService.insert(category);

			resp.sendRedirect(req.getContextPath() + "/admin/category/list");

		} catch (FileUploadException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}