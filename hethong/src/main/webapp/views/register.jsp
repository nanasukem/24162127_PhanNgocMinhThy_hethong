<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5" style="max-width: 450px;">
    <form action="${pageContext.request.contextPath}/register" method="post">
        <h2>Tạo tài khoản mới</h2>
        
        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>

        <section class="form-group">
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon input-group-text"><i class="fa fa-user"></i></span>
                    <input type="text" placeholder="Tài khoản" name="username" class="form-control" required>
                </div>
            </label>
        </section>

        <section class="form-group">
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon input-group-text"><i class="fa fa-id-card"></i></span>
                    <input type="text" placeholder="Họ tên" name="fullname" class="form-control" required>
                </div>
            </label>
        </section>

        <section class="form-group">
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon input-group-text"><i class="fa fa-envelope"></i></span>
                    <input type="email" placeholder="Nhập Email" name="email" class="form-control" required>
                </div>
            </label>
        </section>

        <section class="form-group">
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon input-group-text"><i class="fa fa-phone"></i></span>
                    <input type="text" placeholder="Số điện thoại" name="phone" class="form-control">
                </div>
            </label>
        </section>

        <section class="form-group">
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon input-group-text"><i class="fa fa-lock"></i></span>
                    <input type="password" placeholder="Mật khẩu" name="password" class="form-control" required>
                </div>
            </label>
        </section>

        <section class="form-group">
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon input-group-text"><i class="fa fa-lock"></i></span>
                    <input type="password" placeholder="Nhập lại mật khẩu" name="repassword" class="form-control" required>
                </div>
            </label>
        </section>

        <button type="submit" class="btn btn-primary btn-block">Tạo tài khoản</button>

        <div class="mt-3 text-center">
            <span>Nếu bạn đã có tài khoản? </span>
            <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
        </div>
    </form>
</div>

</body>
</html>