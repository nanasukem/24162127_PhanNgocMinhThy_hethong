<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${not empty product ? 'Cập Nhật Món Bánh' : 'Thêm Món Bánh Mới'}</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body class="bg-light">

	<div class="container py-5" style="max-width: 650px;">
		<div class="card shadow-sm border-0 rounded-4 p-4 bg-white">
			<h3 class="fw-bold mb-4 text-center text-primary">
				<i class="fa-solid fa-cake-candles me-2"></i> ${not empty product ? 'Chỉnh Sửa Món Bánh' : 'Thêm Món Bánh Mới'}
			</h3>

			<c:if test="${not empty alert}">
				<div class="alert alert-danger py-2 mb-3 small">
					<i class="fa-solid fa-triangle-exclamation me-1"></i> ${alert}
				</div>
			</c:if>

			<form
				action="${pageContext.request.contextPath}/admin/product/${not empty product ? 'edit' : 'add'}"
				method="post" enctype="multipart/form-data">

				<c:if test="${not empty product}">
					<input type="hidden" name="id" value="${product.id}">
				</c:if>

				<div class="mb-3">
					<label class="form-label fw-semibold">Tên món bánh <span
						class="text-danger">*</span></label> <input type="text" name="name"
						class="form-control" value="${product.name}" required
						placeholder="Ví dụ: Bánh Mousse Dâu">
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Danh mục bánh <span
						class="text-danger">*</span></label> <select name="categoryId"
						class="form-select" required>
						<option value="">-- Chọn danh mục bánh --</option>
						<c:forEach items="${categories}" var="c">
							<option value="${c.categoryid}"
								${(not empty product && not empty product.category && product.category.categoryid == c.categoryid) ? 'selected' : ''}>
								${c.categoryname}</option>
						</c:forEach>
					</select>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Giá bán (VNĐ) <span
						class="text-danger">*</span></label> <input type="number" step="1000"
						min="1000" name="price" class="form-control"
						value="${product.price}" required placeholder="35000">
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Ảnh đại diện sản phẩm</label>
					<input type="file" name="imageFile" class="form-control"
						accept="image/*">
					<c:if test="${not empty product.image}">
						<div class="mt-2">
							<small class="text-muted d-block mb-1">Ảnh hiện tại:</small>
							<c:choose>
								<c:when test="${fn:startsWith(product.image, 'http')}">
									<img src="${product.image}" height="80" class="rounded border"
										alt="Ảnh hiện tại">
								</c:when>
								<c:otherwise>
									<img
										src="${pageContext.request.contextPath}/image?fname=${product.image}"
										height="80" class="rounded border" alt="Ảnh hiện tại">
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
				</div>

				<div class="mb-4">
					<label class="form-label fw-semibold">Mô tả sản phẩm</label>
					<textarea name="description" rows="3" class="form-control"
						placeholder="Hương vị bơ béo, xốp mềm...">${product.description}</textarea>
				</div>

				<div class="d-flex justify-content-between">
					<a href="${pageContext.request.contextPath}/admin/products"
						class="btn btn-secondary rounded-pill px-4"> <i
						class="fa-solid fa-arrow-left me-1"></i> Quay lại
					</a>
					<button type="submit"
						class="btn btn-primary rounded-pill px-4 fw-semibold">
						<i class="fa-solid fa-floppy-disk me-1"></i> Lưu thông tin
					</button>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>