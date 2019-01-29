<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
<link rel="stylesheet" type="text/css" href="../CSS/TotalStyle.css">
</head>
<body>
	<div class="LoadMain"><jsp:include page="../Main/MainIndex.jsp"></jsp:include></div>
		<div class="ViewEntire">
	<table>
		<tr>
			<td>
				검색어 : '${word }'에 대한 검색 결과
			</td>
		</tr>
		<tr>
			<td>
				<h2>영화 검색 결과</h2>
				<hr>
			</td>
		</tr>
		<tr>
			<td>
				<div id="result1">
					<c:forEach items="${arr1 }" var="arr1" varStatus="status">
						
						<h3>
							<a href="../Movie/View?movienum=${arr1.num }">${arr1.subject }</a>
						</h3>
				${fn:substring(arr1.summary, 0, 200)}
				<c:if test="${fn:length(arr1.summary)>200 }">
				...
				</c:if><br>
				개봉일 : ${arr1.rel_date }
						<c:if test="${status.count==5 }"><br>
							<h4 align="right"><a href="MoreMovie?word=${word }">검색 결과 더보기</a></h4>
						</c:if>
						<br><br>
					</c:forEach>
					<c:if test="${fn:length(arr1)==0 }">
				검색 결과가 없습니다.
				</c:if>
				</div> <br> <br> <br> <br> <br>
			</td>
		</tr>
		<tr>
			<td>
				<h2>게시글 검색 결과</h2>
				<hr>
			</td>
		</tr>
		<tr>
			<td>
				<div id="result2">
				
						<c:forEach items="${arr2 }" var="arr2" varStatus="status">
						${status.count}
				
							<h3>
								<a href="../Board/boardView?num=${arr2.num }">${arr2.subject }</a>
							</h3>
				${fn:substring(arr2.content, 0, 200)}
				<c:if test="${fn:length(arr2.content)>200 }">
				...
				</c:if><br>			
				작성일 : ${arr2.created }
							<c:if test="${status.count==5 }"><br>
								<h4 align="right"><a href="MoreBoard?word=${word }">검색 결과 더보기</a></h4>
							</c:if>
							<br><br>
						</c:forEach>
								<c:if test="${fn:length(arr2)==0 }">
				검색 결과가 없습니다.
				</c:if>
				</div>
			</td>
		</tr>
	</table>
	</div>
	<div id="Footer">
		<jsp:include page="MainFooter.jsp" />
	</div>
</body>
</html>