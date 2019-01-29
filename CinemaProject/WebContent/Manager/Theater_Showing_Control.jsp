<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	$(document)
			.ready(
					function() {
						$
								.ajax({
									type : "get",
									url : "../Main/PlaceCallback",
									success : function(data) {
										data = $.parseJSON(data);
										var InsertcityList = "";
										InsertcityList += "<select id ='cityName' name = 'cityName' onclick='cityChoice()'>"
										for (var i = 0; i < data.length; i++) {
											InsertcityList += "<option value='"+data[i].city+"'>"
													+ data[i].city
													+ "</option>";
										}
										InsertcityList += "</select>"
										$("#citycombo").html(InsertcityList);
									},
									error : function(e) {
										alert("error : " + 오류발생);
									}

								})
					})
	function cityChoice() {
		var city = $("#cityName").val();
		$
				.ajax({//영화관 불러옴
					type : "post",
					url : "../Main/PlaceCallback",
					data : {
						"city" : city
					},
					success : function(data) {
					
						data = $.parseJSON(data);
						var theaterList = "";
						theaterList += "<select id ='theatername' name = 'theatername' onclick='theaterChoice()'>"
						for (var i = 0; i < data.length; i++) {
							theaterList += "<option value='"+data[i].theaternum+"'>"
									+ data[i].theatername + "</option>";
						}
						theaterList += "</select>"
						$("#theaterList").html(theaterList);
					},
					error : function(e) {
						alert("error : " + e);
					}
				})
	}
	function theaterChoice() {//상영관 불러옴
		var theaterNum = $("#theatername").val();
		$
				.ajax({
					type : "get",
					url : "RoomCallBack",
					data : {
						"theaterNum" : theaterNum
					},
					success : function(data) {
						data = $.parseJSON(data);
						var roomList = "";
						roomList += "<select id ='roomList' name = 'roomList'>"
						for (var i = 0; i < data.length; i++) {
							roomList += "<option value='"+data[i].RoomNum+"'>"
									+ data[i].RoomName + "</option>";
						}
						roomList += "</select>"
						$("#roomListCombo").html(roomList);

					},
					error : function(e) {
						alert("error : " + e);
					}
				})

	}
	function thisDateMovie(){
		
		var thisDate = $("#showingDate").val()
		if(thisDate==null||thisDate==""){
			alert("날짜를 선택해주세요")
			return false;
		}
		$.ajax({
			type:"post",
			url: "SearchMovieInfo",
			data :{"thisDate":thisDate},
			success:function(data){
				data=$.parseJSON(data);
				
				var movieList = "";
				movieList += "<select id ='movieList' name = 'movieList' >"
				for (var i = 0; i < data.length; i++) {
					movieList += "<option value='"+data[i].movie_num+"'>"+data[i].MovieSubject + "</option>";
				}
				movieList += "</select>"
				
				$("#callmovieList").html(movieList);
				document.getElementById("ShowDate").value=thisDate;
				$("#showingDate").attr("disabled","true");
			},
			error : function(e) {
				alert("error : " + e);
			}
		})
	}
var showCountIdx=0;
function theater_time_insert(){
	if($("#roomList").val()==""){
		alert("상영관을 선택해주세요")
		return false;
	}
	if($("#movieList").val()==""){
		alert("영화를 선택해주세요");
		return false;
	}
	for(var i = 0; i<showCountIdx ; i++){
		if($("#Showcount"+i).val()==""){
			alert("상영시간을 입력해주세요");
			return false
		}
	}
	InsertTheaterTimeFrm.submit();
	
}
</script>
<style>
	#TSC_Table{
		max-width: 100%;
		margin-top: 10%;
	}
</style>
<form action="insertMovieDate" id="InsertTheaterTimeFrm" method="post">
	<table id="TSC_Table">
		<tr>
			<th colspan=2>영화 상영시간 등록</th>
		</tr>
		<tr>
			<th>시/도 선택</th>
			<td><span id="citycombo"></span></td>
		</tr>
		<tr>
			<th>영화관 선택</th>
			<td><span id="theaterList"><select><option>시/도
							선택</option></select></span></td>

		</tr>
		<tr>
			<td>상영관 선택</td>
			<td><span id ="roomListCombo"><select><option>영화관 선택</option></select></span></td>
		</tr>
		<tr>
			<td>날짜선택</td>
			<td><input type=date id="showingDate"><input type="button" value="조회" onclick ="thisDateMovie()"></td>
		</tr>

		<tr>
			<td>영화선택</td>
			<td><span id="callmovieList"><select><option>영화선택콤보</option></select></span></td>
		</tr>
		<tr>
			<td>회차선택</td>
			<td>하루<select id="showCount" onclick="makeShowTime()">
					<option value="1">1회</option>
					<option value="2">2회</option>
					<option value="3">3회</option>
					<option value="4">4회</option>
					<option value="5">5회</option>
					<option value="6">6회</option>
					<option value="7">7회</option>
					<option value="8">8회</option>
			</select>회 상영
			</td>
		</tr>
		<tr>
			<td colspan = 2>
			<input type="hidden" id="ShowDate" name="ShowDate">
			<input type="hidden" id=ShowCountIdx name = ShowCountIdx><span id="showTime"></span></td>
		</tr>
		<tr>
			<td colspan=2 align = center><input type=button value="상영시간등록" onclick="theater_time_insert()"><input type= reset value="재등록"></td>
		</tr>
	</table>
</form>
<script>
	function makeShowTime() {
		showCountIdx=0;
		var textCount = document.getElementById("showCount").value
		var inputText = "";
		for (var i = 0; i < textCount; i++) {
			showCountIdx++;
			inputText += "<b>"+(i+1)+"회차상영시간</b> : <input type = text id=Showcount"+i+" name=Showcount"+i+" ><br>"
		};
		document.getElementById("ShowCountIdx").value=showCountIdx;
		document.getElementById("showTime").innerHTML = inputText;
	};
</script>