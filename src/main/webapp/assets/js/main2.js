/*------------- Individual Chart 01  --------------*/

google.charts.load('current', {packages: ['corechart', 'bar', 'line', 'gauge']});
//google.charts.setOnLoadCallback(drawMaterial);
//google.charts.setOnLoadCallback(drawMaterial2);
//google.charts.setOnLoadCallback(drawChart);
//google.charts.setOnLoadCallback(drawChart2);
//google.charts.setOnLoadCallback(drawChartg);
//google.charts.setOnLoadCallback(drawChartg2);

function drawMaterial(dataforload) {
    var data = google.visualization.arrayToDataTable(dataforload);

    //[
    //    ['Criteria', 'Expected', 'Achived'],
    //    ['Criteria 1', 8175000, 8008000],
    //    ['Criteria 2', 3792000, 3694000],
    //    ['Criteria 3', 2695000, 2896000],
    //    ['Criteria 4', 2099000, 1953000],
    //    ['Criteria 5', 1526000, 1517000]
    //]
    var options = {
        chart: {
            title: ''
        },
        hAxis: {
            title: 'Total Population',
            minValue: 0
        },
        vAxis: {
            title: 'City'
        },
        bars: 'horizontal',
        colors: ['#14b3ff','#bac8c9'],
        'legend':'bottom'
    };
    var material = new google.charts.Bar(document.getElementById('chart_div1'));
    material.draw(data, options);
}

function drawMaterial2(dataForCriteria) {
	var data = google.visualization.arrayToDataTable(dataForCriteria);

    var options = {
        chart: {
            title: ''
        },
        hAxis: {
            title: 'Total Population',
            minValue: 0
        },
        vAxis: {
            title: 'City'
        },
        bars: 'horizontal',
        colors: ['#14b3ff','#bac8c9'],
        'legend':'bottom'
    };
    var material = new google.charts.Bar(document.getElementById('chart_div2'));
    material.draw(data, options);
}

/*------------- Line Charts  --------------*/

function drawChart(rdata) {

    var data = new google.visualization.DataTable();
    data.addColumn('number', 'Sprints');
    data.addColumn('number', 'Score');

    data.addRows(rdata);

    var options = {
    	  width: 350,
    	  height: 250,
	      chart: {
	        title: ''
	      },
	      hAxis: {
	          title: 'Week'
	      },
	      vAxis: {
	          title: 'Performance Index'
	      },
	      vAxis: {
	          viewWindow: {
	            max: 100
	          }
	        }
    };

    var chart = new google.charts.Line(document.getElementById('line_top_x'));

    chart.draw(data, options);
  }

function drawChart2(chartData) {

    var data = new google.visualization.DataTable();
    data.addColumn('number', 'Sprint');
    data.addColumn('number', 'Score');

    data.addRows(chartData);

    var options = {
	  width: 350,
	  height: 250,
      chart: {
        title: ''
      },
      hAxis: {
          title: 'Week'
      },
      vAxis: {
          title: 'Performance Index'
      },
      vAxis: {
          viewWindow: {
            max: 100
          }
        }
    };

    var chart = new google.charts.Line(document.getElementById('line_top_x2'));

    chart.draw(data, options);
  }

/*------------- Gauge Charts  --------------*/

var drawChartGaugeIndi = function(data) {

    var data = google.visualization.arrayToDataTable([
      ['Label', 'Value'],
      ['Score', data]
    ]);

    var options = {
      width: 300, height: 200,
      greenFrom: 75, greenTo: 100,
      yellowFrom:50, yellowTo: 75,
      redFrom:0, redTo: 50,
      minorTicks: 5
    };

    var chart = new google.visualization.Gauge(document.getElementById('chart_divg'));

    chart.draw(data, options);

    //setInterval(function() {
    //  data.setValue(0, 1, 40 + Math.round(60 * Math.random()));
    //  chart.draw(data, options);
    //}, 13000);
  }

function drawChartg2(data) {

    var data = google.visualization.arrayToDataTable([
      ['Label', 'Value'],
      ['Score', data]
    ]);

    var options = {
      width: 300, height: 200,
      greenFrom: 75, greenTo: 100,
      yellowFrom:50, yellowTo: 75,
      redFrom:0, redTo: 50,
      minorTicks: 5
    };

    var chart = new google.visualization.Gauge(document.getElementById('chart_divg2'));

    chart.draw(data, options);

    //setInterval(function() {
    //  data.setValue(0, 1, 40 + Math.round(60 * Math.random()));
    //  chart.draw(data, options);
    //}, 13000);
  }