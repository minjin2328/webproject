 var bounds = ol.proj.transformExtent([126, 43, 128, 32], 'EPSG:4326', 'EPSG:3857');

 var geojsonFormat = new ol.format.GeoJSON();

 window.loadFeatures = function(response) {
     vectorSource.addFeatures(geojsonFormat.readFeatures(response));
 };
 var vectorSource = new ol.source.Vector({
     loader: function(extent, resolution, projection) {
         var url = 'http://localhost:8080/geoserver/TripWS/ows?service=WFS&' +
             'version=1.1.0&request=GetFeature&typename=TripWS:tbl_coor&' +
             'outputFormat=text/javascript&format_options=callback:loadFeatures' +
             '&srsname=EPSG:3857&bbox=' + extent.join(',') + ',EPSG:3857';
         $.ajax({
             url: url,
             dataType: 'jsonp',
             jsonp: false
         });
     },
     strategy: ol.loadingstrategy.tile(ol.tilegrid.createXYZ({
         maxZoom: 19
     }))
 });
 /*지오서버 호스트 또는 포트가 다른 경우는 위의 방식(Geoserver의 web.xml의 ENABLE_JSONP를 활성화 하여야 함)
  	var vectorSource = new ol.source.Vector({
	  loader: function(extent, resolution, projection) {
		  var url =  'http://localhost:8080/geoserver/DB_CAMPSITEWEB/ows?service=WFS&' +
	        'version=1.1.0&request=GetFeature&typename=DB_CAMPSITEWEB:tbl_coor&' +
	        'outputFormat=application/json' +
	        '&srsname=EPSG:3857&bbox=' + extent.join(',') + ',EPSG:3857';
	  },
	  strategy: ol.loadingstrategy.tile(ol.tilegrid.createXYZ({
	    maxZoom: 19
	  }))
	}); */
 //wms의 source설정
 var imagesourceWMS = new ol.source.ImageWMS({
   ratio: 1,
   url: 'http://localhost:8080/geoserver/TripWS/wms',
   params: {'FORMAT': 'image/png', LAYERS: 'TripWS:tbl_coor'}
 });
 
 var osmlayer = new ol.layer.Tile({
     source: new ol.source.OSM()
 });

 var pCampsiteWFSlayer = new ol.layer.Vector({
     source: vectorSource
 });

 //위에서 설정한 source 입력, source를 포함하면 untiled가 하나의 레이어가 됨
 var untiled = new ol.layer.Image({
   source: imagesourceWMS
 });
 

 

 //마지막으로 설정한 레이어와 view 등 map에 입력, map에 지금까지 설정한 모든 지도의 정보가 들어있음
 var map = new ol.Map({
     controls: ol.control.defaults({
         attribution: false
     }),
     target: 'map',
     layers: [osmlayer, pCampsiteWFSlayer, untiled]
 });

 //화면크기에 알맞게 지도띄우기
 map.getView().fit(bounds, map.getSize());
 map.on('singleclick', function(evt) {
     displayFeatureInfo(evt.pixel);
 });
 var displayFeatureInfo = function(pixel) {
     var feature = map.forEachFeatureAtPixel(pixel, function(feature, layer) {
         return feature;
     });

     if (feature) {
         FeatureInfoLoad(feature);
     } else {
         FeatureInfoWndClose();
     }
 };
 
 function setGIDList(selectgid){
		map.getLayers().item(2)
	   //cql쿼리문 입력
	   var strCQL = "trip_id in("+selectgid+")";
	   //map에 있는 현재 레이어를 찾고 그 레이어의 source를 찾고 source안에있는 params값을 업데이트한다.
	   imagesourceWMS.updateParams({'FEATUREID': selectgid});
	   
	 }
 
 