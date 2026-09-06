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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
body {
	background-color: #f4f7f6;
	font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

.profile-card {
	border-radius: 16px;
	border: none;
	background: #ffffff;
}
</style>
</head>
<body class="py-5">
	<div class="container" style="max-width: 520px;">
		<div class="card profile-card shadow-sm p-4 p-md-4">
			<h4 class="mb-4 text-center fw-bold text-dark">
				<i class="fa-solid fa-user-gear text-primary me-2"></i>Thông Tin Cá
				Nhân
			</h4>

			<c:if test="${not empty message}">
				<div class="alert alert-success py-2 text-center small mb-3">
					<i class="fa-solid fa-circle-check me-1"></i> ${message}
				</div>
			</c:if>
			<c:if test="${not empty alert}">
				<div class="alert alert-danger py-2 text-center small mb-3">
					<i class="fa-solid fa-triangle-exclamation me-1"></i> ${alert}
				</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/profile"
				method="post" enctype="multipart/form-data">

				<!-- Khối hiển thị Avatar -->
				<div class="text-center mb-4">
					<c:choose>
						<c:when test="${not empty sessionScope.account.avatar}">
							<img
								src="${pageContext.request.contextPath}/image?fname=${sessionScope.account.avatar}"
								class="rounded-circle border shadow-sm"
								style="width: 110px; height: 110px; object-fit: cover;"
								alt="Avatar"
								onerror="this.onerror=null;this.src='https://ui-avatars.com/api/?name=${sessionScope.account.fullName}&background=0099dd&color=fff&size=128';">
						</c:when>
						<c:otherwise>
							<img
								src="https://ui-avatars.com/api/?name=${sessionScope.account.fullName}&background=0099dd&color=fff&size=128"
								class="rounded-circle border shadow-sm"
								style="width: 110px; height: 110px; object-fit: cover;"
								alt="Default Avatar">
						</c:otherwise>
					</c:choose>
				</div>

				<div class="mb-3">
					<label class="form-label small fw-semibold text-secondary">Tên
						đăng nhập</label> <input type="text" class="form-control bg-light"
						value="${sessionScope.account.userName}" disabled>
				</div>

				<div class="mb-3">
					<label class="form-label small fw-semibold text-secondary">Email</label>
					<input type="email" class="form-control bg-light"
						value="${sessionScope.account.email}" disabled>
				</div>

				<div class="mb-3">
					<label class="form-label small fw-semibold text-secondary">Họ
						và tên <span class="text-danger">*</span>
					</label> <input type="text" name="fullName" class="form-control"
						value="${sessionScope.account.fullName}" required
						placeholder="Nhập họ và tên">
				</div>

				<div class="mb-3">
					<label class="form-label small fw-semibold text-secondary">Số
						điện thoại <span class="text-danger">*</span>
					</label> <input type="tel" name="phone" class="form-control"
						value="${sessionScope.account.phone}" required pattern="0[0-9]{9}"
						title="Số điện thoại gồm 10 số và bắt đầu bằng số 0"
						placeholder="09xxxxxxxx">
				</div>

				<div class="mb-4">
					<label class="form-label small fw-semibold text-secondary">Đổi
						ảnh đại diện</label> <input type="file" name="image" class="form-control"
						accept="image/png, image/jpeg, image/jpg, image/webp">
				</div>

				<div class="d-grid gap-2">
					<button type="submit"
						class="btn btn-primary rounded-pill py-2 fw-semibold">
						<i class="fa-solid fa-floppy-disk me-1"></i> Lưu thay đổi
					</button>
					<a href="${pageContext.request.contextPath}/home"
						class="btn btn-outline-secondary rounded-pill py-2"> <i
						class="fa-solid fa-house me-1"></i> Quay lại Trang chủ
					</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>