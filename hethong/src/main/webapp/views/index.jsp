<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>

<!-- Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Font Awesome Icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
body {
	min-height: 100vh;
	background: linear-gradient(135deg, #0099dd, #00c6ff) !important;
	font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 0;
}

/* Khung nội dung trung tâm */
.welcome-box {
	width: 420px;
	background: #ffffff;
	padding: 40px 30px;
	text-align: center;
	border-radius: 20px;
	box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
	transition: transform 0.3s ease;
}

.welcome-box:hover {
	transform: translateY(-5px);
}

.logo-icon {
	font-size: 65px;
	color: #0099dd;
	margin-bottom: 15px;
}

h1 {
	color: #2c3e50;
	font-size: 24px;
	font-weight: 700;
	margin-bottom: 8px;
}

.sub-text {
	color: #6c757d;
	font-size: 15px;
	margin-bottom: 25px;
}

.custom-btn {
	width: 100%;
	height: 48px;
	background: linear-gradient(to right, #0099dd, #007bb8);
	border: none;
	color: #ffffff;
	font-size: 16px;
	font-weight: 600;
	border-radius: 25px;
	box-shadow: 0 5px 15px rgba(0, 153, 221, 0.4);
	transition: all 0.3s ease;
	text-decoration: none;
	display: inline-flex;
	align-items: center;
	justify-content: center;
	gap: 8px;
}

.custom-btn:hover {
	background: linear-gradient(to right, #007bb8, #005682);
	box-shadow: 0 8px 20px rgba(0, 153, 221, 0.6);
	color: #fff;
	transform: scale(1.02);
}
</style>
</head>
<body>

	<div class="welcome-box">
		<!-- Icon chính -->
		<div class="logo-icon">
			<i class="fa-solid fa-building"></i>
		</div>

		<!-- Tên tiêu đề chào mừng -->
		<h1>
			Chào mừng bạn
			<c:if test="${not empty sessionScope.account}">
				<span class="text-primary">${sessionScope.account.fullName}</span>
			</c:if>
		</h1>

		<p class="sub-text">Hệ thống quản lý doanh nghiệp</p>

		<!-- ĐÃ ĐĂNG NHẬP: Hiện nút Chỉnh sửa thông tin cá nhân -->
		<c:if test="${not empty sessionScope.account}">
			<a href="${pageContext.request.contextPath}/profile"
				class="custom-btn"> <i class="fa-solid fa-user-pen"></i> Chỉnh
				sửa thông tin cá nhân
			</a>
		</c:if>

		<!-- CHƯA ĐĂNG NHẬP: Hiện nút Đăng nhập -->
		<c:if test="${empty sessionScope.account}">
			<a href="${pageContext.request.contextPath}/login" class="custom-btn">
				<i class="fa-solid fa-right-to-bracket"></i> Đăng nhập
			</a>
		</c:if>
	</div>

</body>
</html>