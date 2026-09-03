<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Trang Chủ</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

    <div class="container" style="margin-top: 50px;">
        <div class="jumbotron text-center">
            <h2>Xin chào, ${sessionScope.account.userName}!</h2>
            <p class="text-success">Chào mừng bạn đã đăng nhập thành công vào hệ thống.</p>
            <hr>
            
            <div class="text-left" style="max-width: 400px; margin: 0 auto; margin-bottom: 20px;">
                <p><strong>Họ và tên:</strong> ${sessionScope.account.fullName}</p>
                <p><strong>Tài khoản:</strong> ${sessionScope.account.userName}</p>
                <p><strong>Email:</strong> ${sessionScope.account.email}</p>
                <p><strong>Số điện thoại:</strong> ${sessionScope.account.phone}</p>
            </div>

            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Đăng Xuất</a>
        </div>
    </div>

</body>
</html>
