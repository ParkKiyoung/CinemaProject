<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String pageMovie = request.getParameter("source1");
	if (pageMovie == null) {
		pageMovie = "Movie_Insert";
	}
%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	/* 숫자들어가야하는 곳 유효성 검사 아직 안함 */
	function movieInsert() {
		if (!$("#M_Img").val()) {
			alert("영화 포스터를 등록해 주세요.");
			return false;
		}
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
		if ($("#M_Playtime").val() % $("#M_Playtime").val() != 0 &&  $("#M_Playtime").val() !=0) {
			alert("상영시간에는 분단위의 숫자만 입력하세요.");
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
		if (totalChecked == 0) {
			alert("장르를 선택하세요.");
			return false;
		}
		frm.action = "MovieInsert.do";
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
<style>
	#Manager_Movie{
		width : 100%;
		border-bottom : 1px solid #cccccc;
		border-bottom-width : thin;
		margin-top: 5px;
	}
	#Mm_a{
		text-decoration : none;
		color : black;
	}
	#Mm_a:hover{
		text-decoration : none;
		color : #ff382e;
	}
</style>
<form id="frm" method="post" enctype="multipart/form-data">
		<table id="Manager_Movie">
			<tr>
				<th><a id="Mm_a" href="Manager_Main.jsp?source1=Movie_Insert">새영화
									등록</a></th>
							<th><a id="Mm_a" href="M_CallPlan.do">상영 예정작</a></th>
							<th><a id="Mm_a" href="M_CallList.do">현재 상영작</a></th>
							<th><a id="Mm_a" href="M_CallHistory.do">지난 상영작</a></th>
			</tr>
		</table>
<div align="center">
	<jsp:include page='<%=pageMovie + ".jsp"%>' />
</div>
</form>
