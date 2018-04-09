function initMap(callback) {
    /**Deze functie vraagt een lijst met alle projecten op.
     * Zodra we de lijst hebben wordt de functie getLatLng opgeroepen*/

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
    /**De data die we binnen krijgen is een aray van JSON's als string.
     * Deze funtie zal hiervan een array van JSON's maken en daarna uit
     * elke JSON de naam, latitude en longitude halen en die in hun eigen array steken.
     * Zodra we data hebben waar we iets mee zijn roepen we de methode map op.*/

    data = data.slice(0, -1);   //De ']' van de array wegnemen
    var res = data.split("}");  //De string splitten op '}' omdat een JSON eindigd op een '}'
    var ans = "";

    var name = [];
    var lat = [];
    var lng = [];

    for (var i = 0; i < res.length-1; i++) {

        ans = res[i].concat("}");   //Aan de gesplitte string de '}' terugplaatsen
        ans = ans.slice(1);         //Het eerste kar weghalen want dat is een ',' of een '['

        //console.log(ans);

        var json = JSON.parse(ans); //De JSON als string omzetten naar een JSON

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
    /**Deze methode is verantwoordelijk voor het weergeven van de map.*/

    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 1,
        center: new google.maps.LatLng(15, 0),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var infowindow = new google.maps.InfoWindow();

    var marker, i;

    //Custom markers
    var unicorn = '../resources/unicornMarker.png';
    var unicornPink = '../resources/unicornMarkerPink.png';
    //var unicornRoyalBlue = '../resources/unicornMarkerRoyalBlue.png';
    //var flag = '../resources/flag.png';

    //Elk project uit de data weergeven als een marker op de map.
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

    //Speciale marker
    marker = new google.maps.Marker({
        position: new google.maps.LatLng(51.059246, 3.707904),
        map: map,
        //icon: flag
        icon: unicornPink
    });

    google.maps.event.addListener(marker, 'click', (function (marker, i) {
        return function () {
            infowindow.setContent("Home");
            infowindow.open(map, marker);
        }
    })(marker, i));


}