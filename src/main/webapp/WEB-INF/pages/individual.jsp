<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <!--<title>Heroic Features - Start Bootstrap Template</title>-->
    <title>Project EPA</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="../../assets/css/main.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>
<body>

  <div class="wrapper">
    <header>
        <div class="user-bar">
           <div class="padding-gen">
              <div class="logo">
                  <img src="../../assets/images/img-logo.png" alt="">
              </div>

              <div class="pull-right">
                  <div class="panel-user">
                      <div class="user-icon"><img src="../../assets/images/user-icon.png" alt="user-icon"></div>
                      <div class="user-name">P. Prashanth</div>
                      <div class="user-role">
                        <a href="/admin">Admin</a>
                      </div>
                      <div class="logout"><button class="button-txt">Logout</button></div>
                  </div>
              </div>
            </div>
        </div>
    </header>

    <div class="panel-title">
      <div class="padding-gen">Dashboard - Individual</div>
    </div>

      <div class="sub-title">
          <div class="padding-gen">
              <form id="employeeList">
                  <label>Employee : </label>
                  <select name="user" id="employeeSelected" onchange="loadData()">
                  <c:forEach items="${users}" var="user">
                      <option value="${user[0]}">${user[1]}</option>
                  </c:forEach>
              </select>
              </form>
          </div>
      </div>

    <div class="content">
          <div class="padding-gen">           
           <div class="row">
	                  <div class="w20">
	                      <div class="sub-title"> Individual Performance</div>
	                      <div id="chart_divg"></div>
	                  </div>
	                  <div class="w30">
	                      <div class="sub-title"> Performance Index Score of Every Sprint</div>
	                      <div id="line_top_x"></div>
	                  </div>
	                  <div class="w20">
	                      <div class="sub-title"> Team Performance</div>
	                      <div id="chart_divg2"></div>
	                  </div>
	                  <div class="w30">
	                      <div class="sub-title"> Performance Index Score of Every Two Weeks</div>
	                      <div id="line_top_x2"></div>
	                  </div>
             </div>             
              <div class="row margin-top48">
                  <div class="w50">
                      <div class="sub-title"> Criteria Breakdown</div>
                      <div id="chart_div1"></div>
                  </div>
                  <div class="w50">
                      <div class="sub-title"> Criteria Breakdown</div>
                      <div id="chart_div2"></div>
                  </div>
              </div>
          </div>
      </div>

    <footer>
      @Copyright 2017. Prashanth Prabaharan.
    </footer>
  </div>
  
   <!-- scripts -->
  <script type="text/javascript" src="../../assets/js/jquery-1.12.3.min.js"></script>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript" src="../../assets/js/main2.js"></script>
  <script type="text/javascript">
      window.onload = function () {
          loadData();
      };
      var loadData = function () {
          var value = document.getElementById("employeeSelected");
          var empId = value.options[value.selectedIndex].value;

          var data1 = {};
          data1["empId"] = empId;
          $.ajax({
              type: "POST",
              headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
              },
              url: "/load_individual_page",
              data: JSON.stringify(data1),
              dataType: 'json',
              success: function (result) {
                  console.log(result);
                  individualGauge(result.totalScore);
                  individualCriteria(result.criteriaIndividual);
                  lineChartIndi(result.lineChartData);
                  projectGauge(result.projectTotalScore);
                  groupCriteria(result.projectCriteria);
                  lineChartgroup(result.projectLineChartData);8
              },
              error: function (result) {
                  alert('error');
                  console.log(result);
              }
          });
      };

      var individualGauge = function(data){
          drawChartGaugeIndi(data);
      };

      var projectGauge = function(data){
          drawChartg2(data);
      };

      var individualCriteria = function (data) {
          var indiData = [];
          for(i =0; i<data.length; i++){
              indiData.push(data[i]);
          }
          drawMaterial(indiData);
      };

      var lineChartIndi = function (data) {
          var indiData = [];
          for(i =0; i<data.length; i++){
              indiData.push(data[i]);
          }
          drawChart(indiData);
      };

      var lineChartgroup = function (data) {
          var group = [];
          for(i =0; i<data.length; i++){
              group.push(data[i]);
          }
          drawChart2(group);
      };

      var groupCriteria = function (data) {
          drawMaterial2(data);
      };
  </script>

  
</body>
</html>
