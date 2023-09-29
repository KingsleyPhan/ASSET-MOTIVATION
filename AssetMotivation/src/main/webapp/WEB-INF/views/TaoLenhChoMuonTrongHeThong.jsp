<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
            width: 170px;
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
        .form-control {
            border: 1px solid #1d2124;
        }
        .alert-danger, .alert-success {
        	text-align: center;
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
					<span><%=request.getSession().getAttribute("SESSION_USER_NAME")%> (<%=request.getSession().getAttribute("SESSION_USER_CD")%>)</span>			
			</td>
		</tr>
	
	</table>
	<form:form action="TaoLenhChoMuonTrongHeThong" method="POST" modelAttribute="moveObject">
    <%@include file="message.jsp" %>
	<div style="width: 100%; padding: 10px;">
    <div class="tittle_part">QUẢN LÝ LỆNH</div>
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">ID</label>
                <form:input readonly="true" path="id" type="text" class="form-control"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">MÃ LỆNH</label>
                <form:input path="code" readonly="true" type="text" class="form-control"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">SỐ LỆNH</label>
                <form:input readonly="true" path="no" type="text" class="form-control"/>
            </div>
        </div>

    </div>
    <div class="tittle_part">ĐƠN VỊ ĐỀ XUẤT</div>
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
        <div class="col-sm-4">
            <div class="form-group">
             <label for="usr">NGÀY XUẤT TÀI SẢN</label>
               <form:input path="dateOut" type="text" placeholder="DD/MM/YYYY" class="form-control"/>
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
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">NGÀY TRẢ TÀI SẢN</label>
                <form:input path="dateIn" type="text"  placeholder="DD/MM/YYYY" class="form-control"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="form-group">
                <label for="usr">LÝ DO ĐIỀU ĐỘNG</label>
                <form:input path="reason" type="text" class="form-control"/>
            </div>
        </div>
    </div>
    
    <form:input path="status" style="display:none" type="text" class="form-control"/>
    <form:input path="deleteFg" style="display:none" type="text" class="form-control"/>
    <form:input path="cmpnCd" style="display:none" type="text" class="form-control"/>
    
    <div class="text-right" style="width: 100%; display: inline-block; background-color: rgb(148, 150, 151);">
     <button class="btn btnAction"  type="submit" name="back">TRỞ VỀ</button>
     <c:if test="${moveObject.getId() != null}">
      	<c:if test="${fn:length(moveObject.getId()) > 0}">
     		<button class="btn btnAction" type="submit" name="delete">XÓA</button>
     	</c:if>
     </c:if>
        <button class="btn btnAction" type="submit" name="save">LƯU</button>
       
    </div>
</div>
</form:form>
</body>
</html>
<script>
	function getDeptByCompany(cmpnCd, deptCd, deptname) {
		var cmpnCdValue = document.getElementById(cmpnCd).value;
		if(cmpnCdValue != null && cmpnCdValue != undefined && cmpnCdValue.length >0) {
			openDialogue('GetListDepartment?param1=' +  deptCd + '&param2=' + deptname+ '&param3=' + cmpnCdValue);
		} else {
			alert("Vui Lòng Chọn Công Ty");
		}
	}

</script>