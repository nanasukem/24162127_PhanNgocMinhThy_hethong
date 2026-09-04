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
	border-radius: 10px;
	box-shadow: 0px 10px 30px rgba(0, 0, 0, 0.12);
}

.title {
	text-align: center;
	color: #8c8c8c;
	font-size: 25px;
	margin-bottom: 35px;
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
}

.register-btn:hover {
	background: #007bb8;
}

.login-link {
	text-align: center;
	margin-top: 25px;
	color: #888;
}

.login-link a {
	color: #0099dd;
	text-decoration: none;
}

.login-link a:hover {
	color: #007bb8;
}
</style>
</head>
<body>

	<div class="login-box">
		<div class="title">Tạo Tài Khoản Mới</div>
		<c:if test="${alert != null}">
			<div class="alert alert-danger text-center">${alert}</div>
		</c:if>
		<form action="${pageContext.request.contextPath}/register"
			method="post" onsubmit="return checkPassword()">
			<div class="input-group mb-3">
				<span class="input-group-text"> <i class="fa-solid fa-user"></i>
				</span> <input type="text" name="username" class="form-control"
					placeholder="Tài khoản" required>
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text"> <i
					class="fa-solid fa-id-card"></i>
				</span> <input type="text" name="fullname" class="form-control"
					placeholder="Họ tên" required>
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text"> <i
					class="fa-solid fa-envelope"></i>
				</span> <input type="email" name="email" class="form-control"
					placeholder="Nhập Email" required>
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text"> <i class="fa-solid fa-phone"></i>
				</span> <input type="text" name="phone" class="form-control"
					placeholder="Số điện thoại" required>
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text"> <i class="fa-solid fa-lock"></i>
				</span> <input type="password" id="password" name="password"
					class="form-control" placeholder="Mật khẩu" required>
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text"> <i class="fa-solid fa-lock"></i>
				</span> <input type="password" id="confirmPassword" class="form-control"
					placeholder="Nhập lại mật khẩu" required>
			</div>
			<button type="submit" class="btn register-btn">
				<i class="fa-solid fa-user-plus"></i> &nbsp; Tạo tài khoản
			</button>
		</form>
		<div class="login-link">
			Bạn đã có tài khoản? <a
				href="${pageContext.request.contextPath}/login"> Đăng nhập </a>
		</div>
	</div>
	<script>
		function checkPassword() {
			let password = document.getElementById("password").value;
			let confirmPassword = document.getElementById("confirmPassword").value;
			if (password !== confirmPassword) {
				alert("Mật khẩu nhập lại không đúng!");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>