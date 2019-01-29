<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
<style>
.Board_Main_Table {
	width: 80%;
	/* 	border: 1px solid #A9A9A9; */
	border-collapse: collapse;
}

.Board_Main_Td {
	/* 	border: 1px solid #A9A9A9; */
	padding: 10px;
	text-align: center;
	border-bottom: 1px solid #cccccc;
	
}
#Board_Main_Td_Pasing{
	text-align: center;
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
#Board_Main_Td_Search{
	text-align: center;
	margin-top: 5%;
}
.Board_Main_Th {
	/* 	border: 1px solid #A9A9A9; */
	padding: 10px;
	text-align: center;
	background: black;
	color: white;
}
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
#Board_Btn_Css{
	border : none;
	background-color : black;
	color : white;
	font-size : 15px;
	font-weight : 900;
	padding : 5px 10px 5px 10px;
	cursor : pointer; 
}
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
#B_Range_Btn2{
	border : none;
	background-color : #555555;
	color : #ffffff;
	font-weight : bolder;
	font-size : 15px;
	padding: 5px 10px 5px 10px;
	border-radius: 3px;
}
#B_Range_Btn2:hover{
	border : none;
	background-color : #555555;
	color : #ffffff;
	font-weight : bolder;
	font-size : 15px;
	padding: 5px 10px 5px 10px;
	border-radius: 3px;
	cursor : pointer;
}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	function searchBtn() {
		if ($("#word").val() == "") {
			alert("검색어를 입력하세요.");
			return false;
		}
		if ($("#field").val() == "choice") {
			alert("검색카테고리를 선택하세요.");
			return false;
		}
		frm.submit();
	}
	function boardinsert() {
		location.href = "../Main/BoardInsertTemp.jsp";
	}
	function categoryBtn(str) {
		if (str == "") {
			location.href = "../Board/boardList";
			return;
		}
		location.href = "../Board/boardList?category=" + str + "";
	}
	function pagingBtn(pageNum) {
		if ($("#word_u").val() == "") {
			location.href = "boardList?pageNum=" + pageNum + "&category="
					+ $("#category").val() + "";
		} else {
			location.href = "boardList?pageNum=" + pageNum + "&category="
					+ $("#category").val() + "&field=" + $("#field_u").val()
					+ "&word=" + $("#word_u").val() + "";
		}
	}
	$(document).ready(function() {
		$('#sorting').change(function() {
			if ($("#word_u").val() == "") {
				location.href = "../Board/boardList?sorting="+$(this).val()+"&pageNum="+  $("#pageNum").val() + "&category="
				+ $("#category").val();
			} else {
				location.href = "../Board/boardList?sorting="+$(this).val()+"&pageNum="+  $("#pageNum").val() + "&category="
				+ $("#category").val() + "&field=" + $("#field_u").val()
				+ "&word=" + $("#word_u").val() + "";
			}
		});
	});
</script>
</head>
<body>
	<h2 align="center">게시글</h2>
	<div align="center">
		<c:if test="${mb == null }">
		</c:if>
		<input type="hidden" id="pageNum" name="pageNum"value="${pageNum}">
		<form id="frm" action="boardList">
			<input type="button" id="B_Range_Btn" value="전체글" onclick="categoryBtn('')"> 
			<input type="button" id="B_Range_Btn" value="영화글" onclick="categoryBtn('movie')">
			<input type="button" id="B_Range_Btn" value="장르글" onclick="categoryBtn('genre')">
			<input type="button" id="B_Range_Btn" value="배우글" onclick="categoryBtn('actor')">

			<input type="hidden" id="field_u" name="field_u" value="${field}">
			<input type="hidden" id="word_u" name="word_u" value="${word}">
			<input type="hidden" id="category" name="category"value="${category}">
			<table class="Board_Main_Table">
				</tr>
				<tr align="center">
				<td colspan="6">
						<c:if test="${mb != null }">
							<h5 style="text-align: right;"><input type="button" id="B_Range_Btn2" value="글쓰기" onclick="boardinsert()"></h5>
						</c:if>
						<c:if test="${mb == null }">
							<h5>게시판 글쓰기는 <a href = "../Main/MainLoginTemp.jsp">로그인</a> 이후 이용가능합니다.</h5>
						</c:if>
					</td>
				</tr>
				<tr align="right">
				<td colspan="3" align="left">
					총 <strong>${totcount }</strong> 건의 게시글
					<c:if test="${totcount != count }">
						중 <strong>${count }</strong>건의 결과
					</c:if>
				</td>
					<td colspan="3">
					<select id = "sorting">
					<option value = "">선택</option>
					<option value = "readcount">조회순</option>
					<option value = "good">추천순</option>
					<option value = "bad">비추천순</option>
					</select>
					</td>
				</tr>
				<tr>
					<th width="5%" class="Board_Main_Th">카테고리</th>
					<th width="10%" class="Board_Main_Th">작성자</th>
					<th width="30%" class="Board_Main_Th">제목</th>
					<th width="10%" class="Board_Main_Th">작성일</th>
					<th width="5%" class="Board_Main_Th">조회수</th>
					<th width="20%" class="Board_Main_Th">추천/비추천</th>
				</tr>
				<c:set var="number" value="${count-(nowPage*pageSize)}"></c:set>
				<c:forEach items="${arr }" var="i">
					<tr>
						<c:set var="category" value="movie"></c:set>
						<c:if test="${i.category==category }">
							<td class="Board_Main_Td">영화</td>
						</c:if>
						<c:set var="category" value="genre"></c:set>
						<c:if test="${i.category==category }">
							<td class="Board_Main_Td">장르</td>
						</c:if>
						<c:set var="category" value="actor"></c:set>
						<c:if test="${i.category==category }">
							<td class="Board_Main_Td">배우</td>
						</c:if>
						<td class="Board_Main_Td">${i.userid }</td>
						<c:if test="${i.subjectcount == 0 }">
						<td class="Board_Main_Td"><a href="boardView?num=${i.num}">${fn:substring(i.subject,0,25) }
						<c:if test="${fn:length(i.subject)>25 }">
							...
							</c:if>
						</a></td>
						</c:if>
						<c:if test="${i.subjectcount != 0 }">
						<td class="Board_Main_Td"><a href="boardView?num=${i.num}">${fn:substring(i.subject,0,25) }
						<c:if test="${fn:length(i.subject)>25 }">
							...
							</c:if>
							(${i.subjectcount })
						</a></td>
						</c:if>
						<td class="Board_Main_Td">${i.created }</td>
						<td class="Board_Main_Td">${i.readcount }</td>
						
						<td class="Board_Main_Td">
						<a id="Board_Like_Do" href="goodbadClick?checknum=1&boardnum=${i.num }&membernum=${mb.num}"><img style="width : 13px; height : 13px;" src="../Images/Good_Img.png">
								Like ${i.good }</a>
						<a id="Board_Dislike_Do" href="goodbadClick?checknum=-1&boardnum=${i.num }&membernum=${mb.num}"><img style="width : 13px; height : 13px;" src="../Images/Bad_Img.png">
								Dislike ${i.bad }</a>
						</td>
					</tr>
				</c:forEach>
					<tr>
						<td id="Board_Main_Td_Search" colspan="6" align="center">
						<br>
						<select name="field" id="field">
								<option value="choice">선택</option>
								<option value="userid">작성자</option>
								<option value="subject">제목</option>
						</select> 
							<input type="text" id="word" name="word"> <input type="button" value="검색" onclick="searchBtn()"></td>
					</tr>
				<tr align="right" style="color: #A9A9A9">
				<td colspan="6">page ${pageNum }/${totpage }</td>
				</tr>
				<tr>
					<td id="Board_Main_Td_Pasing" colspan="6" align="center">
					<c:if test="${startpage > blockpage }">
							<a id="BMT_a" href = "javascript:pagingBtn(${startpage })">처음으로 |</a>
							<a id="BMT_a" href="javascript:pagingBtn(${startpage-blockpage })">이전</a>
						</c:if> 
						<c:forEach begin="${startpage }" end="${endpage }" var="i">
							<c:if test="${nowPage == i }">
							${i }
							</c:if>
							<c:if test="${nowPage != i }">
								<a id="BMT_a" href="javascript:pagingBtn(${i })">${i }</a>
							</c:if>
						</c:forEach> 
						<c:if test="${endpage < totpage }">
							<a id="BMT_a" href="javascript:pagingBtn(${endpage+1 })">다음</a>
							<a id="BMT_a" href = "javascript:pagingBtn(${totpage })">| 끝으로</a>
						</c:if>
						</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="Footer">
		<jsp:include page="../Main/MainFooter.jsp" />
	</div>
</body>
</html>