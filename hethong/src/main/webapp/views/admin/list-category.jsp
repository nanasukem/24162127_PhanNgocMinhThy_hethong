<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Danh sách danh mục</title>

</head>


<body>


	<h2>Danh sách danh mục</h2>



	<table border="1">


		<tr>

			<th>STT</th>

			<th>Hình ảnh</th>

			<th>Tên danh mục</th>

			<th>Chức năng</th>


		</tr>



		<c:forEach items="${cateList}" var="cate" varStatus="STT">



			<tr>


				<td>${STT.index + 1}</td>




				<td><c:url value="/image" var="imgUrl">

						<c:param name="fname" value="${cate.icon}" />

					</c:url> <img src="${imgUrl}" width="150" height="120"></td>




				<td>${cate.catename}</td>





				<td><a
					href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.cateid}">

						Sửa </a> | <a
					href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.cateid}">

						Xóa </a></td>




			</tr>




		</c:forEach>




	</table>



</body>

</html>