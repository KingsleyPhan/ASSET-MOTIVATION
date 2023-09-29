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
	font-weight: 500;
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
.table_sign tr th, .table_sign tr td {
	font-size: 11px;
}
</style>
</head>
<body style="margin: 0px">
	<table style="width: 100%">
		<tr>
			<th style="width: 50%"><p>TỔNG CÔNG TY CỔ PHẦN MAY CÔNG TIẾN</p>
			<c:if test="${moveObject.code != ''}">
				<p style="font-weight: 300">Số: ${moveObject.no}/${moveObject.code}/CĐ</p></th>
			</c:if>
			<c:if test="${moveObject.code == ''}">
				<p style="font-weight: 300">Số: ${moveObject.no}/CĐ</p></th>
			</c:if>
				
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
			<th style="text-transform: uppercase;"><b>LÝ DO ĐIỀU ĐỘNG: </b> ${moveObject.reason}</th>
		</tr>
		<tr>
			<th><b>ĐIỀU TỪ ĐƠN VỊ:</b> ${moveObject.cmpnOutName} - ${moveObject.deptOutName}</th>
		</tr>
		<tr>
			<th><b>ĐẾN ĐƠN VỊ: </b>  ${moveObject.cmpnInName} - ${moveObject.deptInName}</th>
		</tr>
		<tr>
			<th><b>THỜI GIAN TỪ: </b>  ${moveObject.dateOut} <b><span style="margin-left: 200px">		</span>ĐẾN: </b>  ${moveObject.dateIn}</th>
		</tr>
	</table>
	<h3 style="text-align: center; margin-bottom: 5px;">GIÁM ĐỊNH
		THIẾT BỊ VÀ PHỤ TÙNG KÈM THEO</h3>

	<table class="tableData" style="width: 100%">
		<tr>
			<th>STT</th>
			<th>TÊN THIẾT BỊ - KÍ HIỆU</th>
			<th>SỐ MÁY</th>
			<th>MÃ KẾ TOÁN</th>
			<th>NGUYÊN GIÁ</th>
			<th>PHỤ TÙNG KÈM THEO</th>
		</tr>
		<% int stt = 0; %>
		<c:forEach items="${lstChiTiet}" var="elm">
		<tr>
			
			<td style="width:40px; text-align: right;"><%=++stt%></td>
			<td><span>${elm.name}</span></td>
			<td style="text-align: center">${elm.series}</td>
			<td style="text-align: center">${elm.accountCd}</td>
			<td style="text-align: right">${elm.price}</td>
			<td>${elm.assesseries}</td>
		</tr>
		</c:forEach>
	</table>
	<p style="margin-top: 10px;"><b>NHẬN XÉT CỦA BAN GIAO NHẬN:</b> ${moveObject.noteCreate}</p>
	<p style="width: 100%; text-align: right">${dateString}</p>
	<table class="table_sign" style="width: 100%">
		<tr>
			<th>TỔNG GIÁM ĐỐC</th>
			<th>KẾ TOÁN TRƯỞNG</th>
			<th>ĐD CÔNG TY</th>
			<th>ĐD BÊN GIAO</th>
			<th>ĐD BÊN NHẬN</th>
		</tr>
		<tr class="check_sign">
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
			<td>Nguyễn Băn A</td>
		</tr>
		<tr class="auto_sign">
				<td style="color: white">(Đã ký)</td>
			 <c:if test = "${isAccount == 'true'}">
         		<td>(Đã ký)</td>
         	</c:if>
         	 <c:if test = "${isAccount == 'false'}">
         		<td style="color: white">(Đã ký)</td>
         	</c:if>
         	 <c:if test = "${isManager == 'true'}">
         		<td>(Đã ký)</td>
         	</c:if>
         	<c:if test = "${isManager == 'false'}">
         		<td style="color: white">(Đã ký)</td>
         	</c:if>
         	 <c:if test = "${isCreate == 'true'}">
         		<td>(Đã ký)</td>
         	</c:if>
         	<c:if test = "${isCreate == 'false'}">
         		<td style="color: white">(Đã ký)</td>
         	</c:if>
         	
         	
         	

   			<td style="color: white">(Đã ký)</td>

		</tr>

		<tr class="name_sign">			
			<td></td>
			 <c:if test = "${isAccount == 'true'}">
         		<td>${moveObject.userAccount}</td>
         	</c:if>
         	 <c:if test = "${isAccount == 'false'}">
         		<td style="color: white">${moveObject.userAccount}</td>
         	</c:if>
         	 <c:if test = "${isManager == 'true'}">
         		<td>${moveObject.userManager}</td>
         	</c:if>
         	<c:if test = "${isManager == 'false'}">
         		<td style="color: white">${moveObject.userManager}</td>
         	</c:if>
         	 <c:if test = "${isCreate == 'true'}">
         		<td>${moveObject.userCreate}</td>
         	</c:if>
         	<c:if test = "${isCreate == 'false'}">
         		<td style="color: white">${moveObject.userCreate}</td>
         	</c:if>
		
			<td></td>
		</tr>
	</table>
</body>
</html>