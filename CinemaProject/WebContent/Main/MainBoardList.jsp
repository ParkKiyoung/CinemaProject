<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
	#Main_BoardList_Content3{
		border-collapse: collapse;
	}
	#Main_BoardList_Content3 #MBC3_Th,#MBC3_Td{
		border-bottom : 1px solid #cccccc;
		padding-bottom : 10px;
		padding-top : 10px;
	}
	#Main_BoardList_Content3 #MBC3_Title_Th{
		border-bottom : 2px solid #999999;
	}
</style>
<script>
	$(document).ready(function() {
		$.get("GetMovie_BoardAction", {
			"category" : "movie"
		}, function(data) {//영화 게시판
			data = $.parseJSON(data);
			var movieBoard = "";
			movieBoard += "<table id='Main_BoardList_Content3'>"
			movieBoard += "<tr><th id='MBC3_Title_Th' colspan = 3>영화게시판</th><tr>"
			movieBoard += "<tr>"
			movieBoard += "<th id='MBC3_Th' width=200px>제목</th>"
			movieBoard += "<th id='MBC3_Th'>추천수</th>"
			movieBoard += "</tr>"
			for (var i = 0; i < data.length; i++) {
				movieBoard += "<tr>"
				movieBoard += "<td id='MBC3_Td'><a href =../Board/boardView?num="+data[i].board_num+">" + data[i].subject.substring(0,10)
						if(data[i].subject.length>10){
							movieBoard+="...";
						}
				movieBoard += "</a></td>"
				movieBoard += "<td id='MBC3_Td'>" + data[i].good + "</td>"
				movieBoard += "</tr>"
			}
			movieBoard += "</table>";
			$("#movieBoard").html(movieBoard);
		})
		$.get("GetMovie_BoardAction", {
			"category" : "actor"
		}, function(data) {//배우게시판
			data = $.parseJSON(data);
			var actorBoard = "";
			actorBoard += "<table id='Main_BoardList_Content3'>"
			actorBoard += "<tr><th id='MBC3_Title_Th' colspan = 3>배우게시판</th><tr>"
			actorBoard += "<tr>"
			actorBoard += "<th id='MBC3_Th' width=200px>제목</th>"
			actorBoard += "<th id='MBC3_Th'>추천수</th>"
			actorBoard += "</tr>"
			for (var i = 0; i < data.length; i++) {
				actorBoard += "<tr>"
				actorBoard += "<td id='MBC3_Td'><a href =../Board/boardView?num="+data[i].board_num+">" + data[i].subject.substring(0,10)
				if(data[i].subject.length>10){
					actorBoard+="...";
				}
				actorBoard += "</a></td>"
				actorBoard += "<td id='MBC3_Td'>" + data[i].good + "</td>"
				actorBoard += "</tr>"
			}
			actorBoard += "</table>";
			$("#actorBoard").html(actorBoard);
		});
		$.get("GetMovie_BoardAction", {
			"category" : "genre"
		}, function(data) {//장르 게시판
			data = $.parseJSON(data);
			var genreBoard = "";
			genreBoard += "<table id='Main_BoardList_Content3'>"
			genreBoard += "<tr><th id='MBC3_Title_Th' colspan = 3>장르게시판</th><tr>"
			genreBoard += "<tr>"
			genreBoard += "<th id='MBC3_Th' width=200px>제목</th>"
			genreBoard += "<th id='MBC3_Th'>추천수</th>"
			genreBoard += "</tr>"
			for (var i = 0; i < data.length; i++) {
				genreBoard += "<tr>"
				genreBoard += "<td id='MBC3_Td'><a href =../Board/boardView?num="+data[i].board_num+">" + data[i].subject.substring(0,10)
				if(data[i].subject.length>10){
					genreBoard+="...";
				}						
				genreBoard += "</a></td>"
				genreBoard += "<td id='MBC3_Td'>" + data[i].good + "</td>"
				genreBoard += "</tr>"
			}
			genreBoard += "</table>";
			$("#genreBoard").html(genreBoard);
		})
	})
</script>
<table width="100%">
	<tr>
		<th align="center">
			<h2>와글 게시판</h2>
		</th>
	</tr>
	<tr>
		<td align="center">
			<span style="margin-top:2%; float: left; width:33%;" id="movieBoard"></span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="margin-top:2%; float: left; width:33%;" id="actorBoard"></span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="margin-top:0; float: left; width:33%;" id="genreBoard"></span>
		</td>
	</tr>
</table>