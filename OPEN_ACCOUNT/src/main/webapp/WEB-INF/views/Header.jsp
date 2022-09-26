
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row header">
	<div class="col-lg-6">
		<span><%=request.getSession().getAttribute("SESSION_CMPN_NAME")%></span>
	</div>
	<div class="col-lg-6" style="text-align: right">
		<a href="/Motivation" style="text-decoration: none; color: #FFFFFF"><span><%=request.getSession().getAttribute("SESSION_USER_NAME")%> (<%=request.getSession().getAttribute("SESSION_USER_CD")%>)</span></a>
	</div>
</div>
