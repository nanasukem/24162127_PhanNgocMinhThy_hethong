<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh Sách Bánh</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<style>
.card-product {
	transition: all 0.3s ease;
	text-decoration: none;
	color: inherit;
	border-radius: 14px;
	overflow: hidden;
	height: 100%;
}
.card-product:hover {
	transform: translateY(-5px);
	box-shadow: 0 10px 20px rgba(0,0,0,0.12);
}
.product-img {
	height: 200px;
	object-fit: cover;
	width: 100%;
	background-color: #f8f9fa;
}
</style>
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm mb-4">
  <div class="container">
    <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/home">
      <i class="fa-solid fa-cake-candles me-2"></i>Sweet Bakery
    </a>
    <a class="btn btn-outline-light rounded-pill px-3" href="${pageContext.request.contextPath}/home">
      <i class="fa-solid fa-house me-1"></i> Trang chủ
    </a>
  </div>
</nav>

<div class="container mb-5">
  <h3 class="fw-bold mb-4 text-secondary">Tất cả sản phẩm (Phân trang 6 sp/trang)</h3>

  <!-- Danh sách 6 sản phẩm -->
  <div class="row row-cols-1 row-cols-md-3 g-4 mb-4">
    <c:forEach items="${products}" var="p">
      <div class="col">
        <!-- Bấm vào sản phẩm chuyển tới chi tiết -->
        <a href="${pageContext.request.contextPath}/product/detail?id=${p.id}" class="card card-product border-0 shadow-sm">
          <c:choose>
            <c:when test="${not empty p.image}">
              <img src="${pageContext.request.contextPath}/image?fname=${p.image}" class="card-img-top product-img" alt="${p.name}">
            </c:when>
            <c:otherwise>
              <img src="https://placehold.co/400x300/f6d365/ffffff?text=${p.name}" class="card-img-top product-img" alt="${p.name}">
            </c:otherwise>
          </c:choose>
          
          <div class="card-body p-3">
            <span class="badge bg-warning text-dark mb-2">${p.category.categoryname}</span>
            <h5 class="card-title fw-bold text-truncate">${p.name}</h5>
            <p class="text-danger fw-bold fs-5 mb-0">
              <fmt:formatNumber value="${p.price}" pattern="#,###"/> VNĐ
            </p>
          </div>
        </a>
      </div>
    </c:forEach>
  </div>

  <!-- Thanh phân trang số trang -->
  <c:if test="${endPage > 1}">
    <nav aria-label="Page navigation">
      <ul class="pagination justify-content-center">
        <c:forEach begin="1" end="${endPage}" var="i">
          <li class="page-item ${currentPage == i ? 'active' : ''}">
            <a class="page-link" href="${pageContext.request.contextPath}/product?page=${i}">${i}</a>
          </li>
        </c:forEach>
      </ul>
    </nav>
  </c:if>
</div>

</body>
</html>