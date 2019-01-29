<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
<script>
	function getPage(movienum, flag, pageNum) {
		$("#results").load("GradeList", {
			"movienum" : movienum,
			"flag" : flag,
			"pageNum" : pageNum,
		}, function(data) {
			$("#results").html(data)
		});
	}

	function gradeDelete(gradenum, movienum, flag) {
		if (confirm("정말 삭제하시겠습니까??") == true) { //확인
			location.href = "GradeDelete?gradenum=" + gradenum + "&movienum="
					+ movienum + "&flag=" + flag;
		} else { //취소
			return;
		}
	}
</script>
<style>
	#Board_Like_Do{
	background-color : #ff382e;
	color : white;
	font-size : 13px;
	font-weight : 800;
	padding: 5px 10px 5px 10px;
	border-radius : 5px;
}
#Board_Dislike_Do{
	background-color : #fe9247;
	color : white;
	font-size : 13px;
	font-weight : 900;
	padding: 5px 10px 5px 10px;
	border-radius : 5px;
}
#BMT_a{
	color : black;
	font-size: 14px;
	font-weight: 700;
}
#BMT_a:hover{
	color : #ff382e;
	font-size: 14px;
	font-weight: 700;
	text-decoration: underline;
}
</style>
</head>
<body>
	<table>
		<c:forEach items="${grade }" var="gra">
			<tr>
				<td width="1000px" colspan="2">
					<hr>
				</td>
			</tr>
			<tr>
				<td width="200px" rowspan="3" valign="top">${gra.score }</td>
				<td valign="top">${gra.reply }</td>
			</tr>
			<tr>
				<td><br>${gra.userId } | ${gra.grade_Date } <c:if
						test="${mb.num==gra.member_Num || mb.userid=='admin'}"> | <a
							href="javascript:gradeDelete(${gra.g_Num },${movienum },${flag })">삭제</a>
					</c:if></td>
			</tr>
			<tr>
				<td width="800px" align="right"><br> <!-- <input type="button"> -->
					<%-- id="Board_Like_Do" name="good" value="좋아요">
					<img style="width : 13px; height : 13px;" src="../Images/Good_Img.png">
								Like ${gra.good } | <input
					type="button" id="bad" name="bad" value="싫어요">${gra.bad }
				
					<a id="Board_Like_Do" href="goodbadClick?checknum=1&boardnum=${i.num }&membernum=${mb.num}"><img style="width : 13px; height : 13px;" src="../Images/Good_Img.png">
							Like ${gra.good }</a>
					<a id="Board_Dislike_Do" href="goodbadClick?checknum=-1&boardnum=${i.num }&membernum=${mb.num}"><img style="width : 13px; height : 13px;" src="../Images/Bad_Img.png">
							Dislike ${gra.bad }</a> --%>
				</td>	
			</tr> 
		</c:forEach>
		<tr>
			<td colspan="2"><hr></td>
		</tr>
	</table>
	<div align="center">
		<!-- 이전 -->
		<c:if test="${startpage>blockpage }">
			<a id="BMT_a" href="javascript:getPage(${movienum },${flag },${startpage-blockpage});">이전</a>
		</c:if>
		<!-- 페이지출력 -->
		<c:forEach begin="${startpage }" end="${endpage }" var="i">
			<c:if test="${i==currentPage }">
				<b>${i }</b>
			</c:if>
			<c:if test="${i!=currentPage }">
				<a id="BMT_a" href="javascript:getPage(${movienum },${flag },${i });">${i }</a>
			</c:if>
		</c:forEach>
		<!-- 다음 -->
		<c:if test="${endpage<totpage }">
			<a id="BMT_a" href="javascript:getPage(${movienum },${flag },${endpage+1});">다음</a>
		</c:if>
	</div>
</body>
</html>