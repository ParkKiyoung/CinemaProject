<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pageReservation = request.getParameter("source4");
	if(pageReservation==null){
		pageReservation="Reservation_List";
	}
%>
<style>
	#Manager_Reservation{
		width : 100%;
		border-bottom : 1px solid #cccccc;
		border-bottom-width : thin;
		margin-top: 5px;
	}
</style>
<form>
	<div>
		<table id="Manager_Reservation">
			<tr>
				<th>
					예매 현황
				</th>
			</tr>
		</table>
		<jsp:include page='<%=pageReservation+".jsp"%>'/>
	</div>
</form>