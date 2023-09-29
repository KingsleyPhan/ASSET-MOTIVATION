<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
* {
	font-family: "Times New Roman", Times, serif;
	font-size: 13px;
}

@page {
	size: 21cm 29.7cm;
	padding: 0mm 0mm 0mm 0mm;
	/* change the margins as you want them to be. */
}

p {
	margin: 0px;
	margin-bottom: 5px;
}

.tableDataImfor {
	width: 100%;
}

.tableDataImfor th {
	text-align: left;
}

.tableData {
	width: 100%;
	border-collapse: collapse;
}

.tableData tr th, .tableData tr td {
	border: 1px solid black;
}

.square_text {
	float: left;
	line-height: 20px;
}

.square {
	border: 1px solid black;
	height: 20px;
	width: 20px;
	float: left;
	margin-left: 10px;
}

.check_text {
	margin: auto;
	text-align: center;
}

.check_item {
	margin-right: 20px;
}
.auto_sign td {
	text-align: center;
	font-style: italic;
}

.check_sign td {

	text-align: center;
	font-weight: 700;
	margin-top: 40px;
	padding-top: 60px;
	color: white;
}

.name_sign td {
	text-align: center;
	font-weight: 700;
	margin-top: 40px;
}
</style>
</head>
<body>
	<table style="width: 100%">
		<tr>
			<th style="width: 50%"><p>TỔNG CÔNG TY CỔ PHẦN MAY CÔNG TIẾN</p>
				<p style="font-weight: 300">Số: 01/CĐ</p></th>
			<th style="width: 50%; text-align: right;">
				<p  style="font-weight: 300"> Mẩu số: 13/CĐ</p>
				<p  style="font-weight: 300">Ban hành: 1/0</p>
			</th>
		</tr>

	</table>
	<h3 style="text-align: center; font-size: 18px; margin: 5px;">LỆNH
		ĐIỀU ĐỘNG KIÊM BIÊN BẢN XUẤT THIẾT BỊ</h3>
	<table style="margin: auto; margin-top: -10px;">
		<tr>
			<td>
				<div class="check_item">
					<p class="square_text">BÀN GIAO</p>
					<p class="square"></p>
				</div>
			</td>
			<td>
				<div class="check_item">
					<p class="square_text">MƯỢN</p>
					<p class="square"></p>
				</div>
			</td>
			<td>
				<div class="check_item">
					<p class="square_text">TRẢ</p>
					<p class="square"></p>
				</div>
			</td>
			<td>
				<div class="check_item">
					<p class="square_text">THUÊ</p>
					<p class="square"></p>
				</div>
			</td>
		</tr>
	</table>
	<table class="tableDataImfor" style="width: 100%; margin-top:10px;">
		<tr>
			<th><b>LÝ DO ĐIỀU ĐỘNG: </b> ${TaoLenhDiChuyenNoiBoForm.reason}</th>
		</tr>
		<tr>
			<th><b>ĐIỀU TỪ ĐƠN VỊ:</b> ${TaoLenhDiChuyenNoiBoForm.deptOutName}</th>
		</tr>
		<tr>
			<th><b>ĐẾN ĐƠN VỊ: </b>  ${TaoLenhDiChuyenNoiBoForm.deptInName}</th>
		</tr>
		<tr>
			<th><b>THỜI GIAN TỪ: </b>  ${TaoLenhDiChuyenNoiBoForm.dateOut} <b>ĐẾN: </b></th>
		</tr>
	</table>
	<h3 style="text-align: center; margin-bottom: 5px;">GIÁM ĐỊNH
		THIẾT BỊ VÀ PHỤ TÙNG KÈM THEO</h3>

	<table class="tableData" style="width: 100%">
		<tr>
			<th>STT</th>
			<th>TÊN THIẾT BỊ - KÍ HIỆU</th>
			<th>SỐ MÁY</th>
			<th>NGUYÊN GIÁ</th>
			<th>PHỤ TÙNG KÈM THEO</th>
		</tr>
		<% int stt = 0; %>
		<c:forEach items="${lstChiTiet}" var="elm">
		<tr>
			
			<td style="width:40px; text-align: right;"><%=++stt%></td>
			<td><span>${elm.name}</span>-<span>${elm.model}</span></td>
			<td>${elm.series}</td>
			<td style=""></td>
			<td>${elm.assesseries}</td>
		</tr>
		</c:forEach>
	</table>
	<p style="margin-top: 10px;"><b>NHẬN XÉT CỦA BAN GIAO NHẬN:</b></p>
	<table class="table_sign" style="width: 100%">
		<tr>
			<th>TỔNG GIÁM ĐỐC</th>
			<th>KẾ TOÁN TRƯỞNG</th>
			<th>ĐD CÔNG TY</th>
			<th>ĐD BÊN GIAO</th>
			<th>ĐD BÊN NHẬN</th>
		</tr>
		<tr class="auto_sign">
			<td>(Đã ký)</td>
			<td>(Đã ký)</td>
			<td>(Đã ký)</td>
			<td>(Đã ký)</td>
			<td>(Đã ký)</td>
		</tr>
		<tr class="check_sign">
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
		</tr>
		<tr class="name_sign">
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
		</tr>
	</table>
</body>
</html>