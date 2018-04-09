function getData(n) {
    var arr = [],
        i,
        x;
    for (
        i = 0, x = i*20;
        i < n;
        i = i + 1, x = x + 20
    ) {
        var ran = (Math.random()*10 + Math.random()*10 + Math.random()*10)/30
        var time = toTime(x);
        arr.push([
            time,
            2 * (ran-0.5) + Math.sin(i * ran)
        ]);
    }
    return arr;
}

function toTime(x) {
    var str = x.toString();
    var time;
    if (x > 1000) {
        time =  str.slice(0, -3) + "s " + str.slice(-3) + "ms";
    } else {
        time = str + " ms";
    } return time;
}

var n = 500,
    xData = getData(n),
    yData = getData(n),
    zData = getData(n);


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
    },{
        data: yData,
        lineWidth: 1,
        name: 'Data points on y axis'
    },{
        data: zData,
        lineWidth: 1,
        name: 'Data points on z axis'
    }]

});
console.timeEnd('line');
