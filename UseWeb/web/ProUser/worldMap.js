function initMap(callback) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var inputData = this.responseText;
            //document.getElementById("demo").innerHTML = this.responseText;

            getLatLng(inputData);
        }
    };
    xhttp.open("GET", "http://localhost:8080/UseWebWeb/rest_unicorn/SpotterProjectData/all", true);
    xhttp.send();
}

function getLatLng(data) {

    data = data.slice(0, -1);
    var res = data.split("}");
    var ans = "";

    var name = [];
    var lat = [];
    var lng = [];

    for (var i = 0; i < res.length-1; i++) {

        ans = res[i].concat("}");
        ans = ans.slice(1);

        //console.log(ans);

        var json = JSON.parse(ans);

        name.push(json.naam);
        lat.push(json.latitude);
        lng.push(json.longitude);

        //document.getElementById(i).innerHTML = ans;
    }

    /*var x = document.getElementById("test");

    x.innerHTML = data;

    console.log(ans);
    console.log(lat);
    console.log(lng);*/

    map(name, lat, lng);


}

function map(name, lat, lng) {

    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 1,
        center: new google.maps.LatLng(15, 0),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var infowindow = new google.maps.InfoWindow();

    var marker, i;

    var unicorn = '../resources/unicornMarker.png';
    var flag = '../resources/flag.png';

    for (i = 0; i < name.length; i++) {
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(lat[i], lng[i]),
            map: map,
            icon: unicorn
        });

        google.maps.event.addListener(marker, 'click', (function (marker, i) {
            return function () {
                infowindow.setContent(name[i]);
                infowindow.open(map, marker);
            }
        })(marker, i));
    }

    marker = new google.maps.Marker({
        position: new google.maps.LatLng(51.059246, 3.707904),
        map: map,
        icon: flag
    });

    google.maps.event.addListener(marker, 'click', (function (marker, i) {
        return function () {
            infowindow.setContent(Home);
            infowindow.open(map, marker);
        }
    })(marker, i));


}