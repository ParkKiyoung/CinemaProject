<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pageGrade = request.getParameter("source2");
	if(pageGrade==null){
		pageGrade="Grade_Delete";
	}
%>
<style>
	#Manager_Grade{
		width : 100%;
		border-bottom : 1px solid #cccccc;
		border-bottom-width : thin;
		margin-top: 5px;
	}
</style>
<form>
	<div>
		<table id="Manager_Grade">
			<tr>
				<th>
					평점 리스트
				</th>
			</tr>
		</table>
		<jsp:include page='<%=pageGrade+".jsp"%>'/>
	</div>
</form>