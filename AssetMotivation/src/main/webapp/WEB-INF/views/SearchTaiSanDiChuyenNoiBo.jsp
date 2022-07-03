<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<script type="text/javascript">
        function Init () {
            var sharedObject = window.dialogArguments;

        
        }

        function OnOK (rfid, name, model, series) {
//             var forename = document.getElementById ("forename");
//             var surname = document.getElementById ("surname");

//             if (window.showModalDialog) {
//                 var sharedObject = {};
//                 sharedObject.text1 = rfid;
//                 sharedObject.text2 = model;

//                 window.returnValue = sharedObject;
//             }
//             else {
//                     // if not modal, we cannot use the returnValue property, we need to update the opener window
//                var sharedObject = window.dialogArguments;
//                window.opener.UpdateFields(sharedObject.text1, sharedObject.text2, sharedObject.text3, sharedObject.text4,rfid, name, model, series);
//                // var sharedObject = window.dialogArguments;
//                 //window.opener.document.getElementById(sharedObject.text1).value = rfid;
//                 //window.opener.document.getElementById(sharedObject.text2).value = model;
//             }

			var i = opener.document.getElementById('idSelect').value;
			opener.document.getElementById('rfid_'+i).value = rfid;
			opener.document.getElementById('model_'+i).value = model;
			opener.document.getElementById('series_'+i).value = series;
			opener.document.getElementById('name_'+i).value = name;
			
            window.close ();
        }

        function OnCancel () {
            window.close ();
        }
    </script>
<script type="text/javascript">
	function getvalue(rfid, name, model, series)
	{
		if (window.opener != null && !window.opener.closed) {
            var txt1 = window.opener.document.getElementById('<%= request.getParameter("param1") %>');
            var txt2 = window.opener.document.getElementById('<%= request.getParameter("param2") %>');
            var txt3 = window.opener.document.getElementById('<%= request.getParameter("param3") %>');
            var txt4 = window.opener.document.getElementById('<%= request.getParameter("param4") %>');
            txt1.value = rfid;
            txt2.value = name;
            txt3.value = model;
            txt4.value = series;
        }
    window.close();
	}
</script>
</head>
<body onload="">

	<div class="container" style="width: 100%; padding: 5px;">
		<%@include file="Header.jsp"%>
		
			<div style="width: 100%;">
				<form:form action="SearchTaiSanDiChuyenNoiBo?dept=${deptCd}" method="POST" modelAttribute="TaiSanDiChuyenNoiBoForm">
				<div class="form-search">
					<div class="row">
						<div class="col-lg-4">
							<label>TÊN TÀI </label><br>
							<div class="ui-widget">
								<form:select path="name" items="${lstName}"></form:select>
							</div>
						</div>
						<div class="col-lg-4">
							<label>RFFID</label><br>
							<div class="ui-widget">
								<form:select path="rfid" items="${lstRfid}"></form:select>
							</div>
						</div>
						<div class="col-lg-4">
							<label>MODEL</label><br>
							<div class="ui-widget">
								<form:select path="model" items="${lstModel}"></form:select>
							</div>
						</div>
						<div class="col-lg-4">
							<label>SERIES</label><br>
							<div class="ui-widget">
								<form:select path="series" items="${lstSeries}"></form:select>
							</div>
						</div>
					</div>
				</div>


				<div class="control-action">
					<button class="btn btn-action" type="button" onclick="window.click()" name="back">QUAY LẠI</button>
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
											<th class="cel-bor-top-black" style="width:100px; text-align: center;">RFID</th>
											<th class="cel-bor-top-black">TÊN TÀI SẢN</th>
											<th class="cel-bor-top-black">MODEL</th>
											<th class="cel-bor-top-black">SERIES</th>
											<th class="cel-bor-top-black" style="width:100px; text-align: center;">CHN</th>
											
										</tr>
									</thead>
									<tbody>
									<%
										int stt = 0;
									%>
										 <c:forEach items="${lst}" var="elm">
										<tr>
											<td style="text-align: center;"><%=++stt%></td>
											<td style="text-align: center;">${elm.getRfid()}</td>
											<td>${elm.getName()}</td>
											<td>${elm.getModel()}</td>
											<td>${elm.getSeries()}</td>
											<td style="text-align: center;">
												<button type="button" class="btn btn-action" onclick="return OnOK('${elm.getRfid()}','${elm.getName()}','${elm.getModel()}','${elm.getSeries()}')">CHỌN</button>
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
		$("#name").combobox();
		$("#rfid").combobox();
		$("#series").combobox();
		$("#model").combobox();

		$("#toggle").click(function() {
			$("#name").toggle();
		});
		$("#toggle").click(function() {
			$("#rfid").toggle();
		});
		$("#toggle").click(function() {
			$("#series").toggle();
		});
		$("#toggle").click(function() {
			$("#model").toggle();
		});
	});
</script>

</html>
