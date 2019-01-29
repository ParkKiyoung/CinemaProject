<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../CSS/TotalStyle.css">
<script>
</script>
<meta charset="UTF-8">
<title>OCC</title>
<script>
	function SelectBeforeMovie(){
		var SelectBox = document.getElementById("Select_Schedule_Movie");
		var SelectedValue = SelectBox.options[SelectBox.selectedIndex].value;
		location.href="MovieBefore.do?SelectedValue="+SelectedValue;

	}
	function SelectAfterMovie(){
		var SelectBox = document.getElementById("Select_Showing_Movie");
		var SelectedValue = SelectBox.options[SelectBox.selectedIndex].value;
		location.href="MovieAfter.do?SelectedValue="+SelectedValue;
	}
	function R_Go(){
		location.href="#";
	}
</script>
<style>
	#Total_Btn_Style{
		border : none;
		background-color : #ff382e;
		color : white;
		padding : 5px 10px 5px 10px;
		text-algin : center;
		font-size : 12px;
		font-weight : 900;
	}
	#Total_Btn_Style:hover{
		border : none;
		background-color : #ff382e;
		color : white;
		padding : 5px 10px 5px 10px;
		text-algin : center;
		font-size : 12px;
		font-weight : 900;
		cursor : pointer;
	}
</style>
</head>
<body>
	<div class="LoadMain"><jsp:include page="../Main/MainIndex.jsp"></jsp:include></div>
		<div class="ViewEntire">
			<h3>네티즌 평점 & 100자평</h3>
			<hr>
			<div id = "Grade_Area" align="center">
				<table style="width : 600px; height : 100px;" class="Grade_Select_Movie">
						<c:if test="${SelectedValue!=null}">
							<c:if test="${signal==1}">
							<c:set var="flag" value="false" />
								<c:forEach items="${MovieAfterList}" var="gl">
									<c:if test="${not flag}">
										<c:set var="flag" value="true"/>
									<tr style="border:none; background-color : white;">
										<td id="GSM_Td" rowspan="5" width="150px" height="200px">
											<img src="../Movie/PosterIMG/${gl.getImg()}" width= 150px height= 200px>
										</td>
										<th colspan="3" style="border:none; background-color : white;" id="GSM_Td" align="left">
											${gl.getMovieTitle()}
										</th>
										<td style="border:none; background-color : white;" colspan="2" id="GSM_Td" align="right">
											<a href="../Main/ReservationTemp.jsp" id="Total_Btn_Style">예매하기</a>
										</td>
									</tr>
									<tr style="background-color : white;">
										<td style="border:none; background-color : white;" id="GSM_Td" width="100px" align="left" colspan="1">
											기본정보
										</td>
										<td style="border:none; background-color : white;" id="GSM_Td" align="left" colspan="4">
											${gl.getGenre()} | ${gl.getPlaytime()} | ${gl.getRel_date()}
										</td>
									</tr>
									<tr style="background-color : white;">
										<td style="border:none; background-color : white;" id="GSM_Td" align="left" colspan="1">
											감독
										</td>
										<td style="border:none; background-color : white;" id="GSM_Td" align="left" colspan="4">
											${gl.getDirector()}
										</td>
									</tr>
									<tr style="background-color : white;">
										<td style="border:none; background-color : white;" id="GSM_Td" align="left" colspan="1">
											출연
										</td>
										<td style="border:none; background-color : white;" id="GSM_Td" align="left" colspan="4">
											${gl.getActor()}
										</td>
									</tr>
									<tr style="background-color : white;">
										<td style=" border:none; background-color : white;" id="GSM_Td" align="left" colspan="1">
											개봉후 평점
										</td>
										<td style="width: 120px; border:none; background-color : white; color : #ff382e;" id="GSM_Td" align="left" colspan="1">
												<c:forEach begin="1" end="${gl.getOn_Rating()/2}">
												★
												</c:forEach>
												<c:if test="${gl.getOn_Rating()%2==1}">
												☆
												</c:if>
										</td>
										<th style="border:none; background-color : white;" id="GSM_Td" style="font: 300;" width="50px" align="left" colspan="3">
											${gl.getOn_Rating()} / 10
										</th>
									</tr>
									<tr style="background-color : white;">
									</tr>
									</c:if>
								</c:forEach>
								</c:if>
								<c:if test="${signal==2}">
								<c:set var="flag" value="false" />
									<c:forEach items="${MovieBeforeList}" var="gl">
										<c:if test="${not flag}">
											<c:set var="flag" value="true"/>
										<tr style="background-color : white;">
											<td style="border:none; background-color : white;" id="GSM_Td" rowspan="5" width="150px" height="200px">
												<img src="../Movie/PosterIMG/${gl.getImg()}" width= 150px height= 200px>
											</td>
											<th colspan="5" style="border:none; background-color : white;" id="GSM_Td" align="left">
												${gl.getMovieTitle()}
											</th>
										</tr>
										<tr style="background-color : white;">
											<td style="border:none; background-color : white;" id="GSM_Td" width="100px" align="left" colspan="1">
												기본정보
											</td>
											<td style="border:none; background-color : white;" id="GSM_Td" align="left" colspan="4">
												${gl.getGenre()} | ${gl.getPlaytime()} | ${gl.getRel_date()}
											</td>
										</tr>
										<tr style="background-color : white;">
											<td style="border:none; background-color : white;" id="GSM_Td" align="left" colspan="1">
												감독
											</td>
											<td style="border:none; background-color : white;" id="GSM_Td" align="left" colspan="4">
												${gl.getDirector()}
											</td>
										</tr>
										<tr style="background-color : white;">
											<td style="border:none; background-color : white;" id="GSM_Td" align="left" colspan="1">
												출연
											</td>
											<td style="border:none; background-color : white;" id="GSM_Td" align="left" colspan="4">
												${gl.getActor()}
											</td>
										</tr>
										<tr style="background-color : white;">
											<td style="border:none; background-color : white;" id="GSM_Td" align="left" colspan="1">
												개봉전 평점
											</td>
											<td style="width: 120px; border:none; background-color : white; color : #ff382e;" id="GSM_Td" align="left" colspan="1">
												<c:forEach begin="1" end="${gl.getRel_Rating()/2}">
												★
												</c:forEach>
												<c:if test="${gl.getRel_Rating()%2==1}">
												☆
												</c:if>
											</td>
											<th style="border:none; background-color : white;" id="GSM_Td" style="font: 300;" width="50px" align="left" colspan="3">
												${gl.getRel_Rating()} / 10
											</th>
										</tr>
										<tr style="background-color : white;">
										</tr>
										</c:if>
									</c:forEach>
								</c:if>
						</c:if>
					<tr>
						<td colspan="6">
							<table>
								<tr>
									<td align="center" width="300px">
										<label>현재 상영작 평점 & 100자평 보기</label>
											<br><br>
												<select id="Select_Showing_Movie" onchange="SelectAfterMovie()">
													<option> 현재 상영작 </option>
														<c:forEach items="${OnScreenTitle}" var="ost">
															<option value="${ost.getSubject()}">${ost.getSubject()}</option>
														</c:forEach>
												</select>
									</td>
									<td align="center" width="300px">
										<label>상영 예정작 평점 & 100자평 보기</label>
											<br><br>
												<select id="Select_Schedule_Movie" onchange="SelectBeforeMovie()">
													<option> 상영 예정작 </option>
														<c:forEach items="${RelScreenTitle}" var="rst">
															<option value="${rst.getSubject()}">${rst.getSubject()}</option>
														</c:forEach>
												</select>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
				</table>
				<br><br><br><br>
			<div>
				<table id="Grade_View_Label"style="width:100%; font-size : 12px;">
					<tr id="GVLabel_Tr">
						<td id="GVLabel_Td" align="left"><b>전체리스트</b></td>
						<td style="color:#999999;" id="GVLabel_Td" align="right">
							<b>총<label style="color : #333333;">${count}</label>개의 평점 100자평이 있습니다.</b>
						</td>
					</tr>
				</table>
			</div>
			</div>
			
			<table style="border: 4px solid #888888; 
						  background-color : #EEEEEE;
						  color : white;" 
						  width="100%">
				<tr>
					<td>
			 			<div class="Grade_View_Menu">
							<ul class="GVM_ul">
								<c:if test="${SelectedValue != null}">
									<li style="margin-right: 10px;" class="GVM_li">
										<a class="S_B_Grade" href="MovieBefore.do?SelectedValue=${SelectedValue}"> 개봉전 평점▼ </a>
									</li>
								</c:if>
								<c:if test="${SelectedValue == null}">
									<li style="margin-right: 10px;" class="GVM_li"><a class="B_Grade" href="MovieBefore.do"> 개봉전 평점▼ </a></li>
								</c:if>
								<c:if test="${SelectedValue != null}">
									<li style="margin-right: 10px;" class="GVM_li">
										<a class="S_A_Grade" href="MovieAfter.do?SelectedValue=${SelectedValue}"> 개봉후 평점▼ </a>
									</li>
								</c:if>
								<c:if test="${SelectedValue == null}">
									<li style="margin-right: 10px;" class="GVM_li"><a class="A_Grade" href="MovieAfter.do"> 개봉후 평점▼ </a></li>
								</c:if>
							</ul>
						</div>
					</td>
				</tr>
			</table>
			<hr>
			 <div class="Grade_View_List" align="center">
				<jsp:include page="GradeList.jsp"/>
			</div>
		</div>
		<div id="Footer">
			<jsp:include page="../Main/MainFooter.jsp"/>
		</div>
</body>
</html>