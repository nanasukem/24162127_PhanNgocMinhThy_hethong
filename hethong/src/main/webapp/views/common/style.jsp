<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, sans-serif;
}

/* =================
   SIDEBAR
================= */
.sidebar {
	position: fixed;
	top: 0;
	left: 0;
	width: 230px;
	height: 100vh;
	background: #0099ff;
	color: white;
	z-index: 2000;
}

.sidebar h2 {
	margin: 0;
	padding: 20px;
	font-size: 26px;
	background: #0088ee;
	text-align: center;
}

.menu {
	margin-top: 20px;
}

.menu a {
	display: block;
	padding: 15px 20px;
	color: white;
	text-decoration: none;
	font-size: 16px;
}

.menu a:hover {
	background: red;
}

/* =================
   TOPBAR
================= */
.topbar {
	position: fixed;
	top: 0;
	left: 230px;
	right: 0;
	height: 60px;
	background: #0099ff;
	color: white;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0 30px;
	z-index: 1000;
}

.topbar-title {
	font-size: 22px;
	font-weight: bold;
}

.topbar-user {
	display: flex;
	align-items: center;
	gap: 15px;
}

.logout-btn {
	background: #ff5722;
	border: none;
	color: white;
	padding: 8px 15px;
	border-radius: 4px;
	cursor: pointer;
}

.logout-btn:hover {
	background: #e64a19;
}

/* =================
   CONTENT
================= */
.content {
	margin-left: 230px;
	padding: 90px 30px 30px 30px;
}

.card {
	width: 90%;
	margin: auto;
}

.card-header {
	background: #f5f5f5;
}

.card-header h3 {
	color: #555;
}

.form-control {
	margin-top: 5px;
}

.category-img {
	width: 120px;
	height: 120px;
	border-radius: 50%;
	object-fit: cover;
}
</style>