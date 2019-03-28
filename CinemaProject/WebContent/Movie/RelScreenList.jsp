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
<script>
function movieView(num){
	location.href = "../Movie/View?movienum="+num;
}
</script>
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
		<br>
		<br>
		<br>
		<div align="right">
			<input type="button" id="B_R_Btn" onclick="RR_Btn()" value="예매하기">
			<input type="button" id="B_Range_Btn" onclick="LV_Btn()" value="리스트뷰"> 
			<input type="button" id="B_Range_Btn" onclick="IV_Btn()" value="이미지뷰">
		</div>
		<c:set var="dateline" value="0000-00"></c:set>
		<c:forEach items="${movies }" var="mov">
			<c:if test="${fn:substring(mov.rel_date, 0, 7)!=dateline }">
				<c:set var="dateline" value="${fn:substring(mov.rel_date, 0, 7)}"></c:set>
				<hr>
				<h3 align="center">${fn:substring(mov.rel_date, 0, 4)}년
					${fn:substring(mov.rel_date, 5, 7)}월 개봉예정작</h3>
			</c:if>
			<form>
				<table width="1000px" height="200px">
					<tr>
						<td width="200px" rowspan="5" valign="top"><img
							src="PosterIMG/${mov.img }" width=150px height=200px></td>
						<td width="100px">제목</td>
						<td width="300px">${mov.subject }</td>
						<td width="100px">개봉전평점</td>
						<td width="300px">${mov.rel_rating }점</td>
					</tr>
					<tr>
						<td>장르</td>
						<td>${mov.genre }</td>
						<td>개봉예정일</td>
						<td>${mov.rel_date }</td>
					</tr>
					<tr>
						<td>감독</td>
						<td>${mov.director }</td>
						<td>관람가</td>
						<td><c:if test="${mov.age_res==0 }">전체관람가</c:if> <c:if
								test="${mov.age_res==12 }">12세관람가</c:if> <c:if
								test="${mov.age_res==15 }">15세관람가</c:if> <c:if
								test="${mov.age_res==19 }">청소년관람불가</c:if></td>
					</tr>
					<tr>
						<td>출연</td>
						<td colspan="3">${fn:substring(mov.actor, 0, 30)}<c:if
								test="${fn:length(mov.actor)>30 }">
							...
							</c:if></td>
					</tr>
					<tr>
						<td height="90px" valign="top">줄거리</td>
						<td colspan="3" valign="top">${fn:substring(mov.summary, 0, 200)}
							<c:if test="${fn:length(mov.summary)>200 }">
							...
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center"><input type="button" id="B_Range_Btn"
							value="상세보기" onclick="javascript:movieView(${mov.num })"></td>
				</table>
			</form>
			<br>
			<br>
		</c:forEach>
	</div>
	<div id="Footer">
		<jsp:include page="../Main/MainFooter.jsp" />
	</div>
</body>
</html>

