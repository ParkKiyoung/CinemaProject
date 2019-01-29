<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
	.Menubar li{
		display : inline-block;
		list-style-type : none;
		list-style : none;
		font-weight : 700;
		font-size : 25px;
		margin-left : 2%;
		margin-right : 2%;
	}
	.Menubar ul{
		text-align : center;
	}
	.Menubar{
		width : 100%;
	}
	#Top_Line_Area{
		margin-left: 10%;
		margin-right: 10%;
		font : bold;
		font-size : 12px;
	}
</style>
<script>
function login(){
   location.href = "../Main/MainLoginTemp.jsp";
}
function join(){
	location.href = "../Main/MainJoinTemp.jsp";
}
function logout(){
   location.href = "../Main/logout";
}
function myInfo(num){
	location.href = "../Main/myActivity?num="+num;
}
function mainSearch(){
	location.href = "../Main/MainSearch?word="+$("#SearchBar").val();
}
</script>
<header>
	<div id="Top_Line_Area" align="right">
		<table style="font-size : 13.5px; font-weight : bold;" id="H_Login">
               <tr>
               <c:if test="${mb==null}">
                  <td><a href = "javascript:login()">로그인</a></td>
                  <td> | </td>
                  <td><a href="javascript:join()">회원가입</a></td>
               </c:if>
               <c:if test="${mb!=null}">
               	<c:if test="${mb.getUserid()=='admin'}">
	               <td style="color : red;">${mb.getName()}님</td>
	               <td> | </td>
	               <td><a style="color : red;" href = "../Manager/Manager_Main.jsp">관리자 페이지</a></td>
	               <td> | </td>
	               <td><a href = "javascript:myInfo(${mb.num })">마이페이지</a></td>
	               <td> | </td>
	               <td><a href = "javascript:logout()">로그아웃</a></td>
	           	</c:if>
	           	<c:if test="${mb.getUserid()!='admin'}">
	               <td>${mb.getName()}님</td>
	               <td> | </td>
	               <td><a href = "javascript:myInfo(${mb.num })">마이페이지</a></td>
	               <td> | </td>
	               <td><a href = "javascript:logout()">로그아웃</a></td>
	           	</c:if>
               </c:if>
               </tr>
		</table>
	</div>
        <hr style="margin-top : 0px; width : 100%;">
	<br>
	<table align="center">
		<tr>
			<td>
				<div id="Main_Top_Area">
					<a href="../Main/MainHome.jsp"><img width="250" height="60" src="../Images/MainLogo.png"></a>
					<input type="text" id="SearchBar" name="SearchBar">
					<a id="SearchBtn" name="SearchBtn" onclick="javascript:mainSearch()">검색</a>
				</div>
				<br>
			</td>
		</tr>
	</table>
</header>
<nav>
	<div class="Menubar">
		<hr>
		<ul>
			<li id="M_Home"><a href="../Main/MainHome.jsp">홈</a></li>
			<li id="M_Now"><a href="../Movie/OnScreen">현재 상영작</a></li>
			<li id="M_Future"><a href="../Movie/RelScreen">상영 예정작</a></li>
			<li id="M_Future"><a href="../Movie/OutScreen">영화</a></li>
			<li id="M_Reservation"><a href="../Main/ReservationTemp.jsp">예매</a></li>
			<li id="M_Review"><a href="../Grade/MovieAfter.do">평점</a></li>
			<li id="M_Board"><a href="../Board/boardList">게시판</a></li>
		</ul>
		<hr>
	</div>
</nav>