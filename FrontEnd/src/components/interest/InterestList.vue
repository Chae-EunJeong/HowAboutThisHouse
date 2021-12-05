<template>
  <div>
    <section class="interest">
      <div class="interest__container">
        <header class="interest__header">
          <div class="interest__header__title">관심지역</div>
          <div class="interest__header__container">
            <div class="interest__header__describe">
              <span
                ><font-awesome-icon icon="volume-up" /> {{ userInfo.name }}님의
                관심지역을 등록해주세요.<br />
                <font-awesome-icon icon="volume-up" /> 최대 3개까지 등록할 수
                있어요.
              </span>
              <selector-box />
            </div>
            <ul class="interest__header__interest-list" v-if="interests.length">
              <li
                v-for="(interest, index) in interests"
                :key="index"
                class="interest__items"
              >
                <span
                  class="interest__items__name"
                  @click="markingMap(interest)"
                  >{{ interest.interestSido }}
                  {{ interest.interestGugun }}
                  {{ interest.interestDong }}</span
                >
                <font-awesome-icon
                  icon="times"
                  class="interest__items__icon"
                  @click="removeInterest(interest)"
                />
              </li>
            </ul>
          </div>
        </header>
        <section class="interest__list__main">
          <div class="interest__menu__tab">
            <ul id="interest__tab">
              <li class="interest__tab__item active" @click="moveTab('realty')">
                부동산
              </li>
              <li class="interest__tab__item" @click="moveTab('school')">
                초등학교
              </li>
              <li class="interest__tab__item" @click="moveTab('restaurant')">
                근처 맛집
              </li>
              <li class="interest__tab__item" @click="moveTab('subway')">
                지하철역
              </li>
            </ul>
          </div>
          <div id="interestMap"></div>
        </section>
      </div>
    </section>
  </div>
</template>

<script>
import { mapState } from "vuex";
import {
  getInterestList,
  deleteInterest,
  realtorList,
} from "@/api/interest.js";
import SelectorBox from "../layout/SelectorBox.vue";

const memberStore = "memberStore";
// const interestStore = "interestStore";

export default {
  components: { SelectorBox },
  name: "InterestList",
  data() {
    return {
      interests: [],
      map: null,
      markers: [],
      geocoder: null,
      infowindow: null,
      selectDongCode: "1111018000",
      address: [],
      realty: [],
      realtors: [
        {
          address: "서울특별시 동대문구 장한로12길 49 1층 (장안동)",
          realty: "대성공인중개사사무소",
        },
        {
          address: "서울특별시 동대문구 한천로6길 10 (장안동)",
          realty: "대우부동산중개인사무소",
        },
        {
          address: "서울특별시 동대문구 천호대로83길 84 ",
          realty: "대원공인중개사사무소",
        },
        {
          address: "서울특별시 동대문구 장한로6길 43 ",
          realty: "대지공인중개사사무소",
        },
        {
          address: "서울특별시 동대문구 장한로28가길 76 ",
          realty: "대지부동산중개인사무소",
        },
        {
          address: "서울특별시 동대문구 사가정로 225 1층 (장안동)",
          realty: "대한공인중개사사무소",
        },
        {
          address:
            "서울특별시 동대문구 장한로27가길 37 상가 105호 (장안동, 장안태영데시앙)",
          realty: "데시앙 단지내 부동산공인중개사사무소",
        },
        {
          address: "서울특별시 동대문구 천호대로85길 40 ",
          realty: "동광공인중개사사무소",
        },
        {
          address: "서울특별시 동대문구 천호대로85길 40 마동 1층",
          realty: "동광부동산공인중개사사무소",
        },
        {
          address: "서울특별시 동대문구 사가정로 225 1층(장안동)",
          realty: "동대문공인중개사사무소",
        },
        {
          address: "서울특별시 동대문구 장한로31길 20 1층(장안동)",
          realty: "동부공인중개사사무소",
        },
        {
          address: "서울특별시 동대문구 장안벚꽃로 167 주상가동 1층 125호",
          realty: "래미안공인중개사사무소",
        },
      ],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    // ...mapState(interestStore, ["realtors", "address", "realty"]),
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    // this.getRealtorList(this.selectDongCode);
  },
  created() {
    getInterestList(
      this.userInfo.email,
      (response) => {
        this.interests = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
    // this.initMap();
  },
  methods: {
    // ...mapActions(interestStore, ["getRealtorList"]),
    // ...mapMutations(interestStore, ["CLEAR_REALTOR_LIST"]),

    // moveRegisterInterest() {
    //   console.log("test");
    // },
    getRealtorList(dongCode) {
      window.localStorage.clear();
      const SERVICE_KEY = process.env.VUE_APP_RLT_KEY_API_KEY;
      // for (var i = 1; i < 26000; i += 1000) {
      let params = SERVICE_KEY + "/json/landBizInfo/"; // 최대 1000개씩 요청 가능
      params += 1 + "/" + 1000;
      realtorList(
        // console.log(dongCode);
        params,
        (response) => {
          let temp = response.data.landBizInfo.row;
          temp.forEach((data) => {
            if (data.BJDONG_CD === dongCode) {
              var parsedobj1 = JSON.parse(JSON.stringify(data.ADDRESS));
              var parsedobj2 = JSON.parse(JSON.stringify(data.CMP_NM));
              this.address.push(parsedobj1);
              this.realty.push(parsedobj2);
            }
          });
        },
        (error) => {
          console.log(error);
        }
      );
      // }
    },
    removeInterest(interest) {
      deleteInterest(interest.interestNo, () => {
        this.$router.go();
      });
    },
    // 관심지역 클릭시 지도 이동
    markingMap(interest) {
      // this.selectDongCode = interest.dongCode;
      this.address = [];
      this.realty = [];
      this.getRealtorList(interest.dongCode);
      // console.log(this.realtors.realty);
      // this.initMap;
      // this.initMap(this.selectDongCode);
      this.setmarkerMap();
    },
    initMap() {
      var mapContainer = document.getElementById("interestMap");
      var mapOption = {
        center: new kakao.maps.LatLng(37.570033541783, 126.98167021854),
        level: 4,
      };
      // // 지도를 생성합니다
      this.map = new kakao.maps.Map(mapContainer, mapOption);

      // // 주소-좌표 변환 객체를 생성합니다
      // var geocoder = new kakao.maps.services.Geocoder();

      // this.realtors.forEach((item) => {
      //   // 주소로 좌표를 검색합니다
      //   geocoder.addressSearch(item.address, function (result, status) {
      //     // 정상적으로 검색이 완료됐으면
      //     if (status === kakao.maps.services.Status.OK) {
      //       var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

      //       // 결과값으로 받은 위치를 마커로 표시합니다
      //       var marker = new kakao.maps.Marker({
      //         map: map,
      //         position: coords,
      //       });

      //       // 인포윈도우로 장소에 대한 설명을 표시합니다
      //       var infowindow = new kakao.maps.InfoWindow({
      //         content:
      //           '<div style="width:150px;text-align:center;padding:6px 0;">' +
      //           item.realtyName +
      //           "</div>",
      //       });
      //       infowindow.open(map, marker);

      //       // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
      //       map.setCenter(coords);
      //     }
      //   });
      // });

      // var marker = new kakao.maps.Marker({ position: map.getCenter() });
      // marker.setMap(map);
    },
    addScript() {
      const script = document.createElement("script");
      /* global kakao */ script.onload = () => kakao.maps.load(this.initMap);
      script.src = process.env.VUE_APP_MAP_KEY;
      document.head.appendChild(script);
    },
    setmarkerMap() {
      var mapContainer = document.getElementById("interestMap");
      var mapOption = {
        center: new kakao.maps.LatLng(37.570033541783, 126.98167021854),
        level: 4,
      };
      // // 지도를 생성합니다
      var map = new kakao.maps.Map(mapContainer, mapOption);
      var geocoder = new kakao.maps.services.Geocoder();
      this.realtors.forEach((realtor) => {
        console.log(realtor.realty);
        geocoder.addressSearch(realtor.address, function (result, status) {
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
              map: map,
              position: coords,
            });
            // console.log(item.address);
            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
              content: `<div style="width:150px;text-align:center;padding:6px 0;">${realtor.realty}</div>`,
            });
            infowindow.open(map, marker);
            map.setCenter(coords);
          }
        });
      });
    },
    // CSS for clicking interest menu tab
    moveTab() {
      var divElement = document.getElementById("interest__tab");
      for (var i = 0; i < divElement.childElementCount; i++) {
        divElement.childNodes[i].setAttribute("class", "interest__tab__item");
      }
      divElement.addEventListener("click", (e) => {
        var target = e.target;
        target.setAttribute("class", "interest__tab__item active");
      });
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
</style>
