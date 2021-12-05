<template>
  <div>
    <div id="interestMap"></div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const interestStore = "interestStore";

export default {
  name: "Interest",
  props: {
    interestDongCode: String,
  },
  data() {
    return {
      map: null,
      markers: [],
      //   geocoder: null,
      //   infowindow: null,

      //   markerImage: null,
      //   markerPosition: null,

      // 카테고리
      // markers2: [],
      // currCategory: null,
      // ps: null,
    };
  },
  computed: {
    ...mapState(interestStore, ["realtors"]),
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    // this.getRealtorList();
    console.log(this.interestDongCode);
  },
  methods: {
    ...mapActions(interestStore, ["getRealtorList"]),

    initMap() {
      var mapContainer = document.getElementById("interestMap");
      var mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };
      var map = new kakao.maps.Map(mapContainer, mapOption);

      // ****************************************************마킹 반복문
      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch(
        "서울특별시 동대문구 천호대로89길 24",
        function (result, status) {
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
              map: map,
              position: coords,
            });

            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
              content:
                '<div style="width:150px;text-align:center;padding:6px 0;">한경공인중개사사무소</div>',
            });
            infowindow.open(map, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
          }
        }
      );
      // ****************************************************여기까지

      //   var marker = new kakao.maps.Marker({ position: map.getCenter() });
      //   marker.setMap(map);
    },
    addScript() {
      const script = document.createElement("script");
      /* global kakao */ script.onload = () => kakao.maps.load(this.initMap);
      script.src = process.env.VUE_APP_MAP_KEY;
      document.head.appendChild(script);
    },
  },
};
</script>

<style>
/* @import "~@/assets/styles/interest.css"; */

#interestMap {
  align-content: center;
  width: 100%;
  height: 500px;
  /* z-index: 1; */
}
</style>
