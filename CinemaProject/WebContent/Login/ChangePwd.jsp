<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<%String member_num = request.getParameter("member_num"); %>
<script>
$(document).ready(function(){
	$("#ChangePwdBtn").click(function(){
	if(changePwdfrm.pwd.value==""){
		alert("비밀번호를 입력해주세요");
		return false;
	}
	if(changePwdfrm.pwdchk.value==""){
		alert("비밀번호 확인을 입력해주세요");
		return false;
	}
	if(changePwdfrm.pwdchk.value!=changePwdfrm.pwd.value){
		alert("비밀번호가 일치 하지 않습니다.");
		return false;
	}
	changePwdfrm.submit();
	});
})

</script>

</head>
<body>
	<form action="changePwd" id="changePwdfrm">
		<div align=center>
		<input type  = hidden id = member_num name= member_num value =<%=member_num %>>
			<table>
				<tr>
					<th colspan=2 align=center>비밀번호 수정</th>
				</tr>
				<tr>
					<td>비밀번호 입력</td>
					<td><input type=password id=pwd name=pwd>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type=password id=pwdchk name=pwdchk>
				</tr>
			</table>

			<input type=button id=ChangePwdBtn value="비밀번호 수정">
		</div>

	</form>
</body>
</html>