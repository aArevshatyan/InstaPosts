
ymaps.ready(init);
var myMap, myPlaceMark;

function init() {
    myMap = new ymaps.Map("map", {
        center: [30.763, 37.648],
        zoom: 3,
    });

    loadDoc();
    load();

    var coords = [
        [55.763, 37.648],
        [1, 1],
        [24.763, 160.648],
    ];


    for (var i = 0; i < coords.length; i++) {
        myPlaceMark = new ymaps.Placemark(coords[i], {
            hintContent: 'placeName' + i,
            balloonContent: 'description' + i
        });

        myMap.geoObjects.add(myPlaceMark);
    }

}



function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var s = this.responseText;

            loadPics(s, 1);
        }
    };
    xhttp.open("GET", "/instagram/urls", true);
    xhttp.send();

}



function loadPics(str, index) {
    var firstIndex = str.indexOf("\"") + 1;
    var lastIndex = str.indexOf("\"", firstIndex)
    var res = str.substring(firstIndex, lastIndex);
    var inner = '<a href=" '+ res + '"><img  src="'+ res + '"/></a>'
    document.getElementById('li' + index).innerHTML = inner;

    res = str.substring(lastIndex + 1, str.length);

    loadPics(res, index + 1);

}
function load() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var s = this.responseText;

            console.log(s);
            // there's no locations on each post
            // so I chose to add custom pins on map

        }
    };
    xhttp.open("GET", "/instagram/locations", true);
    xhttp.send();

}

