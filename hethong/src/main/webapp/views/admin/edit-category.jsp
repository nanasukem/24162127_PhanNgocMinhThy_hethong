<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh Sửa Danh Mục</title>
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
				<i class="fa-solid fa-pen-to-square me-2"></i> Chỉnh Sửa Danh Mục
			</h4>

			<c:if test="${not empty alert}">
				<div class="alert alert-danger py-2 mb-3 small">
					<i class="fa-solid fa-triangle-exclamation me-1"></i> ${alert}
				</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/admin/category/edit"
				method="post" enctype="multipart/form-data">

				<input type="hidden" name="cateid" value="${category.categoryid}">

				<div class="mb-3">
					<label class="form-label fw-semibold">Tên danh mục <span
						class="text-danger">*</span></label> <input type="text" name="catename"
						class="form-control" value="${category.categoryname}" required
						maxlength="100">
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Ảnh hiện tại:</label>
					<div class="mb-2">
						<c:choose>
							<c:when test="${not empty category.images}">
								<img
									src="${pageContext.request.contextPath}/image?fname=${category.images}"
									height="80" class="rounded border" alt="Ảnh danh mục">
							</c:when>
							<c:otherwise>
								<span class="text-muted small">Chưa có ảnh</span>
							</c:otherwise>
						</c:choose>
					</div>
					<label class="form-label fw-semibold">Đổi ảnh mới (nếu
						muốn):</label> <input type="file" name="icon" class="form-control"
						accept="image/*">
				</div>

				<div class="d-flex justify-content-between">
					<a href="${pageContext.request.contextPath}/admin/category/list"
						class="btn btn-secondary rounded-pill px-4"> <i
						class="fa-solid fa-arrow-left me-1"></i> Quay lại
					</a>
					<button type="submit"
						class="btn btn-primary rounded-pill px-4 fw-semibold">
						<i class="fa-solid fa-floppy-disk me-1"></i> Lưu thay đổi
					</button>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>