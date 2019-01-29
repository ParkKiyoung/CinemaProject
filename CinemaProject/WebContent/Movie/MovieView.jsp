<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
<link rel="stylesheet" type="text/css" href="../CSS/TotalStyle.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	function gradeInsert() {
		if (!$("#membernum").val()) {
			alert("로그인이 필요한 기능입니다.");
			return false;
		} else if (!$("#reply").val()) {
			alert("내용을 입력하세요.");
			return false;
		} else {
			frm.action = "GradeInsert"
			frm.submit();
		}
	}

	$(document).ready(function getData() {
		$("#results").load("GradeList", {
			"movienum" : $("#movienum").val(),
			"flag" : $("#flag").val(),
		}, function(data) {
			$("#results").html(data)
		});
	});

	function getData(movienum, flag) {
		$("#results").load("GradeList", {
			"movienum" : movienum,
			"flag" : flag,
		}, function(data) {
			$("#results").html(data)
		});
	}
</script>
<style>
	#B_Range_Btn{
		border : none;
		background-color : #111111;
		color : #ffffff;
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
</style>
</head>
<body>
	<jsp:useBean id="currTime" class="java.util.Date" />
	<fmt:formatDate value="${currTime}" var="sysdate" pattern="yyyy-MM-dd" />
	<!-- 개봉예정작 flag==0 -->
	<c:if test="${dto.rel_date > sysdate }">
		<c:set var="flag" value="0"></c:set>
	</c:if>
	<!-- 현재상영작 flag==1 -->
	<c:if test="${dto.rel_date <= sysdate }">
		<c:set var="flag" value="1"></c:set>
	</c:if>
	<div class="LoadMain"><jsp:include page="../Main/MainIndex.jsp" /></div>
	<div class="ViewEntire" align="center">
		<form>
			<table width="1000px">
				<tr>
					<td colspan="2"><h3>${dto.subject }</h3></td>
				</tr>
				<tr>
					<td width="800px">개요 | ${dto.genre } | ${dto.playtime }분 |
						${dto.rel_date } 개봉</td>
					<td width="200px" rowspan="5" valign="top"><img
						src="PosterIMG/${dto.img }" width=150px height=200px></td>
				</tr>
				<tr>
					<td>감독 | ${dto.director }</td>
				</tr>
				<tr>
					<td>출연 | ${dto.actor }</td>
				</tr>
				<tr>
					<td>등급 | <c:if test="${dto.age_res==0 }">전체관람가</c:if> <c:if
							test="${dto.age_res==12 }">12세관람가</c:if> <c:if
							test="${dto.age_res==15 }">15세관람가</c:if> <c:if
							test="${dto.age_res==19 }">청소년관람불가</c:if></td>
				</tr>
				<tr>
					<td>평점 | 개봉 전 : ${dto.rel_rating }점 <c:if
							test="${dto.rel_date <= sysdate }">
					/   개봉 후 : ${dto.on_rating }점
					</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr>
						<h3>줄거리</h3> <br>${dto.summary }
						<hr></td>
				</tr>
			</table>
		</form>
		<!-- 평가 입력 폼 테이블 로그인 여부에 따라 c:if -->
		<form id="frm" name="frm">
			<input type="hidden" id="flag" name="flag" value="${flag }">
			<input type="hidden" id="movienum" name="movienum"
				value="${dto.num }"> <input type="hidden" id="membernum"
				name="membernum" value="${mb.num }">
			<table width="1000px">
				<tr>
					<td align="right"><select name="score" id="score">
							<c:forTokens var="point" items="0,1,2,3,4,5,6,7,8,9,10"
								delims=",">
								<option value="${point}">${point}점</option>
							</c:forTokens>
					</select></td>
					<td align="center"><textarea rows="3" cols="100" id="reply"
							name="reply" placeholder="MESSAGE" required="required"></textarea></td>
					<td align="left"><input type="button" value="등록" id="B_Range_Btn"
						onclick="javascript:gradeInsert()"></td>
				</tr>
				<tr>
					<td colspan="3"><hr></td>
				</tr>
			</table>
		</form>

		<input type="hidden" id="flag" name="flag" value="${flag }"> <input
			type="hidden" id="movienum" name="movienum" value="${dto.num }">
		<a href="javascript:getData(${dto.num },0);">개봉전평점</a>
		<c:if test="${dto.rel_date <= sysdate }">
		 | <a href="javascript:getData(${dto.num },1);">개봉후평점</a>
		</c:if>
		<table align="center">
			<tr>
				<td>
					<div id="results"></div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>