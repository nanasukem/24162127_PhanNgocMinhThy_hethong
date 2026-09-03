package sukem.vn.controller;

import sukem.vn.entity.User;
import sukem.vn.services.UserService;
import sukem.vn.services.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/profile"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class ProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Forward đúng vị trí /views/profile.jsp
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");
        Part filePart = req.getPart("imageFile"); 

        account.setFullName(fullName);
        account.setPhone(phone);

        // Xử lý upload file nếu người dùng chọn ảnh mới
        if (filePart != null && filePart.getSize() > 0) {
            String submittedFileName = extractFileName(filePart);
            String fileName = System.currentTimeMillis() + "_" + submittedFileName;

            // Đường dẫn thư mục uploads chuẩn trong context
            String uploadPath = getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Ghi file ảnh vào Server
            filePart.write(uploadPath + File.separator + fileName);

            // Cập nhật tên avatar mới cho đối tượng User
            account.setAvatar(fileName);
        }

        // Cập nhật thông tin vào CSDL qua JPA
        userService.update(account);

        // Đánh tráo/Cập nhật lại thông tin đối tượng trong Session
        session.setAttribute("account", account);

        // SỬA LỖI 404: Forward đúng địa chỉ /views/profile.jsp
        req.setAttribute("message", "Cập nhật hồ sơ thành công!");
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }

    private String extractFileName(Part part) {
        // Cú pháp lấy tên file an toàn cho Servlet 3.1+ / Jakarta EE
        String submitted = part.getSubmittedFileName();
        if (submitted != null && !submitted.isEmpty()) {
            return Paths.get(submitted).getFileName().toString();
        }
        
        // Fallback đọc header content-disposition
        String contentDisp = part.getHeader("content-disposition");
        for (String s : contentDisp.split(";")) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1).replace("\"", "");
            }
        }
        return "default.png";
    }
}
