<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../CSS/TotalStyle.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function searchAddr(){
	window.open("../Login/ZipCheck.jsp","","width=700 height=600");
	
}
function Memberupdate(){
	if(frm.userid.value ==""){
		alert("아이디를 입력하세요");
		return false;
	};
	if(frm.password.value != frm.pwdCheck.value||frm.password.value==""){
		alert("비밀번호가 일치하지 않거나, 미입력 상태입니다.");
		return false;
	};
	if(frm.name.value ==""){
		alert("이름을 입력하세요");
		return false;
	};
	if(frm.pnum.value ==""){
		alert("생년월일을 입력하세요");
		return false;
	};
	if(frm.phone.value ==""){
		alert("전화번호를 입력하세요");
		return false;
	};
 	if(frm.zipcode.value ==""){
		alert("주소를 입력해주세요");
		return false;
	}; 
	frm.submit();
}
function deleteBtn(){
	alert("회원탈퇴 페이지로 넘어갑니다.");
	location.href="../Login/MemberPassCheck.jsp"
}
var maxChecked = 3; //선택가능한 체크박스 갯수
var totalChecked = 0; // 설정 끝
function CountChecked(field) {//체크박스 3개만 고르도록 하기
	if (field.checked)
		totalChecked += 1;
	else
		totalChecked -= 1;

	if (totalChecked > maxChecked) {
		alert("최대 3개 까지만 가능합니다.");
		field.checked = false;
		totalChecked -= 1;
	}
}
</script>
<style>
#B_Range_Btn{
	border : none;
	background-color : #555555;
	color : #ffffff;
	font-weight : bolder;
	font-size : 15px;
	padding: 5px 10px 5px 10px;
	border-radius: 3px;
}
#B_Range_Btn:hover{
	border : none;
	background-color : #555555;
	color : #ffffff;
	font-weight : bolder;
	font-size : 15px;
	padding: 5px 10px 5px 10px;
	border-radius: 3px;
	cursor : pointer;
}
</style>
<title>OCC</title>
</head>
<body>
	<div class="LoadMain"><jsp:include page="../Main/MainIndex.jsp"></jsp:include></div>
	<div class="ViewEntire" align="center">
		<h1>나의 정보</h1>
		<form action="../Login/MemberUpdate" id="frm" method="post" name="frm">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" id=userid name=userid readonly
						value="${mb.userid}"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" id=password name=password>*</td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" id=pwdCheck name=pwdCheck>*</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" id=name name=name value="${mb.name}"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td><input type=radio id=gender name=gender value="male"
						<c:if test="${mb.gender=='male'}">checked</c:if> disabled="true">남
						<input type=radio id=gender name=gender value="female"
						<c:if test="${mb.gender=='female'}">checked</c:if> disabled="true">여</td>
				</tr>
				<tr>
					<td>주민 번호</td>
					<td><input type="text" id=pnum name=pnum value="${mb.pnum}"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" id=phone name=phone
						value="${mb.phone }"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" id=email name=email
						value="${mb.email }"></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input type="text" id=zipcode name=zipcode readonly
						value="${mb.zipcode}"> <input id="B_Range_Btn" type=button
						onclick="searchAddr()" value="검색"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" id=addr1 name=addr1 size=70 readonly
						value="${mb.addr1}"></td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td><input type="text" id=addr2 name=addr2 value="${mb.addr2}"></td>
				</tr>
			</table>
			<hr>
			<div>
				선택 사항입니다. 선택시 원하는 영화의 정보를 보다 빠르게 추천받으실수 있습니다.<br>선택하지 않는 경우 기존
				선택에서 바뀌지 않습니다.
			</div>
			<br>
			<div>
				선호 장르(최대 3개 선택 가능)<br>나의 선택
			</div>
			<table>
				<tr>
					<td width=120><input type="checkbox" name="checklist"
						value="action" onclick="CountChecked(this)"> 액션
					<td width=120><input type="checkbox" name="checklist"
						value="fantasy" onclick="CountChecked(this)"> 판타지</td>
					<td width=120><input type="checkbox" name="checklist"
						value="school" onclick="CountChecked(this)"> 학원</td>
					<td width=120><input type="checkbox" name="checklist"
						value="noir" onclick="CountChecked(this)"> 느와르</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checklist" value="SF"
						onclick="CountChecked(this)"> SF
					<td><input type="checkbox" name="checklist" value="drama"
						onclick="CountChecked(this)"> 드라마</td>
					<td><input type="checkbox" name="checklist" value="horor"
						onclick="CountChecked(this)"> 호러</td>
					<td><input type="checkbox" name="checklist" value="crime"
						onclick="CountChecked(this)"> 범죄</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checklist" value="anime"
						onclick="CountChecked(this)"> 애니메이션
					<td><input type="checkbox" name="checklist" value="family"
						onclick="CountChecked(this)"> 가족</td>
					<td><input type="checkbox" name="checklist" value="comedy"
						onclick="CountChecked(this)"> 코메디</td>
					<td><input type="checkbox" name="checklist" value="docum"
						onclick="CountChecked(this)"> 다큐멘터리</td>
				</tr>
				<script>
					for(i=0; i<frm.checklist.length;i++){
						if(frm.checklist[i].value=="${mb.inter1}"){
							frm.checklist[i].checked=true;
							totalChecked = 1;
						}
						if(frm.checklist[i].value=="${mb.inter2}"){
							frm.checklist[i].checked=true;
							totalChecked = 2;
						}
						if(frm.checklist[i].value=="${mb.inter3}"){
							frm.checklist[i].checked=true;
							totalChecked = 3;
						}
				}
				</script>
			</table>
			<br>
			<input id="B_Range_Btn" type=button value="수정" onclick="Memberupdate()"> 
			<input id="B_Range_Btn" type=reset value="취소">
			<input id="B_Range_Btn" type=button value="회원탈퇴" onclick="deleteBtn()">
		</form>
		<hr>
		<h2>예매 내역</h2>
	<jsp:include page="../Login/ResActivity.jsp"></jsp:include>
		<hr>
		<h2>남긴 평가</h2>
	<jsp:include page="../Login/GradeActivity.jsp"></jsp:include>
		<hr>
		<h2>남긴 게시글</h2>
	<jsp:include page="../Login/BoardActivity.jsp"></jsp:include>
	</div>
	<div id="Footer">
		<jsp:include page="../Main/MainFooter.jsp" />
	</div>
</body>
</html>