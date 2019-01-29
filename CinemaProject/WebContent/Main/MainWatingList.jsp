<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function() {

		$.get("GetMovie_NumAction", function(data) {
			obj = $.parseJSON(data);
			var movie_num = obj.GradeWatingNum;//미개봉 평점 높은 영화번호
			$.ajax({//미개봉 평점 높은 영화의 최신 평가글
				type:"get",
				url : "../Main/MainWatingAction",
				data : {"movie_num":movie_num},
				success:function(data){
					data=$.parseJSON(data);
					var GradeWaitingTable="";
					GradeWaitingTable+= "<table style='margin:0;'>";
					GradeWaitingTable+= "<tr>";
					GradeWaitingTable+= "<td rowspan='6'><a href =../Movie/View?movienum="+data[0].movie_num+"><img src='../Movie/PosterIMG/"+data[0].img+"' width=200px height=300px></a></td>";
					GradeWaitingTable+= "<th colspan='2' width='300'><a href =../Movie/View?movienum="+data[0].movie_num+">"+data[0].subject+"</a></th>"
					GradeWaitingTable+= "</tr>";
					for(var i = 0; i<data.length ; i++){
						GradeWaitingTable+= "<tr>";
						GradeWaitingTable+= "<td>"+"&nbsp;&nbsp;&nbsp;"+data[i].reply.substring(0, 10);
						if(data[i].reply.length>10){
							GradeWaitingTable+="...";
						}
						GradeWaitingTable+= "</td>";
						GradeWaitingTable+= "<td align ='center'>"+data[i].score+"점";
						GradeWaitingTable+= "</td>";
						GradeWaitingTable+= "</tr>";
					}
					GradeWaitingTable+= "</table>";
					$("#GradeWaitingTable").html(GradeWaitingTable);
				},
				error : function(e){
					alert("error"+e);
				}
			})
		$.post("../Main/MainWatingAction",
				function(data){
			data=$.parseJSON(data);
			
			var RankingWatingTable="";
			RankingWatingTable+= "<table>";
			RankingWatingTable+= "<tr>";
			RankingWatingTable+= "<td rowspan='13'><a href =../Movie/View?movienum="+data[0].movie_num+"><img src='../Movie/PosterIMG//"+data[0].img+"' width=200px height=300px></a></td>";
			RankingWatingTable+= "<th width='300'><a href =../Movie/View?movienum="+data[0].movie_num+">랭킹 1위 : "+data[0].subject.substring(0,10)
					if(data[0].subject.length>10){
						RankingWatingTable+="..."	
					}
			RankingWatingTable+="</a></th>"
			RankingWatingTable+= "<th>평가수 : "+data[0].commentCount+"개</th>"
			RankingWatingTable+= "</tr>";
			for(var i = 1; i<data.length ; i++){
				RankingWatingTable+= "<tr>";
				RankingWatingTable+= "<td align ='center'><a href =../Movie/View?movienum="+data[i].movie_num+">"+data[i].subject;
				RankingWatingTable+= "</a></td>";
				RankingWatingTable+= "<td align ='center'>"+data[i].commentCount+"개";
				RankingWatingTable+= "</td>";
				RankingWatingTable+= "</tr>";
			}
			RankingWatingTable+= "</table>";
			$("#RankingWatingTable").html(RankingWatingTable);
		});
		})
	});
</script>
	<h2 style="text-align: center;" class="New_Movies_H2">기대되는 영화</h2>
	<table width="100%">
	<tr>
	<td align="center" style="max-width:100%; font-size: 17px; font-weight:bolder; color:#288CFF;">
		네티즌 기대영화
	</td>
	<td align="center" style="max-width:100%; font-size: 17px; font-weight:bolder; color:#FF5A5A;">
		기대되는 화제작
	</td>
	</tr>
	<tr>
		<td align="center" colspan="2">
			<span style="float:left; width:50%; font-size:15px; color:#111111; max-width: 100%;" id="GradeWaitingTable"></span>
			<span style="float:left; width:50%; font-size:15px; color:#111111; max-width: 100%;" id="RankingWatingTable"></span>
		</td>
	</tr>
</table>
