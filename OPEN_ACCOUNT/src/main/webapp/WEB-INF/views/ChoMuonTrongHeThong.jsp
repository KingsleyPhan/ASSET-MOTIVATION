<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CHO MƯỢN TRONG HỆ THỐNG</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/js/common.js"></script>
  
  <style>
        .form-group label {
            font-weight: 700;
            text-transform: uppercase;
        }
        .tittle_part {
            width: fit-content; 
            padding: 5px 10px; 
            background-color: gray; 
            color: white; font-weight: 700;
            margin-bottom: 10px;
        }
        .btnAction {
            background-color: #007bff;
            color: white;
            text-transform: uppercase;
            /* float: right; */
            margin-left: 10px;
            width: 142px;
            border-radius: 0px;
            font-weight: 700;
            float: right;
        }
        .table_data {
            width: 100%;
        }
        .table_data thead th{
            border: 1px solid black;
            text-align: center;
            background-color: darkgrey;
        }
        .table-general tbody td {
        	border:  1px solid gray;
        }
         .table-general thead th {
        	border:  1px solid gray;
        	text-align: center;
        	background-color: #005BB5; 
        	color: white;
        }
        .btn-table-action {
            border: none;
		    padding: 2px 10px;
		    background-color: #0062cc;
		    color: #FFFFFF;
        }
          .btn-select {
        	float: right;
		    margin-top: 5px;
		    margin-left: 5px;
		    background-color: #e41c1c;
		    color: #FFFFFF;
		    font-weight: 700;
		    border: 1px solid gray;
		    cursor: pointer !important;
        }
  </style>
</head>
<body>
	<table style="width:100%; height:40px; background-color: #005BB5">
		<tr>
			<td  style="width:50%; color: white; font-weight: 700">
			<span><%=request.getSession().getAttribute("SESSION_CMPN_NAME")%></span>
			</td>
			<td style="width:50%; color: white; font-weight: 700; text-align: right">
					<a href="/Motivation" style="text-decoration: none; color: #FFFFFF"><span><%=request.getSession().getAttribute("SESSION_USER_NAME")%> (<%=request.getSession().getAttribute("SESSION_USER_CD")%>)</span></a>		
			</td>
		</tr>
	</table>
	<%@include file="message.jsp" %>
	<form:form action="ChoMuonTrongHeThong" method="POST" modelAttribute="moveObject">
	
	<div style="width: 100%; padding: 10px;">
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">MÃ LỆNH</label>
                <form:input path="code"  type="text" class="form-control"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">SỐ LỆNH</label>
                <form:input  path="no" type="text" class="form-control"/>
            </div>
        </div>
           <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">Trạng thái</label>
                <form:input  path="status" type="text" class="form-control"/>
            </div>
        </div>

    </div>
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">CÔNG TY XUẤT TÀI SẢN</label>
                <form:input path="cmpnOutName" readonly="true" type="text" class="form-control"/>
                <form:input style="display:none" path="cmpnOutId" type="text" class="form-control"/>
                <button type="button" class="btn-select" onclick="return clearText('cmpnOutId','cmpnOutName' )">XÓA</button>
                <button type="button" class="btn-select" onclick="return openDialogue('PopupCompanyInput?param1=cmpnOutId&param2=cmpnOutName')">CHỌN</button>
               
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">ĐƠN VỊ XUẤT TÀI SẢN</label>
                <form:input readonly="true" path="deptOutName" type="text" class="form-control"/>
                <form:input style="display:none" path="deptOutId" type="text" class="form-control"/>
                <button type="button" class="btn-select" onclick="return clearText('deptOutId','deptOutName' )">XÓA</button>
                <button type="button" class="btn-select" onclick="return getDeptByCompany('cmpnOutId', 'deptOutId','deptOutName')">CHỌN</button>
            </div>
        </div>
      

    </div>
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">CÔNG TY NHẬP TÀI SẢN</label>
                <form:input readonly="true" path="cmpnInName" type="text" class="form-control"/>
                <form:input style="display:none" path="cmpnInId" type="text" class="form-control"/>
                <button type="button" class="btn-select" onclick="return clearText('cmpnInId','cmpnInName' )">XÓA</button>
                <button type="button" class="btn-select" onclick="return openDialogue('PopupCompanyInput?param1=cmpnInId&param2=cmpnInName')">CHỌN</button>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">ĐƠN VỊ NHẬP TÀI SẢN</label>
                <form:input readonly="true" path="deptInName" type="text" class="form-control"/>
                <form:input style="display:none" path="deptInId" type="text" class="form-control"/>
                 <button type="button" class="btn-select" onclick="return clearText('deptInId','deptInName' )">XÓA</button>
                <button type="button" class="btn-select" onclick="return getDeptByCompany('cmpnInId', 'deptInId','deptInName')">CHỌN</button>
            </div>
        </div>
      
    </div>
	<div class="text-right" style="width: 100%; display: inline-block; margin-top: 10px;  background-color: rgb(148, 150, 151);">
         <a class="btn btnAction" href="DieuDongTaiSan">QUAY LẠI</a>
        <button class="btn btnAction" type="button" data-toggle="modal" data-target="#exampleModal" name="create">TẠO</button>
        <button class="btn btnAction" type="submit" name="search">TÌM KIẾM</button>
       
    </div>
</form:form>
	<c:if test="${lst != null}">
	<table class="table-general" style="width:100%">
		<thead>
			<th>STT</th>
			<th>MÃ LỆNH</th>
			<th>CTY XUẤT</th>
			<th>ĐV XUẤT</th>
			<th>CTY NHẬP</th>
			<th>ĐV NHẬP</th>
			<th>TRẠNG THÁI</th>
			<th>THAO TÁC</th>	
			<th>XEM XÉT</th>		
			<th>XÓA</th>
		</thead>
		<tbody>
		<% int stt = 0; %>
		<c:forEach items="${lst}" var="elm">		
			<tr>
				<td style="text-align: right"><%= ++stt %></td>
				<c:if test="${elm.getCode()!=''}">
					<td style="text-align: center">${elm.getNo()}/${elm.getCode()}</td>
				</c:if>
				<c:if test="${elm.getCode()==''}">
					<td style="text-align: center">${elm.getNo()}</td>
				</c:if>
				
				<td>${elm.getCmpnOutName()}</td>
				<td>${elm.getDeptOutName()}</td>
				<td>${elm.getCmpnInName()}</td>
				<td>${elm.getDeptInName()}</td>
				<td style="text-align: center">${elm.getStatus()}</td>
				<td  style="text-align: center">
					<c:if test="${elm.getStatus().trim() == 'NEW'}">
						<form action="ChoMuonTrongHeThong" method="POST">
							<input type="text" style="display:none;" name="id" value="${elm.getId()}"/>
							<button type="submit" class="btn-table-action" name="declare">KHAI BÁO</button>
						</form>
					</c:if>
					<c:if test="${elm.getStatus().trim() != 'NEW'}">
						<form action="ChoMuonTrongHeThong" method="POST">
							<input type="text" style="display:none;" name="id" value="${elm.getId()}"/>
							<input type="text" style="display:none;" name="backurl" value="ChoMuonTrongHeThong"/>
							
							<button type="submit" class="btn-table-action" name="view">XEM</button>
						</form>
					</c:if>
				</td>
				<td style="text-align: center;">
					<c:if test = "${elm.getStatus().trim() == 'NEW'}">
						<form action="ChoMuonTrongHeThong" method="POST">
						<input type="text" style="display:none;" name="id" value="${elm.getId()}"/>
						<button type="submit" name="PCD">PHÒNG CƠ ĐIỆN</button>
						</form>
					</c:if>
					<c:if test="${elm.getStatus().trim() == 'NOT_PCD'}">
						<form action="ChoMuonTrongHeThong" method="POST">
						<input type="text" style="display:none;" name="id" value="${elm.getId()}"/>
						<button type="submit" name="PCD">PCĐ K DUYỆT</button>
						</form>
					</c:if>
					<c:if test="${elm.getStatus().trim() == 'PCD'}">
						<form action="ChoMuonTrongHeThong" method="POST">
						<input type="text" style="display:none;" name="id" value="${elm.getId()}"/>
						<button type="submit" name="KT">KẾ TOÁN DUYỆT</button>
						</form>
					</c:if>
					<c:if test="${elm.getStatus().trim() == 'NOT_KT'}">
						<form action="ChoMuonTrongHeThong" method="POST">
						<input type="text" style="display:none;" name="id" value="${elm.getId()}"/>
						<button type="submit" name="KT">PKT K DUYỆT</button>
						</form>
					</c:if>
					<c:if test="${elm.getStatus().trim() == 'KT'}">
						<form action="ChoMuonTrongHeThong" method="POST">
						<input type="text" style="display:none;" name="id" value="${elm.getId()}"/>
						<button type="submit" name="KV">KHO VẬN DUYỆT</button>
						</form>
					</c:if>
					<c:if test="${elm.getStatus().trim() == 'NOT_KV'}">
						<form action="ChoMuonTrongHeThong" method="POST">
						<input type="text" style="display:none;" name="id" value="${elm.getId()}"/>
						<button type="submit" name="KV">PKV K DUYỆT</button>
						</form>
					</c:if>
					<c:if test="${elm.getStatus().trim() == 'OK'}">
						<form action="ChoMuonTrongHeThong" method="POST">
						<input type="text" style="display:none;" name="id" value="${elm.getId()}"/>
						<span style="padding: 4px 10px; background-color: green; color: #FFFFFF">ĐÃ ĐIỀU ĐỘNG</span>
						</form>
					</c:if>
				</td>
				<td style="text-align: center">
					<c:if test="${elm.getUserCreate() != 'SYSTEM'}">
						<c:if test="${elm.getStatus().trim() == 'NEW'}">
							<form action="ChoMuonTrongHeThong" method="POST">
								<input type="text" style="display:none;" name="id" value="${elm.getId()}"/>
								<button type="submit" class="btn-table-action" name="edit">SỬA - XÓA</button>
							</form>
						</c:if>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	
	<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">CHỌN LOẠI LỆNH</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<form action="ChoMuonTrongHeThong" method="POST">
      	<button type="submit" name="create" class="btn btn-primary">CHO MƯỢN TRONG HỆ THỐNG</button><br>
      	<button type="submit" style="margin-top:10px" name="LenhDiChuyenNoiBo" class="btn btn-primary">DI CHUYỂN NỘI BỘ</button><br>
      	<button type="submit" style="margin-top:10px" name=TraTaiSanMuon class="btn btn-primary">TRẢ TÀI SẢN MƯỢN</button><br>
      	<button type="submit" style="margin-top:10px" name=ThueTaiSan class="btn btn-primary">THUÊ TÀI SẢN TRONG HỆ THỐNG</button>
      	<button type="submit" style="margin-top:10px" name=TraThueTaiSan class="btn btn-primary"> TRẢ THUÊ TÀI SẢN TRONG HỆ THỐNG</button>
      	<button type="submit" style="margin-top:10px" name=BanGiaoTaiSan class="btn btn-primary">BÀN GIAO TÀI SẢN</button>
      	
      	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">ĐÓNG</button>
        
      </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
function getDeptByCompany(cmpnCd, deptCd, deptname) {
	var cmpnCdValue = document.getElementById(cmpnCd).value;
	if(cmpnCdValue != null && cmpnCdValue != undefined && cmpnCdValue.length >0) {
		openDialogue('GetListDepartment?param1=' +  deptCd + '&param2=' + deptname+ '&param3=' + cmpnCdValue);
	} else {
		alert("Vui Lòng Chọn Công Ty");
	}
}
</script>
</html>