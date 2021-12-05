<template>
  <div class="map_wrap">
    <div id="map">
      <ul id="category">
        <li id="BK9" data-order="0">
          <span class="category_bg bank"></span>
          은행
        </li>
        <li id="MT1" data-order="1">
          <span class="category_bg mart"></span>
          마트
        </li>
        <li id="PM9" data-order="2">
          <span class="category_bg pharmacy"></span>
          약국
        </li>
        <li id="OL7" data-order="3">
          <span class="category_bg oil"></span>
          주유소
        </li>
        <li id="CE7" data-order="4">
          <span class="category_bg cafe"></span>
          카페
        </li>
        <li id="CS2" data-order="5">
          <span class="category_bg store"></span>
          편의점
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseMap",
  data() {
    return {
      map: null,
      markers: [],
      geocoder: null,
      infowindow: [],
      markerImage: null,
      markerPosition: null,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "isOK",
      "isOK2",
      "isOK3",
      "isOK4",
      "gugun",
      "dong",
      "house",
      "settingPrice",
      "settingArea",
    ]),
    housesOne: function () {
      return this.houses.filter(
        (i) =>
          i.법정동.trim() == this.dong &&
          parseInt(i.거래금액.trim().replace(",", "")) * 10000 >=
            this.settingPrice[0] &&
          parseInt(i.거래금액.trim().replace(",", "")) * 10000 <=
            this.settingPrice[1] &&
          parseInt(i.전용면적) >= this.settingArea[0] &&
          parseInt(i.전용면적) <= this.settingArea[1]
      );
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      console.log(this.isOK);
      this.initMap();
      this.setAptsOnMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.initMap);
      script.src = process.env.VUE_APP_MAP_KEY;
      document.head.appendChild(script);
    }
  },
  watch: {
    isOK: function () {
      this.setAptsOnMap();
    },
    isOK2: function () {
      this.setAptsOnMap();
    },
    isOK3: function () {
      this.setAptsOnMap();
    },
    isOK4: function () {
      this.setAptsOnMap();
    },
  },
  methods: {
    initMap() {
      var mapContainer = document.getElementById("map");
      var mapOption = {
        center: new window.kakao.maps.LatLng(36.3549798, 127.2982882),
        level: 5,
      };

      this.map = new kakao.maps.Map(mapContainer, mapOption);

      var imageSrc = require("@/assets/marker.png"),
        imageSize = new kakao.maps.Size(30, 50),
        imageOption = { offset: new kakao.maps.Point(27, 69) };

      (this.markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      )),
        (this.markerPosition = new kakao.maps.LatLng(36.3549798, 127.2982882));

      if (this.house) {
        this.setAptsOnMap();
      }
      // var markerPosition = new kakao.maps.LatLng(33.450701, 126.570667);

      // ////////////////////// 카테고리 해보기//////////////////
      var placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 }),
        contentNode = document.createElement("div"), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        markers2 = [], // 마커를 담을 배열입니다
        currCategory = "";

      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places(this.map);

      // this.map이 안돼서.... 화살표 함수가 안돼서.. 이렇게라도...
      let self = this;

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, "idle", searchPlaces);
      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      addEventHandle(contentNode, "mousedown", kakao.maps.event.preventMap);
      addEventHandle(contentNode, "touchstart", kakao.maps.event.preventMap);

      // 커스텀 오버레이 컨텐츠를 설정합니다
      placeOverlay.setContent(contentNode);

      // 각 카테고리에 클릭 이벤트를 등록합니다
      addCategoryClickEvent();

      // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
      function addEventHandle(target, type, callback) {
        if (target.addEventListener) {
          target.addEventListener(type, callback);
        } else {
          target.attachEvent("on" + type, callback);
        }
      }

      // 카테고리 검색을 요청하는 함수입니다
      function searchPlaces() {
        if (!currCategory) {
          return;
        }

        // 커스텀 오버레이를 숨깁니다
        placeOverlay.setMap(null);

        // 지도에 표시되고 있는 마커를 제거합니다
        removeMarker();

        ps.categorySearch(currCategory, placesSearchCB, {
          useMapBounds: true,
        });
      }

      // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
      function placesSearchCB(data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {
          // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
          displayPlaces(data);
          console.log(pagination);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
          // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
          alert("검색 결과가 없습니다.");
        } else if (status === kakao.maps.services.Status.ERROR) {
          // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
          alert("error");
        }
      }

      // 지도에 마커를 표출하는 함수입니다
      function displayPlaces(places) {
        // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
        // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
        var order = document
          .getElementById(currCategory)
          .getAttribute("data-order");
        for (var i = 0; i < places.length; i++) {
          // 마커를 생성하고 지도에 표시합니다
          var marker2 = addMarker(
            new kakao.maps.LatLng(places[i].y, places[i].x),
            order
          );

          // 마커와 검색결과 항목을 클릭 했을 때
          // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
          (function (marker2, place) {
            kakao.maps.event.addListener(marker2, "click", () => {
              displayPlaceInfo(place);
            });
          })(marker2, places[i]);
        }
      }

      // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
      function addMarker(position2, order) {
        var imageSrc2 =
            "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
          imageSize2 = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
          imgOptions2 = {
            spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
            spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
          },
          markerImage2 = new kakao.maps.MarkerImage(
            imageSrc2,
            imageSize2,
            imgOptions2
          ),
          marker2 = new kakao.maps.Marker({
            position: position2, // 마커의 위치
            image: markerImage2,
          });

        marker2.setMap(self.map); // 지도 위에 마커를 표출합니다
        markers2.push(marker2); // 배열에 생성된 마커를 추가합니다
        return marker2;
      }

      // 지도 위에 표시되고 있는 마커를 모두 제거합니다
      function removeMarker() {
        for (var i = 0; i < markers2.length; i++) {
          markers2[i].setMap(null);
        }
        markers2 = [];
      }

      // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
      function displayPlaceInfo(place) {
        var content =
          '<div class="placeinfo">' +
          '   <a class="title" href="' +
          place.place_url +
          '" target="_blank" title="' +
          place.place_name +
          '">' +
          place.place_name +
          "</a>";

        if (place.road_address_name) {
          content +=
            '    <span title="' +
            place.road_address_name +
            '">' +
            place.road_address_name +
            "</span>" +
            '  <span class="jibun" title="' +
            place.address_name +
            '">(지번 : ' +
            place.address_name +
            ")</span>";
        } else {
          content +=
            '    <span title="' +
            place.address_name +
            '">' +
            place.address_name +
            "</span>";
        }

        content +=
          '    <span class="tel">' +
          place.phone +
          "</span>" +
          "</div>" +
          '<div class="after"></div>';

        contentNode.innerHTML = content;
        placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        placeOverlay.setMap(self.map);
      }

      // 각 카테고리에 클릭 이벤트를 등록합니다
      function addCategoryClickEvent() {
        var category = document.getElementById("category"),
          children = category.children;

        for (var i = 0; i < children.length; i++) {
          children[i].onclick = onClickCategory;
        }
      }

      // 카테고리를 클릭했을 때 호출되는 함수입니다
      function onClickCategory() {
        var id = this.id,
          className = this.className;

        placeOverlay.setMap(null);

        if (className === "on") {
          currCategory = "";
          changeCategoryClass();
          removeMarker();
        } else {
          currCategory = id;
          changeCategoryClass(this);
          searchPlaces();
        }
      }

      // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
      function changeCategoryClass(el) {
        var category = document.getElementById("category"),
          children = category.children,
          i;

        for (i = 0; i < children.length; i++) {
          children[i].className = "";
        }

        if (el) {
          el.className = "on";
        }
      }

      ////////////////////////////////////////////////////////////

      var marker = new kakao.maps.Marker({
        position: this.markerPosition,
        image: this.markerImage, // 마커이미지 설정
      });
      marker.setMap(this.map);
    },

    setAptsOnMap() {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      if (this.infowindow.length > 0) {
        this.infowindow.close();
      }

      this.markers = [];
      this.geocoder = new window.kakao.maps.services.Geocoder();

      this.housesOne.forEach((item) => {
        let aptAddress =
          item.도로명 +
          " " +
          item.도로명건물본번호코드 +
          " " +
          item.도로명건물부번호코드;

        this.geocoder.addressSearch(aptAddress, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
              map: this.map,
              position: coords,
              title: item.아파트,
              clickable: true,
              image: this.markerImage, // 마커이미지 설정
            });

            var contentTemp =
              '<div class="wrap">' +
              '    <div class="info">' +
              '        <div class="title">' +
              item.아파트 +
              "        </div>" +
              '        <div class="body">' +
              '            <div class="img">' +
              '                <img src="http://localhost:8080/img/%E3%85%87%E3%85%88%E3%85%87%E3%84%B8.34a93518.png" width="73" height="70">' +
              "           </div>" +
              '            <div class="desc">' +
              '                <div class="ellipsis">' +
              this.gugun +
              " " +
              item.도로명 +
              " " +
              item.도로명건물본번호코드.replace(/(^0+)/, "") +
              "                </div>" +
              '                <div class="jibun ellipsis">(지번)' +
              item.법정동 +
              " " +
              item.법정동본번코드 +
              "-" +
              item.법정동부번코드 +
              "</div>" +
              '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' +
              "            </div>" +
              "        </div>" +
              "    </div>" +
              "</div>";

            // 인포윈도우로 장소에 대한 설명을 표시
            this.infowindow = new kakao.maps.InfoWindow({
              content: contentTemp,
              position: coords,
              removable: true,
            });

            kakao.maps.event.addListener(
              marker,
              "mouseover",
              this.makeOverListener(this.map, marker, this.infowindow)
            );
            kakao.maps.event.addListener(
              marker,
              "mouseout",
              this.makeOutListener(this.infowindow)
            );

            this.markers.push(marker);

            if (this.house.아파트 == item.아파트) {
              this.infowindow.open(this.map, marker); //인포윈도우 열기
              // this.makeOverListener(this.map, marker, this.infowindow);
              // this.map.setCenter(marker);
              this.map.panTo(coords);
            } else {
              // 지도의 중심을 결과값으로 받은 위치로 이동
              this.infowindow.close();
              this.map.panTo(this.coords);
              // this.map.setCenter(marker);
            }
          }
        });
      });
    },
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },
    // 인포윈도우를 닫는 클로저를 만드는 함수
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },
  },
};
</script>

<style>
@import "~@/assets/styles/house.css";
</style>
