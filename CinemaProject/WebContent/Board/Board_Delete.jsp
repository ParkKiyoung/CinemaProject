<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>
<body>
	<form>
	<input type="hidden" id="category" name="category"value="${category}">
		<table style="width: 100%">
			<tr>
				<th>게시판번호</th>
				<th>아이디</th>
				<th>회원번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성일</th>
				<th>추천개수</th>
				<th>비추천개수</th>
				<th>조회수</th>
				<th>IP</th>
				<th>최종수정일</th>
				<th>카테고리</th>
				<th>댓글수</th>
				<th>기능</th>
			</tr>
			<c:forEach items="${arr }" var="i">
				<tr>
					<td>${i.num }</td>
					<td>${i.userid }</td>
					<td>${i.membernum }</td>
					<td>${i.subject }</td>
					<td>${i.content }</td>
					<td>${i.created }</td>
					<td>${i.good }</td>
					<td>${i.bad }</td>
					<td>${i.readcount }</td>
					<td>${i.ip }</td>
					<td>${i.updated }</td>
					<c:set var="category" value="movie"></c:set>
						<c:if test="${i.category==category }">
							<td class="td">영화</td>
						</c:if>
						<c:set var="category" value="genre"></c:set>
						<c:if test="${i.category==category }">
							<td class="td">장르</td>
						</c:if>
						<c:set var="category" value="actor"></c:set>
						<c:if test="${i.category==category }">
							<td class="td">배우</td>
						</c:if>
					<td>${i.subjectcount }</td>
					<td><input type="button" value = "삭제" onclick = "deleteBtn(${i.num })"></td>
				</tr>
			</c:forEach>
			<tr>
					<td class="td" colspan="14" align="center"><c:if
							test="${startpage > blockpage }">
							<a href="javascript:pagingBtn(${startpage-blockpage })">이전</a>
						</c:if> <c:forEach begin="${startpage }" end="${endpage }" var="i">
							<c:if test="${nowPage == i }">
							[${i }]
							</c:if>
							<c:if test="${nowPage != i }">
								<a href="javascript:pagingBtn(${i })">[${i }]</a>
							</c:if>
						</c:forEach> <c:if test="${endpage < totpage }">
							<a href="javascript:pagingBtn(${endpage+1 })">다음</a>
						</c:if></td>
				</tr>
		</table>
	</form>
</body>
</html>