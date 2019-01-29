<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
<link rel="stylesheet" type="text/css" href="../CSS/TotalStyle.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	function getPage(pageNum,word) {
		$("#result").load("MoreBoard", {
			"pageNum" : pageNum,
			"word" : word,
		}, function(data) {
			$("#result").html(data)
		});
	}
</script>
</head>
<body>
	<div id="result">
	<div class="LoadMain"><jsp:include page="../Main/MainIndex.jsp"></jsp:include></div>
		<div class="ViewEntire">
		<table>
			<tr>
				<td><c:forEach items="${arr }" var="arr" varStatus="status">
						<h3>
							<a href="../Board/boardView?num=${arr.num }">${arr.subject }</a>
						</h3>
						<br>
				${fn:substring(arr.content, 0, 200)}
				<c:if test="${fn:length(arr.content)>200 }">
				...
				</c:if><br>	
				작성일 : ${arr.created }<br>
						<br>
						<br>
					</c:forEach></td>
			</tr>
		</table>


		<div align="center">
			<!-- 이전 -->
			<c:if test="${startpage>blockpage }">
				<a href="javascript:getPage(${startpage-blockpage},'${word }');">[이전]</a>
			</c:if>
			<!-- 페이지출력 -->
			<c:forEach begin="${startpage }" end="${endpage }" var="i">
				<c:if test="${i==currentPage }">
					<b>[${i }]</b>
				</c:if>
				<c:if test="${i!=currentPage }">
					<a href="javascript:getPage(${i },'${word }');">[${i }]</a>
				</c:if>
			</c:forEach>
			<!-- 다음 -->
			<c:if test="${endpage<totpage }">
				<a href="javascript:getPage(${endpage+1},'${word }');">[다음]</a>
			</c:if>
		</div>
	</div>
	</div>
	<div id="Footer">
		<jsp:include page="MainFooter.jsp" />
	</div>
</body>
</html>