<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String pageGraph = request.getParameter("Graph");
	if(pageGraph==null){
		pageGraph="U_Graph_Gender";
	}
%>
<style>
	#Mug_a{
		text-decoration: none;
		color: black;
		font-size: 14px;
	}
	#Mug_a:hover{
		text-decoration: none;
		color: #ff382e;
		font-size: 14px;
		border-collapse: collapse;
		border-bottom: 2px solid #ff382e;
	}
</style>
<div style="margin-top: 2%; border: 1px solid #dddddd;" align="center">
	<label> | </label>
	<a id="Mug_a" href="U_GraphAction.do?Graph=U_Graph_Gender">남여 회원 현황</a>
	<label> | </label>
	<a id="Mug_a" href="U_GraphAction.do?Graph=U_Graph_Genre">장르 선호도 현황</a>
	<label> | </label>
	<a id="Mug_a" href="U_GraphAction.do?Graph=U_Graph_Age">연령별 회원 현황</a>
	<label> | </label>
	<jsp:include page='<%=pageGraph+".jsp"%>'/>
</div>





















