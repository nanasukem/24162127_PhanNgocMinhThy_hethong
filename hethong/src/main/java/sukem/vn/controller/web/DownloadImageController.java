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

@WebServlet(urlPatterns = { "/image" })
public class DownloadImageController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fname");
		if (fileName == null || fileName.trim().isEmpty()) {
			return;
		}

		// Lấy đường dẫn thư mục uploads trên Tomcat Server
		String uploadPath = getServletContext().getRealPath("/uploads");
		File file = new File(uploadPath + File.separator + fileName);

		if (file.exists()) {
			// Thiết lập kiểu trả về là hình ảnh
			String mimeType = getServletContext().getMimeType(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			resp.setContentType(mimeType);
			resp.setContentLength((int) file.length());

			// Đọc file và ghi ra Response Stream
			try (FileInputStream inStream = new FileInputStream(file);
					OutputStream outStream = resp.getOutputStream()) {
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				while ((bytesRead = inStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
			}
		}
	}
}