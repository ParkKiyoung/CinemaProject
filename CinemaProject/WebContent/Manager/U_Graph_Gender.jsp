<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
google.charts.load("current", {packages:['corechart']});
google.charts.setOnLoadCallback(GenderChart);
function GenderChart() {
    var data = google.visualization.arrayToDataTable([
      ['Pac Man', 'Percentage'],
      ['남성', ${male}],
      ['여성', ${female}]
    ]);

    var options = {
      title: '남여 회원 현황',
      is3D: true,
    };

    var chart = new google.visualization.PieChart(document.getElementById('GenderChart'));
    chart.draw(data, options);
  }
</script>
<div id="GenderChart" style="width: 50%; height: 500px;"></div>