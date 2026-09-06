<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
body {
	min-height: 100vh;
	background: linear-gradient(135deg, #0099dd, #00c6ff);
	display: flex;
	justify-content: center;
	align-items: center;
	font-family: "Segoe UI", sans-serif;
	padding: 20px 0;
}

.login-box {
	width: 420px;
	background: white;
	padding: 35px 40px;
	border-radius: 15px;
	box-shadow: 0px 10px 30px rgba(0, 0, 0, 0.15);
}

.title {
	text-align: center;
	color: #333;
	font-size: 25px;
	font-weight: 600;
	margin-bottom: 25px;
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

.register-btn {
	width: 100%;
	height: 45px;
	background: #0099dd;
	border: none;
	color: white;
	font-size: 16px;
	border-radius: 25px;
}

.register-btn:hover {
	background: #007bb8;
}

.login-link {
	text-align: center;
	margin-top: 20px;
	color: #888;
	font-size: 14px;
}

.login-link a {
	color: #0099dd;
	text-decoration: none;
	font-weight: 500;
}

.login-link a:hover {
	color: #007bb8;
}
</style>
</head>
<body>

	<div class="login-box">
		<div class="title">Tạo Tài Khoản Mới</div>

		<%-- Thông báo lỗi từ Server nếu có --%>
		<c:if test="${not empty alert}">
			<div class="alert alert-danger text-center py-2 mb-3"
				style="font-size: 14px;">
				<i class="fa-solid fa-triangle-exclamation"></i> ${alert}
			</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/register"
			method="post" onsubmit="return validateRegisterForm()">

			<div class="input-group mb-3">
				<span class="input-group-text"><i class="fa-solid fa-user"></i></span>
				<input type="text" name="username" id="username"
					class="form-control" placeholder="Tài khoản (tối thiểu 4 ký tự)"
					value="${param.username}" minlength="4" maxlength="50" required>
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text"><i class="fa-solid fa-id-card"></i></span>
				<input type="text" name="fullname" class="form-control"
					placeholder="Họ và tên" value="${param.fullname}" required>
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text"><i
					class="fa-solid fa-envelope"></i></span> <input type="email" name="email"
					class="form-control" placeholder="Nhập Email"
					value="${param.email}" required>
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text"><i class="fa-solid fa-phone"></i></span>
				<input type="tel" name="phone" class="form-control"
					placeholder="Số điện thoại (10 chữ số)" pattern="^0[0-9]{9}$"
					title="Số điện thoại phải gồm 10 chữ số và bắt đầu bằng số 0"
					value="${param.phone}" required>
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text"><i class="fa-solid fa-lock"></i></span>
				<input type="password" id="password" name="password"
					class="form-control" placeholder="Mật khẩu (tối thiểu 6 ký tự)"
					minlength="6" required>
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text"><i class="fa-solid fa-shield"></i></span>
				<input type="password" id="confirmPassword" class="form-control"
					placeholder="Nhập lại mật khẩu" minlength="6" required>
			</div>

			<button type="submit" class="btn register-btn">
				<i class="fa-solid fa-user-plus"></i> &nbsp; Tạo tài khoản
			</button>
		</form>

		<div class="login-link">
			<p class="mb-1">
				Bạn đã có tài khoản? <a
					href="${pageContext.request.contextPath}/login">Đăng nhập</a>
			</p>
			<p class="mb-0">
				<a href="${pageContext.request.contextPath}/"><i
					class="fa-solid fa-arrow-left"></i> Quay lại Trang chủ</a>
			</p>
		</div>
	</div>

	<script>
		function validateRegisterForm() {
			let u = document.getElementById("username").value.trim();
			let p = document.getElementById("password").value;
			let cp = document.getElementById("confirmPassword").value;

			if (u.length < 4) {
				alert("Tên tài khoản phải có ít nhất 4 ký tự!");
				return false;
			}
			if (p.length < 6) {
				alert("Mật khẩu phải có ít nhất 6 ký tự!");
				return false;
			}
			if (p !== cp) {
				alert("Mật khẩu xác nhận không khớp! Vui lòng kiểm tra lại.");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>