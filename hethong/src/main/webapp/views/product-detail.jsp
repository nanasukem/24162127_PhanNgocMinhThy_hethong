<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi Tiết Bánh</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body class="bg-light">

	<nav
		class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm mb-4">
		<div class="container">
			<a class="navbar-brand fw-bold"
				href="${pageContext.request.contextPath}/home"> <i
				class="fa-solid fa-cake-candles me-2"></i>Sweet Bakery
			</a>
			<div>
				<a class="btn btn-outline-light rounded-pill me-2"
					href="${pageContext.request.contextPath}/home"> <i
					class="fa-solid fa-house me-1"></i> Trang chủ
				</a> <a class="btn btn-warning rounded-pill"
					href="${pageContext.request.contextPath}/product"> <i
					class="fa-solid fa-basket-shopping me-1"></i> Danh mục bánh
				</a>
			</div>
		</div>
	</nav>

	<div class="container py-4">
		<div class="card border-0 shadow-sm p-4 rounded-4">
			<div class="row g-4">
				<div class="col-md-5">
					<c:choose>
						<%-- Đã sửa thành product.image (không có chữ 's') --%>
						<c:when test="${not empty product.image}">
							<img
								src="${pageContext.request.contextPath}/image?fname=${product.image}"
								class="img-fluid rounded-3 border w-100"
								style="max-height: 400px; object-fit: cover;"
								alt="${product.name}">
						</c:when>
						<c:otherwise>
							<img
								src="https://placehold.co/400x300/f6d365/ffffff?text=${product.name}"
								class="img-fluid rounded-3 border w-100"
								style="max-height: 400px; object-fit: cover;"
								alt="${product.name}">
						</c:otherwise>
					</c:choose>
				</div>

				<div class="col-md-7">
					<span class="badge bg-warning text-dark fs-6 mb-2"> ${not empty product.category ? product.category.categoryname : 'Món bánh'}
					</span>
					<h2 class="fw-bold mb-3">${product.name}</h2>
					<h3 class="text-danger fw-bold mb-3">
						<fmt:formatNumber value="${product.price}" pattern="#,###" />
						VNĐ
					</h3>

					<hr>
					<h5 class="fw-bold text-secondary">Mô tả sản phẩm:</h5>
					<p class="text-muted" style="line-height: 1.8;">${not empty product.description ? product.description : 'Đang cập nhật mô tả...'}
					</p>

					<div class="mt-4">
						<a href="javascript:history.back()"
							class="btn btn-secondary rounded-pill px-4"> <i
							class="fa-solid fa-arrow-left me-1"></i> Quay lại
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>