<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập hệ thống</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
body {
	height: 100vh;
	background: linear-gradient(135deg, #0099dd, #00c6ff);
	display: flex;
	justify-content: center;
	align-items: center;
	font-family: "Segoe UI", sans-serif;
}

.login-box {
	width: 420px;
	background: white;
	padding: 40px;
	border-radius: 20px;
	box-shadow: 0px 15px 35px rgba(0, 0, 0, 0.2);
}

.title {
	text-align: center;
	color: #333;
	font-size: 25px;
	font-weight: 600;
	margin-bottom: 30px;
}

.input-group-text {
	background: white;
	border-right: none;
	color: #aaa;
}

.form-control {
	border-left: none;
	height: 45px;
}

.form-control:focus {
	box-shadow: none;
	border-color: #0099dd;
}

.login-btn {
	width: 100%;
	height: 45px;
	background: #0099dd;
	border: none;
	color: white;
	font-size: 16px;
	border-radius: 25px;
}

.login-btn:hover {
	background: #007bb8;
}

.footer-links {
	text-align: center;
	margin-top: 20px;
	color: #888;
	font-size: 14px;
}

.footer-links a {
	color: #0099dd;
	text-decoration: none;
	font-weight: 500;
}

.footer-links a:hover {
	color: #007bb8;
}
</style>
</head>
<body>
	<div class="login-box">
		<div class="title">Đăng Nhập Hệ Thống</div>

		<%-- Hiển thị thông báo lỗi nếu có --%>
		<c:if test="${not empty alert}">
			<div class="alert alert-danger text-center py-2 mb-3">${alert}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text"><i class="fa-solid fa-user"></i></span>
				<input type="text" name="username" class="form-control"
					placeholder="Tài khoản" value="${param.username}" required>
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text"><i class="fa-solid fa-lock"></i></span>
				<input type="password" name="password" class="form-control"
					placeholder="Mật khẩu" required>
			</div>

			<div class="form-check mb-3">
				<input class="form-check-input" type="checkbox" name="remember"
					id="remember"> <label class="form-check-label"
					for="remember"> Ghi nhớ đăng nhập </label>
			</div>

			<button type="submit" class="btn login-btn">
				<i class="fa-solid fa-right-to-bracket"></i> &nbsp; Đăng nhập
			</button>
		</form>

		<div class="footer-links">
			<p class="mb-1">
				Chưa có tài khoản? <a
					href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
			</p>
			<p class="mb-0">
				<a href="${pageContext.request.contextPath}/"><i
					class="fa-solid fa-arrow-left"></i> Quay lại Trang chủ</a>
			</p>
		</div>
	</div>
</body>
</html>