<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
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
                            <a href="/individual">Individual</a>
                        </div>
                        <div class="logout">
                            <button class="button-txt">Logout</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <div class="panel-title">
        <div class="padding-gen">Dashboard - Admin</div>
    </div>

    <div class="content">
        <div class="padding-gen">
            <div class="row">
                <div class="w50">
                    <div class="sub-title"> Top ${bestPerformers.size()} Employees</div>
                    <div id="columnchart_values-1"></div>
                </div>
                <div class="w50">
                    <div class="sub-title"> Top ${bestProjects.size()} Projects</div>
                    <div id="columnchart_values-2"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="panel-title">
        <div class="padding-gen">Configurations</div>
    </div>
    <div class="content">
        <div class="padding-gen">
            <div class="row">
                <div class="w50">
                    <div class="sub-title" style="text-align: left;"> Spring Duration</div>
                    <div style="padding: 3%;">
                        <form id="duration_form">
                            <h7 class="list-group-item-heading">Sprint duration (in days) :</h7> <span>
                            <input type="number" id="sprint_interval"></span>
                            <br>

                            <div style="padding-top: 6px;">
                                <h7 class="list-group-item-heading">Start Date and time :</h7> <span>
                            <input type="datetime-local" name="date" id="startDate"
                                   value="2017-01-01T00:00:00"></span>
                            </div>
                            <div style="padding-top: 6px;">
                                <span><button id="setDuration">Update</button></span>
                            </div>
                        </form>
                        <div id="error" hidden>
                            <P style="color: red"> Insert a valid number</P>
                        </div>
                    </div>

                    <div class="sub-title" style="text-align: left;"> Personal Traits Score</div>
                    <div style="padding: 3%;">

                        <form id="personal_traits_form">
                            <h7>Select Employee :</h7><span style="padding-left: 21%;"> <select name="user"
                                                                                                id="userTraitSelected"
                                                                                                style="
                                width: 31%;">
                            <c:forEach items="${users}" var="user">
                                <option value="${user[0]}">${user[1]}</option>
                            </c:forEach>
                        </select></span>
                            <br>

                            <div style="margin-top: 12px;">
                                <h7>Personal traits score out of 100:</h7>
                                <span style="padding-left: 3%;"><input type="number" id="personal_traits_score"></span>
                            </div>
                            <br>

                            <div style="padding-left: 44%;">
                                <button id="setPTS" style="width: 55%;">Add Score</button>
                            </div>
                            </span>
                        </form>
                    </div>
                    <div id="errorPT" hidden>
                        <P style="color: red"> Insert a valid score</P>
                    </div>
                </div>

                <div class="w50">
                    <div class="sub-title" style="text-align: left;">Criteria Weightage of Individual</div>
                    <div style="padding: 3%;">
                        <form id="individual_score_weight">
                            <div class="w50">
                                <label>Select Employee :</label>
                            <span><div style="margin-left: 8%; padding-top: 6px;">
                                <select name="user" id="iswSelected" style="width: 70%;">
                                    <c:forEach items="${users}" var="user">
                                        <option value="${user[0]}">${user[1]}</option>
                                    </c:forEach>
                                </select>
                            </div></span>
                                <br>
                                <label>Work completion :</label>
                            <span><div style="margin-left: 8%; padding-top: 6px;"><input type="number" id="wc"
                                                                                         style="width: 70%;">
                            </div></span>
                                <br><label>Planning the project :</label>
                            <span><div style="margin-left: 8%; padding-top: 6px;"><input type="number" id="pp"
                                                                                         style="width: 70%;">
                            </div></span>
                                <br><label>Work efficiency :</label>
                            <span><div style="margin-left: 8%; padding-top: 6px;"><input type="number" id="wes"
                                                                                         style="width: 70%;">
                            </div></span>
                                <br><label>Defects count :</label>
                            <span><div style="margin-left: 8%; padding-top: 6px;"><input type="number" id="dc"
                                                                                         style="width: 70%;">
                            </div></span>
                            </div>
                            <div class="w50">
                                <label>Fixed defects count :</label>
                            <span><div style="margin-left: 8%; padding-top: 6px;"><input type="number" id="fdc"
                                                                                         style="width: 70%;">
                            </div></span>
                                <br><label>Code quality issue : </label>
                            <span><div style="margin-left: 8%; padding-top: 6px;"><input type="number" id="cqi"
                                                                                         style="width: 70%;">
                            </div></span>
                                <br><label>Code quality issue fixed : </label>
                            <span><div style="margin-left: 8%; padding-top: 6px;"><input type="number" id="cqif"
                                                                                         style="width: 70%;">
                            </div></span>
                                <br><label>Project team contribution : </label>
                            <span><div style="margin-left: 8%; padding-top: 6px;"><input type="number" id="ptc"
                                                                                         style="width: 70%;">
                            </div></span>
                                <br><label>Personal traits : </label>
                            <span><div style="margin-left: 8%; padding-top: 6px;"><input type="number" id="pt"
                                                                                         style="width: 70%;">
                            </div></span>
                                <br>
                            </div>

                            <div style="margin-left: 7%; padding-top: 6px;">
                                <button id="setWeight" style="width: 30%;">Update Ratio</button>
                            </div>
                        </form>
                    </div>
                    <div id="errorPC" hidden>
                        <P style="color: red"> insert a valid number</P>
                    </div>

                    <div id="errorPC2" hidden>
                        <P style="color: red"> Total must be 100</P>
                    </div>
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
<%--<script type="text/javascript" src="../../assets/js/loader.js"></script>--%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="../../assets/js/main.js"></script>
<script type="text/javascript">
    window.onload = function () {
        drawChart1(${bestPerformersData});
        drawChart2(${bestProjectsData});
    };

    $('#setDuration').click(function (e) {
        e.preventDefault();
        var value = $('#sprint_interval').val();
        var date = document.getElementById("startDate");
        var currentDate = date.value;
        var formattedDate = new Date(currentDate.replace('T', ' '));
        if (!value.isEmpty && value > 0) {

            $('#error').hide();
            var myForm = $('#duration_form')[0];
            var fd = new FormData(myForm);

            var search = {};
            search["duration"] = $('#sprint_interval').val();
            search["startDate"] = formattedDate;

            $.ajax({
                type: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                url: "/update_duration",
                data: JSON.stringify(search),
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    document.getElementById("duration_form").reset();
                    alert('Successfully Updated');
                },
                error: function (result) {
                    alert('error');
                    console.log(result);
                }
            });
        } else {
            $('#error').show();
        }

    });

    $('#setPTS').click(function (e) {
        e.preventDefault();
        var value = document.getElementById("userTraitSelected");
        var empId = value.options[value.selectedIndex].value;

        var score = $('#personal_traits_score').val();

        if (!score.isEmpty && score >= 0 && score <= 100) {

            $('#errorPT').hide();

            var data1 = {};
            data1["empId"] = empId;
            data1["score"] = score;

            $.ajax({
                type: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                url: "/update_personal_traits",
                data: JSON.stringify(data1),
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    document.getElementById("personal_traits_form").reset();
                    alert('Successfully Updated');
                },
                error: function (result) {
                    alert('error');
                    console.log(result);
                }
            });
        } else {
            $('#errorPT').show();
        }

    });

    $('#setWeight').click(function (e) {
        e.preventDefault();
        var value = document.getElementById("iswSelected");
        var empId = value.options[value.selectedIndex].value;

        var wc = $('#wc').val();
        var pp = $('#pp').val();
        var wes = $('#wes').val();
        var dc = $('#dc').val();
        var fdc = $('#fdc').val();
        var cqi = $('#cqi').val();
        var cqif = $('#cqif').val();
        var ptc = $('#ptc').val();
        var pt = $('#pt').val();

        var total = Number(wc) + Number(pp) + Number(wes) + Number(dc) + Number(fdc) + Number(cqi) + Number(cqif) + Number(ptc) + Number(pt);
        if (wc >= 0 && wc <= 100 && pp >= 0 && pp <= 100 && wes >= 0 && wes <= 100
                && dc >= 0 && dc <= 100 && fdc >= 0 && fdc <= 100 && cqi >= 0 && cqi <= 100 && cqif >= 0 && cqif <= 100
                && ptc >= 0 && ptc <= 100 && pt >= 0 && pt <= 100) {

            if (total == 100) {
                $('#errorPC2').hide();
                $('#errorPC').hide();

                var data2 = {};
                data2["emp_id"] = empId;
                data2["work_completion"] = wc;
                data2["planning_the_project"] = pp;
                data2["work_efficiency"] = wes;
                data2["defects_count"] = dc;
                data2["fixed_defects_count"] = fdc;
                data2["code_quality_issue"] = cqi;
                data2["cqi_fixed"] = cqif;
                data2["project_team_contribution"] = ptc;
                data2["personal_traits"] = pt;


                $.ajax({
                    type: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    url: "/update_isw",
//                    data: JSON.stringify(data2),
                    data: JSON.stringify(data2),
                    dataType: 'json',
                    success: function (result) {
                        console.log(result);
                        document.getElementById("individual_score_weight").reset();
                        alert('Successfully Updated');
                    },
                    error: function (result) {
                        alert('error');
                        console.log(result);
                    }
                });
            } else {
                $('#errorPC2').show();
            }
        } else {
            $('#errorPC').show();
        }
    });
</script>
</body>
</html>
