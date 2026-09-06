<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Chủ - Sweet Bakery</title>

<!-- Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome Icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
body {
	background-color: #f8f9fa;
	font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
	margin: 0;
}

/* Thanh Header / Navbar */
.main-navbar {
	background-color: #ffffff;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
}

.avatar-nav {
	width: 42px;
	height: 42px;
	object-fit: cover;
	border: 2px solid #0099dd;
}

/* Dropdown khi hover chuột vào vùng thông tin người dùng */
.user-dropdown:hover .dropdown-menu {
	display: block;
	margin-top: 0;
}

.user-dropdown .dropdown-toggle::after {
	vertical-align: middle;
}

/* Card sản phẩm */
.product-card {
	background: #ffffff;
	border-radius: 14px;
	border: 1px solid rgba(0, 0, 0, 0.05);
	transition: all 0.3s ease;
	text-decoration: none;
	color: inherit;
	overflow: hidden;
	height: 100%;
	display: flex;
	flex-direction: column;
}

.product-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 12px 24px rgba(0, 0, 0, 0.12);
	color: inherit;
}

.product-img {
	height: 180px;
	width: 100%;
	object-fit: cover;
	background-color: #fff3e0;
}

.product-badge {
	font-size: 11px;
	padding: 4px 8px;
	border-radius: 20px;
}
</style>
</head>
<body>

	<!-- =================== NAVBAR =================== -->
	<nav class="navbar navbar-expand-lg main-navbar sticky-top py-2">
		<div class="container">

			<!-- GÓC TRÁI: Logo, Lời chào, Tên & Avatar (kèm Dropdown Menu) -->
			<div class="d-flex align-items-center">
				<a
					class="navbar-brand fw-bold text-primary me-3 d-flex align-items-center gap-2"
					href="${pageContext.request.contextPath}/home"> <i
					class="fa-solid fa-cake-candles text-danger fs-4"></i> <span>Sweet
						Bakery</span>
				</a>

				<c:choose>
					<%-- Khi ĐÃ ĐĂNG NHẬP --%>
					<c:when test="${not empty sessionScope.account}">
						<div class="dropdown user-dropdown">
							<a
								class="dropdown-toggle text-decoration-none d-flex align-items-center gap-2 text-dark py-1"
								href="#" role="button" data-bs-toggle="dropdown"
								aria-expanded="false"> <!-- Avatar người dùng --> <c:choose>
									<c:when test="${not empty sessionScope.account.avatar}">
										<img
											src="${pageContext.request.contextPath}/image?fname=${sessionScope.account.avatar}"
											class="rounded-circle avatar-nav" alt="Avatar"
											onerror="this.onerror=null;this.src='https://ui-avatars.com/api/?name=${sessionScope.account.fullName}&background=0099dd&color=fff&size=128';">
									</c:when>
									<c:otherwise>
										<img
											src="https://ui-avatars.com/api/?name=${sessionScope.account.fullName}&background=0099dd&color=fff&size=128"
											class="rounded-circle avatar-nav" alt="Default Avatar">
									</c:otherwise>
								</c:choose> <!-- Lời chào và Tên -->
								<div>
									<span class="text-secondary small d-block">Xin chào,</span> <span
										class="fw-semibold text-primary">${sessionScope.account.fullName}</span>
								</div>
							</a>

							<!-- Menu thả xuống khi di chuột hoặc bấm vào tên -->
							<ul class="dropdown-menu shadow border-0 py-2">
								<li><a class="dropdown-item py-2"
									href="${pageContext.request.contextPath}/profile"> <i
										class="fa-solid fa-user-pen me-2 text-info"></i>Chỉnh sửa
										thông tin
								</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item py-2 text-danger"
									href="${pageContext.request.contextPath}/logout"> <i
										class="fa-solid fa-right-from-bracket me-2"></i>Đăng xuất
								</a></li>
							</ul>
						</div>
					</c:when>

					<%-- Khi CHƯA ĐĂNG NHẬP --%>
					<c:otherwise>
						<span class="text-secondary small">Chào mừng bạn đến với
							tiệm bánh!</span>
					</c:otherwise>
				</c:choose>
			</div>

			<!-- GÓC PHẢI: Nút Đăng xuất hoặc Nút Đăng nhập -->
			<div>
				<c:if test="${not empty sessionScope.account}">
					<a href="${pageContext.request.contextPath}/logout"
						class="btn btn-outline-danger btn-sm rounded-pill px-3 fw-semibold">
						<i class="fa-solid fa-arrow-right-from-bracket me-1"></i> Đăng
						xuất
					</a>
				</c:if>

				<c:if test="${empty sessionScope.account}">
					<a href="${pageContext.request.contextPath}/login"
						class="btn btn-primary btn-sm rounded-pill px-3 fw-semibold">
						<i class="fa-solid fa-right-to-bracket me-1"></i> Đăng nhập
					</a>
				</c:if>
			</div>

		</div>
	</nav>

	<!-- =================== NỘI DUNG CHÍNH TRANG CHỦ =================== -->
	<div class="container py-4">

		<!-- ĐÃ ĐĂNG NHẬP: HIỂN THỊ 10 SẢN PHẨM MỚI NHẤT -->
		<c:if test="${not empty sessionScope.account}">

			<!-- DÒNG TRÊN ĐẦU: TIÊU ĐỀ + DÒNG TẤT CẢ SẢN PHẨM DẪN SANG /product -->
			<div
				class="d-flex justify-content-between align-items-center mb-4 p-3 bg-white rounded-3 shadow-sm">
				<div>
					<h4 class="fw-bold mb-1 text-dark">
						<i class="fa-solid fa-fire text-danger me-2"></i>10 Sản Phẩm Mới
						Ra Lò Hôm Nay
					</h4>
					<small class="text-muted">Những món bánh thơm ngon vừa mới
						được làm trong ngày</small>
				</div>

				<!-- DÒNG DẪN ĐẾN TRANG /product CÓ ĐỦ SẢN PHẨM -->
				<a href="${pageContext.request.contextPath}/product"
					class="btn btn-warning rounded-pill px-3 fw-bold text-dark text-decoration-none shadow-sm">
					<i class="fa-solid fa-basket-shopping me-1"></i> Tất cả sản phẩm
					&rarr;
				</a>
			</div>

			<!-- LƯỚI 10 SẢN PHẨM (2 hàng, mỗi hàng 5 bánh trên màn hình lớn) -->
			<div
				class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-5 g-3">
				<c:forEach items="${top10Products}" var="p">
					<div class="col">
						<!-- Bấm vào thẻ để chuyển sang trang xem chi tiết sản phẩm -->
						<a
							href="${pageContext.request.contextPath}/product/detail?id=${p.id}"
							class="product-card shadow-sm"> <c:choose>
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

							<div class="p-3 d-flex flex-column flex-grow-1">
								<span
									class="badge bg-warning text-dark align-self-start mb-2 product-badge">
									${not empty p.category ? p.category.categoryname : 'Món bánh'}
								</span>
								<h6 class="fw-bold text-dark text-truncate mb-2"
									title="${p.name}">${p.name}</h6>
								<p class="text-danger fw-bold fs-6 mt-auto mb-0">
									<fmt:formatNumber value="${p.price}" pattern="#,###" />
									VNĐ
								</p>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>

		</c:if>

		<!-- CHƯA ĐĂNG NHẬP: THÔNG BÁO YÊU CẦU ĐĂNG NHẬP -->
		<c:if test="${empty sessionScope.account}">
			<div class="text-center py-5">
				<div class="card shadow-sm border-0 mx-auto p-5 rounded-4"
					style="max-width: 480px; background: #ffffff;">
					<i class="fa-solid fa-lock text-warning display-3 mb-3"></i>
					<h3 class="fw-bold text-dark mb-2">Vui lòng đăng nhập</h3>
					<p class="text-muted mb-4">Bạn cần đăng nhập vào tài khoản để
						xem thực đơn và những món bánh mới nhất của quán.</p>
					<a href="${pageContext.request.contextPath}/login"
						class="btn btn-primary rounded-pill py-2 fw-semibold shadow">
						<i class="fa-solid fa-right-to-bracket me-1"></i> Đăng nhập ngay
					</a>
				</div>
			</div>
		</c:if>

	</div>

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>