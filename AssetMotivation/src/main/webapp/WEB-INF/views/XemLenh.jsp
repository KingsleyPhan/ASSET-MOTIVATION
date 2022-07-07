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
  <link type="text/css" href="resources/css/main.css" rel="stylesheet" />
  
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
	<form:form action="XemLenh" method="POST" modelAttribute="moveObject">
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
<!--                 <button type="button" class="btn-select" onclick="return clearText('cmpnOutId','cmpnOutName' )">XÓA</button> -->
<!--                 <button type="button" class="btn-select" onclick="return openDialogue('PopupCompanyInput?param1=cmpnOutId&param2=cmpnOutName')">CHỌN</button> -->
               
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">ĐƠN VỊ XUẤT TÀI SẢN</label>
                <form:input readonly="true" path="deptOutName" type="text" class="form-control"/>
                <form:input style="display:none" path="deptOutId" type="text" class="form-control"/>
<!--                 <button type="button" class="btn-select" onclick="return clearText('deptOutId','deptOutName' )">XÓA</button> -->
<!--                 <button type="button" class="btn-select" onclick="return getDeptByCompany('cmpnOutId', 'deptOutId','deptOutName')">CHỌN</button> -->
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
             <label for="usr">NGÀY XUẤT TÀI SẢN</label>
               <form:input readonly="true" path="dateOut" type="text" placeholder="DD/MM/YYYY" class="form-control"/>
            </div>
        </div>

    </div>
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">CÔNG TY NHẬP TÀI SẢN</label>
                <form:input readonly="true" path="cmpnInName" type="text" class="form-control"/>
                <form:input style="display:none" path="cmpnInId" type="text" class="form-control"/>
<!--                 <button type="button" class="btn-select" onclick="return clearText('cmpnInId','cmpnInName' )">XÓA</button> -->
<!--                 <button type="button" class="btn-select" onclick="return openDialogue('PopupCompanyInput?param1=cmpnInId&param2=cmpnInName')">CHỌN</button> -->
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">ĐƠN VỊ NHẬP TÀI SẢN</label>
                <form:input readonly="true" path="deptInName" type="text" class="form-control"/>
                <form:input style="display:none" path="deptInId" type="text" class="form-control"/>
<!--                  <button type="button" class="btn-select" onclick="return clearText('deptInId','deptInName' )">XÓA</button> -->
<!--                 <button type="button" class="btn-select" onclick="return getDeptByCompany('cmpnInId', 'deptInId','deptInName')">CHỌN</button> -->
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">NGÀY TRẢ TÀI SẢN</label>
                <form:input readonly="true" path="dateIn" type="text"  placeholder="DD/MM/YYYY" class="form-control"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="form-group">
                <label for="usr">LÝ DO ĐIỀU ĐỘNG</label>
                <form:input path="reason" readonly="true"  type="text" class="form-control"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">NGƯỜI TẠO</label>
                <form:input readonly="true" path="userCreate" type="text" class="form-control"/>
               
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">NGÀY TẠO</label>
                <form:input  readonly="true" path="dateCreate" type="text" class="form-control"/>           
            </div>
        </div>
        <div class="col-sm-12">
            <div class="form-group">
             <label for="usr">GHI CHÚ - TRẠNG THÁI TÀI SẢN</label>
               <form:input readonly="true" path="noteCreate" type="text" class="form-control"/>
            </div>
        </div>
        </div>
        <c:if test="${moveObject.userManager != null}">
        <div class="tittle_part">PHÒNG CƠ ĐIỆN XÉT DUYỆT</div>
        <div class="row">
        
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">NGƯỜI TẠO</label>
                <form:input readonly="true" path="userManager" type="text" class="form-control"/>
               
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">NGÀY TẠO</label>
                <form:input  readonly="true" path="dateManager" type="text" class="form-control"/>           
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
             <label for="usr">GHI CHÚ</label>
               <form:input readonly="true" path="noteManager" type="text" class="form-control"/>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="form-group">
             <label for="usr">PHÒNG CƠ ĐIỆN XÁC NHẬN</label>
               <form:input readonly="true"  path="commentManager" required="true" type="text" class="form-control"/>
            </div>
        </div>
        </div>
        </c:if>
        <c:if test="${moveObject.userAccount != null}">
         <div class="tittle_part">KẾ TOÁN XÁC NHẬN</div>
        <div class="row">
       
         <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">NGƯỜI TẠO</label>
                <form:input readonly="true" path="userAccount" type="text" class="form-control"/>
               
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">NGÀY TẠO</label>
                <form:input  readonly="true" path="dateAccount" type="text" class="form-control"/>           
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
             <label for="usr">GHI CHÚ</label>
               <form:input readonly="true" path="noteAccount" type="text" class="form-control"/>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="form-group">
             <label for="usr">PHÒNG KẾ TOÁN XÁC NHẬN</label>
               <form:input  readonly="true" path="commentAccount" required="true" type="text" class="form-control"/>
            </div>
        </div>
        </div>
        </c:if>
         <c:if test="${moveObject.userStock != null}">
        <div class="tittle_part">KHO VẬN XÁC NHẬN</div>
        <div class="row">	
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">NGƯỜI TẠO</label>
                <form:input readonly="true" path="userStock" type="text" class="form-control"/>
               
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">NGÀY TẠO</label>
                <form:input  readonly="true" path="dateStock" type="text" class="form-control"/>           
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
             <label for="usr">GHI CHÚ</label>
               <form:input readonly="true" path="noteStock" type="text" class="form-control"/>
            </div>
        </div>	
		 <div class="col-sm-12">
            <div class="form-group">
             <label for="usr">PHÒNG KHO VẬN XÁC NHẬN</label>
               <form:input readonly="true"  path="commentStock" required="true" type="text" class="form-control"/>
            </div>
        </div>
        </div>
        </c:if>
    </div>
    <form:input path="status" style="display:none" type="text" class="form-control"/>
    <form:input path="deleteFg" style="display:none" type="text" class="form-control"/>
    <form:input path="cmpnCd" style="display:none" type="text" class="form-control"/>
    
    <div class="text-right" style="width: 100%; display: inline-block; background-color: rgb(148, 150, 151);">
     <a class="btn btnAction" href="<%=request.getParameter("pageBack") %>"  type="button" name="back">TRỞ VỀ</a>
     <c:if test="${moveObject.status == 'OK' }">
     <button class="btn btnAction"  type="submit" name="export">XUẤT LỆNH</button>
     </c:if>
    </div>
<!--     <span style="font-weight: 700; float:left;line-height: 35px;">Số lượng dòng: </span> -->
<%--     <input type="number" class="form-control" style="width:80px; float:left; margin-left: 10px; " name="numberRow" value="${numberRow}" min="${numberRow}" max="100"> --%>
<!--     <button class="btn btn-success" type="submit" name="addRow" style="margin-left: 10px; background-color: red; border-radius: 0px;">THÊM DÒNG</button> -->
<!--     <button class="btn btn-success" style="margin-left: 10px; background-color:green; border-radius: 0px;">KIỂM TRA</button> -->
    
        <table class="table-data-new input-table" style="margin-top: 10px;">
            <thead>
                <tr>
                    <th class="cel-bor-top-black cel-bor-left-black" style="width:80px">STT</th>
                    <th class="cel-bor-top-black">MÃ RFID</th>
                    <th class="cel-bor-top-black">MODEL</th>
                     <th class="cel-bor-top-black">SERIES</th>
                    <th class="cel-bor-top-black">KẾ TOÁN</th>
                    <th class="cel-bor-top-black">TÊN TÀI SẢN</th>
                    <th class="cel-bor-top-black">PHỤ TÙNG KÈM THEO</th>
                </tr>
            </thead>
            <tbody>
            	<c:if test="${lstChiTiet != null}">
            	<% int stt = 0;%>
				<c:forEach items="${lstChiTiet}" var="elm">
                <tr>
                    <td style="text-align: center;">
                    	<%=++stt%>
                    </td>
                    <td>
                    	 <input type="text"readonly="true" style="display:none;" name="idMove_<%=stt%>" value="${elm.getIdMove() }" class="form-control input-table-data">
                    	 <input type="text" readonly="true" name="rfid_<%=stt%>" value="${elm.getRfid() }" id="rfid_<%=stt%>" value="${elm.getRfid() }" class="form-control input-table-data">
                    </td>
                    <td>
                    	 <input type="text" readonly="true" name="model_<%=stt%>" value="${elm.getModel() }" id="model_<%=stt%>" value="${elm.getModel() }" class="form-control input-table-data">
                    </td>
                    <td>
                    	 <input type="text" readonly="true" name="series_<%=stt%>" value="${elm.getSeries() }" id="series_<%=stt%>" value="${elm.getSeries() }" class="form-control input-table-data">
                    </td>
                     <td>
                    	 <input type="text" readonly="true" name="account_<%=stt%>" value="${elm.getAccountCd() }" id="account_<%=stt%>" value="${elm.getAccountCd() }" class="form-control input-table-data">
                    </td>
                    <td>
                    	 <input type="text" readonly="true" name="name_<%=stt%>" id="name_<%=stt%>" value="${elm.getName() }" class="form-control input-table-data">
                    </td>
                    <td>
                    	 <input type="text" readonly="true" name="assesseries_<%=stt%>" id="assesseries_<%=stt%>" value="${elm.getAssesseries() }" class="form-control input-table-data">
                    </td>
                </tr>
                </c:forEach>
                </c:if>
            </tbody>
        </table>
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
<script type="text/javascript">
        function UpdateFields (text1,text2,text3,text4, param1, param2, param3, param4) {
        	// var text1 = document.getElementById (param1);
           //  var text2 = document.getElementById (param2);
           // text1.value = retValue.text1;
          //  text2.value = retValue.text2;
         // alert("ve roi");
        // alert(text1);
         document.getElementById(text1).value = param1;
         document.getElementById(text2).value = param2;
         document.getElementById(text3).value = param3;
         document.getElementById(text4).value = param4;
        }

        function ShowModal(param1, param2, param3, param4) {
            var text1 = document.getElementById (param1);
            var text2 = document.getElementById (param2);
            var text3 = document.getElementById (param3);
            var text4 = document.getElementById (param4);

            var sharedObject = {};
            sharedObject.text1 = param1;
            sharedObject.text2 = param2;
            sharedObject.text3 = param3;
            sharedObject.text4 = param4;
            var deptCd = document.getElementById('deptOutId').value;
            if (window.showModalDialog) {
                var retValue = showModalDialog ("SearchTaiSanDiChuyenNoiBo?dept="+deptCd, sharedObject, "dialogWidth:200px; dialogHeight:200px; dialogLeft:300px;");
                if (retValue) {
                    UpdateFields (retValue,param1, param2, param3, param4 );
                }
            }
            else {
                    // we want similar functionality in Opera, but it's not modal!
                var modal = window.open ("SearchTaiSanDiChuyenNoiBo?dept="+deptCd, null, "width=200,height=200,left=300,modal=yes,alwaysRaised=yes", null);
                modal.dialogArguments = sharedObject;
            }
        }
    </script>