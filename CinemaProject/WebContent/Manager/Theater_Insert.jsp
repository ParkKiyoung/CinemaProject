<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
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
										InsertcityList += "<select id ='cityList' name = 'cityList' onclick='InsertcityChoice()'>"
											InsertcityList += "<option value=''>직접입력</option>"
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
	function InsertcityChoice() {
		var city = document.getElementById("cityList").value
		document.getElementById("city").value = city
	}
	function createTheater() {
		if ($("#city").val() == "") {
			alert("도시명을 입력해주세요");
			return false;
		}
		if ($("#theaterName").val() == "") {
			alert("영화관 명을 입력해주세요");
			return false;
		}
		$("#frm").attr("action", "CreateNew");
		$("#frm").submit();
	}
	var roomCountIdx=0;
	function createNewRoomBtn(){
		for(var i = 0;i< roomCountIdx;i++){
			if($("#theaterRoom"+i).val()==""){
				alert("상영관명을 입력해주세요");
				return false
			}
			if($("#seat_x"+i).val()==""){
				alert("좌석갯수를 적어주세요");
				return false;
			}
			if($("#seat_x"+i).val()%$("#seat_x"+i).val()!=0||$("#seat_x"+i).val()>18){
				alert("가로열 입력 에러 : 좌석은 18이하의 숫자로만 적어주세요");
				return false;
			}
			if($("#seat_y"+i).val()==""){
				alert("좌석갯수를 적어주세요");
				return false;
			}
			if($("#seat_y"+i).val()%$("#seat_y"+i).val()!=0||$("#seat_y"+i).val()>18){
				alert("세로열 입력 에러 : 좌석은 18 이하의 숫자로만 적어주세요");
				return false;
			}
		}
		createNewRoom.submit();
		
	}
</script>
<style>
	#Ti_table{
		max-width: 100%;
		margin-top: 8%;
		margin-left: 20%;
	}
	#Ri_table{
		max-width: 100%;
	}
	#ITN_div{
		margin-top: 10px;
		text-align: center;
	}
</style>
<div>
<table id="Ti_table">
	<tr>
		<td>
			<form id="frm" name="frm">
					<table>
						<thead>
							<tr>
								<th colspan=3>영화관 등록</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>시/도</th>
								<td><input type="text" id="city" name="city"
									<c:if test="${cityName!=null }">disabled=true</c:if>> <span
									id="citycombo"></span></td>
							</tr>
							<tr>
								<th>영화관 명</th>
								<td><input type="text" id="theaterName" name="theaterName"
									<c:if test="${cityName!=null }">disabled=true</c:if>></td>
							</tr>
							<tr>
								<th colspan=2><input type="button" value="생성"
									onclick="createTheater()"> <input type="reset"
									value="취소"></th>
							</tr>
						</tbody>
					</table>
			</form>
		</td>
	</tr>
	<tr>
		<td>
			<form id="createNewRoom" method="post">
					<table  id="Ri_table">
						<thead>
							<tr>
								<th colspan=3>상영관 등록</th>
							</tr>
						</thead>
						<c:if test="${cityName!=null }">
						<script>alert("상영관 정보를 입력해주세요")</script>
						<input type = hidden id="theaterNum" name="theaterNum" value="${theaterNum }">
						<input type =hidden id=roomCountIdx name=roomCountIdx >
							<tr>
								<td>도시명 : ${cityName}</td>
								<td>영화관명 : ${theaterName }</td>
							</tr>
						</c:if>
						<tr>
							<th>상영관 수</th>
							<td><select onclick="makeInputRoomName()" id="theaterCount"
								name="theaterCount" <c:if test="${cityName==null }">disabled=true</c:if>>
									<option value=""></option>
									<option value=1>1</option>
									<option value=2>2</option>
									<option value=3>3</option>
									<option value=4>4</option>
									<option value=5>5</option>
									<option value=6>6</option>
									<option value=7>7</option>
									<option value=8>8</option>
									<option value=8>8</option>
									<option value=9>9</option>
									<option value=10>10</option>
							</select>개</td>
						</tr>
						<tr>
							<th colspan=2><br><input type="button" value="생성" onclick="createNewRoomBtn()">
								<input type="reset" value="취소"></th>
						</tr>
						</tbody>
					</table>
				<div id="ITN_div">
					<span id="inputTheaterName"></span>
				</div>
			</form>
		</td>
	</tr>
</table>
</div>
<style>
	#R_Info{
		font-size: 13px;
		font-weight: 700;
	}
</style>
<script>
	function makeInputRoomName() {
		var textCount = document.getElementById("theaterCount").value
		var inputText = "";
		for (var i = 0; i < textCount; i++) {
			roomCountIdx++;
			
			inputText += "<label id='R_Info'>상영관명</label>  <input type='text' id=theaterRoom" + i
					+ " name=theaterRoom" + i + " size='10px' value=상영관" + (i + 1)
					+ "><label id='R_Info'> 가로 좌석수</label>  <input type = text id=seat_x"+i+" name=seat_x"+i+" size='10px'> "
					+" <label id='R_Info'> 세로 좌석수</label>  <input type = text id=seat_y"+i+" name=seat_y"+i+" size='10px'> <br>"
		};
		document.getElementById("roomCountIdx").value=roomCountIdx;
		document.getElementById("inputTheaterName").innerHTML = inputText;
	};
</script>