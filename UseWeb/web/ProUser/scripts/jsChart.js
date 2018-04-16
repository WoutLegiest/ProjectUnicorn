//getDataFromREST();

function getDataFromREST(id) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var inputData = this.responseText;
            //document.getElementById("demo").innerHTML = this.responseText;

            toJSON(inputData);
        }
    };
    xhttp.open("GET", "http://localhost:8080/UseWebWeb/rest_unicorn/ProfMeasurementData/data", true);
    xhttp.setRequestHeader("measurementId", id);
    xhttp.send();
}

function toJSON(data){
    var json = JSON.parse(data); //De JSON als string omzetten naar een JSON
    drawChart(json);
}


function getData(array) {

    var n = array.length;

    var arr = [],
        i,
        x;
    for (
        i = 0, x = i * 20;
        i < n;
        i = i + 1, x = x + 20
    ) {
        var time = toTime(x);
        arr.push([
            time,
            array[i]
        ]);
    }

    return arr;
}

function toTime(x) {
    var str = x.toString();
    var time;
    if (x > 1000) {
        time = str.slice(0, -3) + "s " + str.slice(-3) + "ms";
    } else {
        time = str + " ms";
    }
    return time;
}

function drawChart(json) {

    var n = 500,
        xData = getData(json.xData),
        yData = getData(json.yData),
        zData = getData(json.zData);

    console.time('line');
    Highcharts.chart('container', {

        chart: {
            zoomType: 'x'
        },

        title: {
            text: 'Measurements'
        },

        subtitle: {
            text: 'On the x, y and z axis'
        },

        xAxis: {
            type: 'linear'
        },

        yAxis: [{ // left y axis
            title: {
                text: null
            },
            labels: {
                align: 'left',
                x: 3,
                y: 16,
                format: '{value:.,0f}'
            },
            showFirstLabel: false
        }, { // right y axis
            linkedTo: 0,
            gridLineWidth: 0,
            opposite: true,
            title: {
                text: null
            },
            labels: {
                align: 'right',
                x: -3,
                y: 16,
                format: '{value:.,0f}'
            },
            showFirstLabel: false
        }],

        tooltip: {
            shared: true,
            crosshairs: true
        },

        series: [{
            data: xData,
            lineWidth: 0.5,
            name: 'Data points on x axis'
        }, {
            data: yData,
            lineWidth: 1,
            name: 'Data points on y axis'
        }, {
            data: zData,
            lineWidth: 1,
            name: 'Data points on z axis'
        }]

    });
    console.timeEnd('line');
}
