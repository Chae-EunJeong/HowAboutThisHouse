<template>
  <b-row
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
    class="underscore"
  >
    <b-col cols="5" class="text-center align-self-center">
      <b-img
        thumbnail
        :src="require('@/assets/ㅇㅈㅇㄸ.png')"
        alt="Image 1"
      ></b-img>
    </b-col>
    <b-col cols="7" class="align-self-center">
      <b-row>
        <h5 style="color: black; font-weight: bolder">
          매매
          {{
            numberToKorean(parseInt(house.거래금액.replace(",", "")) * 10000)
          }}
        </h5> </b-row
      ><br />
      <b-row>
        {{ house.전용면적 }}m<sup style="top: 0">2</sup> &nbsp;·
        {{ house.층 }}층</b-row
      >
      <b-row> {{ gugun }} {{ dong }} </b-row>
    </b-col>
  </b-row>
</template>

<script>
import { mapActions, mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  computed: {
    ...mapState(houseStore, ["dong", "gugun"]),
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
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
