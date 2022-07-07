<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách công ty</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.titileScreen {
	width: 100%;
	line-height: 60px;
	font-size: 20px;
	color: white;
	text-align: center;
	font-weight: 600;
}

.headerPDA {
	height: 60px;
	background-color: #005BB5;
}

.headerPDA p {
	font-size: 3vw;
}

.logo {
	width: 70px;
	line-height: 40px;
	font-size: 20px;
	color: white;
	text-align: center;
	font-weight: 600;
	position: fixed;
	border: 2px solid white;
	height: 45px;
	margin-top: 7px;
	margin-left: 7px;
}

.logo p {
	width: 100%;
	text-align: center;
	font-weight: 600;
}

.title {
	display: inline-block;
	margin-bottom: 5px;
	margin-top: 5px;
	font-size: 16px;
	font-weight: 700;
}

button[type="submit"] {
	border-radius: 0px;
	border: 1px solid #e0e0e0;
}

button[type="button"] {
	border-radius: 0px;
	border: 1px solid #e0e0e0;
}

.message {
	width: 90%;
	margin: auto;
	margin-top: 60px;
	border: 1px solid;
	border-radius: 0px;
}

input[type="text"]#rfid {
	border-radius: 0px;
	border: 1px solid #e0e0e0;
	height: 50px;
	font-size: 35px;
	font-weight: 700;
	text-align: center;
}

table.tablePopup thead tr th {
	padding: 5px;
	text-align: center;
	background-color: green;
	color: white;
	border: 2px solid black;
}

table.tablePopup tbody tr td {
	padding: 5px;
	border: 2px solid black;
}
</style>
<script type="text/javascript">
function show() {
	document.getElementById("tao").style.display ="block";
}

</script>
</head>
<body>
	
	<form action="PopupCompanyInput" method="POST" style="width:90%; margin: auto;">
		<p style="padding-top:15px; color: red; font-size: 14px; font-weight: 700"> (*) Vui lòng tạo công ty mới nếu hệ thống chưa tồn tại công ty của bạn</p>
    	 <button type="button" onclick="show()" class="btn btn-default" style="background-color: #f32701; color: white;">Tạo công ty</button>
    
    <div id="tao" style="display:none">
    <div class="form-group">
      <label for="email">Tên viết tắt:</label>
      <input type="text" class="form-control" required="required" id="email" name="shortName">
      <input type="text" class="form-control" style="display: none;" value="${param1}" required="required" id="email" name="param1">
      <input type="text" class="form-control" style="display: none;"  value="${param2}"  required="required" id="email" name="param2">
    </div>
    <div class="form-group">
      <label for="pwd">Tên đầy đủ:</label>
      <input type="text" class="form-control" required="required" id="pwd" name="fullName">
    </div>
    <div class="checkbox">
      <label><input type="checkbox" required="required" name="isOke">Tôi xác nhận chưa tồn tại công ty này trong danh sách</label>
    </div>
    <button type="submit" class="btn btn-default" style="background-color: #f32701; color: white;">Đăng ký</button>
  </div>
  </form>
	<c:if test="${lst != null}">
		<table class="table   tablePopup"
			style="width: 90%; margin: auto; margin-top: 12px; border-collapse: collapse;">
			<thead>
				<th style="display: none;">MÃ CÔNG TY</th>
				<th>TÊN VIẾT TẮT</th>
				<th colspan="2">TÊN CÔNG TY</th>
			</thead>
			<tbody>
				<c:forEach var="p" items="${lst}">
					<tr>
						<td style="width: 35%; font-weight: 700; display: none;">${p.getCompany_cd()}</td>
						<td style="width: 35%; font-weight: 700;background-color: #e5bc85">${p.getCompany_shortname()}</td>
						<td style="color: black; font-weight: 700">${p.getCompany_name()}</td>
						<td style="width: 80px; height: 30px"><button
								style="width: 100%; height: 100%"
								onclick="return getvalue('${p.getCompany_cd()}','${p.getCompany_name()}')">CHỌN</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
<script type="text/javascript">
	function getvalue(code, name)
	{
		if (window.opener != null && !window.opener.closed) {
            var cd = window.opener.document.getElementById('<%= request.getParameter("param1") %>');
            var na = window.opener.document.getElementById('<%= request.getParameter("param2") %>');
            cd.value = code;
            na.value = name;
            
        }
 		var btnSaveRight = window.opener.document.getElementById('reload');
 		if(btnSaveRight != null)
 		{
 			btnSaveRight.click();
 		}
    window.close();
	}
</script>
</html>