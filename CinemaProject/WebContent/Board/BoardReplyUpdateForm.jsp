<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function replyUpdateBtn(){
	if(confirm("수정하시겠습니까?")){
		frm.submit();
	}
}
function windowCloseBtn(){
	self.close();
}
</script>
</head>
<body>
<h2 align = "center">댓글수정</h2>
<br>
<hr>
<br>
<div align = "center">
<form id = "frm" action = "replyUpdate">
<input type = "hidden" id = "num" name = "num" value = "${param.num }">
<textarea cols="60" rows="10" id = "content" name = "content">${param.content }</textarea>
<br>
<input type = "button" value ="수정" onclick = "replyUpdateBtn()">
<input type = "button" value ="창닫기" onclick = "windowCloseBtn()">
</form>
</div>
</body>
</html>