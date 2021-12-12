export default class Map {
  constructor() {
    this.geocoder = new kakao.maps.services.Geocoder();
    this.bounds = new kakao.maps.LatLngBounds();
    this.markers = [];
    this.hMarkers = [];
    this.cMarkers = [];
  }
  /* address를 중심으로 map 세팅 */
  setMap(address) {
    let center = new kakao.maps.LatLng(37.57305262781906, 126.97082874999028);

    const options = {
      center,
      level: 4,
    };

    this.geocoder.addressSearch(address, (result, status) => {
      if (status === kakao.maps.services.Status.OK) {
        options.center = new kakao.maps.LatLng(result[0].y, result[0].x);
      }
      this.map = new kakao.maps.Map(document.querySelector("#map"), options);
      return this.map;
    });
  }
  
  /* list를 활용해서 지도 위의 마커 세팅 */
  setMarkers(list, imageOpt, type) {
    for (let m of list) {
      let position;
      this.geocoder.addressSearch(m.address, (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          position = new kakao.maps.LatLng(result[0].y, result[0].x);
          this._displayMarker(m, imageOpt, position, this._setMarkerList(type));
        }
      });
    }
  }
  
  /* type에 따라 마커 배열 선정 */
  _setMarkerList(type){
	  switch(type){
    	case "safeHospital":
    		return this.hMarkers;
    	case "selectedClinic":
    		return this.cMarkers;
    		break;
    	default:
    		return this.markers;
    }
  }

  /* 각 마커 표시 및 이벤트 등록 */
  _displayMarker(marker, imageOpt, position, markers) {
	const {imageSrc, imageSize, imageOption} = imageOpt;
	const content = marker.content;
	
    let image = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
    marker = new kakao.maps.Marker({
      position,
      image,
    });

    let iwContent = `
      <div style="padding:0.25rem;">
       ${content}
      </div>`;
    let infowindow = new kakao.maps.InfoWindow({
      content: iwContent,
    });

    kakao.maps.event.addListener(
      marker,
      "click",
       onClickListener(this.map, marker)
    );
    
    kakao.maps.event.addListener(
      marker,
      "mouseover",
      onOverListener(this.map, marker, infowindow)
    );
    kakao.maps.event.addListener(
      marker,
      "mouseout",
      onOutListener(infowindow)
    );

    marker.setMap(this.map);
    markers.push(marker);
    this.bounds.extend(position);
  }
  
  	/* 마커 세팅 */
	setMarker(address, infos, markers){
		const content = infoToHtml(infos.title, infos.contents);
		markers.push({address, content});
	}
	
	/* 마커 숨기기 */
	hideMarkers(type){
		for(let m of this._setMarkerList(type)){
			m.setMap(null);
		}
	}

	/* 지도 확대하기 */
	setLevel(level){
		this.map.setLevel(level);
	}
	
}

/* 마커의 인포윈도우 작성 */
function infoToHtml(title, infos) {
	let content = `<p>${title}</p>`;

	for (let [key, value] of Object.entries(infos)) {
	content += `<span>${key}: ${value}</span><br/>`;
	}

	return content;
}

/* 마커에 마우스 클릭할 때 이벤트 콜백 함수 */
function onClickListener(map, marker){
	return () => {
		map.setLevel(3);
		map.setCenter(marker.getPosition());
	};
}

/* 마커에 마우스 올릴 때 이벤트 콜백 함수 */
function onOverListener(map, marker, infowindow) {
  return () => {
    infowindow.open(map, marker);
  };
}
/* 마커에 마우스 떠날때 이벤트 콜백 함수 */
function onOutListener(infowindow) {
  return () => {
    infowindow.close();
  };
}
