<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#LoginBtn").click(function(){
		if($("#id").val()==""){
			alert("아이디를 입력하세요")
			return false;
		};
		if($("#password").val()==""){
			alert("비밀번호를 입력하세요")
			return false;
		};
		$.post("login",
				{"id":$("#id").val(),"password":$("#password").val()},
				function(data){
					if(data==1){
						 location.href=document.referrer;//뒤로가기후 새로고침
					};
					if(data!=1){
					alert(data);
					};
				}
		)
	});
	$("#findInfoBtn").click(function(){
		window.open("../Login/FindInfo","a","width=500 height = 500");
	})
})
</script>
<style>
	#findInfoBtn{
		border : none;
		background-color : #ffffff;
		color : #111111;
		font-weight : bolder;
		font-size : 12px;
		padding: 5px 10px 5px 10px;
		border-radius: 3px;
	}
	#findInfoBtn:hover{
		border : none;
		background-color : #ffffff;
		color : #ff382e;
		font-weight : bolder;
		font-size : 12px;
		padding: 5px 10px 5px 10px;
		border-radius: 3px;
		cursor : pointer;
		text-decoration: underline;
	}
	   #join{
	border : none;
		background-color : #ffffff;
		color : #111111;
		font-weight : bolder;
		font-size : 12px;
		padding: 5px 10px 5px 10px;
		border-radius: 3px;
	}
	#join:hover{
		border : none;
		background-color : #ffffff;
		color : #ff382e;
		font-weight : bolder;
		font-size : 12px;
		padding: 5px 10px 5px 10px;
		border-radius: 3px;
		cursor : pointer;
		text-decoration: underline;
	}
	#LoginBtn{
		border : none;
		background-color : #111111;
		color : #ffffff;
		font-weight : bolder;
		font-size : 15px;
		padding: 10px 15px 10px 15px;
		border-radius: 3px;
	}
	#LoginBtn:hover{
		border : none;
		background-color : #111111;
		color : #ffffff;
		font-weight : bolder;
		font-size : 15px;
		padding: 10px 15px 10px 15px;
		border-radius: 3px;
		cursor : pointer;
	}
</style>
</head>
<body>
<div align = center>
	<h1>LOGIN</h1>
	<form action = "login" method = "post" id = "frm">
	<table>
		<tr>
		
			<td>아이디</td>
			<td><input type="text" id=id name=id></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id=password name=password></td>	
		</tr>
		<tr>
			<td colspan="3" align="center"><input type = "button" value = "로그인" id="LoginBtn" name = "LoginBtn"></td>
		</tr>
		<tr>
		<td colspan = 3 align = center>
		<input type = "button" id="join" name = "join" value = "회원가입" onclick = "location.href='../Main/MainJoinTemp.jsp'">
		<input type = "button" id="findInfoBtn" name = "findInfoBtn" value = "아이디/비밀번호 찾기">
		</td>
		</tr>		
	</table>
	</form>
	</div>
	<div id="Footer">
		<jsp:include page="../Main/MainFooter.jsp" />
	</div>
</body>
</html>