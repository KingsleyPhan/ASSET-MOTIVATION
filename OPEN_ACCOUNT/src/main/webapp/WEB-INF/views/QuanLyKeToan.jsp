<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
   <script type="text/javascript" src="resources/js/common.js"></script>
	<style type="text/css">
		.square-color {
			width: fit-content;
			padding: 1px 18px;
			margin-right: 10px;
			float: left;
			display: flex;
		}
		.square-green {
		    background-color: #a7e6a7;
		    width: fit-content;
		    padding: 1px 18px;
		}
		.square-orange {
		    background-color: #fae4af;
		    width: fit-content;
		    padding: 1px 18px;
		}
		
		.square-color  p{
			 margin: 0px;
		    font-size: 14px;
		    font-weight: 700;
		    padding: 3px 4px;
		}
		
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
        .btnAccountant{
        	background-color: #a50d0d !important;
        }
        
        .btnGeneral {
        	background-color: gray !important
        }
	</style>
</head>
<body>

<div class="container" style="width:100%; padding: 5px;">
<%@include file="Header.jsp" %>
	<div style="width: 240px; float: left;">
     <%@include file="MenuMotivation.jsp" %>
       
    </div>
    <div style="width: calc(100% - 250px); float: left; margin-left: 10px">
    	
    	<h3 style="width:100%; text-align: center;">SỐ THEO DỎI DI CHUYỂN TSCD P.KẾ TOÁN</h3>
       <form:form action="DieuDongTaiSan" method="POST" cssStyle="display:none" modelAttribute="moveObject">	
       	
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">TÊN TÀI SẢN</label>
                <form:input path="code"  type="text" class="form-control"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">MODEL</label>
                <form:input  path="no" type="text" class="form-control"/>
            </div>
        </div>
           <div class="col-sm-4">
            <div class="form-group">
                <label for="usr">SERIES</label>
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
       	
       	<div style="margin-bottom: 10px; display: flex;">
<!--        		<div class="square-green square-color"> -->
<!--        			<p> ĐÃ TRẢ </p> -->
<!--        		</div> -->
       		
<!--        		<div class="square-orange square-color"> -->
<!--        			<p> ĐÃ TRỄ HOÀN TRẢ </p> -->
<!--        		</div> -->
       	</div>
        <table class="table-data-new">
            <thead>
                <tr>
                    <th style="width:60px" class="cel-bor-top-black cel-bor-left-black">STT</th>
                    <th style="width:120px"  class="cel-bor-top-black">MÃ LỆNH</th>
                    <th style="width:120px"  class="cel-bor-top-black">NGÀY TẠO</th>
                    
                    <th   class="cel-bor-top-black">TÊN</th>
                    <th   class="cel-bor-top-black">MODEL</th>                   
                    <th   class="cel-bor-top-black">SERIES</th>
                    <th   class="cel-bor-top-black">MÃ KẾ TOÁN</th>
                    <th   class="cel-bor-top-black">ĐƠN VỊ XUẤT</th>
                    <th   class="cel-bor-top-black">ĐƠN VỊ NHẬP</th>                   
                    <th   class="cel-bor-top-black">NGÀY KẾT THÚC</th>
                </tr>
            </thead>
            <tbody>
            <% int stt=0; %>
            <c:if test="${lstMove != null}">
            	<c:forEach items="${lstMove}" var="elm">
            	 <c:if test="${elm.lstAsset != null}">
            		<c:forEach items="${elm.lstAsset}" var="item">
            			<c:choose>   
					         <c:when test = "${item.status == 'DA_TRA'}">
					            <tr style="background-color: #a7e6a7">
					         </c:when>    
					         <c:otherwise>
					           <tr>
					         </c:otherwise>
					    </c:choose>		                
		                    <td style="text-align: center;"><%=++stt%></td>
		                    <c:if test="${elm.code != ''}">
		                     	<td style="text-align: center">${elm.no}/${elm.code}</td>
		                    </c:if>
		                     <c:if test="${elm.code == ''}">
		                     	<td style="text-align: center">${elm.no}</td>
		                    </c:if>	                   
		                    <td  style="text-align: center">${elm.dateCreate}</td>
		                    
		                    <td>${item.name}</td>
		                    <td>${item.model}</td>
		                    <td>${item.series}</td>
		                     <td>${item.accountCd}</td>
		                    <td  style="text-align: center">${elm.deptOutName}</td>
		                     <td  style="text-align: center">${elm.deptInName}</td>
		                    <td  style="text-align: center">${elm.dateIn}</td>
<!-- 		                    <td  style="text-align: center"> -->
<%-- 		                    <form action="ChoMuonTrongHeThong" method="POST"> --%>
<%-- 								<input type="text" style="display:none;" name="id" value="${elm.getId()}"/> --%>
<!-- 								<input type="text" style="display:none;" name="backurl" value="DieuDongTaiSan"/> -->
<!-- 								<button type="submit" style="background-color: yellow; border-radius: 0px; border: 1px solid black; font-weight: 700; color: black;" class="btn-table-action" name="view">XEM</button> -->
<%-- 							</form> --%>
<!-- 		                    </td> -->
		                </tr>
		               
	                 </c:forEach>
	                  </c:if>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
<script>
    (function( $ ) {
    $.widget( "custom.combobox", {
    _create: function() {
    this.wrapper = $( "<span>" )
    .addClass( "custom-combobox" )
    .insertAfter( this.element );
    
    this.element.hide();
    this._createAutocomplete();
    this._createShowAllButton();
    },
    
    _createAutocomplete: function() {
    var selected = this.element.children( ":selected" ),
    value = selected.val() ? selected.text() : "";
    
    this.input = $( "<input>" )
    .appendTo( this.wrapper )
    .val( value )
    .attr( "title", "" )
    .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
    .autocomplete({
    delay: 0,
    minLength: 0,
    source: $.proxy( this, "_source" )
    })
    .tooltip({
    tooltipClass: "ui-state-highlight"
    });
    
    this._on( this.input, {
    autocompleteselect: function( event, ui ) {
    ui.item.option.selected = true;
    this._trigger( "select", event, {
    item: ui.item.option
    });
    },
    
    autocompletechange: "_removeIfInvalid"
    });
    },
    
    _createShowAllButton: function() {
    var input = this.input,
    wasOpen = false;
    
    $( "<a>" )
    .attr( "tabIndex", -1 )
    .attr( "title", "Show All Items" )
    .tooltip()
    .appendTo( this.wrapper )
    .button({
    icons: {
    primary: "ui-icon-triangle-1-s"
    },
    text: false
    })
    .removeClass( "ui-corner-all" )
    .addClass( "custom-combobox-toggle ui-corner-right" )
    .mousedown(function() {
    wasOpen = input.autocomplete( "widget" ).is( ":visible" );
    })
    .click(function() {
    input.focus();
    
    // Close if already visible
    if ( wasOpen ) {
    return;
    }
    
    // Pass empty string as value to search for, displaying all results
    input.autocomplete( "search", "" );
    });
    },
    
    _source: function( request, response ) {
    var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
    response( this.element.children( "option" ).map(function() {
    var text = $( this ).text();
    if ( this.value && ( !request.term || matcher.test(text) ) )
    return {
    label: text,
    value: text,
    option: this
    };
    }) );
    },
    
    _removeIfInvalid: function( event, ui ) {
    
    // Selected an item, nothing to do
    if ( ui.item ) {
    return;
    }
    
    // Search for a match (case-insensitive)
    var value = this.input.val(),
    valueLowerCase = value.toLowerCase(),
    valid = false;
    this.element.children( "option" ).each(function() {
    if ( $( this ).text().toLowerCase() === valueLowerCase ) {
    this.selected = valid = true;
    return false;
    }
    });
    
    // Found a match, nothing to do
    if ( valid ) {
    return;
    }
    
    // Remove invalid value
    this.input
    .val( "" )
    .attr( "title", value + " didn't match any item" )
    .tooltip( "open" );
    this.element.val( "" );
    this._delay(function() {
    this.input.tooltip( "close" ).attr( "title", "" );
    }, 2500 );
    this.input.data( "ui-autocomplete" ).term = "";
    },
    
    _destroy: function() {
    this.wrapper.remove();
    this.element.show();
    }
    });
    })( jQuery );
    
    $(function() {
            $( "#combobox1" ).combobox();
            $( "#combobox2" ).combobox();
            $( "#combobox3" ).combobox();

             $( "#toggle" ).click(function() {
                    $( "#combobox1" ).toggle();
            });
            $( "#toggle" ).click(function() {
                    $( "#combobox2" ).toggle();
            });
            $( "#toggle" ).click(function() {
                    $( "#combobox3" ).toggle();
            });
    });
    </script>
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
