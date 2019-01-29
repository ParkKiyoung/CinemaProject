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
<script type="text/javascript">
function deleteBtn(num){
	if(confirm("정말 삭제하시겠습니까?")){
		location.href = "boardDelete?boardnum="+num;
	}
}
function pagingBtn(pageNum){
	location.href = "boardList?pageNum=" + pageNum + "&category="
	+ $("#category").val() + "";
}
</script>
<style>
	#B_List_Table{
		border-collapse: collapse;
		 width: 100%;
	}
	#B_List_Th{
		border-collapse: collapse;
		border-bottom: 1px solid #888888;
		width:auto;
		font-size: 14px;
	}
	#B_List_Td{
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
		<table id="B_List_Table">
			<tr>
				<th id="B_List_Th">게시판번호</th>
				<th id="B_List_Th">아이디</th>
				<th id="B_List_Th">회원번호</th>
				<th id="B_List_Th">제목</th>
				<th id="B_List_Th">내용</th>
				<th id="B_List_Th">작성일</th>
				<th id="B_List_Th">추천개수</th>
				<th id="B_List_Th">비추천개수</th>
				<th id="B_List_Th">조회수</th>
				<th id="B_List_Th">IP</th>
				<th id="B_List_Th">최종수정일</th>
				<th id="B_List_Th">카테고리</th>
				<th id="B_List_Th">댓글수</th>
				<th id="B_List_Th">기능</th>
			</tr>
			<c:forEach items="${arr }" var="i">
				<tr>
					<td id="B_List_Td">${i.num }</td>
					<td id="B_List_Td">${i.userid }</td>
					<td id="B_List_Td">${i.membernum }</td>
					<td id="B_List_Td"><a id="MU_a" href="../Board/boardView?num=${i.num }">${fn:substring(i.subject, 0, 10)}
						<c:if test="${fn:length(i.subject)>10 }">
						...
						</c:if></a></td>
					<td id="B_List_Td">${fn:substring(i.content, 0, 10)}
						<c:if test="${fn:length(i.content)>10 }">
						...
						</c:if></td>
					<td id="B_List_Td">${i.created }</td>
					<td id="B_List_Td">${i.good }</td>
					<td id="B_List_Td">${i.bad }</td>
					<td id="B_List_Td">${i.readcount }</td>
					<td id="B_List_Td">${i.ip }</td>
					<td id="B_List_Td">${i.updated }</td>
					<c:set var="category" value="movie"></c:set>
						<c:if test="${i.category==category }">
							<td id="B_List_Td" class="td">영화</td>
						</c:if>
						<c:set var="category" value="genre"></c:set>
						<c:if test="${i.category==category }">
							<td id="B_List_Td" class="td">장르</td>
						</c:if>
						<c:set var="category" value="actor"></c:set>
						<c:if test="${i.category==category }">
							<td id="B_List_Td" class="td">배우</td>
						</c:if>
					<td>${i.subjectcount }</td>
					<td id="B_List_Td"><input type="button" value = "삭제" onclick = "deleteBtn(${i.num })"></td>
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