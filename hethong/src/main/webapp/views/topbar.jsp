<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<style>
.right-topbar {
	position: absolute;
	top: 20px;
	left: 30px;
}

.right-topbar a {
	color: white;
	text-decoration: none;
	font-size: 16px;
}

.right-topbar a:hover {
	color: #ddd;
}
</style>


<div class="right-topbar">

	<ul class="list-inline">

		<c:choose>

			<c:when test="${sessionScope.account == null}">

				<li><a href="${pageContext.request.contextPath}/login">
						Đăng nhập </a> | <a href="${pageContext.request.contextPath}/register">
						Đăng ký </a></li>

			</c:when>


			<c:otherwise>

				<li><a
					href="${pageContext.request.contextPath}/member/myaccount"> Xin
						chào, ${sessionScope.account.fullName} </a> | <a
					href="${pageContext.request.contextPath}/logout"> Đăng xuất </a></li>

			</c:otherwise>


		</c:choose>


	</ul>

</div>