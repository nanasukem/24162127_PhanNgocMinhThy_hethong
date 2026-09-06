<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xác thực mã OTP</title>

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

.otp-box {
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

.otp-input {
	height: 50px;
	font-size: 24px;
	letter-spacing: 8px;
	text-align: center;
	font-weight: bold;
	border: 1px solid #ced4da;
}

.otp-input:focus {
	border-color: #0099dd;
	box-shadow: none;
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

.links-group a {
	color: #0099dd;
	text-decoration: none;
	font-weight: 500;
}

.links-group a:hover {
	color: #007bb8;
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="otp-box">
		<div class="title">Xác Thực OTP</div>
		<p class="text-center text-muted small mb-4">Mã kích hoạt gồm 6
			chữ số đã được gửi tới email của bạn. Vui lòng kiểm tra hộp thư (cả
			mục Spam/Rác).</p>

		<%-- Thông báo gửi lại mã thành công --%>
		<c:if test="${not empty message}">
			<div class="alert alert-success text-center py-2 mb-3"
				style="font-size: 14px;">
				<i class="fa-solid fa-circle-check"></i> ${message}
			</div>
		</c:if>

		<%-- Thông báo lỗi nếu có --%>
		<c:if test="${not empty alert}">
			<div class="alert alert-danger text-center py-2 mb-3"
				style="font-size: 14px;">
				<i class="fa-solid fa-triangle-exclamation"></i> ${alert}
			</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/verify-otp"
			method="post">
			<div class="mb-4">
				<input type="text" name="otp" class="form-control otp-input"
					placeholder="------" maxlength="6" required autofocus>
			</div>

			<button type="submit" class="btn submit-btn">
				<i class="fa-solid fa-check"></i> &nbsp; Kích hoạt tài khoản
			</button>
		</form>

		<div class="links-group text-center mt-3 small">
			<p class="mb-2 text-muted">
				Chưa nhận được mã? <a
					href="${pageContext.request.contextPath}/resend-otp"> <i
					class="fa-solid fa-rotate-right"></i> Gửi lại mã OTP
				</a>
			</p>
			<div>
				<a href="${pageContext.request.contextPath}/login" class="me-3">
					<i class="fa-solid fa-right-to-bracket"></i> Đăng nhập
				</a> <a href="${pageContext.request.contextPath}/register"> <i
					class="fa-solid fa-arrow-left"></i> Đăng ký lại
				</a>
			</div>
		</div>
	</div>
</body>
</html>