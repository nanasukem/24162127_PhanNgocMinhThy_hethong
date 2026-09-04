<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5" style="max-width: 500px;">
		<h3 class="mb-4 text-center">CẬP NHẬT THÔNG TIN CÁ NHÂN</h3>

		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
		<c:if test="${not empty alert}">
			<div class="alert alert-danger">${alert}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/profile"
			method="post" enctype="multipart/form-data">

			<!-- Hiển thị Avatar -->
			<div class="text-center mb-3">
				<c:choose>
					<%-- Nếu user đã có avatar --%>
					<c:when test="${not empty sessionScope.account.avatar}">
						<img
							src="${pageContext.request.contextPath}/image?fname=${sessionScope.account.avatar}"
							class="rounded-circle border"
							style="width: 120px; height: 120px; object-fit: cover;"
							alt="Avatar">
					</c:when>
					<%-- Nếu chưa có avatar -> Hiển thị ảnh mặc định --%>
					<c:otherwise>
						<img src="https://cdn-icons-png.flaticon.com/512/149/149071.png"
							class="rounded-circle border"
							style="width: 120px; height: 120px; object-fit: cover;"
							alt="Default Avatar">
					</c:otherwise>
				</c:choose>
			</div>

			<div class="mb-3">
				<label class="form-label">Tên đăng nhập (Username)</label> <input
					type="text" class="form-control"
					value="${sessionScope.account.userName}" disabled>
			</div>

			<div class="mb-3">
				<label class="form-label">Email</label> <input type="email"
					class="form-control" value="${sessionScope.account.email}" disabled>
			</div>

			<div class="mb-3">
				<label class="form-label">Họ và tên (Full Name)</label> <input
					type="text" name="fullName" class="form-control"
					value="${sessionScope.account.fullName}" required>
			</div>

			<div class="mb-3">
				<label class="form-label">Số điện thoại (Phone)</label> <input
					type="text" name="phone" class="form-control"
					value="${sessionScope.account.phone}">
			</div>

			<div class="mb-3">
				<label class="form-label">Đổi ảnh đại diện (Avatar)</label> <input
					type="file" name="image" class="form-control" accept="image/*">
			</div>

			<div class="d-grid gap-2">
				<button type="submit" class="btn btn-primary">Lưu thay đổi</button>
				<a href="${pageContext.request.contextPath}/home"
					class="btn btn-outline-secondary">Quay lại Home</a>
			</div>
		</form>
	</div>
</body>
</html>