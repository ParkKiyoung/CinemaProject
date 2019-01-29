<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

	function movieSearch() {
		window.open("Theater_Search_Movie.jsp", "", "width=500 height=600");
	}
	function theater_date_Update(){
		if(insertMovieFrm.movieTitle.value==""){
			alert("영화를 선택해주세요")
			return false;
		}
		if(insertMovieFrm.indate.value==""){
			alert("상영시작날짜를 선택해주세요")
			return false;
		}
		if(insertMovieFrm.outdate.value==""){
			alert("상영종료날짜를 선택해주세요")
			return false;
		}
		insertMovieFrm.submit();
	}
</script>
<style>
	#TDC_Table{
		max-width: 100%;
		margin-left: 15%;
	}
</style>
<form action="insertMovieDate" method="get" id="insertMovieFrm">
	<table id="TDC_Table">
		<tr>
			<th colspan=3>
				영화 상영등록
			</th>
		</tr>
		<tr>
			<td>영화 선택</td>
			<input type="hidden" id="movie_num" name="movie_num">
			<td><input type="text" id="movieTitle" name="movieTitle"
				readonly></td>
			<td><input type=button value="영화 찾기" onclick="movieSearch()"></td>
		</tr>
		<tr>
			<td>상영시작일 선택:</td>
			<td><input type="date" id="indate" name="indate"></td>
		</tr>
		<tr>
			<td>상영종료일 선택:</td>
			<td><input type="date" id="outdate" name="outdate"></td>
		</tr>
		<tr>
			<td><input type = button onclick = "theater_date_Update()" value="등록"><input type=reset value="취소"></td>
		</tr>
	</table>
</form>