<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
google.charts.load("current", {packages:['corechart']});
google.charts.setOnLoadCallback(AgeChart);
function AgeChart() {
  var data = google.visualization.arrayToDataTable([
    ["Element", "회원수", { role: "style" } ],
    ["10대", ${uab.getAge10()}, "#3366CC"],
    ["20대", ${uab.getAge20()}, "#A0A0FF"],
    ["30대", ${uab.getAge30()}, "#ACFFEF"],
    ["40대", ${uab.getAge40()}, "#98FB98"],
    ["50대", ${uab.getAge50()}, "#FF7E9D"],
    ["60대 이상", ${uab.getAge60()+uab.getAge70()+uab.getAge80()+uab.getAge90()}, "#FF5A5A"]
  ]);

  var view = new google.visualization.DataView(data);
  view.setColumns([0, 1,
                   { calc: "stringify",
                     sourceColumn: 1,
                     type: "string",
                     role: "annotation" },
                   2]);

  var options = {
    title: "연령별 회원 현황",
    width: 600,
    height: 400,
    bar: {groupWidth: "15%"},
    legend: { position: "none" },
  };
  var chart = new google.visualization.ColumnChart(document.getElementById("AgeChart"));
  chart.draw(view, options);
}
</script>
<div id="AgeChart" style="width: 50%; height: 500px;"></div>