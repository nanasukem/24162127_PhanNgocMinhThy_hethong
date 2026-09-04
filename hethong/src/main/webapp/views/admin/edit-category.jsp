<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>

<html>


<head>

<meta charset="UTF-8">

<title>Edit Category</title>

</head>



<body>




	<h2>Chỉnh sửa danh mục</h2>





	<form action="${pageContext.request.contextPath}/admin/category/edit"
		method="post" enctype="multipart/form-data">





		<input type="hidden" name="cateid" value="${category.cateid}">





		<label> Tên danh sách: </label> <input type="text" name="catename"
			value="${category.catename}"> <br>
		<br> <img
			src="${pageContext.request.contextPath}/image?fname=${category.icon}"
			width="150" height="120"> <br>
		<br> <label> Ảnh đại diện: </label> <input type="file"
			name="icon"> <br>
		<br>





		<button type="submit">Edit</button>





		<button type="reset">Reset</button>





	</form>




</body>


</html>