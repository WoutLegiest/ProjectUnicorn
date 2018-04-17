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

function toJSON(data) {
    var json = JSON.parse(data); //De JSON als string omzetten naar een JSON
    makeDataSets(json);
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
        arr.push([
            x,
            array[i]
        ]);
    }

    return arr;
}

function getResults(array, xVals) {

    var n = array.length;

    var arr = [],
        i;
    for (
        i = 0;
        i < n - 1;
        i = i + 1
    ) {
        arr.push([
            xVals[i],
            array[i]
        ]);
    }

    return arr;
}

function getResFreq(array1, array2) {
    var ret = [];
    ret[0] = array1[array1.length - 1];
    ret[1] = array2[array2.length - 1];
    return ret;
}

function makeDataSets(json) {
    var xData = getData(json.xData);
    var yData = getData(json.yData);
    var zData = getData(json.zData);
    drawDataChart(xData, yData, zData);

    var xR1 = getResults(json.xR1, json.xF);
    var xR2 = getResults(json.xR2, json.xF);
    var xF = getResFreq(json.xR1, json.xR2);
    drawResultsChart(xR1, xR2, xF, 'x');

    var yR1 = getResults(json.yR1, json.yF);
    var yR2 = getResults(json.yR2, json.yF);
    var yF = getResFreq(json.yR1, json.yR2);
    drawResultsChart(yR1, yR2, yF, 'y');

    var zR1 = getResults(json.zR1, json.zF);
    var zR2 = getResults(json.zR2, json.zF);
    var zF = getResFreq(json.zR1, json.zR2);
    drawResultsChart(zR1, zR2, zF, 'z');
}

function drawDataChart(xData, yData, zData) {
    console.time('line');
    Highcharts.chart('dataChart', {

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
            type: 'linear',
            labels: {
                formatter: function () {
                    return this.value/1000 + ' s';
                }
            }
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
            lineWidth: 0.5,
            name: 'Data points on y axis'
        }, {
            data: zData,
            lineWidth: 0.5,
            name: 'Data points on z axis'
        }]

    });
    console.timeEnd('line');
}

function drawResultsChart(R1, R2, F, t) {
    console.time('line');
    Highcharts.chart(t + 'Chart', {

        chart: {
            zoomType: 'x'
        },

        title: {
            text: 'Results on the ' + t + ' axis'
        },

        subtitle: {
            text: 'Resonance freq sampled: ' + F[0] + ' Hz | Resonance freq filtered: ' + F[1] + ' Hz'
        },

        xAxis: {
            type: 'linear',
            labels: {
                formatter: function () {
                    return this.value + ' Hz';
                }
            }
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
            data: R1,
            lineWidth: 0.5,
            name: 'Amplitude spectrum based on sampled values'
        }, {
            data: R2,
            lineWidth: 0.5,
            name: 'Amplitude spectrum based on filtered values'
        }]

    });
    console.timeEnd('line');
}
