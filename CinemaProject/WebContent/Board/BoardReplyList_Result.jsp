<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCC</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function replyUpdateForm(num,content){
	window.open("BoardReplyUpdateForm.jsp?num="+num+"&content="+content+"","","width=500,height=500");
}
function replyDeleteBtn(num,boardnum){
	if(confirm("정말삭제하시겠습니까?")){
		location.href = "replyDelete?num="+num+"&boardnum="+boardnum;
	}
}
function pagingBtn(pageNum) {
		location.href = "boardReplyList?pageNum=" + pageNum+"&boardnum="+$("#boardnum").val();
}
</script>
<style>
	#BMT_a{
	color : black;
	font-size: 14px;
	font-weight: 700;
	}
	#BMT_a:hover{
		color : #ff382e;
		font-size: 14px;
		font-weight: 700;
		text-decoration: underline;
	}
</style>
</head>
<body>
<h4>답변 ${totcount }</h4>
	<form>
	<input type = "hidden" id = "boardnum" name = "boardnum" value = "${boardnum }">
			<c:forEach items="${arr }" var="i">
			<table style="width: 1000px;border-bottom: 1px solid #D3D3D3;">
 				<tr>
				<td colspan="2"  style="font-size: 18px;text-align: center;">${i.content }</td>
					<td rowspan="2" align="center"   style="font-size: 13px;">
					<c:if test="${i.membernum == mb.num }">
					<a href = "javascript:replyUpdateForm(${i.num},'${i.content }')">수정</a> | <a href = "javascript:replyDeleteBtn(${i.num},${boardnum })">삭제</a>
					</c:if>
					</td>
				</tr>
				<tr style="font-size: 13px;color: gray;">
					<td align="right">작성자 <strong>${i.userid }</strong></td>
					<td align="right">최초작성일 <strong>${i.created }</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${i.updated != '' }">
					최종수정일 <strong>${i.updated }</strong>
					</c:if>
					</td>
				</tr>
				</table>
				<br>
			</c:forEach>
			<table>
			<tr>
					<td class="Board_Main_Td" colspan="6" align="center">
					<c:if test="${startpage > blockpage }">
							<a id="BMT_a" href="javascript:getData(${startpage-blockpage })">이전</a>
						</c:if> <c:forEach begin="${startpage }" end="${endpage }" var="i">
							<c:if test="${nowPage == i }">
							${i }
							</c:if>
							<c:if test="${nowPage != i }">
								<a id="BMT_a" href="javascript:getData(${i })">${i }</a>
							</c:if>
						</c:forEach> <c:if test="${endpage < totpage }">
							<a id="BMT_a" href="javascript:getData(${endpage+1 })">다음</a>
						</c:if>
						</td>
				</tr>
			</table>
	</form>
</body>
</html>