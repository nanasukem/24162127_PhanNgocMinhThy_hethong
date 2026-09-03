<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật Hồ sơ cá nhân</title>
</head>
<body>
	<div class="container my-4">
		<div class="row justify-content-center">
			<div class="col-md-6 card p-4 shadow-sm">
				<h3 class="text-primary mb-3 text-center">Cập nhật Profile</h3>

				<!-- Thông báo khi cập nhật thành công -->
				<c:if test="${not empty message}">
					<div class="alert alert-success">${message}</div>
				</c:if>

				<form action="${pageContext.request.contextPath}/profile"
					method="post" enctype="multipart/form-data">

					<!-- Display Current Avatar -->
					<div class="mb-3 text-center">
						<c:choose>
							<c:when test="${not empty sessionScope.account.avatar}">
								<img
									src="${pageContext.request.contextPath}/uploads/${sessionScope.account.avatar}"
									width="120" height="120" class="rounded-circle border"
									style="object-fit: cover;" />
							</c:when>
							<c:otherwise>
								<img src="https://via.placeholder.com/120"
									class="rounded-circle border" alt="Avatar mặc định" />
							</c:otherwise>
						</c:choose>
					</div>

					<div class="mb-3">
						<label class="form-label">Tài khoản (Username):</label> <input
							type="text" class="form-control"
							value="${sessionScope.account.userName}" readonly disabled />
					</div>

					<div class="mb-3">
						<label class="form-label">Email:</label> <input type="email"
							class="form-control" value="${sessionScope.account.email}"
							readonly disabled />
					</div>

					<div class="mb-3">
						<label class="form-label">Họ và Tên:</label> <input type="text"
							name="fullName" class="form-control"
							value="${sessionScope.account.fullName}" required />
					</div>

					<div class="mb-3">
						<label class="form-label">Số điện thoại:</label> <input
							type="text" name="phone" class="form-control"
							value="${sessionScope.account.phone}" required />
					</div>

					<div class="mb-3">
						<label class="form-label">Chọn ảnh đại diện mới:</label> <input
							type="file" name="imageFile" class="form-control"
							accept="image/*" />
					</div>

					<button type="submit" class="btn btn-primary w-100">Lưu
						thay đổi</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>