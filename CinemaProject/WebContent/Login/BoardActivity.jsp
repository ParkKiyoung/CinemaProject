<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
function getBoardList(pageNum,board_field,board_word){
	if(board_word==""||board_word==null){
		location.href="../Main/myActivity?bpageNum="+pageNum+"&num="+${mb.num};
	}else{
		location.href="../Main/myActivity?bpageNum="+pageNum+"&num="+${mb.num}+"&board_word="+board_word+"&board_field="+board_field;
	}
	
}
$(document).ready(function(){
	$("#MyBoardSeachBtn").click(function(){
		
		if($("#MyBoardSearchWord").val()==""){
			alert("검색명을 입력하세요")
		}else{
			var board_field = $("#field").val();
			var board_word = $("#MyBoardSearchWord").val();
		location.href="../Main/myActivity?num="+${mb.num}+"&board_word="+board_word+"&board_field="+board_field;
		}
	})
})
</script>
<style>
#B_Range_Btn{
	border : none;
	background-color : #555555;
	color : #ffffff;
	font-weight : bolder;
	font-size : 15px;
	padding: 5px 10px 5px 10px;
	border-radius: 3px;
}
#B_Range_Btn:hover{
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
<table>
	<thead>
	<tr>
	<td colspan=7 align = right>
	총 ${btotPage}페이지 중 ${bcurrentPage}페이지  
	</td>
	</tr>
	<tr>
		<td width=100>글 번호</td>
		<td width=100>게시판명</td>
		<td width=200>제 목</td>
		<td width=100>작성날짜</td>
		<td width=100>조회수</td>
		<td width=100>추천수</td>
		<td width=100>반대수</td>
	</tr>
	</thead>
	<c:forEach items="${mov_arr}" var="i">
		<tr>
			<td>${i.num }</td>
			<c:if test="${i.category=='movie' }">
			<td><a href="../Board/boardList?category=movie">영화게시판</a></td>
			</c:if>
			<c:if test="${i.category=='actor' }">
			<td><a href="../Board/boardList?category=actor">배우게시판</a></td>
			</c:if>
			<c:if test="${i.category=='genre' }">
			<td><a href="../Board/boardList?category=genre">장르게시판</a></td>
			</c:if>
			<td><a href="../Board/boardView?num=${i.num}">${i.subject }</a></td>
			<td>${i.created }</td>
			<td>${i.readcount }</td>
			<td>${i.good }</td>
			<td>${i.bad }</td>
		</tr>
	</c:forEach>
</table>
<c:if test="${bstartPage>bblockPage}">
<a href ="javascript:getBoardList(${gstartPage-gblockPage},'${board_field}','${board_word}')">이전</a>
</c:if>
<c:forEach begin="${bstartPage}" end ="${bendPage}" var="i">
<c:if test="${i==bcurrentPage}">${i}</c:if>
<c:if test="${i!=bcurrentPage}">
<a href="javascript:getBoardList(${i},'${board_field}','${board_word}')">${i}</a>
</c:if>
</c:forEach>
<c:if test="${bendPage<btotPage}">
<a href="javascript:getBoardList(${bendPage+1},'${board_field}','${board_word}')">다음</a>
</c:if>
<br>
<select id = "field">
<option value = "subject">제목</option>
<option value = "category">게시판</option>
</select><input type = text id="MyBoardSearchWord">
<input id="B_Range_Btn" type = button id = "MyBoardSeachBtn" value = "검색">