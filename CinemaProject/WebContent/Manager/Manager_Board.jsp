<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pageBoard = request.getParameter("source3");
	if(pageBoard==null){
		pageBoard="Board_Delete";
	}
%>
<style>
	#Manager_Board{
		width : 100%;
		border-bottom : 1px solid #cccccc;
		border-bottom-width : thin;
		margin-top: 5px;
	}
</style>
<form>
	<div>
		<table id="Manager_Board">
			<tr>
				<th>
					게시판 리스트
				</th>
			</tr>
		</table>
		<jsp:include page='<%=pageBoard+".jsp"%>'/>
	</div>
</form>