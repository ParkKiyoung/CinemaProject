<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function getGradeList(pageNum,movie_subject){
	if(movie_subject==""||movie_subject==null){
		location.href="../Main/myActivity?gpageNum="+pageNum+"&num="+${mb.num};
	}else{
		location.href="../Main/myActivity?gpageNum="+pageNum+"&num="+${mb.num}+"&movie_subject="+movie_subject;
	}

}
function MyGradeSeachBtn(){
	if($("#MyGradeSearchWord").val()==""){
		alert("영화 제목을 입력하세요")
	}else{
		var movie_subject = $("#MyGradeSearchWord").val();
	location.href="../Main/myActivity?num="+${mb.num}+"&movie_subject="+movie_subject;
	}
}
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
	총 ${gtotPage}페이지 중 ${gcurrentPage}페이지  
	</td>
	</tr>
	<tr>
		<td width=100>글 번호</td>
		<td width=150>영화제목</td>
		<td width=100>내용</td>
		<td width=100>점수</td>
		<td width=150>날짜</td>
		<td width=100>추천수</td>
		<td width=100>반대수</td>
	</tr>
	</thead>
	<c:forEach items="${gra_arr}" var="i">
		<tr>
			<td>${i.getG_Num() }</td>
			<td><a href="../Movie/View?movienum=${i.getMovie_Num()}">${i.getMovieTitle() }</a></td>
			<td>${i.getReply() }</td>
			<td>${i.getScore() }점</td>
			<td>${i.getGrade_Date() }</td>
			<td>${i.getGood() }</td>
			<td>${i.getBad() }</td>
		</tr>
	</c:forEach>
</table>

<c:if test="${gstartPage>gblockPage}">
<a href ="javascript:getGradeList(${gstartPage-gblockPage},'${movie_subject }')">이전</a>
</c:if>
<c:forEach begin="${gstartPage}" end ="${gendPage}" var="i">
<c:if test="${i==gcurrentPage}">${i}</c:if>
<c:if test="${i!=gcurrentPage}">
<a href="javascript:getGradeList(${i},'${movie_subject }')">${i}</a>
</c:if>
</c:forEach>
<c:if test="${gendPage<gtotPage}">
<a href="javascript:getGradeList(${gendPage+1 },'${movie_subject }')">다음</a>
</c:if>
<br>
<br>
제목 검색&nbsp;<input type = text id="MyGradeSearchWord" name = "MyGradeSearchWord">
<input id="B_Range_Btn" type = button onclick= "MyGradeSeachBtn()" value = "검색">