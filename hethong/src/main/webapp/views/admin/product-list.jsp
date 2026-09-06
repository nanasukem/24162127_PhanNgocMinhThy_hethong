<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản Trị Bánh - Sweet Bakery Admin</title>

<!-- Bootstrap 5 CSS & Font Awesome -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
body {
	background-color: #f4f6f9;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

/* Card sản phẩm admin */
.admin-product-card {
	background: #ffffff;
	border-radius: 16px;
	border: none;
	position: relative;
	overflow: hidden;
	transition: all 0.3s ease;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.admin-product-card:hover {
	transform: translateY(-6px);
	box-shadow: 0 12px 24px rgba(0, 0, 0, 0.12);
}

.product-img-box {
	position: relative;
	width: 100%;
	height: 200px;
	overflow: hidden;
	background-color: #fff8e1;
}

.product-img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	transition: transform 0.4s ease;
}

.admin-product-card:hover .product-img {
	transform: scale(1.08);
}

/* Lớp phủ chứa nút Sửa / Xóa khi rê chuột vào */
.action-overlay {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: rgba(0, 0, 0, 0.55);
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 12px;
	opacity: 0;
	visibility: hidden;
	transition: all 0.3s ease;
	backdrop-filter: blur(2px);
}

/* Hiệu ứng rê chuột: Hiện 2 nút */
.admin-product-card:hover .action-overlay {
	opacity: 1;
	visibility: visible;
}

.btn-circle {
	width: 44px;
	height: 44px;
	border-radius: 50%;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 16px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
	transition: transform 0.2s ease;
}

.btn-circle:hover {
	transform: scale(1.15);
}
</style>
</head>
<body>

	<!-- Header dành riêng cho Admin -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm py-2">
		<div class="container-fluid px-4">
			<a class="navbar-brand fw-bold"
				href="${pageContext.request.contextPath}/admin/products"> <i
				class="fa-solid fa-shield-halved text-warning me-2"></i>Khu Vực Quản
				Trị
			</a>
			<div class="d-flex align-items-center gap-3">
				<span class="text-light small"> Admin: <strong
					class="text-warning">${sessionScope.account.fullName}</strong>
				</span> <a href="${pageContext.request.contextPath}/logout"
					class="btn btn-outline-danger btn-sm rounded-pill px-3"> <i
					class="fa-solid fa-right-from-bracket me-1"></i> Đăng xuất
				</a>
			</div>
		</div>
	</nav>

	<div class="container-fluid px-4 py-4">

		<!-- DÒNG TRÊN CÙNG: TIÊU ĐỀ + CHỈ CÒN NÚT THÊM MỚI -->
		<div
			class="d-flex flex-wrap justify-content-between align-items-center mb-4 bg-white p-3 rounded-4 shadow-sm">
			<div>
				<h4 class="fw-bold mb-1 text-dark">
					<i class="fa-solid fa-boxes-stacked text-primary me-2"></i>Quản Lý
					Sản Phẩm Tiệm Bánh
				</h4>
				<small class="text-muted">Di chuột vào từng sản phẩm để Sửa
					thông tin hoặc Xóa</small>
			</div>

			<div>
				<!-- NÚT DUY NHẤT: Thêm sản phẩm mới -->
				<a href="${pageContext.request.contextPath}/admin/product/add"
					class="btn btn-success rounded-pill px-3 fw-semibold shadow-sm">
					<i class="fa-solid fa-circle-plus me-1"></i> Thêm Sản Phẩm Mới
				</a>
			</div>
		</div>

		<!-- DANH SÁCH CARD CÁC SẢN PHẨM -->
		<div
			class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4">
			<c:forEach items="${products}" var="p">
				<div class="col">
					<div class="card admin-product-card h-100">

						<!-- Khung ảnh có hiệu ứng Overlay khi hover -->
						<div class="product-img-box">
							<c:choose>
								<c:when
									test="${not empty p.image and (fn:startsWith(p.image, 'http://') or fn:startsWith(p.image, 'https://'))}">
									<img src="${p.image}" class="product-img" alt="${p.name}">
								</c:when>
								<c:when test="${not empty p.image}">
									<img
										src="${pageContext.request.contextPath}/image?fname=${p.image}"
										class="product-img" alt="${p.name}">
								</c:when>
								<c:otherwise>
									<img
										src="https://placehold.co/400x300/f6d365/ffffff?text=${p.name}"
										class="product-img" alt="${p.name}">
								</c:otherwise>
							</c:choose>

							<!-- LỚP PHỦ HOVER: NÚT SỬA VÀ NÚT XOÁ -->
							<div class="action-overlay">
								<!-- Nút Sửa -->
								<a
									href="${pageContext.request.contextPath}/admin/product/edit?id=${p.id}"
									class="btn btn-warning text-dark btn-circle"
									title="Sửa sản phẩm"> <i class="fa-solid fa-pen-to-square"></i>
								</a>

								<!-- Nút Xóa -->
								<a
									href="${pageContext.request.contextPath}/admin/product/delete?id=${p.id}"
									class="btn btn-danger btn-circle" title="Xóa sản phẩm"
									onclick="return confirm('Bạn có chắc chắn muốn xóa món bánh [${p.name}] không?');">
									<i class="fa-solid fa-trash"></i>
								</a>
							</div>
						</div>

						<!-- Thông tin bánh -->
						<div class="card-body p-3 d-flex flex-column">
							<span
								class="badge bg-light text-secondary border align-self-start mb-2 small">
								${not empty p.category ? p.category.categoryname : 'Chưa phân loại'}
							</span>
							<h6 class="fw-bold text-dark text-truncate mb-2"
								title="${p.name}">${p.name}</h6>
							<div class="mt-auto">
								<span class="text-danger fw-bold fs-6"> <fmt:formatNumber
										value="${p.price}" pattern="#,###" /> VNĐ
								</span>
							</div>
						</div>

					</div>
				</div>
			</c:forEach>
		</div>

	</div>

	<!-- Bootstrap 5 JS Bundle -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>