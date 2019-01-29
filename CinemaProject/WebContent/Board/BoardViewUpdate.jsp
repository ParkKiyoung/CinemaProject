<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
  .Board_View_Update_Table {
    width: 60%;
    border: 1px solid #A9A9A9;
    border-collapse: collapse;
  }
  .Board_View_Update_Th {
    border: 1px solid #A9A9A9;
    padding: 10px;
    text-align: center;
  }
  .Board_View_Update_Td {
    border: 1px solid #A9A9A9;
    padding: 10px;
  }
  #content{
	height: 400px;  
	vertical-align: top;
  }
</style>
<title>Insert title here</title>
</head>
<body>
<form>
		<table class="Board_View_Update_Table">
			<tr>
				<th class="Board_View_Update_Th">제목</th>
				<td class="Board_View_Update_Td">${bb.subject }</td>
				<th class="Board_View_Update_Th">작성자</th>
				<td class="Board_View_Update_Td">${bb.userid }</td>
			</tr>
			<tr>
				<th class="Board_View_Update_Th">등록일</th>
				<td class="Board_View_Update_Td">${bb.created }</td>
				<th class="Board_View_Update_Th">조회수</th>
				<td class="Board_View_Update_Td">${bb.readcount }</td>
			</tr>
			<tr>
				<td class="Board_View_Update_Td" colspan = "4" id = "content">${bb.content }</td>
			</tr>
			<tr>
				<td class="Board_View_Update_Td" colspan = "4" align = "center">
				<input type = "button" value = "글목록" onclick = "BoardMain()">
				<input type = "button" value = "수정" onclick = "passcheckBtn(1)">
				<input type = "button" value = "삭제" onclick = "passcheckBtn(2)">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>