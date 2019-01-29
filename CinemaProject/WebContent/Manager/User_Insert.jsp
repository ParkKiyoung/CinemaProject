<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
<table style="margin-top: 10%;">
	<tr>
		<th width="30%" align="right">
			아이디
		</th>
		<td style="padding-left: 20px;">
			<input type="text" id="U_Id" name="U_Id" readonly>
			<input type="button" id="U_Id_Confirm" name="U_Id_Confirm" value="중복확인" onclick="U_Id_Check()">
		</td>
	</tr>
	<tr>
		<th align="right">
			비밀번호
		</th>
		<td style="padding-left: 20px;">
			<input type="password" id="U_Pwd" name="U_Pwd">
		</td>
	</tr>
	<tr>
		<th align="right">
			비밀번호 확인
		</th>
		<td style="padding-left: 20px;">
			<input type="password" id="U_Pwd_Confirm" name="U_Pwd_Confirm">
		</td>
	</tr>
	<tr>
		<th align="right">
			이름
		</th>
		<td style="padding-left: 20px;">
			<input type="text" id="U_Name" name="U_Name">
		</td>
	</tr>
	<tr>
		<th align="right">
			주민번호
		</th>
		<td style="padding-left: 20px;">
			<input type="text" id="U_Pnum" name="U_Pnum">
		</td>
	</tr>
	<tr>
		<th align="right">
			전화번호
		</th>
		<td style="padding-left: 20px;">
			<input type="text" id="U_Phone" name="U_Phone">
		</td>
	</tr>
	<tr>
		<th align="right">
			성별
		</th>
		<td style="padding-left: 20px;">
			<input type="radio" id="U_Gender" name="U_Gender" value="Male" checked>남자
			<input type="radio" id="U_Gender" name="U_Gender" value="Female">여자
		</td>
	</tr>
	<tr>
		<th align="right">
			이메일
		</th>
		<td style="padding-left: 20px;">
			<input type="text" id="U_Email" name="U_Email">@
			<select id="E_Addr" name="E_Addr">
				<option>선택</option>
				<option value="@naver.com">naver.com</option>
				<option value="@daum.com">daum.com</option>
				<option value="@gmail.com">gmail.com</option>
			</select>
			<input type="hidden" id="W_Email" name="W_Email">
		</td>
	</tr>
	<tr>
		<th align="right">
			우편번호
		</th>
		<td style="padding-left: 20px;">
			<input type="text" id="U_Zip" name="U_Zip">
			<input type=button onclick="searchAddr()" value="검색">
		</td>
	</tr>
	<tr>
		<th align="right">
			주소
		</th>
		<td style="padding-left: 20px;">
			<input type="text" id="U_Addr1" name="U_Addr1" size="50px">
		</td>
	</tr>
	<tr>
		<th align="right">
			상세주소
		</th>
		<td style="padding-left: 20px;">
			<input type="text" id="U_Addr2" name="U_Addr2" size="50px">
		</td>
	</tr>
	<tr>
		<th align="right">
			관심 장르<br> 
			(최대 3개 선택 가능)
		</th>
		<td style="padding-left: 15px;">
			<table style="border:1px solid #dddddd;">
				<tr>
					<td>
						<input type="checkbox" id="U_Cb" name="U_Cb" value="스포츠" onclick="CountChecked(this)">스포츠
						<input type="checkbox" id="U_Cb" name="U_Cb" value="범죄" onclick="CountChecked(this)">범죄
						<input type="checkbox" id="U_Cb" name="U_Cb" value="드라마" onclick="CountChecked(this)">드라마
						<input type="checkbox" id="U_Cb" name="U_Cb" value="코미디" onclick="CountChecked(this)">코미디
						<input type="checkbox" id="U_Cb" name="U_Cb" value="로맨스/멜로" onclick="CountChecked(this)">로맨스/멜로
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" id="U_Cb" name="U_Cb" value="스릴러" onclick="CountChecked(this)">스릴러
						<input type="checkbox" id="U_Cb" name="U_Cb" value="로맨스/코미디" onclick="CountChecked(this)">로맨스/코미디
						<input type="checkbox" id="U_Cb" name="U_Cb" value="전쟁" onclick="CountChecked(this)">전쟁
						<input type="checkbox" id="U_Cb" name="U_Cb" value="가족" onclick="CountChecked(this)">가족
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" id="U_Cb" name="U_Cb" value="판타지" onclick="CountChecked(this)">판타지
						<input type="checkbox" id="U_Cb" name="U_Cb" value="액션" onclick="CountChecked(this)">액션
						<input type="checkbox" id="U_Cb" name="U_Cb" value="SF" onclick="CountChecked(this)">SF
						<input type="checkbox" id="U_Cb" name="U_Cb" value="애니메이션" onclick="CountChecked(this)">애니메이션
						<input type="checkbox" id="U_Cb" name="U_Cb" value="다큐멘터리" onclick="CountChecked(this)">다큐멘터리
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
	<br><br>
	<div align="center">
		<input type="button" id="U_Insert_Btn" name="U_Insert_Btn" value="회원 등록" onclick="signup()">
		<input type="reset" id="U_Reser_Btn" name="U_Reser_Btn" value="다시 작성">
	</div>