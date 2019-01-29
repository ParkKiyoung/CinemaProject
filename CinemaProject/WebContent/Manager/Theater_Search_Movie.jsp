<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 검색</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	function SearchMovie() {
		var movieSubject = document.getElementById("movieSubject").value;
		$.ajax({
			type : "get",
			url : "SearchMovieInfo",
			data : {
				"movieSubject" : movieSubject
			},
			success : function(data) {
				
				data = $.parseJSON(data);
				var movieList = ""
				movieList +="<table>"
					movieList+="<tr><th>영화제목</th><th width='150px'>개봉날짜</th><th>관람제한</th><th>상영시간</th></tr>"
				for(var i = 0 ; i<data.length; i++){
					title = data[i].movieSubject;
					uptitle=title.replace(/ /gi,"");
					movieList+="<tr>";
					movieList+="<td align = 'center'><a href=javascript:sendMovieNum("+data[i].movie_num+",'"+uptitle+"')>";
					movieList+=data[i].movieSubject+" </a></td><td align = 'center'>"+data[i].rel_date+"</td><td align = 'center'>"+data[i].age+"세 이용가</td><td align = 'center'>"+data[i].runtime+"</td>";
					movieList+="</tr>"
				}
				movieList+="</table>";
				$("#movieSearchList").html(movieList);
			},
			error : function(e) {
				alert("error : " + 오류발생);
			}
		})
	}
function sendMovieNum(num,subject){
 
	$(opener.document).find("#movieTitle").val(subject);
	$(opener.document).find("#movie_num").val(num);
	self.close();
	
}
</script>

</head>
<body>
	<div align = center>
		<h3 align=center>영화 제목 검색</h3>
		영화제목 : <input type="text" id="movieSubject" name="movieSubject"
			onkeyup="SearchMovie()">
		<span id = movieSearchList></span>
	</div>
</body>
</html>