<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<form id="frm">
		<div id="U_Id_Info_Area" align="center">
			<h3>아이디 : ${memBean.userid}</h3>
			<table style="font-size: 12px; width: 500px;" >
				<tr>
					<th>이름</th>
					<td>${memBean.name }</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>${memBean.zipcode}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${memBean.addr1}</td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td>${memBean.addr2}</td>
				</tr>

			</table>
			<table>
				<tr>
					<th colspan=3>관심 장르</th>
				</tr>
				<tr>
					<th>장르1</th>
					<th>장르2</th>
					<th>장르3</th>
				</tr>
				<tr>
					<th>${memBean.inter1}</th>
					<th>${memBean.inter2}</th>
					<th>${memBean.inter3}</th>
				</tr>
			</table>
			<table>
				<tr>
					<th>평점 작성수</th>
					<th>게시판 작성수</th>
					<th>평점 평균</th>
					<th>예매 이용수</th>
				</tr>
				<tr>
					<th>${Gcnt}</th>
					<th>${Bcnt}</th>
					<th>${Avg_Score}</th>
					<th>${Res_Count }</th>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>


