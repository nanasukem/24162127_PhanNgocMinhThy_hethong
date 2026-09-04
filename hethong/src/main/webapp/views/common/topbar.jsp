<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div
	style="background-color: #00aaff; padding: 10px 20px; display: flex; justify-content: space-between; align-items: center; color: white;">
	<div>
		<a href="${pageContext.request.contextPath}/home"
			style="color: white; text-decoration: none; font-weight: bold;">Dashboard</a>
	</div>

	<div style="display: flex; align-items: center; gap: 10px;">
		<c:if test="${not empty sessionScope.account}">
			<!-- Hiển thị Avatar nhỏ trên Topbar -->
			<c:choose>
				<c:when test="${not empty sessionScope.account.avatar}">
					<img
						src="${pageContext.request.contextPath}/uploads/${sessionScope.account.avatar}"
						style="width: 32px; height: 32px; border-radius: 50%; object-fit: cover;" />
				</c:when>
				<c:otherwise>
					<img src="https://via.placeholder.com/32"
						style="width: 32px; height: 32px; border-radius: 50%;" />
				</c:otherwise>
			</c:choose>

			<!-- Bấm vào tên để đến trang /profile -->
			<a href="${pageContext.request.contextPath}/profile"
				style="color: white; text-decoration: none; font-weight: bold;">
				Xin chào ${not empty sessionScope.account.fullName ? sessionScope.account.fullName : sessionScope.account.userName}
			</a>

			<a href="${pageContext.request.contextPath}/logout"
				class="btn btn-sm btn-light ms-2">Đăng xuất</a>
		</c:if>
	</div>
</div>