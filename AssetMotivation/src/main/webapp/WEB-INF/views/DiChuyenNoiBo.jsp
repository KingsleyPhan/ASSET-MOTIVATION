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
		
			<div style="width: 100%;">
				<form:form action="DiChuyenNoiBo" method="POST" modelAttribute="DiChuyenNoiBoForm">
				<div class="form-search">
					<div class="row">
						<div class="col-lg-4">
							<label>ĐƠN VỊ XUẤT</label><br>
							<div class="ui-widget">
								<form:select path="deptOut" items="${lstDept}"></form:select>
							</div>
						</div>
						<div class="col-lg-4">
							<label>ĐƠN VỊ NHẬP</label><br>
							<div class="ui-widget">
								<form:select path="deptIn" items="${lstDept}"></form:select>
							</div>
						</div>
						<div class="col-lg-4">

							<div class="form-group">
								<label>NGÀY XUẤT</label><br>
								<div class="input-group">
									<form:input path="dateOut" type="text" class="form-control date-input" readonly="readonly" />
									<label class="input-group-btn" for="dateOut"> <span class="btn btn-default"> <span class="glyphicon glyphicon-calendar"></span>
									</span>
									</label>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<label>MÃ LỆNH</label><br>
							<form:input path="sticker" class="form-control" />
						</div>
						<div class="col-lg-4">
							<label>TRẠNG THÁI LỆNH</label><br>
							<div class="ui-widget">
								<form:select path="status">
									<form:option value="ALL">TẤT CẢ</form:option>
									<form:option value="NEW">CHƯA DUYỆT</form:option>
									<form:option value="CD">CƠ ĐIỆN DUYỆT</form:option>
									<form:option value="KT">KẾT TOÁN DUYỆT</form:option>
									<form:option value="KV">KHO VẬN DUYỆT</form:option>
									<form:option value="TH">ĐÃ THU HỒI LỆNH</form:option>
								</form:select>
							</div>
						</div>
					</div>
				</div>


				<div class="control-action">
					<button class="btn btn-action" type="submit" name="back">QUAY LẠI</button>
					<button class="btn btn-action" type="submit" name="TaoLenh">TẠO LỆNH</button>
					<button class="btn btn-action" type="submit" name="search">TÌM KIẾM</button>

				</div>
				</form:form>
				<div>
					<ul class="nav nav-pills" style="margin-bottom: 5px;">
						<li class="active"><a data-toggle="pill" href="#home">TỔNG QUÁT LỆNH</a></li>
						<li><a data-toggle="pill" href="#menu1">CHI TIẾT LỆNH</a></li>
						<li><a data-toggle="pill" href="#menu2">CHI TIẾT TÀI SẢN</a></li>
					</ul>

					<div class="tab-content">
						<div id="home" class="tab-pane fade in active">
						
							<c:if test="${lst != null}">
								<table class="table-data-new">
									<thead>
										<tr>
											<th class="cel-bor-top-black cel-bor-left-black" style="width:50px; text-align: center;">STT</th>
											<th class="cel-bor-top-black" style="width:100px; text-align: center;">MÃ LỆNH</th>
											<th class="cel-bor-top-black">ĐV NHẬP</th>
											<th class="cel-bor-top-black">ĐV XUẤT</th>
											<th class="cel-bor-top-black">NGÀY ĐIỀU ĐỘNG</th>
											<th class="cel-bor-top-black" style="width:100px; text-align: center;">TRẠNG THÁI</th>
											<th class="cel-bor-top-black cel-bor-right-black"style="width:150px; text-align:center;">KHAI BÁO</th>
											<th class="cel-bor-top-black cel-bor-right-black" style="width:150px; text-align:center;">THAO TÁC</th>
											<th class="cel-bor-top-black cel-bor-right-black" style="width:80px; text-align:center;">XOÁ</th>
										</tr>
									</thead>
									<tbody>
									<%
										int stt = 0;
									%>
										 <c:forEach items="${lst}" var="elm">
										<tr>
											<td style="text-align: center;"><%=++stt%></td>
											<td style="text-align: center;">${elm.getNoNumber()}</td>
											<td>${elm.getDeptInName()}</td>
											<td>${elm.getDeptOutName()}</td>
											<td>${elm.getDateStart()}</td>
											<td style="text-align: center;">${elm.getStatus()}</td>
											<td style="text-align: center;"> 
												<c:choose>      
										         <c:when test = "${elm.getStatus().trim() == 'NEW'}">
										            <form action="DiChuyenNoiBo" method="POST">
													<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
													<button type="submit" name="declare">KHAI BÁO TÀI SẢN</button>
													</form>
										         </c:when>
										         
										         <c:when test = "${elm.getStatus().trim() == 'NOT_PCD'}">
										           <form action="DiChuyenNoiBo" method="POST">
													<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
													<button type="submit" name="declare">KHAI BÁO TÀI SẢN</button>
													</form>
										         </c:when>
										         
										         <c:otherwise>
										            <form action="DiChuyenNoiBo" method="POST">
													<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
													<button type="submit" name="view_card">XEM LỆNH</button>
													</form>
										         </c:otherwise>
										      </c:choose>
												
											</td>
											<td style="text-align: center;">
												<c:if test = "${elm.getStatus().trim() == 'NEW'}">
													<form action="DiChuyenNoiBo" method="POST">
													<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
													<button type="submit" name="PCD">PHÒNG CƠ ĐIỆN</button>
													</form>
												</c:if>
												<c:if test="${elm.getStatus().trim() == 'NOT_PCD'}">
													<form action="DiChuyenNoiBo" method="POST">
													<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
													<button type="submit" name="PCD">PHÒNG CƠ ĐIỆN</button>
													</form>
												</c:if>
												<c:if test="${elm.getStatus().trim() == 'PCD'}">
													<form action="DiChuyenNoiBo" method="POST">
													<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
													<button type="submit" name="KT">KẾ TOÁN DUYỆT</button>
													</form>
												</c:if>
												<c:if test="${elm.getStatus().trim() == 'NOT_KT'}">
													<form action="DiChuyenNoiBo" method="POST">
													<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
													<button type="submit" name="KT">KẾ TOÁN DUYỆT</button>
													</form>
												</c:if>
												<c:if test="${elm.getStatus().trim() == 'KT'}">
													<form action="DiChuyenNoiBo" method="POST">
													<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
													<button type="submit" name="KV">KHO VẬN DUYỆT</button>
													</form>
												</c:if>
												<c:if test="${elm.getStatus().trim() == 'NOT_KV'}">
													<form action="DiChuyenNoiBo" method="POST">
													<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
													<button type="submit" name="KV">KHO VẬN DUYỆT</button>
													</form>
												</c:if>
												<c:if test="${elm.getStatus().trim() == 'OK'}">
													<form action="DiChuyenNoiBo" method="POST">
													<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
													<span style="padding: 4px 10px; background-color: green; color: #FFFFFF">ĐÃ ĐIỀU ĐỘNG</span>
													</form>
												</c:if>
											</td>
											<td style="text-align: center;">
												<c:if test="${elm.getStatus().trim() == 'NEW'}">
													<form action="DiChuyenNoiBo" method="POST">
														<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
														<button type="submit" name="delete">XOÁ</button>
													</form>
												</c:if>
												<c:if test="${elm.getStatus().trim() == 'NOT_PCD'}">
													<form action="DiChuyenNoiBo" method="POST">
														<input type="text" style="display:none;" name="couponId" value="${elm.getId()}"/>
														<button type="submit" name="delete">XOÁ</button>
													</form>
												</c:if>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
						</div>
						<div id="menu1" class="tab-pane fade">
							<h3>Đang cập nhật .... </h3>
							
					</div>
				</div>
			</div>
		
	</div>
</body>
<script>
	(function($) {
		$
				.widget(
						"custom.combobox",
						{
							_create : function() {
								this.wrapper = $("<span>").addClass(
										"custom-combobox").insertAfter(
										this.element);

								this.element.hide();
								this._createAutocomplete();
								this._createShowAllButton();
							},

							_createAutocomplete : function() {
								var selected = this.element
										.children(":selected"), value = selected
										.val() ? selected.text() : "";

								this.input = $("<input>")
										.appendTo(this.wrapper)
										.val(value)
										.attr("title", "")
										.addClass(
												"custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left")
										.autocomplete({
											delay : 0,
											minLength : 0,
											source : $.proxy(this, "_source")
										}).tooltip({
											tooltipClass : "ui-state-highlight"
										});

								this._on(this.input, {
									autocompleteselect : function(event, ui) {
										ui.item.option.selected = true;
										this._trigger("select", event, {
											item : ui.item.option
										});
									},

									autocompletechange : "_removeIfInvalid"
								});
							},

							_createShowAllButton : function() {
								var input = this.input, wasOpen = false;

								$("<a>")
										.attr("tabIndex", -1)
										.attr("title", "Show All Items")
										.tooltip()
										.appendTo(this.wrapper)
										.button(
												{
													icons : {
														primary : "ui-icon-triangle-1-s"
													},
													text : false
												})
										.removeClass("ui-corner-all")
										.addClass(
												"custom-combobox-toggle ui-corner-right")
										.mousedown(
												function() {
													wasOpen = input
															.autocomplete(
																	"widget")
															.is(":visible");
												}).click(function() {
											input.focus();

											// Close if already visible
											if (wasOpen) {
												return;
											}

											// Pass empty string as value to search for, displaying all results
											input.autocomplete("search", "");
										});
							},

							_source : function(request, response) {
								var matcher = new RegExp($.ui.autocomplete
										.escapeRegex(request.term), "i");
								response(this.element
										.children("option")
										.map(
												function() {
													var text = $(this).text();
													if (this.value
															&& (!request.term || matcher
																	.test(text)))
														return {
															label : text,
															value : text,
															option : this
														};
												}));
							},

							_removeIfInvalid : function(event, ui) {

								// Selected an item, nothing to do
								if (ui.item) {
									return;
								}

								// Search for a match (case-insensitive)
								var value = this.input.val(), valueLowerCase = value
										.toLowerCase(), valid = false;
								this.element
										.children("option")
										.each(
												function() {
													if ($(this).text()
															.toLowerCase() === valueLowerCase) {
														this.selected = valid = true;
														return false;
													}
												});

								// Found a match, nothing to do
								if (valid) {
									return;
								}

								// Remove invalid value
								this.input.val("").attr("title",
										value + " didn't match any item")
										.tooltip("open");
								this.element.val("");
								this._delay(function() {
									this.input.tooltip("close").attr("title",
											"");
								}, 2500);
								this.input.data("ui-autocomplete").term = "";
							},

							_destroy : function() {
								this.wrapper.remove();
								this.element.show();
							}
						});
	})(jQuery);

	$(function() {
		$("#deptOut").combobox();
		$("#deptIn").combobox();
		$("#status").combobox();

		$("#toggle").click(function() {
			$("#deptOut").toggle();
		});
		$("#toggle").click(function() {
			$("#deptIn").toggle();
		});
		$("#toggle").click(function() {
			$("#status").toggle();
		});
	});
</script>

</html>
