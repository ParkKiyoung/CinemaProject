<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pageTheater = request.getParameter("source6");
	if(pageTheater==null){
		pageTheater="Theater_Insert_Temp";
	}
%>
<style>
	#Manager_Theater{
		width : 100%;
		border-bottom : 1px solid #cccccc;
		border-bottom-width : thin;
		margin-top: 5px;
	}
	#Mtt_a{
		text-decoration : none;
		color : black;
	}
	#Mtt_a:hover{
		text-decoration : none;
		color : #ff382e;
	}
</style>
	<div>
		<table  id="Manager_Theater">
			<tr>
				<th>
					<a id="Mtt_a" href="Manager_Main.jsp?page=Manager_Theater&source6=Theater_Insert_Temp">영화관 등록</a>
				</th>
				<th>
					<a id="Mtt_a" href="Manager_Main.jsp?page=Manager_Theater&source6=Theater_Control_Temp">영화관 관리</a>
				</th>
			</tr>
		</table>
	<jsp:include page='<%=pageTheater+".jsp"%>'/>
	</div>
