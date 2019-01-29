<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function deleteBtn(num){
	if(confirm("정말 삭제하시겠습니까?")){
		location.href = "gradeDelete?gradenum="+num;
	}
}
function pagingBtn(pageNum){
	location.href = "gradeList?pageNum=" + pageNum + "&category="
	+ $("#category").val() + "";
}
</script>
<style>
	#G_List_Table{
		border-collapse: collapse;
		 width: 100%;
	}
	#G_List_Th{
		border-collapse: collapse;
		border-bottom: 1px solid #888888;
		width:auto;
		font-size: 14px;
	}
	#G_List_Td{
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
	<input type="hidden" id="category" name="category"value="${category}">
		<table id="G_List_Table">
		<tr>
			<th id="G_List_Th">NO</th>
			<th id="G_List_Th">영화제목</th>
			<th id="G_List_Th">회원번호</th>
			<th id="G_List_Th">평점글내용</th>
			<th id="G_List_Th">점수</th>
			<th id="G_List_Th">날짜</th>
			<th id="G_List_Th">IP</th>
			<th id="G_List_Th">Like</th>
			<th id="G_List_Th">Dislike</th>
			<th id="G_List_Th">기능</th>
		</tr>
			<c:forEach items="${arr }" var="i">
			<tr>
				<td id="G_List_Td">${i.grade_num }</td> 
				<td id="G_List_Td" align=center><a id="MU_a" href="../Movie/View?movienum=${i.m_num }">${fn:substring(i.getMoiveSubject(), 0, 10)}
				<c:if test="${fn:length(i.getMoiveSubject())>10 }">
						...
						</c:if></a></td>
				<td id="G_List_Td">${i.membernum }</td>
				<td id="G_List_Td">${fn:substring(i.reply, 0, 10)}
						<c:if test="${fn:length(i.reply)>10 }">
						...
						</c:if></td>
				<td id="G_List_Td">${i.score }</td>
				<td id="G_List_Td">${i.grade_date }</td>
				<td id="G_List_Td">${i.ip }</td>
				<td id="G_List_Td">${i.good }</td>
				<td id="G_List_Td">${i.bad }</td>
				<td id="G_List_Td">
				<input type = "button" value = "삭제" onclick = "deleteBtn(${i.grade_num })">
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