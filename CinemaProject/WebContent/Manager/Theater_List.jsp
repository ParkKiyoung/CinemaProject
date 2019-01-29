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
function pagingBtn(pageNum){
	location.href = "theaterList?pageNum=" + pageNum;
}
</script>
</head>
<body>
	<form>
		<table>
		<tr>
		<th>NO</th>
		<th>도시</th>
		<th>영화관명</th>
		<th>상영관개수</th>
		</tr>
			<c:forEach items="${arr }" var="i">
			<tr>
				<td>${i.theater_num }</td> 
				<td>${i.city }</td>
				<td>${i.theatername}</td>
				<td>${i.theaterroom}</td>
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