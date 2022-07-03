<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  	 	

  </style>
</head>
<body>

<div class="container" style="width:100%; padding-top: 5px;">
<%@include file="Header.jsp" %>
  <form:form action="TaoLenhDiChuyenNoiBo" modelAttribute="TaoLenhDiChuyenNoiBoForm">
    <div style="width: 100%;">
    	<div class="row" style="margin-bottom: 8px;">
    	<%@include file="message.jsp" %>
    	</div>
    
        <div class="form-search">
        	<div class="row" style="margin-bottom: 8px;">
                <div class="col-lg-4">
                    <label>MÃ QUẢN LÝ HỆ THỐNG</label><br>
				    <form:input path="id" type="text" class="form-control input-readonly"  readonly="true" />
                </div>
                <div class="col-lg-4">
                    <label>SỐ LỆNH</label><br>
				    <form:input path="noNumber" type="text" class="form-control input-readonly" readonly="true" />
				    <form:errors path="noNumber" cssClass="error" />
                </div>
                <div class="col-lg-4">
                    <label>MÃ LỆNH</label><br>
				    <form:input path="sticker" type="text" class="form-control input-readonly" readonly="true" />
                	<form:errors path="sticker" cssClass="error" />
                </div>
            </div>
            <div class="row" style="margin-bottom: 8px;">
                <div class="col-lg-4">
                    <label>ĐƠN VỊ XUẤT</label><br>
                    <div class="ui-widget">
                    	<form:select path="deptOut" items="${lstDept}"></form:select>
                    </div>
                    <form:errors path="deptOut" cssClass="error" />
                </div>
                <div class="col-lg-4">
                    <label>ĐƠN VỊ NHẬP</label><br>
                    <div class="ui-widget">
                        <form:select path="deptIn" items="${lstDept}"></form:select>
                    </div>
                    <form:errors path="deptIn" cssClass="error" />
                </div>
                <div class="col-lg-4">
                  
                    <div class="form-group">
                        <label>NGÀY XUẤT</label><br>
                          <div class="input-group">
					            <form:input path="dateOut" type="text" class="form-control date-input" readonly="readonly" />
					            <label class="input-group-btn" for="dateOut">
					                <span class="btn btn-default">
					                    <span class="glyphicon glyphicon-calendar"></span>
					                </span>
					            </label>
					        </div>
					        <form:errors path="dateOut" cssClass="error" />
                      </div>
                      
                </div>
                <div class="col-lg-12">
                    <label>LÝ DO ĐIỀU ĐỘNG</label><br>
                      <form:input path="reason" type="text" class="form-control"/>
                      <form:errors path="reason" cssClass="error" />
                </div>
            </div>
            
        </div>
        <div class="control-action">
            <button class="btn btn-action" type="submit" name="back">QUAY LẠI</button>
            <button class="btn btn-action"  type="submit" name="create">TẠO LỆNH</button>
        </div>
    </div>
  </form:form>
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
            $( "#deptIn" ).combobox();
            $( "#deptOut" ).combobox();

             $( "#toggle" ).click(function() {
                    $( "#deptIn" ).toggle();
            });
            $( "#toggle" ).click(function() {
                    $( "#deptOut" ).toggle();
            });
    });
    </script>
     <script type="text/javascript">
        $(function () {
            $('#dateOut').datepicker({
                format: "dd/mm/yyyy"
            });
        });
    </script>
</html>
