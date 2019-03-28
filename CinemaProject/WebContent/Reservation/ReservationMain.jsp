<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예매</title>
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
								.ajax({//도시 불러옴
									type : "get",
									url : "../Main/PlaceCallback",
									success : function(data) {
										data = $.parseJSON(data);
										var cityList = "";
										cityList += "<select id ='city' name = 'city' onclick='cityChoice()'>"
										for (var i = 0; i < data.length; i++) {
											cityList += "<option value='"+data[i].city+"'>"
													+ data[i].city
													+ "</option>";
										}
										cityList += "</select>"
										$("#cityList").html(cityList);
									},
									error : function(e) {
										alert("error : " + 오류발생);
									}
								})
					})
	function cityChoice() {
		var city = $("#city").val();
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
						theaterList += "<select id ='theatername' name = 'theatername'>"
						for (var i = 0; i < data.length; i++) {
							theaterList += "<option value='"+data[i].theaternum+"'>"
									+ data[i].theatername + "</option>";
						}
						theaterList += "</select>"
						$("#theaterList").html(theaterList);
					},
					error : function(e) {
						alert("error : " + 오류발생);
					}
				})
	}

	function MovieCall() {
		var theater = document.getElementById("theatername").value;
		var resDate = document.getElementById("resCalendar").value;
		

		$.ajax({//조건 영화 조회
			type : "get",
			url : "../Main/ResSearchMovieList",
			data : {
				"theater" : theater,
				"resDate":resDate
			},
			success : function(data) {

				data = $.parseJSON(data)
				if (data == "" || data == null) {
					alert("조건에 맞는 영화가 없습니다.")
					return false;
				}
				var MovieSearchList = "";
				var Title = "";
				Title = data[0].subject;
				MovieSearchList += "원하는 시간대의 영화를 선택해주세요"
				MovieSearchList += "<table>"
				MovieSearchList += "<tr>"
				MovieSearchList += "<td colspan=2><b>제목 : " + Title + "</b></td></tr>"
				MovieSearchList += "<td>상영관명 : " + data[0].room_name + "</td>"
				MovieSearchList += "<td>상영시간</td>"
				for (var i = 0; i < data.length; i++) {
					Title = data[i].subject;
					MovieSearchList += "<td><a href=javascript:RoomCall("+ data[i].movie_num + "," + data[i].room_num + ","	+ data[i].theater_time_num + ")>"+data[i].ontime+ "</a></td>"
					if (i + 1 < data.length) {
						if (Title != data[i + 1].subject) {
							MovieSearchList += "</tr>"
							Title = data[i + 1].subject;
							MovieSearchList += "<tr>"
							MovieSearchList += "<td><b>제목 : " + Title + "</b></td></tr>"
							MovieSearchList += "<td>상영관명 : "
									+ data[i + 1].room_name + "</td>"
							MovieSearchList += "<td>상영시간</td>"
						}
					}
				}
				MovieSearchList += "</table>";
				$("#MovieSearchResult").html(MovieSearchList);

			}
		})
	}
	var styleSplit ="";
	function RoomCall(movie_num, room_num, theater_time_num) {//선택한시간대의 상영관 조회
		document.getElementById("seatNo").value ="";
		styleSplit =null;
		$
				.ajax({
					type : "get",
					url : "../Main/ResRoomInfo",
					data : {
						"movie_num":movie_num,
						"room_num" : room_num,
						"theater_time_num" : theater_time_num
					},
					success : function(data) {
						resResultFrm.movienum.value=movie_num;
						resResultFrm.ttnum.value=theater_time_num;
						resResultFrm.roomnum.value=room_num
						
						data = $.parseJSON(data);
						
						if(data.style!=null){
							var style = data.style//구조 옵션 값만 불러옴
							styleSplit = style.split(',');//스타일 쪼개서 배열화
						}
						var ResSeat = data.ResSeat//예약된 자리
						var ResSeatSplit = ResSeat.split(',')//예약된 자리 쪼개서 배열화

						var seat_x = data.seat_x
						var seat_y = data.seat_y
						var RoomTable = "";

						RoomTable += "<table id='TR_Shape'>"
						RoomTable += "<tr><th id='TR_Th_Shape' colspan="+seat_x+">SCREEN</th></tr>"
						
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
			                              detailTable = "<td><input type='checkbox' name=seatList value="+a+j+" onclick='seatReservation(this)' style='visibility: hidden'></td>";
			                              break;
			                           } else {
			                              for(var m=0 ; m < ResSeatSplit.length ; m++){
			                                 if(ResSeatSplit[m]==(a+j).trim()){
			                                    detailTable = "<td><div id='ck-button'><label><input type='checkbox' name=seatList value="+a+j+" onclick='seatReservation(this)' style='visibility: visible' disabled checked><span>"+a+j+"</span></label></div></td>";
			                                    break;
			                                 }else{
			                                    detailTable = "<td><div id='ck-button'><label><input type='checkbox' name=seatList value="+a+j+" onclick='seatReservation(this)' style='visibility: visible'><span>"+a+j+"</span></label></div></td>";
			                                 }
			                              }   
			                           }
			                           
			                        }
			                        
			                     }else{
			                        for(var m=0 ; m < ResSeatSplit.length ; m++){
			                           if(ResSeatSplit[m]==(a+j).trim()){
			                              detailTable = "<td><div id='ck-button'><label><input type='checkbox' name=seatList value="+a+j+" onclick='seatReservation(this)' style='visibility: visible' disabled checked><span>"+a+j+"</span></label></div></td>";
			                              break;
			                           }else{
			                              detailTable = "<td><div id='ck-button'><label><input type='checkbox' name=seatList value="+a+j+" onclick='seatReservation(this)' style='visibility: visible'><span>"+a+j+"</span></label></div></td>";
			                           }
			                        }
			                     }
								RoomTable += detailTable;
							}
						
							RoomTable += "</tr>"
						}
						RoomTable += "</table>"
						$(".RoomTableInfo").html(RoomTable);
						$("#expPeolpleCountSelect")
								.css('visibility', 'visible');

					}
				})
	}
	var maxChecked = 0;
	var totalChecked = 0;
	function adultCount() {
		maxChecked = parseInt(document.getElementById("adult").value)
				+ parseInt(document.getElementById("youth").value);
		$("#selectedPeopleCount").html("총인원수는 : " + maxChecked);
		
		resResultFrm.adultCount.value=parseInt(document.getElementById("adult").value)
	}
	function youthCount() {
		maxChecked = parseInt(document.getElementById("adult").value)
				+ parseInt(document.getElementById("youth").value);
		$("#selectedPeopleCount").html("총인원수는 : " + maxChecked)
		
		resResultFrm.youthCount.value=parseInt(document.getElementById("youth").value)
	}
	 var arr = new Array();
	  var idx = 0;
	function seatReservation(field) {//인원수만큼 선택되도록
		resResultFrm.seatNo.value="";
		
		if(maxChecked==0){
			alert("인원을 먼저 선택해주세요");
			field.checked = false;
			return false;
		};//인원 선택 유효성
	 
		if(field.checked){
			totalChecked += 1;
			arr[idx]=field.value;
			idx++;
		
		}else if(field.checked ==false){
			totalChecked -= 1
			for(var i=0;i<idx;i++){
				if(arr[i]==field.value){
					arr[i]="";
					idx--;
				}
			}
		}
		if (totalChecked > maxChecked) {
			alert("선택한 인원수보다 많이 선택하셨습니다.");
			field.checked = false;
			totalChecked -= 1;
			
			for(var i=0;i<idx;i++){
				if(arr[i]==field.value){
					arr[i]="";
				}
			}
		
		}
		
		//출력
		for(i=0;i<idx;i++){
			document.getElementById("seatNo").value +=arr[i]+" ";
		}
	}
	
	function resSubmit(){
		if(resResultFrm.seatNo.value==""){
			alert("좌석을 선택해주세요");
		}
		if(totalChecked<maxChecked){
			alert("인원에 맞게 좌석을 선택해주세요");
			return false;
		}
		resResultFrm.submit();
		
	}
</script>
<style>
	#RMT_Btn{
	  	border: none;
	  	background-color: #111111;
	  	color: white;
	  	font-size: 13.5px;
	  	font-weight : 800;
	  	padding: 5px 10px 5px 10px;
	  }
	  #RMT_Btn:hover{
	  	border: none;
	  	background-color: #111111;
	  	color: white;
	  	font-size: 13.5px;
	  	font-weight : 800;
	  	padding: 5px 10px 5px 10px;
	  	cursor: pointer;
	  }
	  #RMT_Btn2{
	  	border: none;
	  	background-color: #111111;
	  	color: white;
	  	font-size: 12px;
	  	font-weight : 800;
	  	padding: 1.5px 6px 1.5px 6px;
	  }
	  #RMT_Btn2:hover{
	  	border: none;
	  	background-color: #111111;
	  	color: white;
	  	font-size: 12px;
	  	font-weight : 800;
	  	padding: 1.5px 6px 1.5px 6px;
	  	cursor : pointer;
	  }
	  #TR_Shape{
	  	border-collapse: separate;
	  	border: 1px solid #000000;
	  	padding: 10px 10px 10px 10px;
	  	max-width: 100%;
	  }
</style>
</head>
<body>
	<div style="border: 1px solid #cccccc;" align=center>
		<h3>영화 예매</h3>
		<h5 align="center"><label>영화 조회</label></h5>
		<table>
			<tr>
				<th style="font-size: 14px; font-weight: 700;">영화관</th>
				<td>
					시/도 
					<span id="cityList"></span> 
					<span id="theaterList">
						<select>
							<option>시/도 선택</option>
						</select>
					</span>
				</td>

			</tr>
			<tr>
				<th style="font-size: 14px; font-weight: 700;">날짜</th>
				<td align="left">
					<input type = "date" id = resCalendar>
				</td>
			</tr>
			<tr>
				<td colspan=4 align=center>
				<input id="RMT_Btn2" type="button" value="조회" onclick="MovieCall()">
				</td>
			</tr>
		</table>
		<br>
		<div id="MovieSearchResult"></div>
		<div id='expPeolpleCountSelect' style="visibility: hidden">
			<br>
			<table>
				<tr>
					<th style="font-size: 14px; font-weight: 700;">인원선택</th>
					<td>성인 <select id="adult" name="adult" onclick="adultCount()">
							<option value=0>0명</option>
							<option value=1>1명</option>
							<option value=2>2명</option>
							<option value=3>3명</option>
							<option value=4>4명</option>
							<option value=5>5명</option>
					</select>
					</td>
					<td>청소년 <select id="youth" name="youth" onclick="youthCount()">
							<option value=0>0명</option>
							<option value=1>1명</option>
							<option value=2>2명</option>
							<option value=3>3명</option>
							<option value=4>4명</option>
							<option value=5>5명</option>
					</select>
					</td>
				</tr>
						<div id="selectedPeopleCount"></div>
			</table>
		</div>
		<br>
		<div width="100%" class="RoomTableInfo"></div>
		<br><br>
		<input id="RMT_Btn" type="button" onclick="resSubmit()" value="예약하기">
		<input id="RMT_Btn" type="button" onclick="location.href='../Main/ReservationTemp.jsp'" value="다시선택">
		<form action="../Main/ResConfirm" method="post" id="resResultFrm">
			<div id="ReservationResult" style="visibility: hidden">
				<table>
					<tr>
						<td style="font-size: 14px; font-weight: 700;">회원번호</td>
						<td><input type="text" id="membernum" name="membernum" value="${mb.num}"></td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: 700;">좌석번호</td>
						<td><input type="text" id="seatNo" name="seatNo" ></td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: 700;">성인수</td>
						<td><input type="text" id="adultCount" name="adultCount" value="0"></td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: 700;">청소년수</td>
						<td><input type="text" id="youthCount" name="youthCount" value="0"></td>
					</tr>
					<tr>
						<td>ttnum</td>
						<td><input type="text" id="ttnum" name="ttnum" ></td>
					</tr>
					<tr>
						<td>movienum</td>
						<td><input type="text" id="movienum" name="movienum" ></td>
					</tr>
					<tr>
						<td>roomnum</td>
						<td><input type="text" id="roomnum" name="roomnum" ></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>