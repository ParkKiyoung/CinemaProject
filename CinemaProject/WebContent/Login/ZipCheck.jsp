<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function searchBtn(){
	if(frm.dong.value==""){
		alert("동이름을 입력해주세요")
		return false;
	};
	frm.submit();
}
function sendZipcode(zipcode,sido,dong,bunji){
	$(opener.document).find("#zipcode").val(zipcode);
	var address = sido+" "+dong+" "+bunji;
	$(opener.document).find("#addr1").val(address);
	self.close();
};
</script>
<style>
	#B_Range_Btn{
		border : none;
		background-color : #111111;
		color : #ffffff;
		font-weight : bolder;
		font-size : 15px;
		padding: 5px 10px 5px 10px;
		border-radius: 3px;
	}
	#B_Range_Btn:hover{
		border : none;
		background-color : #111111;
		color : #ffffff;
		font-weight : bolder;
		font-size : 15px;
		padding: 5px 10px 5px 10px;
		border-radius: 3px;
		cursor : pointer;
	}
</style>
</head>
<body>
	
		<h1 align=center>우편번호 검색</h1>
		<div align=center>
		<form action = ZipSearch id = frm>
			동 입력 : <input type=text id=dong name=dong size = 50> <input type=button id="B_Range_Btn"
				onclick="searchBtn()" value = "검색">
	</form>
	<table>
		<tr>
			<th>우편번호</th>
			<th width = 50>시도</th>
			<th width =300>구  군</th>
			<th>동/읍/면/리</th>
		</tr>
		<c:forEach items="${arr}" var="i">
		<tr>
		<td>${i.zipcode}</td>
		<td align = center>${i.sido}</td>
		<td><a href = "javascript:sendZipcode('${i.zipcode}','${i.sido}','${i.dong}','${i.bunji}')">${i.dong}</a></td>
		<td>${i.bunji}</td>
		</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>