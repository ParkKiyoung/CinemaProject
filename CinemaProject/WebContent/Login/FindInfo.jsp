<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID/비밀번호 찾기</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function() {
	$("#IdSearch").click(function(){
		if(IdFrm.name.value==""){
			alert("이름을 입력해주세요");
			return false;
		};
		if(IdFrm.pnum.value==""){
			alert("생년월일을 입력해주세요")
			return false;
		};
		if(IdFrm.phone.value==""){
			alert("전화번호를 입력해주세요")
			return false;
		};
		IdFrm.submit();
	});
	$("#PwdSearch").click(function(){
		if(PwdFrm.id.value==""){
			alert("아이디를 입력해주세요");
			return false;
		};
		if(PwdFrm.pnum.value==""){
			alert("생년월일을 입력해주세요")
			return false;
		};
		if(PwdFrm.phone.value==""){
			alert("전화번호를 입력해주세요")
			return false;
		};
		PwdFrm.submit();
	})
});
</script>
</head>
<body>
<div align = center>
	<form id = "IdFrm" action = "FindInfo" method = "get">
		<table>
			<tr>
				<th align = center colspan = 2>아이디 찾기</th>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type=text id="name" name="name"></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input type=text id="pnum" name="pnum"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type=text id="phone" name="phone"></td>
			</tr>
			<tr>
			<td colspan =2 align = center>
			<input type = button id="IdSearch" value = "아이디 찾기">
			</td>
			</tr>
		</table>
	</form>
	
	<br>
	<div align = center>
	
	<c:if test="${findMb!=null }">
	<c:if test="${findMb.getUserid()!='null'}">
	검색된 아이디는 ${findMb.getUserid()}입니다.
	</c:if>
	<c:if test="${findMb.getUserid()=='null'}">
	검색되는 아이디가 없습니다.
	</c:if>
		</c:if>
	</div>
	<br>
	
	<form action="FindInfo" id = "PwdFrm" method ="post">
			<table>
			<tr>
				<th align = center colspan = 2>비밀번호 찾기</th>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type=text id="id" name="id"></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input type=text id="pnum" name="pnum"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type=text id="phone" name="phone"></td>
			</tr>
			<tr>
			<td colspan =2 align = center>
			<input type = button id="PwdSearch" value = "비밀번호 찾기">
			</td>
			</tr>
		</table>
		<br>
		<div align =center>
		<c:if test="${findPwd!=null }">
		<c:if test="${findPwd.getPassword()=='0'}">
		입력하신 정보가 일치하지 않거나 비회원입니다.
		</c:if>
		<c:if test="${findPwd.getPassword()=='1'}">
		<script>
		if(confirm("비밀번호 수정으로 이동합니다.")){
			location.href = "../Login/ChangePwd.jsp?member_num="+${findPwd.getNum()};
		}
		</script>
		</c:if>
		</c:if>
		</div>
	</form>
	</div>
</body>
</html>