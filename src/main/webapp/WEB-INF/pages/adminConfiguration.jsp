<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Configuration</title>
</head>
<body>
<div>
    <form id="duration_form">
        Sprint duration in days: <input type="number" id="sprint_interval"> <span> <button id="setDuration">Set</button></span>
    </form>
</div>
<div id="error" hidden>
    <P style="color: red"> insert a valid number</P>
</div>

<%--personal traits--%>
<div>
    <H3>Personal Traits</H3>
    <form id="personal_traits_form">
        Select Employee : <select name="user" id="userTraitSelected">
        <c:forEach items="${users}" var="user">
            <option value="${user[0]}">${user[1]}</option>
        </c:forEach>
    </select>
        <br>
        Personal traits score out of 100: <input type="number" id="personal_traits_score">
    <br>
        <button id="setPTS">Set</button>
        </span>
    </form>
</div>
<div id="errorPT" hidden>
    <P style="color: red"> insert a valid number</P>
</div>
<br>
<br>
<%--Individual score weight--%>
<div>
    <H3>Personal Traits</H3>
    <form id="individual_score_weight">
        Select Employee : <select name="user" id="iswSelected">
        <c:forEach items="${users}" var="user">
            <option value="${user[0]}">${user[1]}</option>
        </c:forEach>
    </select>
        <br>
        <h4> Please fill criteria ratio</h4>
        <br>
        <label>Work completion score out of 100:</label> <input type="number" id="wc">
        <br><label>Planning the project score out of 100:</label> <input type="number" id="pp">
        <br><label>Work efficiency score out of 100:</label> <input type="number" id="wes">
        <br><label>Defects count score out of 100:</label> <input type="number" id="dc">
        <br><label>Fixed defects count score out of 100: </label><input type="number" id="fdc">
        <br><label>Code quality issue score out of 100: </label><input type="number" id="cqi">
        <br><label>Code quality issue fixed score out of 100: </label><input type="number" id="cqif">
        <br><label>Project team contribution score out of 100: </label><input type="number" id="ptc">
        <br><label>Personal traits score out of 100: </label><input type="number" id="pt">
        <br><button id="setWeight">Set</button>
        </span>
    </form>
</div>
<div id="errorPT" hidden>
    <P style="color: red"> insert a valid number</P>
</div>

<div id="errorPT2" hidden>
    <P style="color: red"> Total must be 100</P>
</div>

<script src="../../assets/js/jquery.js"></script>
<script class="include" type="text/javascript" src="../../assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="../../assets/js/jquery.scrollTo.min.js"></script>
<script src="../../assets/js/jQuery-slimScroll-1.3.0/jquery.slimscroll.js"></script>
<script src="../../assets/js/jquery.nicescroll.js"></script>

<script type="text/javascript">


    $('#setDuration').click(function (e) {
        e.preventDefault();
        var value = $('#sprint_interval').val();
        if (!value.isEmpty && value > 0) {

            $('#error').hide();
            var myForm = $('#duration_form')[0];
            var fd = new FormData(myForm);

            var search = {};
            search["duration"] = $('#sprint_interval').val();

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
        if (wc >=0 && wc <= 100 && pp >=0 && pp <= 100 && wes >=0 && wes <= 100
                && dc >=0 && dc <= 100 && fdc >=0 && fdc <= 100 && cqi >=0 && cqi <= 100 && cqif >=0 && cqif <= 100
                && ptc >=0 && ptc <= 100 && pt >=0 && pt <= 100) {

            if(total == 100) {
                $('#errorPT2').hide();
                $('#errorPT').hide();

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
                    },
                    error: function (result) {
                        alert('error');
                        console.log(result);
                    }
                });
            } else {
                $('#errorPT2').show();
            }
        } else {
            $('#errorPT').show();
        }

    });

</script>
</body>
</html>
