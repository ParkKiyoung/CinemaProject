<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
<style>
   .User_Update_List{
      border-collapse : collapse;
      text-align : center;
      margin : auto;
      width : 100%;
   }
   .User_Update_List #UUL_Th{
      border-collapse: collapse;
      border-bottom : 2px solid #888888;
   }
   .User_Update_List #UUL_Td{
      border-collapse: collapse;
      border-bottom : 1px solid #cccccc;
   }
   		#MU_a{
	color : black;
	font-size: 14px;
	font-weight: 700;
	text-decoration: none;
	}
	#MU_a:hover{
		color : #ff382e;
		font-size: 14px;
		font-weight: 700;
		text-decoration: underline;
		
	}
</style>
<script>
   function U_Update(U_Num){
      window.open("U_Update_window.do?U_Num="+U_Num, "", "width=600 height=500");
   }
   function U_Delete(U_Num,U_Id){
      if(confirm("정말 삭제하시겠습니까?")==true){
         frm.action="U_Delete.do?U_Num="+U_Num+"&U_Id="+U_Id;
         frm.submit();
      }else{
         location.reload();
      }
   }
   function U_PageView(pageNum){
      location.href="U_Update_List.do?pageNum="+pageNum;
   }
   function U_Search_PageView(pageNum, U_Id){
      location.href="U_Search.do?pageNum="+pageNum+"&U_Id="+U_Id;
   }
   function U_Search_Do(){
      location.href="U_Search.do?U_Id="+frm.U_Search.value;
   }
</script>
<div>
   <form id="frm" method="post">
   <c:if test="${U_Id == null}">
      <label align="right"><h2>총 회원수 : ${count}</label>
   </c:if>
   <c:if test="${U_Id != null}">
      <label align="right"><h2>검색 회원수 : ${count}</label>
   </c:if>
      <table class="User_Update_List">
         <tr>
            <th id="UUL_Th">
               No
            </th>
            <th id="UUL_Th">
               아이디
            </th>
            <th id="UUL_Th">
               비밀번호
            </th>
            <th id="UUL_Th">
               이름
            </th>
            <th id="UUL_Th">
               주민번호
            </th>
            <th id="UUL_Th">
               전화번호
            </th>
            <th id="UUL_Th">
               성별
            </th>
            <th id="UUL_Th">
               이메일
            </th>

         </tr>
         <c:forEach items="${arr}" var="U">
            <tr>
               <th id="UUL_Td">
                  <input style="border:none; width:35px;" type="text" id="U_Num" name="U_Num" value="${U.getNum()}" readonly>
               </th>
               <td id="UUL_Td">
                  <a id="MU_a" style="font-size: 15px;" href="U_Id_Info.do?U_NUM=${U.getNum()}">${U.getUserid()}<input style="border:none; width:100px;" type="hidden" id="U_Id" name="U_Id" value="${U.getUserid()}" readonly></a>
               </td>
               <td id="UUL_Td">
                  <input style="border:none; width:100px;" type="text" id="U_Pwd" name="U_Pwd" value="${U.getPassword()}" readonly>
               </td>
               <td id="UUL_Td">
                  <input style="border:none; width:80px;" type="text" id="U_Name" name="U_Name" value="${U.getName()}" readonly>
               </td>
               <td id="UUL_Td">
                  <input style="border:none; width:120px;" type="text" id="U_Pnum" name="U_Pnum" value="${U.getPnum()}" readonly>
               </td>
               <td id="UUL_Td">
                  <input style="border:none; width:120px;" type="text" id="U_Phone" name="U_Phone" value="${U.getPhone()}" readonly>
               </td>
               <td id="UUL_Td">
                  <input style="border:none; width:50px;" type="text" id="U_Gender" name="U_Gender" value="${U.getGender()}" readonly>
               </td>
               <td id="UUL_Td">
                  <input style="border:none; width:180px;" type="text" id="U_Email" name="U_Email" value="${U.getEmail()}" readonly>
               </td>

               <td id="UUL_Td">
                  <input style="width:50px;" type="button" id="U_Update_Btn" name="U_Update_Btn" value="수정" 
                     onclick="javascript:U_Update(${U.getNum()})">
                  <input style="width:50px;" type="button" id="U_Delete_Btn" name="U_Delete_Btn" value="삭제"
                     onclick="javascript:U_Delete(${U.getNum()},'${U.getUserid()}')">
               </td>
            </tr>
         </c:forEach>
      </table>
      <div align="center">
         <table>

            <tr>
               <td>
                  <input type="text" id="U_Search" name="U_Search" size="50px">
               </td>
               <td>
                  <input type="button" id="U_Search_Btn" name="U_Search_Btn" value="아이디 검색" onclick="U_Search_Do()">
               </td>
            </tr>
         </table>
      </div>
   <c:if test="${U_Id == null}">
         <div style="font-size: 15px;" align="center">
         <!-- 이전 -->
         <c:if test="${startPage > blockPage}">
            <a id="MU_a" href="javascript:U_PageView(${startPage - blockPage})">이전</a>
         </c:if>
         <!-- 페이지 출력 -->
         <c:forEach begin="${startPage}" end="${endPage}" var="i">
            <c:if test="${i == currentPage}">
               ${i}
            </c:if>
            <c:if test="${i != currentPage}">
               <a id="MU_a" href="javascript:U_PageView(${i})">${i}</a>
            </c:if>
         </c:forEach>
         <!-- 다음 -->
         <c:if test="${endPage < totPage}">
            <a id="MU_a" href="javascript:U_PageView(${endPage+1})">다음</a>
         </c:if>
      </div>
   </c:if>
   <c:if test="${U_Id != null}">
         <div style="font-size: 15px;" align="center">
         <!-- 이전 -->
         <c:if test="${startPage > blockPage}">
            <a id="MU_a" href="javascript:U_Search_PageView(${startPage - blockPage},'${U_Id}')">이전</a>
         </c:if>
         <!-- 페이지 출력 -->
         <c:forEach begin="${startPage}" end="${endPage}" var="i">
            <c:if test="${i == currentPage}">
               ${i}
            </c:if>
            <c:if test="${i != currentPage}">
               <a id="MU_a" href="javascript:U_Search_PageView(${i},'${U_Id}')">${i}</a>
            </c:if>
         </c:forEach>
         <!-- 다음 -->
         <c:if test="${endPage < totPage}">
            <a id="MU_a" href="javascript:U_Search_PageView(${endPage+1},'${U_Id}')">다음</a>
         </c:if>
      </div>
   </c:if>
   </form>
</div>