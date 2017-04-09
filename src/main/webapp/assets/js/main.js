$(document).ready(function () {

    $("#menu-trigger-nav").click(function(){
        $("#main-menu-nav").slideToggle(300);
    });

    var $window = $(window), // caching the objects for performance
        $Menu = $("#main-menu-nav");

    $window.resize(function(){
        if( $window.width() > 1023)$Menu.show();
        else  $Menu.hide();
    });

    $('ul.tabs-gen li').click(function(){
        var tab_id = $(this).attr('data-tab');
        $('ul.tabs-gen li').removeClass('current');
        $('.tab-content').removeClass('current');
        $(this).addClass('current');
        $("#"+tab_id).addClass('current');
    })
});

//
//google.charts.load('current', {'packages':['bar']});
//google.charts.setOnLoadCallback(drawChart);
//
//function drawChart() {
//    var data = google.visualization.arrayToDataTable([
//        ['Time', 'LP_9.5.0', 'LP_1.5.0'],
//        ['14:00', 7, 11],
//        ['15:00', 4, 8],
//        ['16:00', 3, 5],
//        ['17:00', 6, 3],
//    ]);
//
//    var options = {
//        chart: {
//            title: 'Value Chart'
//        },
//        bars: 'vertical',
//        vAxis: {format: 'decimal'},
//        height: 400,
//        colors: ['#ff7007', '#ff7007']
//    };
//
//    var chart = new google.charts.Bar(document.getElementById('chart_div'));
//
//    chart.draw(data, google.charts.Bar.convertOptions(options));
//
//    var btns = document.getElementById('btn-group');
//
//    btns.onclick = function (e) {
//
//        if (e.target.tagName === 'BUTTON') {
//            options.vAxis.format = e.target.id === 'none' ? '' : e.target.id;
//            chart.draw(data, google.charts.Bar.convertOptions(options));
//        }
//    }
//}
//
//
//
///* Toggle between adding and removing the "active" and "show" classes when the user clicks on
//ne of the "Section" buttons. The "active" class is used to add a background color to the
//current button when its belonging panel is open. The "show" class is used to open the
//specific accordion panel */
//
//var acc = document.getElementsByClassName("accordion");
//var i;
//
//for (i = 0; i < acc.length; i++) {
//    acc[i].onclick = function(){
//        this.classList.toggle("active");
//        this.nextElementSibling.classList.toggle("show");
//    }
//}
//
//

/*------------- New Chart --------------*/

google.charts.load("current", {packages:['corechart','bar']});
//google.charts.setOnLoadCallback(drawChart1);


function drawChart1(data) {

    var data = google.visualization.arrayToDataTable(data);

    var view = new google.visualization.DataView(data);
    view.setColumns([0, 1,
        { calc: "stringify",
            sourceColumn: 1,
            type: "string",
            role: "annotation" },
        2]);

    var options = {
        title: "",
        chartArea: {width: '85%'},
        height: 400,
        bar: {groupWidth: "50%"},
        legend: { position: "none"},
        vAxis:{
            baselineColor: '#ccc',
            gridlineColor: '#ccc'
        }
    };
    var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values-1"));
    chart.draw(view, options);
}


function drawChart2(data) {
    var data = google.visualization.arrayToDataTable(data);
    var view = new google.visualization.DataView(data);
    view.setColumns([0, 1,
        { calc: "stringify",
            sourceColumn: 1,
            type: "string",
            role: "annotation" },
        2]);

    var options = {
        title: "",
        chartArea: {width: '85%'},
        height: 400,
        bar: {groupWidth: "50%"},
        legend: { position: "none"},
        vAxis:{
            baselineColor: '#ccc',
            gridlineColor: '#ccc'
        }
    };
    var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values-2"));
    chart.draw(view, options);
}

