<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
<link rel="stylesheet" type="text/css" href="../CSS/TotalStyle.css">
</head>
<body>
	<div class="LoadMain"><jsp:include page="MainIndex.jsp" /></div>
	<div class="ViewEntire" align="center">
		<c:if test="${mb==null}">
로그인 후 이용가능합니다.
</c:if>
		<c:if test="${mb!=null}">
			<jsp:include page="../Reservation/ReservationMain.jsp"></jsp:include>
		</c:if>

	</div>
	<div id="Footer">
		<jsp:include page="MainFooter.jsp" />
	</div>
</body>
</html>