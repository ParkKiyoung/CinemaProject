<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
div label input {
   margin-right:100px;
}
body {
    font-family:sans-serif;
}

#ck-button {
    margin:2px;
    background-color:#EFEFEF;
    border-radius:4px;
    border:1px solid #D0D0D0;
    overflow:auto;
    float:left;
}

#ck-button:hover {
    margin:2px;
    background-color:#EFEFEF;
    border-radius:4px;
    border:1px solid red;
    overflow:auto;
    float:left;
    color:red;
}

#ck-button label {
    float:left;
    width:2.0em;
}

#ck-button label span {
    text-align:center;
    padding:3px 0px;
    display:block;
}

#ck-button label input {
    position:absolute;
    top:-20px;
}

#ck-button input:checked + span {
    background-color:#911;
    color:#fff;
}

</style>
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
										var cityList = "";
										cityList += "<select id ='cityName' name = 'cityName' onclick='cityChoice()'>"
										for (var i = 0; i < data.length; i++) {
											cityList += "<option value='"+data[i].city+"'>"
													+ data[i].city
													+ "</option>";
										}
										cityList += "</select>"
										$("#cityListCombo").html(cityList);
									},
									error : function(e) {
										alert("error : " + e);
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
						roomList += "<select id ='roomList' name = 'roomList' onclick='roomChoice()'>"
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
	var idx = 0;
	var styleSplit ="";
	var arr = new Array();
	function roomChoice() {
		document.getElementById("blockSeat").value ="";
		styleSplit =null;
		idx=0;
		var room_Num = $("#roomList").val();
		document.getElementById("ROOM_NUM").value=room_Num;

		$
				.ajax({
					type : "post",
					url : "RoomCallBack",
					data : {
						"roomNum" : room_Num
					},
					success : function(data) {
						data = $.parseJSON(data);
						if(data.r_blockSeat!=null){
							var style = data.r_blockSeat//구조 옵션 값만 불러옴
							styleSplit = style.split(',');//스타일 쪼개서 배열화
						}
						var seat_x = data.r_seat_x
						var seat_y = data.r_seat_y
						document.getElementById("Roomseat_x").value=seat_x;
						document.getElementById("Roomseat_y").value=seat_y;
						document.getElementById("changedSeat_x").value=seat_x;
						document.getElementById("changedSeat_y").value=seat_y;
						var RoomTable = "";
						RoomTable += "<table border solid>"
						RoomTable += "<tr><th colspan="+seat_y+">SCREEN</th></tr>"
						for (var i = 0; i < seat_x; i++) {
							RoomTable += "<tr>"
							var a = "";
							switch (i) {
							case (0):a = "A";break;
							case (1):a = "B";break;
							case (2):a = "C";break;
							case (3):a = "D";break;
							case (4):a = "E";break;
							case (5):a = "F";break;
							case (6):a = "G";break;
							case (7):a = "H";break;
							case (8):a = "I";break;
							case (9):a = "J";break;
							case (10):a = "K";break;
							case (11):a = "L";break;
							case (12):a = "M";break;
							case (13):a = "N";break;
							case (14):a = "O";break;
							case (15):a = "P";break;
							case (16):a = "Q";break;
							case (17):a = "R";break;
							}
							for (var j = 1; j <= seat_y; j++) {
								if(styleSplit!=null){
									var detailTable = "";
									for (var k = 0; k < styleSplit.length; k++) {
										if (styleSplit[k]==(a+j).trim()) {
											detailTable = "<td><div id='ck-button'><label><input type='checkbox' name=seatList value="+a+j+" onclick='seatBlock(this)' checked><span>"+a+j+"</span></label></div></td>";
											arr[idx] = a+j;
											idx++;
											break;
										}else{
											detailTable ="<td><div id='ck-button'><label><input type='checkbox' name=seatList value="+a+j+" onclick='seatBlock(this)'><span>"+a+j+"</span></label></div></td>";
											
											}
										}
									RoomTable += detailTable;
								}else{
									RoomTable += "<td><div id='ck-button'><label><input type='checkbox' name=seatList value="+a+j+" onclick='seatBlock(this)'><span>"+a+j+"</span></label></div></td>";

								}
								
							}
							RoomTable += "</tr>"

						}
						RoomTable += "</table>"
						$("#RoomTable").html(RoomTable);
						

					},
					error : function(e) {
						alert("error : " + e);
					}
				})
	}


	function seatBlock(field) {
		document.getElementById("blockSeat").value = "";
		if (field.checked) {
			arr[idx] = field.value;
			idx++;
			

		} else if (field.checked == false) {
			for (var i = 0; i < idx; i++) {
				if (arr[i] == field.value) {
					arr[i] = "";
					idx--;
					
				}
			}
		}
		//출력
		for (i = 0; i < idx; i++) {
			document.getElementById("blockSeat").value += arr[i] + " ";
		}

	}

	function updatedSeat(){
		document.getElementById("blockSeat").value ="";
		var sheetSeat_x = document.getElementById("Roomseat_x").value // written number on the textfield
		if(sheetSeat_x>18||sheetSeat_x%sheetSeat_x!=0){
			alert("오류 : 18 이하의 숫자만 입력가능합니다..")
			return false;
		}
		var sheetSeat_y = document.getElementById("Roomseat_y").value // written number on the textfield
		if(sheetSeat_y>18||sheetSeat_y%sheetSeat_y!=0){
			alert("오류 : 18 이하의 숫자만 입력가능합니다..")
			return false
		}
		var RoomTable = "";
		RoomTable += "<table border solid>"
		RoomTable += "<tr><th colspan="+sheetSeat_y+">SCREEN</th></tr>"
		for (var i = 0; i < sheetSeat_x; i++) {
			RoomTable += "<tr>"
			var a = "";
			switch (i) {
			case (0):a = "A";break;
			case (1):a = "B";break;
			case (2):a = "C";break;
			case (3):a = "D";break;
			case (4):a = "E";break;
			case (5):a = "F";break;
			case (6):a = "G";break;
			case (7):a = "H";break;
			case (8):a = "I";break;
			case (9):a = "J";break;
			case (10):a = "K";break;
			case (11):a = "L";break;
			case (12):a = "M";break;
			case (13):a = "N";break;
			case (14):a = "O";break;
			case (15):a = "P";break;
			case (16):a = "Q";break;
			case (17):a = "R";break;
			}
			for (var j = 1; j <= sheetSeat_y; j++) {
				if(styleSplit!=null){
					var detailTable = "";
					for (var k = 0; k < styleSplit.length; k++) {
						if (styleSplit[k]==(a+j).trim()) {
							detailTable = "<td><div id='ck-button'><label><input type='checkbox' name=seatList value="+a+j+" onclick='seatBlock(this)' checked><span>"+a+j+"</span></label></div></td>";
							document.getElementById("blockSeat").value += a+j+" ";
							break;
						}else{
							detailTable ="<td><div id='ck-button'><label><input type='checkbox' name=seatList value="+a+j+" onclick='seatBlock(this)'><span>"+a+j+"</span></label></div></td>";
							}
						}
					RoomTable += detailTable;
				}else{
					RoomTable += "<td><div id='ck-button'><label><input type='checkbox' name=seatList value="+a+j+" onclick='seatBlock(this)'><span>"+a+j+"</span></label></div></td>";

				}
			}
			RoomTable += "</tr>"

		}
		RoomTable += "</table>"
		$("#RoomTable").html(RoomTable);
		document.getElementById("changedSeat_x").value=sheetSeat_x;
		document.getElementById("changedSeat_y").value=sheetSeat_y;
		

	}
	function roomBloackConfirm() {
		//updated form send
		if(confirm("x열 : "+document.getElementById("changedSeat_x").value+" y열 : "+document.getElementById("changedSeat_y").value+" 블럭된 좌석 : "+document.getElementById("blockSeat").value+" 입니다. 확정하시겠습니까?")){
			ConfirmBlockSeatFrm.submit();
			
		}

	}
</script>
<style>
	#TR_Table{
		max-width: 100%;
	}
</style>
<div>
<form action="RoomUpdateAction" id="ConfirmBlockSeatFrm" method="get">

<table id="TR_Table">
	<tr>
		<th colspan="3">
			상영관 좌석 관리
		</th>
	</tr>
	<tr>
		<th>도시</th>
		<td><span id=cityListCombo></span></td>
	</tr>
	<tr>
		<th>영화관</th>
		<td><span id="theaterList"><select><option>도시 선택</option></select></span></td>
	</tr>
	<tr>
		<th>상영관 선택</th>
		<td><span id="roomListCombo"><select><option>영화관 선택</option></select></span></td>
	</tr>
	<tr>
		<td colspan =2 align = center>행 : <input type=text id="Roomseat_x" name="Roomseat_x" size = 10 onkeyup="updatedSeat()">줄
		
		열 갯수 : <input type=text id="Roomseat_y" name="Roomseat_y" size = 10 onkeyup="updatedSeat()">줄</td>
	</tr>
	<tr>
		<td colspan=2 align=center><span id="RoomTable"></span></td>
	</tr>
	<tr>
	<td colspan = 2 align = center> <input type = hidden id="ROOM_NUM" name = "ROOM_NUM">
		<input type="hidden" id="blockSeat" name="blockSeat">
		<input type="hidden" id="changedSeat_x" name="changedSeat_x">
		<input type="hidden" id="changedSeat_y" name="changedSeat_y"><input
			type="button" value="좌석 확정" onclick="roomBloackConfirm()">
			
	</tr>
</table>
	</form>
</div>
