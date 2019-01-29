<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pagefile = request.getParameter("page");
	if(pagefile==null){
		pagefile="Manager_Movie";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
<style>
	#M_I_Menu{
		text-decoration : none;
		color : black;
	}
	#M_I_Menu:hover{
		text-decoration : none;
		color : #ff382e;
	}
	#M_I_Table{
		border : 2px solid #888888;
		font-size: 15px;
		font-weight: 700;
	}
	#M_I_Goback{
		text-decoration : none;
		color : white;
		background-color : #666666;
		padding : 5px 10px 5px 10px;
		float : right;
		margin-right: 10%;
	}
</style>
</head>
<body id="Main_Body"> 
	<header style="margin-left: 15%; margin-right: 15%;">
		<div id="Manager_Header">
			<h2 align="center">
				관리자 모드 
				<label style="font-size : 12px; color : #888888;">
					version 0.1
					<a id="M_I_Goback" href="../Main/MainHome.jsp">OCC 메인으로</a>
				</label>
			</h2>
			<table id="M_I_Table" width="100%" height="100%">
				<tr>
					<th>
						<a id="M_I_Menu" href="Manager_Main.jsp?page=Manager_Movie">영화 관리</a>
					</th>
					<th>
						<a id="M_I_Menu" href="gradeList">평점 관리</a>
					</th>
					<th>
						<a id="M_I_Menu" href="boardList">게시판 관리</a>
					</th>
					<th>
						<a id="M_I_Menu" href="bookingList">예매 관리</a>
					</th>
					<th>
						<a id="M_I_Menu" href="U_GraphAction.do?Graph=U_Graph_Gender">회원 관리</a>
					</th>
					<th>
						<a id="M_I_Menu" href="Manager_Main.jsp?page=Manager_Theater">영화관 관리</a>
					</th>
				</tr>
			</table>
			<jsp:include page='<%=pagefile+".jsp"%>'/>
		</div>
	</header>
</body>
</html>