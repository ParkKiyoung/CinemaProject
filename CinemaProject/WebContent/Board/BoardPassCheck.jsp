<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../CSS/TotalStyle.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function checkBtn(){
	$.ajax({
		url : "passCheck",
		type : "post",
		data : {"passcheck": $("#passcheck").val(),"membernum": $("#membernum").val()},
		success : function(data){
			if(data==1){
				if($("#checknum").val()==1){
					location.href = "boardView?num=${param.num}&checknum="+$("#checknum").val();
				}else{
					if(confirm("정말삭제하시겠습니까?")){
						location.href = "boardDelete?num=${param.num}";
					}
					return;
				}
			}else{
				alert("비밀번호가 틀립니다.");
				return;
			}
		}
	})
}
</script>
<style>
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
</style>
</head>
<body>
<div class="LoadMain"><jsp:include page="../Main/MainIndex.jsp"></jsp:include></div>
		<div class="ViewEntire" align="center">
<form id = "frm">
<input type = "hidden" id = "checknum" name = "checknum" value = "${param.checknum }" >
<input type = "hidden" id = "membernum" name = "membernum" value = "${mb.num }" >
<h2>비밀번호 확인</h2>
<table>
<tr>
<td><input type  = "password" id = "passcheck" name = "passcheck">
<input id="BVR_Btn" type = "button" value = "확인" onclick = "checkBtn()">
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