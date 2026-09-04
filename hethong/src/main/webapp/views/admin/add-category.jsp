<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>

<html>


<head>

<meta charset="UTF-8">

<title>Add Category</title>

</head>


<body>



	<h2>Thêm danh mục</h2>




	<form action="${pageContext.request.contextPath}/admin/category/add"
		method="post" enctype="multipart/form-data">





		<label> Tên danh mục: </label> <input type="text" name="catename">





		<br>
		<br> <label> Ảnh đại diện: </label> <input type="file"
			name="icon"> <br>
		<br>




		<button type="submit">Thêm</button>





		<button type="reset">Hủy</button>






	</form>




</body>


</html>