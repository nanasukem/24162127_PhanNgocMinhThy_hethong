<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property="title" /></title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<sitemesh:write property="head" />
</head>
<body>

	<div class="container my-4">
		<sitemesh:write property="body" />
	</div>

</body>
</html>