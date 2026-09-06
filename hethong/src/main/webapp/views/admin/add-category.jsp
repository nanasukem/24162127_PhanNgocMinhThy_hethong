<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm danh mục bánh</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body class="bg-light">

	<div class="container py-5" style="max-width: 550px;">
		<div class="card shadow-sm border-0 rounded-4 p-4 bg-white">
			<h4 class="fw-bold mb-4 text-center text-primary">
				<i class="fa-solid fa-folder-plus me-2"></i> Thêm Danh Mục Mới
			</h4>

			<c:if test="${not empty alert}">
				<div class="alert alert-danger py-2 mb-3 small">
					<i class="fa-solid fa-triangle-exclamation me-1"></i> ${alert}
				</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/admin/category/add"
				method="post" enctype="multipart/form-data">

				<div class="mb-3">
					<label class="form-label fw-semibold">Tên danh mục <span
						class="text-danger">*</span></label> <input type="text" name="catename"
						class="form-control" placeholder="Ví dụ: Bánh Mousse & Cheesecake"
						required maxlength="100">
				</div>

				<div class="mb-4">
					<label class="form-label fw-semibold">Ảnh đại diện danh mục</label>
					<input type="file" name="icon" class="form-control"
						accept="image/*">
				</div>

				<div class="d-flex justify-content-between">
					<a href="${pageContext.request.contextPath}/admin/products"
						class="btn btn-secondary rounded-pill px-4"> <i
						class="fa-solid fa-arrow-left me-1"></i> Quay lại
					</a>
					<button type="submit"
						class="btn btn-primary rounded-pill px-4 fw-semibold">
						<i class="fa-solid fa-plus me-1"></i> Thêm danh mục
					</button>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>