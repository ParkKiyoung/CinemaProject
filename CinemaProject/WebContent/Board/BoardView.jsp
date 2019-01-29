<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
  .Board_View_Table {
    width: 60%;
    border: 1px solid #A9A9A9; 
    border-collapse: collapse;
  }
  .Board_View_Th {
     padding: 10px;
	text-align: center;
	background: black;
	color: white;
  }
   .Board_View_Td {
     border: 1px solid #A9A9A9; 
    padding: 10px;
  }
  #contentCss{
	height: 400px;  
	vertical-align: top;
  }
  #BV_reply{
	text-align: center;
  	max-width: 100%;
  }
  #BVR_Btn{
  	border: none;
  	background-color: black;
  	color: white;
  	font-size: 13.5px;
  	font-weight : 800;
  	padding: 5px 10px 5px 10px;
  }
  #BVR_Btn:hover{
  	border: none;
  	background-color: black;
  	color: white;
  	font-size: 13.5px;
  	font-weight : 800;
  	padding: 5px 10px 5px 10px;
  	cursor: pointer;
  }
  #BVR_div{
  	text-align: center;
  	max-width: 100%;
  }
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
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function passcheckBtn(num){
	location.href = "BoardPassCheck.jsp?checknum="+num+"&num=${bb.num}";
}
function BoardMain(){
	location.href = "boardList";
}
function replyInsertBtn(){
	if($("#reply").val()==""){
		alert("댓글내용을 입력해주세요.");
		return;
	}
	if($("#membernum").val()==""){
		alert("로그인 후 이용해주세요.");
		return;
	}
	frm2.submit();
}
window.onload = function() {
	getData(1);
}
function getData(pageNum){
	$("#replylist").load("boardReplyList",
			{"pageNum":pageNum,"boardnum":$("#num").val()},
				function(data){
					$("#replylist").html(data);
});
}
function nextBeforeBtn(boardnum,checknum){
	location.href = "viewNextBefore?boardnum="+boardnum+"&checknum="+checknum;
}
</script>
<title>OCC</title>
</head>
<body>
<c:if test="${checknum == -1 }">
				<h2 align="center">글상세보기</h2>
			</c:if>
				<c:if test="${checknum == 1 }">
				<h2 align="center">글수정</h2>
			</c:if>
<br>
<br>
<div align = "center">
	<form id = "frm" action = "boardUpdate">
	<input type = "hidden" id = "num" name = "num" value = "${param.num }">
		<table class="Board_View_Table">
			<tr>
				<th class="Board_View_Th">제목</th>
				<c:if test="${checknum == -1 }">
				<td class="Board_View_Td">${bb.subject }</td>
				</c:if>
				<c:if test="${checknum == 1 }">
				<td class="Board_View_Td"><input type = "text" id = "subject" name = "subject" value = "${bb.subject }"></td>
				</c:if>
				<th class="Board_View_Th">작성자</th>
				<td class="Board_View_Td">${bb.userid }</td>
			</tr>
			<tr>
				<th class="Board_View_Th">등록일</th>
				<td class="Board_View_Td">${bb.created }</td>
				<th class="Board_View_Th">조회수</th>
				<td class="Board_View_Td">${bb.readcount }</td>
			</tr>
			<tr>
			<c:if test="${checknum == -1 }">
				<td class="Board_View_Td" colspan = "4" id = "contentCss">${bb.content }</td>
			</c:if>
				<c:if test="${checknum == 1 }">
				<td class="Board_View_Td" colspan = "4">
				<textarea id = "content" name = "content" cols="50" rows="20">${bb.content }</textarea>
				</td>
			</c:if>
			</tr>
			<tr>
			<c:if test="${checknum == -1 }">
<!-- 			해당회원의 글이면 수정 삭제 버튼 활성화 			 -->
			<c:if test="${bb.membernum == mb.num }">
			<td class="Board_View_Td" colspan = "4" align = "center">
				<input id="BVR_Btn" type = "button" value = "수정" onclick = "passcheckBtn(1)">
				<input id="BVR_Btn" type = "button" value = "삭제" onclick = "passcheckBtn(2)">
				</td>
				</c:if>
				<tr>
				<td class="Board_View_Td" colspan = "4" align = "center">
				<input type = "button" id="B_Range_Btn" value = "이전글" onclick = "nextBeforeBtn(${bb.num},-1)">
				<input type = "button" id="B_Range_Btn" value = "글목록" onclick = "BoardMain()">
				<input type = "button" id="B_Range_Btn" value = "다음글" onclick = "nextBeforeBtn(${bb.num},1)">
				</td>
				</tr>
			</c:if>
			<c:if test="${checknum == 1 }">
			<td class="Board_View_Td" colspan = "4" align = "center">
				<input id="BVR_Btn" type = "submit" value = "확인">
				</td>
			</c:if>
			</tr>
		</table>
		<c:if test="${bb.updated != '' }">
			마지막 수정일자 : ${bb.updated }
			</c:if>
	</form>
	</div>
	<br>
	<br>
	<div id = "replylist" align = "center"></div>
	<br>
	<br>
	<c:if test="${mb != null and checknum == -1 }">
	<form id = "frm2" action = "replyInsert" method = "post">
		<div id="BV_reply" align = "center">
		<input type = "hidden" id = "num" name = "num" value = "${param.num }">
		<input type = "hidden" id = "membernum" name = "membernum" value = "${mb.num }">
		<input type = "hidden" id = "userid" name = "userid" value = "${mb.userid}">
		<textarea id = "reply" name = "reply" rows="5" cols="80"></textarea>
		</div>
		<div id="BVR_div">
			<input id="BVR_Btn" type = "button" value = "댓글등록" onclick = "replyInsertBtn()">
			<input id="BVR_Btn" type = "reset" value = "다시작성">
		</div>
	</form>
	</c:if>
	<c:if test="${mb == null }">
	<div align= center>
	<a href = "../Main/MainLoginTemp.jsp">로그인</a> 후 댓글을 등록할 수 있습니다.
	</div>
	</c:if>
	<div id="Footer">
		<jsp:include page="../Main/MainFooter.jsp" />
	</div>
</body>
</html>