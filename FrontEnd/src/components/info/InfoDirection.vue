<template>
  <div>
    <section class="notice" id="detail">
      <div class="notice__container">
        <header class="notice__header">
          <div class="notice__header__title">오시는길</div>
        </header>
        <section class="notice__list__main">
          <div id="interestMap"></div>
          <div class="info__content">
            <ul>
              <li class="info__content__title">주소안내</li>
              <li class="info__content__item">
                <font-awesome-icon icon="info-circle" />
                대전광역시 유성구 동서대로 98-39 SSAFY
              </li>
              <li class="info__content__item">
                <font-awesome-icon icon="info-circle" />
                SSAFY 98-39, Dongseo-daero, Yuseong-gu, Daejeon, Republic of
                Korea
              </li>
              <li class="info__content__item">
                <font-awesome-icon icon="info-circle" /> happyhouse@ssafy.net
              </li>
              <li class="info__content__item">
                <font-awesome-icon icon="info-circle" /> 대표전화 : 0603-5555
              </li>
            </ul>
          </div>
        </section>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "InfoDirection",
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    // this.getRealtorList(this.selectDongCode);
  },
  methods: {
    initMap() {
      var mapContainer = document.getElementById("interestMap");
      var mapOption = {
        center: new kakao.maps.LatLng(37.570033541783, 126.98167021854),
        level: 4,
      };
      // // 지도를 생성합니다
      var map = new kakao.maps.Map(mapContainer, mapOption);

      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch(
        " 대전광역시 유성구 덕명동 124",
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
                '<div style="width:150px;text-align:center;padding:6px 0;">이 집 어때</div>',
            });
            infowindow.open(map, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
          }
        }
      );

      var marker = new kakao.maps.Marker({ position: map.getCenter() });
      marker.setMap(map);
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

<style scoped>
#interestMap {
  align-content: center;
  width: 100%;
  height: 500px;
  /* z-index: 1; */
}

.info__content {
  padding: 4rem var(--size-medium);
  display: flex;
  text-align: left;
}
.info__content__title {
  font-weight: 600;
  font-size: var(--font-large);
}
.info__content__item {
  font-size: var(--font-regular);
}
</style>
