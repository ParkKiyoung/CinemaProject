<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
google.charts.load("current", {packages:['corechart']});
google.charts.setOnLoadCallback(GenreChart);
function GenreChart() {

  var data = new google.visualization.arrayToDataTable([
  ['Pac Man', 'Percentage'],
    ['스포츠', ${sport}],
    ['범죄', ${crime}],
    ['드라마', ${drama}],
    ['코미디', ${comedy}],
    ['로맨스/멜로', ${romance}],
    ['스릴러', ${thriller}],
    ['로맨틱/코미디', ${romance_comedy}],
    ['전쟁', ${military}],
    ['가족', ${family}],
    ['판타지', ${fantasy}],
    ['액션', ${action}],
    ['SF', ${sf}],
    ['애니메이션', ${anime}],
    ['다큐멘터리', ${documentary}]
  ]);

  var options = {
    title: '회원 장르 선호도'
  };

  var chart = new google.visualization.PieChart(document.getElementById('GenreChart'));
  chart.draw(data, options);
}
</script>
<div id="GenreChart" style="width: 50%; height: 500px;"></div>