
function initialize(){
    mapboxgl.accessToken = 'pk.eyJ1IjoidGJvLTAiLCJhIjoiY2p2d3Jqa2plMXdnMjQ0bXNhaWprNXNmeSJ9.RstlAQNxKnqWffTHIeGLPA';
    window.map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v10',
        center: [18.4241, -33.9249], // starting position [lng, lat]
        zoom: 9 // starting zoom
    });

    window.startPin = new mapboxgl.Marker({draggable : true}).setLngLat([0, 0]).addTo(window.map)
    window.destinationPin = new mapboxgl.Marker({draggable : true}).setLngLat([0, 0]).addTo(window.map)

    window.map.on('click', function(event){
        event.preventDefault();
        var des = document.getElementById('destination');
        var str  = document.getElementById('start');
        if(window.startPoint == true){
            window.destinationPin.setLngLat(event.lngLat);
            window.startPoint = false;
            des.value = event.lngLat.lng + "," + event.lngLat.lat;
            document.getElementById('destination').textContent = "Destination: " + des.value;
        }else{
            window.startPin.setLngLat(event.lngLat);
            window.startPoint = true;
            str.value = event.lngLat.lng + "," + event.lngLat.lat;
            document.getElementById('start').innerHTML = "Start: " + str.value;
        }
    })
}

initialize();