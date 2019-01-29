<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>OCC</title>
<link rel="stylesheet" type="text/css" href="../CSS/TotalStyle.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	function L_Hover(e) {
		e.setAttribute('src', '../Images/left-r-red.png');
	}
	function L_Unhover(e) {
		e.setAttribute('src', '../Images/left-r-black.png');
	}
	function R_Hover(e) {
		e.setAttribute('src', '../Images/right-r-red.png');
	}
	function R_Unhover(e) {
		e.setAttribute('src', '../Images/right-r-black.png');
	}
	var scrollNum=1;
	$(document).ready(function() {
		MovieList(1);

	})
	function Onscreen() {//현재 상영작
		scrollNum = 1;
		MovieList(scrollNum);
	};
	function Watingscreen() {//개봉예정작
		scrollNum = 2;
		MovieList(scrollNum);
	};
	function ResScreen() {//예매순
		scrollNum = 3;
		MovieList(scrollNum);
	};
	function OnscreenScore() {//평정순
		scrollNum = 4;
		MovieList(scrollNum);
	};
	function MovieList(field) {
		//1. 현재 상영작 2.개봉예정작 3.예매순 4. 평점순
		if (field == null || field == "") {
			field = 1;
		}
		;
		$
				.get(
						"MainMovieAction",
						{
							"field" : field
						},
						function(data) {
							data = $.parseJSON(data);
							var MainMovieList = "";
							for (var i = 0; i < data.length; i++) {
								MainMovieList += "<td>"
								MainMovieList += "<a id='Movies"
										+ (i + 1)
										+ "' href=../Movie/View?movienum="
										+ data[i].HomeMovie_num
										+ "><img src='../Movie/PosterIMG/"+data[i].img+"' width= 200px height= 300px><br>"
										MainMovieList += "제목 : " + data[i].subject.substr(0,10);
								 if(data[i].subject.length>10){
									MainMovieList += "..."
									}  
								MainMovieList += "<br>";
								if (field == 2) {
									MainMovieList += "평점 : "
											+ data[i].rel_rating + "<br>"
								} else {
									MainMovieList += "평점 : "
											+ data[i].on_rating + "<br>"
								}
								MainMovieList += "개봉일 : " + data[i].rel_date
										+ "</a><br>"
								MainMovieList += "</td>"
							}
							;
							$("#HomeMainMovie").html(MainMovieList);
						})
	}
	
	function scrollLeft(){
		if(scrollNum==1){
			scrollNum=4;
		}else{
			scrollNum--;	
		}
		MovieList(scrollNum);
	}
	function scrollRight(){
		if(scrollNum==4){
			scrollNum=1;
		}else{
			scrollNum++;	
		}
		MovieList(scrollNum);
	}
</script>
<style>
	#CM_A{
		color: #cccccc;
		font-size: 15px;
		font-weight:bolder;
	}
	#CM_A:hover{
		color: #cc382e;
		font-size: 15px;
		font-weight:bolder;;
	}
</style>
<head>
</head>
<body>
	<div class="LoadMain"><jsp:include page="MainIndex.jsp" /></div>
	<div id="CinemaView" align="center">
		<table>
			<tr>
				<td colspan=7 align="right">
				</td>
			</tr>
			<tr>
				<td rowspan=4><a href="javascript:scrollLeft()"> <img
						src="../Images/left-r-black.png" width="50" height="50"
						onmouseover="L_Hover(this);" onmouseout="L_Unhover(this);" />
				</a></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>
					<p id="HomeMainMovie"></p>
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td rowspan=4><a href="javascript:scrollRight()"> <img
						src="../Images/right-r-black.png" width="50" height="50"
						onmouseover="R_Hover(this);" onmouseout="R_Unhover(this);" />
				</a></td>
			</tr>
		</table>
		<br>
		<div id="CinemaView_Menu" align="center">
			<ul>
				<li><a id="CM_A" href="javascript:Onscreen()">현재상영작</a></li>
				<li><a id="CM_A" href="javascript:Watingscreen()">개봉예정작</a></li>
				<li><a id="CM_A" href="javascript:ResScreen()">예매순</a></li>
				<li><a id="CM_A" href="javascript:OnscreenScore()">평점순</a></li>
			</ul>
		</div>
	</div>
	<br>
	<div class="Content_Total">
		<hr>
		<div id="content1" align="center">
			<jsp:include page="MainOnscreenList.jsp" />
		</div>
		<hr>
		<div id="content2" align="center">
			<jsp:include page="MainWatingList.jsp" />
		</div>
		<hr>
		<div id="content3" align="center">
			<jsp:include page="MainBoardList.jsp" />
		</div>
	</div>
			<div id="Footer">
			<jsp:include page="../Main/MainFooter.jsp"/>
		</div>
</body>
</html>