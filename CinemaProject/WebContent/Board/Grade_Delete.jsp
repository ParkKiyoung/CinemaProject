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
		location.href = "gradeDelete?gradenum="+num;
	}
}
function pagingBtn(pageNum){
	location.href = "gradeList?pageNum=" + pageNum + "&category="
	+ $("#category").val() + "";
}
</script>
</head>
<body>
	<form>
	<input type="hidden" id="category" name="category"value="${category}">
		<table style="width: 100%">
		<tr>
		<th>NO</th>
		<th>영화번호</th>
		<th>회원번호</th>
		<th>평점글내용</th>
		<th>점수</th>
		<th>날짜</th>
		<th>IP</th>
		<th>good</th>
		<th>bad</th>
		<th>기능</th>
		</tr>
			<c:forEach items="${arr }" var="i">
			<tr>
				<td>${i.grade_num }</td> 
				<td>${i.m_num }</td>
				<td>${i.membernum }</td>
				<td>${i.reply}</td>
				<td>${i.score }</td>
				<td>${i.grade_date }</td>
				<td>${i.ip }</td>
				<td>${i.good }</td>
				<td>${i.bad }</td>
				<td>
				<input type = "button" value = "삭제" onclick = "deleteBtn(${i.grade_num })">
				</td>
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