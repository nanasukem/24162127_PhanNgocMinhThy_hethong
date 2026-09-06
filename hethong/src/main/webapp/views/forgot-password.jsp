<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>

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

.forgot-box {
	width: 420px;
	background: white;
	padding: 40px;
	border-radius: 15px;
	box-shadow: 0px 10px 30px rgba(0, 0, 0, 0.15);
}

.title {
	text-align: center;
	color: #333;
	font-size: 24px;
	font-weight: 600;
	margin-bottom: 15px;
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

.submit-btn {
	width: 100%;
	height: 45px;
	background: #0099dd;
	border: none;
	color: white;
	font-size: 16px;
	border-radius: 25px;
}

.submit-btn:hover {
	background: #007bb8;
}

.back-link {
	text-align: center;
	margin-top: 20px;
	font-size: 14px;
}

.back-link a {
	color: #0099dd;
	text-decoration: none;
	font-weight: 500;
}

.back-link a:hover {
	color: #007bb8;
}
</style>
</head>
<body>
	<div class="forgot-box">
		<div class="title">Quên Mật Khẩu</div>
		<p class="text-center text-muted small mb-4">Nhập email bạn đã
			đăng ký tài khoản. Hệ thống sẽ gửi mã xác thực OTP để đổi mật khẩu
			mới.</p>

		<c:if test="${not empty alert}">
			<div class="alert alert-danger text-center py-2 mb-3"
				style="font-size: 14px;">
				<i class="fa-solid fa-triangle-exclamation"></i> ${alert}
			</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/forgot-password"
			method="post">
			<div class="input-group mb-3">
				<span class="input-group-text"><i
					class="fa-solid fa-envelope"></i></span> <input type="email" name="email"
					class="form-control" placeholder="Nhập email của bạn" required
					autofocus>
			</div>

			<button type="submit" class="btn submit-btn">
				<i class="fa-solid fa-paper-plane"></i> &nbsp; Gửi mã OTP
			</button>
		</form>

		<div class="back-link">
			<a href="${pageContext.request.contextPath}/login"> <i
				class="fa-solid fa-arrow-left"></i> Quay lại Đăng nhập
			</a>
		</div>
	</div>
</body>
</html>