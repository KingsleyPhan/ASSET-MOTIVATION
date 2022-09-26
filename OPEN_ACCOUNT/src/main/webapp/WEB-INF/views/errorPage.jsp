<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>ĐIỀU ĐỘNG TÀI SẢN</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" rel="stylesheet" />
<link type="text/css" href="resources/css/main.css" rel="stylesheet" />
<style type="text/css">
.glyphicon-calendar {
	font-size: 15pt;
}
</style>
<script type="text/javascript">
	$(function() {
		$('#dateOut').datepicker({
			format : "dd/mm/yyyy"
		});
	});
</script>
</head>
<body>

	<div class="container" style="width: 100%; padding: 5px;">
		<%@include file="Header.jsp"%>
		<h3 style="margin-bottom: 20px;">Đang Cập Nhật ... </h3>
		<a href="/Motivation" style="padding: 10px 20px; background-color: blue; color: white; margin-top: 20px;">QUAY LẠI</a>
	</div>
</body>


</html>
