<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복검사</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(
			function() {
				$("#checkBtn").click(
						function() {
							$.post("U_Id_Check.do", {
								"U_Id" : $("#U_Id").val()
							}, function(data) {
								if (data == 0) {
									if (confirm("사용가능합니다. 사용하시겠습니까?")) {
										$(opener.document).find("#U_Id").val(
												$("#U_Id").val()
										);
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
	<input type="text" id="U_Id" name="U_Id">
	<input type="button" value="검사" id="checkBtn">
	<div id=flag></div>
</body>
</html>