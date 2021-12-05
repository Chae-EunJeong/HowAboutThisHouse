<template>
  <b-container fluid v-if="house" class="housecontainer1">
    <b-row class="detailtest">
      <strong>{{ gugun }} {{ dong }}</strong>
    </b-row>
    <b-row class="mb-2 mt-3">
      <b-col
        ><b-img :src="require('@/assets/ㅇㅈㅇㄸ.png')" fluid-grow></b-img
      ></b-col>
    </b-row>
    <b-row class="detailtest">
      <p>거래가 가능한 집이에요 :D</p>
    </b-row>
    <b-row class="detailtest">
      <h5 style="color: black; font-weight: bolder">
        매매
        {{ numberToKorean(parseInt(house.거래금액.replace(",", "")) * 10000) }}
      </h5>
    </b-row>
    <hr />
    <b-row>
      <b-col>
        전용면적
        <h5 style="color: rgb(6, 151, 199); font-weight: bolder">
          {{ house.전용면적.toFixed(0) }}m<sup>2</sup>
        </h5>
      </b-col>
      <b-col>
        층 <br />
        <h5 style="color: rgb(6, 151, 199); font-weight: bolder">
          {{ house.층 }}층
        </h5>
      </b-col>
    </b-row>
    <hr />

    <div>
      <b>매물 정보 </b>
      <b-button v-b-toggle="'collapse-a'"
        ><b-icon icon="chevron-down" font-scale="0.5"></b-icon
      ></b-button>
      <b-collapse visible id="collapse-a">
        <b-row>
          <b-col cols="5">아파트명</b-col>
          <b-col cols="7">{{ house.아파트 }}</b-col>
        </b-row>
        <hr />
        <b-row>
          <b-col cols="5">준공날짜</b-col>
          <b-col cols="7"
            >{{ house.건축년도 }}-{{ house.월 }}-{{ house.일 }}</b-col
          >
        </b-row>
        <hr />
        <b-row>
          <b-col cols="5">주소</b-col>
          <b-col cols="7"
            >{{ gugun }} {{ dong }}
            {{ house.도로명건물본번호코드.replace(/(^0+)/, "") }}</b-col
          >
        </b-row>
        <hr />
        <b-row>
          <b-col cols="5">입주가능일</b-col>
          <b-col cols="7">즉시 입주</b-col>
        </b-row>
        <hr />
        <b-row>
          <b-col cols="5">전용 면적</b-col>
          <b-col cols="7">{{ house.전용면적.toFixed(0) }}m<sup>2</sup></b-col>
        </b-row>
        <hr />
        <b-row>
          <b-col cols="5">층 수</b-col>
          <b-col cols="7">{{ house.층 }}층</b-col>
        </b-row>
        <hr />
        <b-row>
          <b-col cols="5">일련번호</b-col>
          <b-col cols="7">{{ house.일련번호 }}</b-col>
        </b-row>
      </b-collapse>
    </div>
    <hr />
    <div>
      <b>로드뷰 </b>
      <b-button v-b-toggle="'collapse-b'"
        ><b-icon icon="chevron-down" font-scale="0.5"></b-icon
      ></b-button>
      <b-collapse visible id="collapse-b">
        <div id="roadview" style="width: 100%; height: 300px"></div>
      </b-collapse>
    </div>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house", "gugun", "dong"]),
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  watch: {
    house: function () {
      this.initRoadView();
    },
  },
  methods: {
    initRoadView() {
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new window.kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new window.kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
      var geocoder = new window.kakao.maps.services.Geocoder();
      geocoder.addressSearch(
        this.gugun +
          " " +
          this.house.도로명 +
          " " +
          this.house.도로명건물본번호코드 +
          " " +
          this.house.도로명건물부번호코드,
        function (result, status) {
          if (status === window.kakao.maps.services.Status.OK) {
            var position = new window.kakao.maps.LatLng(
              result[0].y,
              result[0].x
            );
            // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
            roadviewClient.getNearestPanoId(position, 130, function (panoId) {
              roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
            });
          } else {
            console.log("로드뷰 없음.");
          }
        }
      );
    },
    numberToKorean(number) {
      var inputNumber = number < 0 ? false : number;
      var unitWords = ["", "만", "억", "조", "경"];
      var splitUnit = 10000;
      var splitCount = unitWords.length;
      var resultArray = [];
      var resultString = "";

      for (var i = 0; i < splitCount; i++) {
        var unitResult =
          (inputNumber % Math.pow(splitUnit, i + 1)) / Math.pow(splitUnit, i);
        unitResult = Math.floor(unitResult);
        if (unitResult > 0) {
          resultArray[i] = unitResult;
        }
      }

      for (i = 0; i < resultArray.length; i++) {
        if (!resultArray[i]) continue;
        resultString = String(resultArray[i]) + unitWords[i] + resultString;
      }

      return resultString;
    },
  },
};
</script>

<style>
@import "~@/assets/styles/house.css";
</style>
