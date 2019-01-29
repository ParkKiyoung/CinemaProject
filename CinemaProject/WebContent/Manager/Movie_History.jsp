<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script>
	function movieUpdate(movienum){
		window.open("M_CallData.do?movienum="+movienum,"hiddenFrame","width=1000 height=500")
	}
	function movieDelete(movienum){
		if (confirm("정말 삭제하시겠습니까?") == true) { //확인
			location.href = "MovieDelete.do?movienum=" + movienum;
		} else { //취소
			return;
		}
	}
	function movieSearch(){
		$("#Manager_Header").load("M_CallHistory.do", {
			"word" : $("#word").val(),
		}, function(data) {
			$("#Main_Body").html(data)
		});
	}
</script>
<style>
	#MH_Table{
		border-collapse: collapse;
		font-size: 13px;
		color: #333333;
	}
	#MP_Btn{
		border : none;
	 	text-decoration : none;
		color : white;
		background-color : #555555;
		padding : 5px 10px 5px 10px;
		font-size: 13px;
		font-weight: 800;
		margin-right: 2px;
	}
	#MP_Btn:hover{
		border : none;
	 	text-decoration : none;
		color : white;
		background-color : #555555;
		padding : 5px 10px 5px 10px;
		font-size: 13px;
		font-weight: 800;
		margin-right: 2px;
		cursor : pointer;
	}
	#MP_Search_Btn{
		border : none;
	 	text-decoration : none;
		color : white;
		background-color : #555555;
		padding : 2px 5px 2px 5px;
		font-size: 13px;
		font-weight: 800;
		margin-right: 2px;
	}
	#MP_Search_Btn:hover{
		border : none;
	 	text-decoration : none;
		color : white;
		background-color : #555555;
		padding : 2px 5px 2px 5px;
		font-size: 13px;
		font-weight: 800;
		margin-right: 2px;
	}
	#MHT_Th{
		border-collapse: collapse;
		border-bottom: 1px solid #666666;
		margin : 0;
	}
	#MHT_Td{
		border-collapse: collapse;
		border-bottom: 1px solid #cccccc;
		margin : 0;
	}
</style>
			<br>
			<div align="center">
			<select id="field" name="field">
					<option value="subject">제목</option>
					<option value="actor">배우</option>
					<option value="director">감독</option>
			</select> 
			<input type="text" id="word" name="word" placeholder="검색어가 없으면 전체보기"> 
			<input id="MP_Search_Btn" type="button" onclick="javascript:movieSearch()" value="검색">
			</div>
<table id="MH_Table">
	<tr>

		<th id="MHT_Th" width="20%">제목</th>
		<th id="MHT_Th" width="15%">감독</th>
		<th id="MHT_Th" width="25%">출연진</th>
		<th id="MHT_Th" width="10%">개봉일</th>
		<th id="MHT_Th" width="15%">개봉전/개봉후 평점</th>
		<th id="MHT_Th" width="15%"></th>
	</tr>
	<c:forEach items="${movies }" var="mov">
		<tr>
			<td id="MHT_Td">${fn:substring(mov.subject, 0, 20)}<c:if
					test="${fn:length(mov.actor)>20 }">
							...
							</c:if></td>
			<td id="MHT_Td">${mov.director }</td>
			<td id="MHT_Td">${fn:substring(mov.actor, 0, 15)}<c:if
					test="${fn:length(mov.actor)>15 }">
							...
							</c:if></td>
			<td id="MHT_Td">${mov.rel_date }</td>
			<td id="MHT_Td" align="center">${mov.rel_rating }점/${mov.on_rating }점</td>
			<td id="MHT_Td"><input id="MP_Btn" type="button" value="수정"
				onclick="javascript:movieUpdate(${mov.num })"> <input
				id="MP_Btn" type="button" value="삭제"
				onclick="javascript:movieDelete(${mov.num })"></td>
		</tr>
	</c:forEach>
</table>
