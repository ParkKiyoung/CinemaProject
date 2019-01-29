<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#deleteBtn").click(function(){
		if(frm.password.value==""){
			alert("비밀번호를 입력하세요");
			return false;
		};
		if(confirm("정말 탈퇴하시겠습니까?")){
		$.post("../Login/DeleteAction",
				{"password":$("#password").val(),"num":$("#num").val()},
				function(data){
					if(data==0){
						alert("비밀번호가 일치 하지 않습니다.");
						return false;
					}else if(data==1){
					 alert("탈퇴가 정상적으로 처리 되었습니다.");
					 location.href="../Main/logout";
					}
					
				})
		}
	})
	$("#ResCancelBtn").click(function(){
		$.get("../Login/DeleteAction",
				{"password":$("#password").val(),"num":$("#num").val(),"bookingNum":$("#bookingNum").val()},
				function(data){
					if(data==0){
						alert("비밀번호가 일치 하지 않습니다.");
						return false;
					}else if(data==1){
					 alert("예매취소가 정상적으로 실행되었습니다.");
					 location.href="../Main/myActivity?num="+${mb.num};
					}
				})
	})
});
function backTab(num){
	location.href="../Main/myActivity?num="+num
}
</script>
</head>
<body>
	<div align=center>
		<form action="../Login/DeleteAction" id="frm">
			<table>
				<tr>
					<th colspan=2>비밀번호 확인</th>
				</tr>
				<tr>
					<td>비밀번호 입력</td>
					<td>
					<input type="password" id="password" name="password">
					<input type = "hidden" id = "num" name ="num" value = "${mb.num }">
					<input type = "hidden" id = bookingNum name ="bookingNum" value = "${param.bookingNum }">
					</td>
				</tr>
				<tr>
				<c:if test="${param.checknum==1}">
				<th colspan=2><input type="button" value="예매취소"
						id="ResCancelBtn"> <input type="button" value="뒤로가기"
						onclick="backTab(${mb.num})"></th>
				</c:if>
				<c:if test="${param.checknum==null}">
				<th colspan=2><input type="button" value="탈퇴하기"
						id="deleteBtn"> <input type="button" value="뒤로가기"
						onclick="backTab(${mb.num})"></th>
				</c:if>	
				</tr>
			</table>
		</form>
	</div>
</body>
</html>