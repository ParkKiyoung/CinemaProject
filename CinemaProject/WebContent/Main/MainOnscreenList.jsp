<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function() {

		$.get("GetMovie_NumAction", function(data) {
			obj = $.parseJSON(data);
			var movie_num = obj.GradeShowingNum;//영화번호 콜백
			$.ajax({//인기 영화의 최신 평점
				type:"get",
				url : "../Main/MainOnScreenAction",
				data : {"movie_num":movie_num},
				success:function(data){
					data=$.parseJSON(data);
					var GradeTable="";
					GradeTable+= "<table>";
					GradeTable+= "<tr>";
					GradeTable+= "<td rowspan='6'><a href =../Movie/View?movienum="+data[0].movie_num+"><img src='../Movie/PosterIMG/"+data[0].img+"' width=200px height=300px></a></td>";
					GradeTable+= "<th colspan='2' width='300'><a href =../Movie/View?movienum="+data[0].movie_num+">"+data[0].subject+"</a></th>"
					GradeTable+= "</tr>";
					for(var i = 0; i<data.length ; i++){
						GradeTable+= "<tr>";
						GradeTable+= "<td>"+"&nbsp;&nbsp;&nbsp;"+data[i].reply.substring(0, 10);
						if(data[i].reply.length>10){
							GradeTable+="...";
						}
						GradeTable+= "</td>";
						GradeTable+= "<td align ='center'>"+data[i].score+"점";
						GradeTable+= "</td>";
						GradeTable+= "</tr>";
					}
					GradeTable+= "</table>";
					$("#GradeResult").html(GradeTable);
				},
				error : function(e){
					alert("error"+e);
				}
			})
			$.post("../Main/MainOnScreenAction",function(data){
				data=$.parseJSON(data);
				
				var RankingTable="";
				RankingTable+= "<table>";
				RankingTable+= "<tr>";
				RankingTable+= "<td rowspan='6'><a href =../Movie/View?movienum="+data[0].movie_num+"><img src='../Movie/PosterIMG/"+data[0].img+"' width=200px height=300px></a></td>";
				RankingTable+= "<th width='300'><a href =../Movie/View?movienum="+data[0].movie_num+">랭킹 1위 : "+data[0].subject.substring(0,10)
				if(data[0].subject.length>10){
					RankingTable+="..."	
				}
				RankingTable+="</a></th>";
				RankingTable+= "<th>평점 : "+data[0].score+"</th>"
				RankingTable+= "</tr>";
				for(var i = 1; i<data.length ; i++){
					RankingTable+= "<tr>";
					RankingTable+= "<td align ='center'><a href =../Movie/View?movienum="+data[i].movie_num+">"+data[i].subject;
					RankingTable+= "</a></td>";
					RankingTable+= "<td align ='center'>"+data[i].score+"점";
					RankingTable+= "</td>";
					RankingTable+= "</tr>";
				}
				RankingTable+= "</table>";
				$("#ranking").html(RankingTable);
				
			})
		});
	});
</script>
	<h2 style="text-align: center;" class="New_Movies_H2">HOT!한 개봉</h2>
	<table width="100%">
	<tr>
	<td align="center" style="max-width:100%; font-size: 17px; font-weight:bolder; color:#288CFF;">
	HOT!한 영화의 최신 평점
	</td>
	<td align="center" style="max-width:100%; font-size: 17px; font-weight:bolder; color:#FF5A5A;">
	HOT!한 랭킹
	</td>
	</tr>
		<tr>
			<td align="center" colspan="2">
				<span style="float:left; width:50%; font-size:15px; color:#111111; max-width: 100%;" id="GradeResult"></span>
				<span style="float:left; width:50%; font-size:15px; color:#111111; max-width: 100%;" id="ranking"></span>
			</td>
		</tr>
	</table>