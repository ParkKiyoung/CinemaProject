<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
<table style="margin-top: 10%;">
	<tr>
		<td style="font-weight: 900; color : black;" width="20%" align="right">포스터 등록</td>
		<td style="padding-left: 20px;">
			<input type="file" id="M_Img" name="M_Img" required="required">
		</td>
	</tr>
	<tr>
		<td style="font-weight: 900; color : black;" align="right">영화 제목</td>
		<td style="padding-left: 20px;"><input type="text" id="M_Title" name="M_Title"
			required="required"></td>
	</tr>
	<tr>
		<td style="font-weight: 900; color : black;" align="right">감독</td>
		<td style="padding-left: 20px;"><input type="text" id="M_Director" name="M_Director"
			required="required"></td>
	</tr>
	<tr>
		<td style="font-weight: 900; color : black;" align="right">출연진</td>
		<td style="padding-left: 20px;"><input type="text" id="M_Actor" name="M_Actor"
			required="required" placeholder="ex) 홍길동, 이순신, 강감찬"></td>
	</tr>
	<tr>
		<td style="font-weight: 900; color : black;" align="right">개봉 예정일</td>
		<td style="padding-left: 20px;"><input type="date" id="M_Rel_Date" name="M_Rel_Date"
			required="required"></td>
	</tr>
	<tr>
		<td style="font-weight: 900; color : black;" align="right">상영 시간</td>
		<td style="padding-left: 20px;"><input type="text" id="M_Playtime" name="M_Playtime"
			required="required">분</td>
	</tr>
	<tr>
		<td style="font-weight: 900; color : black;" align="right">관람 연령</td>
		<td style="padding-left: 20px;">
			<select id="M_Age" name="M_Age" required="required">
				<option value=0>전체관람가</option>
				<option value=12>12세관람가</option>
				<option value=15>15세관람가</option>
				<option value=19>청소년관람불가</option>
			</select>
		</td>
	</tr>
	<tr>
		<td style="font-weight: 900; color : black;" align="right" valign="top">줄거리</td>
		<td style="padding-left: 20px;">
			<textarea rows="5" cols="60" id="M_Story" name="M_Story"
				required="required" placeholder="1000자 미만으로 작성해주세요."></textarea>
		</td>
	</tr>
	<tr>
		<td style="font-weight: 900; color : black;" align="right" valign="top">장르</td>
		<td style="padding-left: 20px;">
			<table style="border:1px solid #dddddd;">
				<tr>
					<td width=120><input type="checkbox" name="M_Genre"
						value="스포츠" onclick="CountChecked(this)"> 스포츠</td>
					<td width=120><input type="checkbox" name="M_Genre" value="범죄"
						onclick="CountChecked(this)"> 범죄</td>
					<td width=120><input type="checkbox" name="M_Genre"
						value="드라마" onclick="CountChecked(this)"> 드라마</td>
					<td width=120><input type="checkbox" name="M_Genre"
						value="다큐멘터리" onclick="CountChecked(this)"> 다큐멘터리</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="M_Genre" value="코미디"
						onclick="CountChecked(this)"> 코미디</td>
					<td><input type="checkbox" name="M_Genre" value="스릴러"
						onclick="CountChecked(this)"> 스릴러</td>
					<td><input type="checkbox" name="M_Genre" value="전쟁"
						onclick="CountChecked(this)"> 전쟁</td>
					<td><input type="checkbox" name="M_Genre" value="애니메이션"
						onclick="CountChecked(this)"> 애니메이션</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="M_Genre" value="가족"
						onclick="CountChecked(this)"> 가족</td>
					<td><input type="checkbox" name="M_Genre" value="판타지"
						onclick="CountChecked(this)"> 판타지</td>
					<td><input type="checkbox" name="M_Genre" value="액션"
						onclick="CountChecked(this)"> 액션</td>
					<td><input type="checkbox" name="M_Genre" value="SF"
						onclick="CountChecked(this)"> SF</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="M_Genre" value="로맨스/멜로"
						onclick="CountChecked(this)"> 로맨스/멜로</td>
					<td colspan="2"><input type="checkbox" name="M_Genre" value="로맨스/코미디"
						onclick="CountChecked(this)"> 로맨스/코미디</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
	<div align="center">
		<input type="button" id="M_Insert_Btn"
		name="M_Insert_Btn" value="등록" onclick="javascript:movieInsert();">
		<input type="reset" id="M_Cancel_Btn" name="M_Cancel_Btn"
		value="다시 작성">
	</div>
</div>