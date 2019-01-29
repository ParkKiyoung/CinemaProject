<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<table class="Grade_View_List_Table">
	<tr id="GVL_Tr">
		<th id="GVL_Th">글번호</th>
		<th id="GVL_Th">평점</th>
		<th id="GVL_Th"></th>
		<th id="GVL_Th">100자평</th>
		<th id="GVL_Th">글쓴이·날짜</th>
	</tr>
	<!-- 개봉 후 전체 평점 리스트 -->
	<c:if test="${SelectedValue == null}">
		<c:forEach items="${MovieAfterList}" var="gl">
			<tr id="GVL_Tr">
				<td style="text-align: center; font-size: 12px; color: #888888;"
					width="10%" id="GVL_Td">${gl.getG_Num()}</td>
				<td style="font-size: 15px; color: #ff382e;" id="GVL_Td" width="20%">
					<c:forEach begin="1" end="${gl.getScore()/2}">
						★
					</c:forEach>
					<c:if test="${gl.getScore()%2==1}">
						☆
					</c:if>
				</td>
				<th id="GVL_Td" width="5%"><c:out value="${gl.getScore()}"/></th>
				<td id="GVL_Td" width="50%">
					<a class="Grade_Movie_Title" href="MovieAfter.do?SelectedValue=${gl.getMovieTitle()}">
						${gl.getMovieTitle()}
					</a>
					<br> ${gl.getReply()}</td>
				<td style="font-size: 12px; color: #888888;" id="GVL_Td" width="15%">
					${gl.getUserId()}<br> ${gl.getGrade_Date()}
				</td>
			</tr>
		</c:forEach>
	</c:if>

	<!-- 선택된 개봉후 평점 리스트 -->
	<c:if test="${SelectedValue != null}">
		<c:forEach items="${MovieAfterList}" var="gl">
			<tr id="GVL_Tr">
				<td style="text-align: center; font-size: 12px; color: #888888;"
					width="10%" id="GVL_Td">${gl.getG_Num()}</td>
				<td style="font-size: 15px; color: #ff382e;" id="GVL_Td" width="20%">
					<c:forEach begin="1" end="${gl.getScore()/2}">
						★
					</c:forEach>
					<c:if test="${gl.getScore()%2==1}">
						☆
					</c:if>
				</td>
				<th id="GVL_Td" width="5%"><c:out value="${gl.getScore()}"/></th>
				<td id="GVL_Td" width="50%"><a class="Grade_Movie_Title"
					href="MovieAfter.do?SelectedValue=${gl.getMovieTitle()}">${gl.getMovieTitle()}</a><br> ${gl.getReply()}</td>
				<td style="font-size: 12px; color: #888888;" id="GVL_Td" width="15%">
					${gl.getUserId()}<br> ${gl.getGrade_Date()}
				</td>
			</tr>
		</c:forEach>
	</c:if>

	<!-- 개봉 전 전체 평점 리스트 -->
	<c:if test="${SelectedValue == null}">
		<c:forEach items="${MovieBeforeList}" var="gl">
			<tr id="GVL_Tr">
				<td style="text-align: center; font-size: 12px; color: #888888;"
					width="10%" id="GVL_Td">${gl.getG_Num()}</td>
				<td style="font-size: 15px; color: #ff382e;" id="GVL_Td" width="20%">
					<c:forEach begin="1" end="${gl.getScore()/2}">
						★
					</c:forEach>
					<c:if test="${gl.getScore()%2==1}">
						☆
					</c:if>
				</td>
				<th id="GVL_Td" width="5%"><c:out value="${gl.getScore()}"/></th>
				<td id="GVL_Td" width="50%"><a class="Grade_Movie_Title"
					href="MovieBefore.do?SelectedValue=${gl.getMovieTitle()}">${gl.getMovieTitle()}</a><br> ${gl.getReply()}</td>
				<td style="font-size: 12px; color: #888888;" id="GVL_Td" width="15%">
					${gl.getUserId()}<br> ${gl.getGrade_Date()}
				</td>
			</tr>
		</c:forEach>
	</c:if>

	<!-- 선택된 영화 개봉 전 평점 리스트 -->
	<c:if test="${SelectedValue != null}">
		<c:forEach items="${MovieBeforeList}" var="gl">
			<tr id="GVL_Tr">
				<td style="text-align: center; font-size: 12px; color: #888888;"
					width="10%" id="GVL_Td">${gl.getG_Num()}</td>
				<td style="font-size: 15px; color: #ff382e;" id="GVL_Td" width="20%">
					<c:forEach begin="1" end="${gl.getScore()/2}">
						★
					</c:forEach>
					<c:if test="${gl.getScore()%2==1}">
						☆
					</c:if>
				</td>
				<th id="GVL_Td" width="5%"><c:out value="${gl.getScore()}"/></th>
				<td id="GVL_Td" width="50%"><a class="Grade_Movie_Title"
					href="MovieBefore.do?SelectedValue=${gl.getMovieTitle()}">${gl.getMovieTitle()}</a><br> ${gl.getReply()}</td>
				<td style="font-size: 12px; color: #888888;" id="GVL_Td" width="15%">
					${gl.getUserId()}<br> ${gl.getGrade_Date()}
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
<c:if test="${SelectedValue==null}">
<script>
	function After_PageView(pageNum){
		location.href="MovieAfter.do?pageNum="+pageNum;
	}
	function Before_PageView(pageNum){
		location.href="MovieBefore.do?pageNum="+pageNum;
	}
</script>
</c:if>
<c:if test="${SelectedValue!=null}">
<script>
	function After_PageView(pageNum,SelectedValue){
		location.href="MovieAfter.do?pageNum="+pageNum+"&SelectedValue="+SelectedValue;
	}
	function Before_PageView(pageNum,SelectedValue){
		location.href="MovieBefore.do?pageNum="+pageNum+"&SelectedValue="+SelectedValue;
	}
</script>
</c:if>
<c:if test="${signal == 1}">
	<c:if test="${SelectedValue==null}">
	<br>
	<div align="center">
		<!-- 이전 -->
		<c:if test="${startPage > blockPage}">
			<a id="BMT_a" href="javascript:After_PageView(${startPage - blockPage})">이전</a>
		</c:if>
		<!-- 페이지 출력 -->
		<c:forEach begin="${startPage}" end="${endPage}" var="i">
			<c:if test="${i == currentPage}">
				${i}
			</c:if>
			<c:if test="${i != currentPage}">
				<a id="BMT_a" href="javascript:After_PageView(${i})">${i}</a>
			</c:if>
		</c:forEach>
		<!-- 다음 -->
		<c:if test="${endPage < totPage}">
			<a id="BMT_a" href="javascript:After_PageView(${endPage+1})">다음</a>
		</c:if>
	</div>
	<br><br>
	</c:if>
	<c:if test="${SelectedValue!=null}">
	<br>
	<div align="center">
		<!-- 이전 -->
		<c:if test="${startPage > blockPage}">
			<a id="BMT_a" href="javascript:After_PageView(${startPage - blockPage},'${SelectedValue}')">이전</a>
		</c:if>
		<!-- 페이지 출력 -->
		<c:forEach begin="${startPage}" end="${endPage}" var="i">
			<c:if test="${i == currentPage}">
				${i}
			</c:if>
			<c:if test="${i != currentPage}">
				<a id="BMT_a" href="javascript:After_PageView(${i},'${SelectedValue}')">${i}</a>
			</c:if>
		</c:forEach>
		<!-- 다음 -->
		<c:if test="${endPage < totPage}">
			<a id="BMT_a" href="javascript:After_PageView(${endPage+1},'${SelectedValue}')">다음</a>
		</c:if>
	</div>
	<br><br>
	</c:if>
</c:if>
<c:if test="${signal == 2}">
	<c:if test="${SelectedValue == null}">
	<br>
	<div align="center">
		<!-- 이전 -->
		<c:if test="${startPage > blockPage}">
			<a id="BMT_a" href="javascript:Before_PageView(${startPage - blockPage})">이전</a>
		</c:if>
		<!-- 페이지 출력 -->
		<c:forEach begin="${startPage}" end="${endPage}" var="i">
			<c:if test="${i == currentPage}">
				${i}
			</c:if>
			<c:if test="${i != currentPage}">
				<a id="BMT_a" href="javascript:Before_PageView(${i})">${i}</a>
			</c:if>
		</c:forEach>
		<!-- 다음 -->
		<c:if test="${endPage < totPage}">
			<a id="BMT_a" href="javascript:Before_PageView(${endPage+1})">다음</a>
		</c:if>
	</div>
	<br><br>
	</c:if>
	<c:if test="${SelectedValue != null}">
	<br>
	<div align="center">
		<!-- 이전 -->
		<c:if test="${startPage > blockPage}">
			<a id="BMT_a" href="javascript:Before_PageView(${startPage - blockPage},'${SelectedValue}')">이전</a>
		</c:if>
		<!-- 페이지 출력 -->
		<c:forEach begin="${startPage}" end="${endPage}" var="i">
			<c:if test="${i == currentPage}">
				${i}
			</c:if>
			<c:if test="${i != currentPage}">
				<a id="BMT_a" href="javascript:Before_PageView(${i},'${SelectedValue}')">${i}</a>
			</c:if>
		</c:forEach>
		<!-- 다음 -->
		<c:if test="${endPage < totPage}">
			<a id="BMT_a" href="javascript:Before_PageView(${endPage+1},'${SelectedValue}')">다음</a>
		</c:if>
	</div>
	<br><br>
	</c:if>
</c:if>