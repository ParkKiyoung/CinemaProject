<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
<script>
var emailPattern =/^[a-zA-Z0-9._-]+@[a-zA-z0-9.-]+\.[a-zA-Z]{2,4}$/;

var passpattren=/^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/;

	function idCheck() {
		window.open("../Login/idCheck.jsp", "", "width=400, height=200")
	}
	function signup() {
		if (frm.userid.value == "") {
			alert("아이디를 입력하세요");
			return false;
		}

		;
		if (frm.password.value != frm.pwdCheck.value
				|| frm.password.value == "") {
			alert("비밀번호가 일치하지 않거나, 미입력 상태입니다.");
			return false;
		}
		if(!$("#password").val().match(passpattren)){
			alert("6~20 영문 대소문자,최소 1개의 숫자 혹은 특수 문자 포함하세요");
			return false;
		}
		;
		if (frm.name.value == "") {
			alert("이름을 입력하세요");
			return false;
		}
		;
		if($("#email").val()!=null){
		if(!$("#email").val().match(emailPattern)){
			alert("이메일 형식이 아닙니다.")
			return false;
		}
		}
		if (frm.pnum.value == "") {
			alert("생년월일을 입력하세요");
			return false;
		}
		;
		if (frm.phone.value == "") {
			alert("전화번호를 입력하세요");
			return false;
		}
		;
		if (frm.zipcode.value == "") {
			alert("주소를 입력해주세요");
			return false;
		}
		;
		frm.submit();
	}
	function searchAddr() {//주소검색
		window.open("../Login/ZipCheck.jsp", "", "width=700 height=600");

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
</head>
<body>
	<div align=center>
		<h1>회원 가입</h1>
		<form action="../Login/join" method="post" id=frm>
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" id=userid name=userid readonly>
						<input type="button" value="중복체크" id="B_Range_Btn" onclick="idCheck()" ></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" id=password name=password placeholder="6~20자리 대소문자,최소1개의 숫자 혹은 특수문자">*</td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" id=pwdCheck name=pwdCheck>*</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" id=name name=name></td>
				</tr>
				<tr>
					<td>성별</td>
					<td><input type=radio id=gender name=gender value="male"
						checked>남 <input type=radio id=gender name=gender
						value="female">여</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" id=pnum name=pnum
						placeholder="생년월일 ex)20001021"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" id=phone name=phone></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" id=email name=email></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input type="text" id=zipcode name=zipcode readonly>
						<input type=button id="B_Range_Btn" onclick="searchAddr()" value="검색"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" id=addr1 name=addr1 size=70 readonly></td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td><input type="text" id=addr2 name=addr2></td>
				</tr>
			</table>
			<hr>
			<div>선택 사항입니다. 선택시 원하는 영화의 정보를 보다 빠르게 추천받으실수 있습니다.</div>
			<br>
			<div>선호 장르(최대 3개 선택 가능)</div>
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
			</table>
			<hr>
			<input type=button id="B_Range_Btn" value="가입" onclick="signup()"> <input
				type=reset id="B_Range_Btn" value="취소">
		</form>
	</div>
	<div id="Footer">
		<jsp:include page="../Main/MainFooter.jsp" />
	</div>
</body>
</html>