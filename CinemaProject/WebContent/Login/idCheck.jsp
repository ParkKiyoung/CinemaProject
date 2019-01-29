<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복검사</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
var idpattern = /^[A-Za-z0-9]{4,12}$/;
	$(document).ready(
			function() {
				$("#checkBtn").click(

						function() {
							if(!$("#userid").val().match(idpattern)){
								alert("영문 대,소문자와 숫자 조합으로 4~12자리 입력가능합니다.")
								return false;
							}
							$.post("IdCheck", {
								"userid" : $("#userid").val()
							}, function(data) {
								if (data == 0) {
									if (confirm("사용가능합니다. 사용하시겠습니까?")) {
										$(opener.document).find("#userid").val(
												$("#userid").val());
										self.close();
									}
								} else if (data == 1) {
									alert("중복된 아이디 입니다.")
								}
							});
						});
			});
</script>
</head>
<body>
	아이디 입력 :
	<input type="text" id=userid name=userid placeholde="4~12자리의 대소문자,숫자">
	<input type="button" value="검사" id="checkBtn">
	<div id=flag></div>
</body>
</html>