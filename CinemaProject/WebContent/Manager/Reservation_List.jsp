<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function deleteBtn(num){
	if(confirm("정말 삭제하시겠습니까?")){
		location.href = "bookingDelete?bookingnum="+num;
	}
}
function pagingBtn(pageNum){
	location.href = "bookingList?pageNum=" + pageNum;
}
</script>
<style>
	#R_List_Table{
		border-collapse: collapse;
		 width: 100%;
	}
	#R_List_Th{
		border-collapse: collapse;
		border-bottom: 1px solid #888888;
		width:auto;
		font-size: 14px;
	}
	#R_List_Td{
		border-collapse: collapse;
		border-bottom: 1px solid #cccccc;
		margin-top: 10px;
	}
		#MU_a{
	color : black;
	font-size: 14px;
	font-weight: 700;
	text-decoration: none;
	}
	#MU_a:hover{
		color : #ff382e;
		font-size: 14px;
		font-weight: 700;
		text-decoration: underline;
		
	}
</style>
</head>
<body>
<form>
		<table id="R_List_Table">
		<tr>
			<th id="R_List_Th">NO</th>
			<th id="R_List_Th">회원아이디</th>
			<th id="R_List_Th">좌석</th>
			<th id="R_List_Th">어른수</th>
			<th id="R_List_Th">학생수</th>
			<th id="R_List_Th">지불금액</th>
			<th id="R_List_Th">상영시간</th>
			<th id="R_List_Th">예약날짜</th>
			<th id="R_List_Th">영화</th>
			<th id="R_List_Th">상영관</th>
			<th id="R_List_Th">기능</th>
		</tr>
			<c:forEach items="${arr }" var="i">
			<tr>
				<td id="R_List_Td">${i.booking_num }</td> 
				<td id="R_List_Td">${i.member_userid }</td>
				<td id="R_List_Td">${i.seat}</td>
				<td id="R_List_Td">${i.adult}</td>
				<td id="R_List_Td">${i.youth}</td>
				<td id="R_List_Td">${i.price}</td>
				<td id="R_List_Td">${i.theater_time_ontime}</td>
				<td id="R_List_Td">${i.bookingdate}</td>
				<td id="R_List_Td">${i.movie_subject}</td>
				<td id="R_List_Td">${i.room_roomname}</td>
				<td id="R_List_Td">
				<input type = "button" value = "삭제" onclick = "deleteBtn(${i.booking_num })">
				</td>
			</tr>
			</c:forEach>
			<tr>
					<td class="td" colspan="14" align="center"><c:if
							test="${startpage > blockpage }">
							<a id="MU_a" href="javascript:pagingBtn(${startpage-blockpage })">이전</a>
						</c:if> <c:forEach begin="${startpage }" end="${endpage }" var="i">
							<c:if test="${nowPage == i }">
							${i }
							</c:if>
							<c:if test="${nowPage != i }">
								<a id="MU_a" href="javascript:pagingBtn(${i })">${i }</a>
							</c:if>
						</c:forEach> <c:if test="${endpage < totpage }">
							<a id="MU_a" href="javascript:pagingBtn(${endpage+1 })">다음</a>
						</c:if></td>
				</tr>
		</table>
	</form>
</body>
</html>