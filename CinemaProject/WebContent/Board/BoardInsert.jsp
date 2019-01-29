<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.Board_Main_Th {
	/* 	border: 1px solid #A9A9A9; */
	padding: 10px;
	text-align: center;
	background: black;
	color: white;
}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function insertBtn(){
	if($("#category").val()=="choice"){
		alert("카테고리를 선택하세요.");
		return;
	}
	if($("#subject").val()==""){
		alert("제목을 입력하세요.");
		return;
	}
	if($("#content").val()==""){
		alert("내용을 입력하세요.");
		return;
	}
	frm.submit();
}
function listBtn(){
	location.href = "../Board/boardList";
}
</script>
</head>
<body>
<h2 align="center">글쓰기</h2>
<br>
<br>
<div align="center">
	<form action = "../Board/boardInsert" method = "post" id = "frm">
	<input type = "hidden"  id = "userid" name = "userid" value = "${mb.userid }">
	<input type = "hidden"  id = "membernum" name = "membernum" value = "${mb.num }">
		<table style="width: 60%">
			<tr>
				<th class="Board_Main_Th">카테고리</th>
				<td>
				<select  id = "category" name = "category">
				<option value = "choice">선택</option>
				<option value = "movie">영화</option>
				<option value = "genre">장르</option>
				<option value = "actor">배우</option>
				</select>
				</td>
			</tr>
			<tr>
				<th class="Board_Main_Th">제목</th>
				<td><input type = "text" id = "subject" name = "subject"></td>
			</tr>
			<tr>
				<th class="Board_Main_Th">내용</th>
				<td><textarea id = "content" name = "content" rows="20" cols="50"></textarea></td>
			</tr>
			<tr align="center">
				<td colspan = "2"><input type = "button" value = "글쓰기" onclick = "insertBtn()">
				<input type = "button" value = "글목록" onclick = "listBtn()">
				<input type = "reset" value = "다시작성"></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>