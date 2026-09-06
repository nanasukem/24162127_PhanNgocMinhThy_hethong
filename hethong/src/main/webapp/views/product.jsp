<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Chủ Bán Hàng</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<style>
.card-product {
	transition: all 0.3s ease;
	text-decoration: none;
	color: inherit;
	border-radius: 12px;
	overflow: hidden;
	height: 100%;
}

.card-product:hover {
	transform: translateY(-5px);
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
}

.product-img {
	height: 200px;
	object-fit: cover;
	width: 100%;
}
</style>
</head>
<body class="bg-light">

	<!-- Navbar -->
	<nav
		class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm mb-4">
		<div class="container">
			<a class="navbar-brand fw-bold"
				href="${pageContext.request.contextPath}/home"> <i
				class="fa-solid fa-store me-2"></i>My Shop
			</a>
			<div>
				<a class="btn btn-warning rounded-pill fw-semibold"
					href="${pageContext.request.contextPath}/product"> <i
					class="fa-solid fa-basket-shopping me-1"></i> Tất cả sản phẩm
				</a>
			</div>
		</div>
	</nav>

	<div class="container mb-5">
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h3 class="fw-bold text-dark mb-0">
				<i class="fa-solid fa-fire text-danger me-2"></i>10 Sản phẩm mới
				nhất
			</h3>
			<a href="${pageContext.request.contextPath}/product"
				class="text-primary text-decoration-none">Xem thêm &rarr;</a>
		</div>

		<div class="row row-cols-1 row-cols-md-5 g-3">
			<c:forEach items="${top10Products}" var="p">
				<div class="col">
					<!-- Bấm vào sản phẩm sẽ chuyển tới trang chi tiết -->
					<a
						href="${pageContext.request.contextPath}/product/detail?id=${p.productId}"
						class="card card-product border-0 shadow-sm"> <c:choose>
							<c:when test="${not empty p.images}">
								<img
									src="${pageContext.request.contextPath}/image?fname=${p.images}"
									class="card-img-top product-img" alt="${p.productName}">
							</c:when>
							<c:otherwise>
								<img src="https://placehold.co/300x200?text=No+Image"
									class="card-img-top product-img" alt="No image">
							</c:otherwise>
						</c:choose>

						<div class="card-body p-3 d-flex flex-column">
							<span class="badge bg-info text-dark align-self-start mb-2">${p.category.categoryname}</span>
							<h6 class="card-title fw-bold text-truncate"
								title="${p.productName}">${p.productName}</h6>
							<p class="text-danger fw-bold fs-5 mt-auto mb-0">
								<fmt:formatNumber value="${p.price}" pattern="#,###" />
								VNĐ
							</p>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>