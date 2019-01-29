<%@page import="com.Member.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<script>
	function U_Update_Go(){
		if(W_frm.U_Id.value == ""){
			alert("아이디를 입력하세요");
			return false;
		}
		if(W_frm.U_Pwd.value == ""){
			alert("비밀번호를 입력하세요");
			return false;
		}
		if(W_frm.U_Name.value == ""){
			alert("이름를 입력하세요");
			return false;
		}
		if(W_frm.U_Pnum.value == ""){
			alert("주민번호를 입력하세요");
			return false;
		}6
		if(W_frm.U_Phone.value == ""){
			alert("비밀번호를 입력하세요");
			return false;
		}
		if(W_frm.U_Gender.value == ""){
			alert("성별을 입력하세요");
			return false;
		}
		if(W_frm.U_Email.value == ""){
			alert("이메일을 입력하세요");
			return false;
		}
		if(W_frm.U_Zip.value == ""){
			alert("우편번호를 입력하세요");
			return false;
		}
		if(W_frm.U_Addr1.value == ""){
			alert("주소를 입력하세요");
			return false;
		}
		if(W_frm.U_Addr2.value == ""){
			alert("상세 주소를 입력하세요");
			return false;
		}
		if(W_frm.U_Genre1.value == W_frm.U_Genre2.value || 
				W_frm.U_Genre1.value == W_frm.U_Genre3.value || 
					W_frm.U_Genre1.value == W_frm.U_Genre3.value){
			alert("장르가 중복 선택되었습니다.");
			return false;
			
		}
		W_frm.action="U_UpdateAction.do"
		W_frm.submit();
	}
</script>
</head>
<body>
	<form id="W_frm" method="post">
		<table align="center">
			<tr>
				<td>
					<h2>회원 정보 수정</h2>
				</td>
			</tr>
			<tr>
				<td>
					<label>회원 번호</label>
				</td>
				<td>
					<input type="text" id="U_Num" name="U_Num" value="${mb.getNum()}" readonly>
				</td>
			</tr>
			<tr>
				<td>
					<label>아이디</label>
				</td>
				<td>
					<input type="text" id="U_Id" name="U_Id" value="${mb.getUserid()}">
				</td>
			</tr>
			<tr>
				<td>
					<label>비밀번호</label>
				</td>
				<td>
					<input type="text" id="U_Pwd" name="U_Pwd" value="${mb.getPassword()}">
				</td>
			</tr>
			<tr>
				<td>
					<label>이름</label>
				</td>
				<td>
					<input type="text" id="U_Name" name="U_Name" value="${mb.getName()}">
				</td>
			</tr>
			<tr>
				<td>
					<label>주민번호</label>
				</td>
				<td>
					<input type="text" id="U_Pnum" name="U_Pnum" value="${mb.getPnum()}">
				</td>
			</tr>
			<tr>
				<td>
					<label>전화번호</label>
				</td>
				<td>
					<input type="text" id="U_Phone" name="U_Phone" value="${mb.getPhone()}">
				</td>
			</tr>
			<tr>
				<td>
					<label>성별</label>
				</td>
				<td>
					<input type="text" id="U_Gender" name="U_Gender" value="${mb.getGender()}">
				</td>
			</tr>
			<tr>
				<td>
					<label>이메일</label>
				</td>
				<td>
					<input type="text" id="U_Email" name="U_Email" value="${mb.getEmail()}">
				</td>
			</tr>
			<tr>
				<td>
					<label>우편번호</label>
				</td>
				<td>
					<input type="text" id="U_Zip" name="U_Zip" value="${mb.getZipcode()}">
				</td>
			</tr>
			<tr>
				<td>
					<label>주소</label>
				</td>
				<td>
					<input type="text" id="U_Addr1" name="U_Addr1" value="${mb.getAddr1()}">
				</td>
			</tr>
			<tr>
				<td>
					<label>상세주소</label>
				</td>
				<td>
					<input type="text" id="U_Addr2" name="U_Addr2" value="${mb.getAddr2()}">
				</td>
			</tr>
			<tr>
				<td>
					<label>관심장르1</label>
				</td>
				<td>
					<select name="U_Genre1">
						<c:forTokens var="Genre" 
									items="선택,스포츠,범죄,드라마,코미디,로맨스/멜로,스릴러,로맨스/코미디,전쟁,가족,판타지,액션,SF,애니메이션,다큐멘터리"
									delims=",">
									
							<option value="${Genre}">${Genre}</option>
						</c:forTokens>
					</select>
<script>
			            for (i=0;i<W_frm.U_Genre1.length;i++){
			               if(W_frm.U_Genre1[i].value=='${mb.getInter1()}'){
			            	   W_frm.U_Genre1[i].selected=true;
			               }
			            }
</script>
				</td>
			</tr>
			<tr>
				<td>
					<label>관심장르2</label>
				</td>
				<td>
					<select name="U_Genre2">
						<c:forTokens var="Genre" 
									items="선택,스포츠,범죄,드라마,코미디,로맨스/멜로,스릴러,로맨스/코미디,전쟁,가족,판타지,액션,SF,애니메이션,다큐멘터리"
									delims=",">
									
							<option value="${Genre}">${Genre}</option>
						</c:forTokens>
					</select>
<script>
			            for (i=0;i<W_frm.U_Genre2.length;i++){
			               if(W_frm.U_Genre2[i].value=='${mb.getInter2()}'){
			            	   W_frm.U_Genre2[i].selected=true;
			               }
			            }
</script>
				</td>
			</tr>
			<tr>
				<td>
					<label>관심장르3</label>
				</td>
				<td>
					<select name="U_Genre3">
						<c:forTokens var="Genre" 
									items="선택,스포츠,범죄,드라마,코미디,로맨스/멜로,스릴러,로맨스/코미디,전쟁,가족,판타지,액션,SF,애니메이션,다큐멘터리"
									delims=",">
									
							<option value="${Genre}">${Genre}</option>
						</c:forTokens>
					</select>
<script>
			            for (i=0;i<W_frm.U_Genre3.length;i++){
			               if(W_frm.U_Genre3[i].value=='${mb.getInter3()}'){
			            	   W_frm.U_Genre3[i].selected=true;
			               }
			            }
</script>
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" id="U_Update_Btn" name="U_Update_Btn" value="수정" onclick="U_Update_Go()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>