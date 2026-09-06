<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đặt lại mật khẩu</title>

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

.reset-box {
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
	margin-bottom: 20px;
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
</style>
</head>
<body>
	<div class="reset-box">
		<div class="title">Đặt Lại Mật Khẩu</div>

		<c:if test="${not empty alert}">
			<div class="alert alert-danger text-center py-2 mb-3"
				style="font-size: 14px;">
				<i class="fa-solid fa-triangle-exclamation"></i> ${alert}
			</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/reset-password"
			method="post" onsubmit="return checkPassword()">

			<div class="input-group mb-3">
				<span class="input-group-text"><i class="fa-solid fa-key"></i></span>
				<input type="text" name="otp" class="form-control"
					placeholder="Nhập 6 số OTP từ Email" maxlength="6" required
					autofocus>
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text"><i class="fa-solid fa-lock"></i></span>
				<input type="password" id="newPassword" name="newPassword"
					class="form-control" placeholder="Mật khẩu mới" required>
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text"><i class="fa-solid fa-shield"></i></span>
				<input type="password" id="confirmPassword" name="confirmPassword"
					class="form-control" placeholder="Xác nhận mật khẩu mới" required>
			</div>

			<button type="submit" class="btn submit-btn">
				<i class="fa-solid fa-check-double"></i> &nbsp; Cập nhật mật khẩu
			</button>
		</form>
	</div>

	<script>
		function checkPassword() {
			let p1 = document.getElementById("newPassword").value;
			let p2 = document.getElementById("confirmPassword").value;
			if (p1 !== p2) {
				alert("Mật khẩu xác nhận không trùng khớp!");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>