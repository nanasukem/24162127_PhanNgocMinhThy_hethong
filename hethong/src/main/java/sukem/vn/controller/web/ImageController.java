package sukem.vn.controller.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sukem.vn.constant.Constant;

@WebServlet(urlPatterns = "/image")
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fname");

		if (fileName == null || fileName.trim().isEmpty()) {
			resp.sendRedirect("https://cdn-icons-png.flaticon.com/512/149/149071.png");
			return;
		}

		if (fileName.startsWith("http://") || fileName.startsWith("https://")) {
			resp.sendRedirect(fileName);
			return;
		}

		File file = new File(Constant.UPLOAD_DIR, fileName);

		if (!file.exists()) {
			resp.sendRedirect("https://cdn-icons-png.flaticon.com/512/149/149071.png");
			return;
		}

		String mimeType = getServletContext().getMimeType(file.getName());
		if (mimeType == null) {
			mimeType = "image/jpeg";
		}
		resp.setContentType(mimeType);
		resp.setContentLengthLong(file.length());

		try (FileInputStream in = new FileInputStream(file); OutputStream out = resp.getOutputStream()) {
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
		}
	}
}