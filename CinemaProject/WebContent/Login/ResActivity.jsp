<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
function getGradeList(pageNum){
	if(movie_subject==""||movie_subject==null){
		location.href="../Main/myActivity?rpageNum="+pageNum+"&num="+${mb.num};
};
}
function ResCancel(num){
	if(confirm("예매취소 하시겠습니까?"))	{
		alert("비밀번호 입력이 필요합니다.")
		location.href="../Login/MemberPassCheck.jsp?checknum=1&bookingNum="+num;
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
			<th width=100>예매번호</th>
			<th width=200>영화제목</th>
			<th width=100>상영관명</th>
			<th width=300>상영날짜</th>
			<th width=100>예매날짜</th>
			<th colspan = 2 >예매인원</th>
			<th width=150 >예매좌석</th>
			<th width=100>구입가격</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${res_arr }" var="i">
		<tr>
		<td>${i.getBookingNum() }</td>
		<td><a href = "../Movie/View?movienum='${i.getMovieNum() }'">${i.getMovieSubject() }</a></td>
		<td>${i.getTheatherName() }</td>
		<td align=center>${i.getResDateAndTime() }</td>
		<td>${i.getBookingDate() }</td>
		<td>성인 : ${i.getAdultCount() }명</td>
		<td>청소년 : ${i.getYouthCount() }명 </td>
		<td align = center>${i.getResSeat() }</td>
		<td>${i.getPrice() }원</td>
		<td>
		<input id="B_Range_Btn" type = button onclick = "ResCancel(${i.getBookingNum()})" value = "예매취소">
		</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<c:if test="${rstartPage>rblockPage}">
<a href ="javascript:getBoardList(${rstartPage-rblockPage})">[이전]</a>
</c:if>
<c:forEach begin="${rstartPage}" end ="${rendPage}" var="i">
<c:if test="${i==rcurrentPage}">[${i}]</c:if>
<c:if test="${i!=rcurrentPage}">
<a href="javascript:getBoardList(${i})">[${i}]</a>
</c:if>
</c:forEach>
<c:if test="${rendPage<rtotPage}">
<a href="javascript:getBoardList(${rendPage+1}')">[다음]</a>
</c:if>