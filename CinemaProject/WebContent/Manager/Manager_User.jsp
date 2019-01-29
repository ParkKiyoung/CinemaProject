<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pageUser = request.getParameter("source5");
	if(pageUser==null){
		pageUser="User_Graph";
	}
%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	function U_Id_Check(){
		window.open("../Manager/User_Id_Check.jsp", "", "width=600, height=300");
	}
	function signup(){
		if (frm.U_Id.value == ""){
			alert("아이디를 입력하세요");
			return false;
		}
		if (frm.U_Pwd.value != frm.U_Pwd_Confirm.value || 
				frm.U_Pwd.value == ""){
			alert("비밀번호가 일치하지 않거나, 미입력 상태입니다.");
			return false;
		}
		if (frm.U_Name.value == ""){
			alert("이름을 입력하세요");
			return false;
		}
		if (frm.U_Pnum.value == ""){
			alert("주민번호를 입력하세요");
			return false;
		}
		if (frm.U_Phone.value == ""){
			alert("전화번호를 입력하세요");
			return false;
		}
		if (frm.U_Email.value == ""){
			alert("이메일을 입력하세요.")
			return false;
		}
		if (frm.E_Addr.value == "" || 
				frm.E_Addr.value == "선택"){
			alert("이메일 주소를 선택하세요.")
			return false;
		}
		if (frm.U_Zip.value == ""){
			alert("우편번호를 입력하세요.")
			return false;
		}
		if (frm.U_Addr1.value == "" ||
				frm.U_Addr2.value == ""){
			alert("주소를 입력하세요.")
			return false;
		}
		frm.action="U_InsertAction.do";
		frm.submit();
	}
	function searchAddr(){
		window.open("../Manager/User_Zip_Check.jsp", "", "width=700 height=600");
	}
	var maxChecked = 3;
	var totalChecked = 0;
	function CountChecked(field){
		if(field.checked){
			totalChecked += 1;
		} else{
			totalChecked -= 1;
		}
		if(totalChecked > maxChecked){
			alert("최대 3개 까지만 선택 가능합니다.");
			field.checked = false;
			totalChecked -= 1;
		}
	}
</script>
<style>
	#Manager_User{
		width : 100%;
		border-bottom : 1px solid #cccccc;
		border-bottom-width : thin;
		margin-top: 5px;
	}
	#Mu_a{
		text-decoration : none;
		color : black;
	}
	#Mu_a:hover{
		text-decoration : none;
		color : #ff382e;
	}
</style>
<form id="frm" method="post">
	<div>
		<table id="Manager_User">
			<tr>
				<th>
					<a id="Mu_a" href="U_GraphAction.do?Graph=U_Graph_Gender">회원 현황</a>
				</th>
				<th>
					<a id="Mu_a" href="Manager_Main.jsp?page=Manager_User&source5=User_Insert">회원 등록</a>
				</th>
				<th>
					<a id="Mu_a" href="U_Update_List.do">회원 관리</a>
				</th>
			</tr>
		</table>
		<jsp:include page='<%=pageUser+".jsp"%>'/>
	</div>
</form>