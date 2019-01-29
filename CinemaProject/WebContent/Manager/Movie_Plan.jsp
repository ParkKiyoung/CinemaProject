<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script>
	function movieUpdate(movienum){
		window.open("M_CallData.do?movienum="+movienum,"hiddenFrame","width=1000 height=500")
	}
	function movieDelete(movienum){
		if (confirm("정말 삭제하시겠습니까?") == true) { //확인
			location.href = "MovieDelete.do?movienum=" + movienum;
		} else { //취소
			return;
		}
	}
	function getPage(pageNum) {
		$("#Manager_Header").load("M_CallPlan.do", {
			"pageNum" : pageNum,
		}, function(data) {
			$("#Main_Body").html(data)
		});
	}
</script>
<style>
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
	#MP_Table{
		font-size: 12px;
		font-weight: 900;
		color: #666666;
	}
	#MP_Btn{
		border : none;
	 	text-decoration : none;
		color : white;
		background-color : #555555;
		padding : 5px 10px 5px 10px;
		float : right;
		font-size: 13px;
		font-weight: 800;
		margin-right: 5px;
	}
	#MP_Btn:hover{
		border : none;
	 	text-decoration : none;
		color : white;
		background-color : #555555;
		padding : 5px 10px 5px 10px;
		float : right;
		font-size: 13px;
		font-weight: 800;
		margin-right: 5px;
		cursor : pointer;
	}
</style>
	<table>
		<tr>
			<td><c:forEach items="${movies }" var="mov">
					<table id="MP_Table" width="100%">
						<tr>
							<td width="100px" rowspan="4" valign="top"><img
								src="../Movie/PosterIMG/${mov.img }" width=75px height=100px></td>
							<td width="300px">제목 | ${fn:substring(mov.subject, 0, 10)}<c:if
									test="${fn:length(mov.actor)>10 }">
							...
							</c:if></td>
							<td width="300px">개봉일 | ${mov.rel_date }</td>
							<td width="200px">길이 | ${mov.playtime }분</td>
							<td width="200px">관람가 | <c:if test="${mov.age_res==0 }">전체관람가</c:if>
								<c:if test="${mov.age_res==12 }">12세관람가</c:if> <c:if
									test="${mov.age_res==15 }">15세관람가</c:if> <c:if
									test="${mov.age_res==19 }">청소년불가</c:if></td>
						</tr>
						<tr>
							<td>감독 | ${mov.director }</td>
							<td>출연진 | ${fn:substring(mov.actor, 0, 15)}<c:if
									test="${fn:length(mov.actor)>15 }">
							...
							</c:if></td>
							<td>장르 | ${mov.genre }</td>
							<td>평점 | ${mov.on_rating }점/${mov.rel_rating }점</td>
						</tr>
						<tr>
							<td colspan="7">줄거리 | ${fn:substring(mov.summary, 0, 50)}<c:if
									test="${fn:length(mov.summary)>20 }">
							...
							</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="7" align="right"><input id="MP_Btn" type="button"
								value="수정" onclick="javascript:movieUpdate(${mov.num })">
								<input id="MP_Btn" type="button" value="삭제"
								onclick="javascript:movieDelete(${mov.num })"></td>
					</table>
				</c:forEach></td>
		</tr>
	</table>

	<div style="margin-top: 10px;" align="center">
		<!-- 이전 -->
		<c:if test="${startpage>blockpage }">
			<a id="MU_a" href="javascript:getPage(${startpage-blockpage});">이전</a>
		</c:if>
		<!-- 페이지출력 -->
		<c:forEach begin="${startpage }" end="${endpage }" var="i">
			<c:if test="${i==currentPage }">
				<b>${i }</b>
			</c:if>
			<c:if test="${i!=currentPage }">
				<a id="MU_a" href="javascript:getPage(${i });">${i }</a>
			</c:if>
		</c:forEach>
		<!-- 다음 -->
		<c:if test="${endpage<totpage }">
			<a id="MU_a" href="javascript:getPage(${endpage+1});">다음</a>
		</c:if>
	</div>
