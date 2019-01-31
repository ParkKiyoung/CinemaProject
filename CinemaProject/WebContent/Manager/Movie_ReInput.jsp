<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
</head>
<link rel="stylesheet" type="text/css" href="../CSS/TotalStyle.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
/* 숫자들어가야하는 곳 유효성 검사 아직 안함 */
function movieUpdate(movienum) {
	if (!$("#M_Title").val()) {
		alert("제목을 입력하세요.");
		return false;
	}
	if (!$("#M_Director").val()) {
		alert("감독을 입력하세요.");
		return false;
	}
	if (!$("#M_Actor").val()) {
		alert("출연진을 입력하세요.");
		return false;
	}
	if (!$("#M_Rel_Date").val()) {
		alert("개봉일을 선택하세요.");
		return false;
	}
	if (!$("#M_Playtime").val()) {
		alert("상영시간을 입력하세요.");
		return false;
	}
	if (!$("#M_Age").val()) {
		alert("관람가를 선택하세요.");
		return false;
	}
	if (!$("#M_Story").val()) {
		alert("줄거리를 입력하세요.");
		return false;
	}
	if (totalChecked==0) {
		alert("장르를 선택하세요.");
		return false;
	}
	frm.action = "MovieUpdate.do?movienum="+movienum;
	frm.submit();
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
<body>
	<form name="frm" method="post" enctype="multipart/form-data">
		<input type="hidden" id="M_OriImg" name="M_OriImg" value="${dto.img }">
		<table>
			<tr>
				<td valign="top"><label>포스터 등록</label></td>
				<td><input type="file" id="M_Img" name="M_Img"
					required="required"><br>----- 주의! 포스터를 변경하지 않을시에는 파일을
					등록하지 마시오 -----</td>
			</tr>
			<tr>
				<td><label>영화 제목</label></td>
				<td><input type="text" id="M_Title" name="M_Title"
					required="required" value="${dto.subject }"></td>
			</tr>
			<tr>
				<td><label>감독</label></td>
				<td><input type="text" id="M_Director" name="M_Director"
					required="required" value="${dto.director }"></td>
			</tr>
			<tr>
				<td><label>출연진</label></td>
				<td><input type="text" id="M_Actor" name="M_Actor"
					required="required" value="${dto.actor }"></td>
			</tr>
			<tr>
				<td><label>개봉 예정일</label></td>
				<td><input type="date" id="M_Rel_Date" name="M_Rel_Date"
					required="required" value="${dto.rel_date }"></td>
			</tr>
			<tr>
				<td><label>상영 시간</label></td>
				<td><input type="text" id="M_Playtime" name="M_Playtime"
					required="required" value="${dto.playtime }">분</td>
			</tr>
			<tr>
				<td><label>관람 연령</label></td>
				<td><select id="M_Age" name="M_Age" required="required">
						<option value=0>전체관람가</option>
						<option value=12>12세관람가</option>
						<option value=15>15세관람가</option>
						<option value=19>청소년관람불가</option>
				</select>
				<script>
					if(frm.M_Age[0].value==0) frm.M_Age[0].selected=true;
					if(frm.M_Age[1].value==12) frm.M_Age[1].selected=true;
					if(frm.M_Age[2].value==15) frm.M_Age[2].selected=true;
					if(frm.M_Age[3].value==19) frm.M_Age[3].selected=true;
				</script></td>
			</tr>
			<tr>
				<td valign="top"><label>줄거리</label></td>
				<td><textarea rows="5" cols="100" id="M_Story" name="M_Story"
						required="required" placeholder="1000자 미만으로 작성해주세요.">${dto.summary }</textarea></td>
			</tr>
			<tr>
				<td><label>장르</label></td>
				<td>
				<input type = hidden id = interArr name = interArr value='${arr }'>
					<table>
					<tr>
					<td width=120><input type="checkbox" name="M_Genre" value="스포츠" onclick="CountChecked(this)"> 스포츠 </td>
					<td width=120><input type="checkbox" name="M_Genre" value="범죄" onclick="CountChecked(this)"> 범죄 </td>
					<td width=120><input type="checkbox" name="M_Genre" value="드라마" onclick="CountChecked(this)"> 드라마 </td>
					<td width=120><input type="checkbox" name="M_Genre" value="다큐멘터리" onclick="CountChecked(this)"> 다큐멘터리 </td>
					</tr>
					<tr>
					<td><input type="checkbox" name="M_Genre" value="코미디" onclick="CountChecked(this)"> 코미디</td>
					<td><input type="checkbox" name="M_Genre" value="스릴러" onclick="CountChecked(this)"> 스릴러</td>
					<td><input type="checkbox" name="M_Genre" value="전쟁" onclick="CountChecked(this)"> 전쟁</td>
					<td><input type="checkbox" name="M_Genre" value="애니메이션" onclick="CountChecked(this)"> 애니메이션</td>
					</tr>
					<tr>
					<td><input type="checkbox" name="M_Genre" value="가족" onclick="CountChecked(this)"> 가족</td>
					<td><input type="checkbox" name="M_Genre" value="판타지" onclick="CountChecked(this)"> 판타지</td>
					<td><input type="checkbox" name="M_Genre" value="액션" onclick="CountChecked(this)"> 액션</td>
					<td><input type="checkbox" name="M_Genre" value="SF" onclick="CountChecked(this)"> SF</td>
					</tr>
					<tr>
					<td><input type="checkbox" name="M_Genre" id="romance" value="로맨스/멜로"	onclick="CountChecked(this)"> 로맨스/멜로</td>
					<td><input type="checkbox" name="M_Genre" id="romance" value="로맨스/코미디"	onclick="CountChecked(this)"> 로맨스/코미디</td>
					</tr>
				</table>
												<script>
				var interSplit=""
				if(document.getElementById("interArr").value!=null){
					inter = document.getElementById("interArr").value
					interSplit= inter.split(", ");
					for(i=0; i<frm.M_Genre.length;i++){
						if(frm.M_Genre[i].value==interSplit[0]){
							frm.M_Genre[i].checked=true;
							totalChecked = 1;
						}
						if(frm.M_Genre[i].value==interSplit[1]){
							frm.M_Genre[i].checked=true;
							totalChecked = 1;
						}
						if(frm.M_Genre[i].value==interSplit[2]){
							frm.M_Genre[i].checked=true;
							totalChecked = 1;
						}
				}
				
			} 
				</script>
			</td>
			</tr>
			<tr>
				<td align="center"><input type="button" id="M_Update_Btn" name="M_Update_Btn"
					value="수정" onclick="javascript:movieUpdate(${dto.num });"></td>
			</tr>
		</table>
	</form>
</body>
</html>