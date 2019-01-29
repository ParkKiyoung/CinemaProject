<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
<link rel="stylesheet" type="text/css" href="../CSS/TotalStyle.css">
<style>
#B_Range_Btn{
	border : none;
	background-color : #ffffff;
	color : #111111;
	font-weight : bolder;
	font-size : 15px;
	padding: 5px 10px 5px 10px;
	border-radius: 3px;
}
#B_Range_Btn:hover{
	border : none;
	background-color : #111111;
	color : #ffffff;
	font-weight : bolder;
	font-size : 15px;
	padding: 5px 10px 5px 10px;
	border-radius: 3px;
	cursor : pointer;
}
#B_R_Btn{
	border : none;
	background-color : #ffffff;
	color : #111111;
	font-weight : bolder;
	font-size : 15px;
	padding: 5px 10px 5px 10px;
	border-radius: 3px;
}
#B_R_Btn:hover{
	border : none;
	background-color : #ff382e;
	color : #ffffff;
	font-weight : bolder;
	font-size : 15px;
	padding: 5px 10px 5px 10px;
	border-radius: 3px;
	cursor : pointer;
}
</style>
<script>
	function RR_Btn(){
		location.href="../Main/ReservationTemp.jsp";
	}
	function LV_Btn(){
		location.href="RelScreen";
	}
	function IV_Btn(){
		location.href="RelScreen_2";
	}
</script>
</head>
<body>
	<div class="LoadMain"><jsp:include page="../Main/MainIndex.jsp" /></div>
	<div class="ViewEntire" align="center">
		<h2>상영 예정작</h2>
		<br> <br> <br>
		<div class="Rel_Div" align="right">
			<input type="button" id="B_R_Btn" onclick="RR_Btn()" value="예매하기">
			<input type="button" id="B_Range_Btn" onclick="LV_Btn()" value="리스트뷰">
			<input type="button" id="B_Range_Btn" onclick="IV_Btn()" value="이미지뷰">
		</div>
		<hr>
		<div align="center">
			<table>
				<tr>
					<c:forEach items="${movies }" var="mov" varStatus="status">
						<td style="width: 200px; height: 300px; font-size: 12x"><a
							href="View?movienum=${mov.num }"><img
								src="PosterIMG/${mov.img }" width=150px height=200px><br>${fn:substring(mov.subject, 0, 10)}
								<c:if test="${fn:length(mov.subject)>10 }">
							...
							</c:if><br>평점 : ${mov.rel_rating }</a></td>
						<c:if test="${status.count%5==0 }">
				</tr>
				<tr>
					</c:if>
					</c:forEach>
				</tr>
			</table>
		</div>

	</div>
	<div id="Footer">
		<jsp:include page="../Main/MainFooter.jsp" />
	</div>
</body>
</html>

