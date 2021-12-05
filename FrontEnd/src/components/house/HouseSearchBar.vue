<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col>
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col>
      <b-form-select
        v-model="gugunCode"
        :options="guguns"
        @change="dongList"
      ></b-form-select>
    </b-col>
    <b-col>
      <b-form-select
        v-model="dongCode"
        :options="dongs"
        @change="searchApt"
        item-text="text"
        item-value="dongName"
        return-object
      ></b-form-select>
    </b-col>
    <b-col sm="4">
      <b-row>
        <b-col sm="2">
          <button class="house__header__btn" type="button" @click="priceFilter">
            가격 <b-icon icon="arrow-down-short" aria-hidden="true"></b-icon>
          </button>
        </b-col>
        <b-col sm="2">
          <button class="house__header__btn" type="button" @click="sizeFilter">
            면적 <b-icon icon="arrow-down-short" aria-hidden="true"></b-icon>
          </button>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <div class="showfilter" v-if="showF">
            <div id="pricebar">
              <div class="pricebar-content">
                <vue-range-slider
                  v-model="settingPrice"
                  :min="minPrice"
                  :max="maxPrice"
                  :enable-cross="enableCrossP"
                  :formatter="formatterP"
                  :tooltip-merge="tooltipMergeP"
                ></vue-range-slider>
                <button @click="setPrice">적용</button>
              </div>
            </div>
          </div>
        </b-col>
        <b-col>
          <div class="showfilter2" v-if="showS">
            <div id="pricebar">
              <div class="pricebar-content">
                <vue-range-slider
                  v-model="settingArea"
                  :min="minArea"
                  :max="maxArea"
                  :enable-cross="enableCrossA"
                  :formatter="formatterA"
                  :tooltip-merge="tooltipMergeA"
                ></vue-range-slider>
                <button @click="setArea">적용</button>
              </div>
            </div>
          </div>
        </b-col>
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import VueRangeSlider from "vue-range-component";
import "vue-range-component/dist/vue-range-slider.css";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  components: { VueRangeSlider },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      gugunName: null,
      dongCode: null,
      dongName: null,
      dongSplit: null,
      showF: false,
      showS: false,
      settingPrice: [0, 4999999999],
      settingArea: [0, 200],
    };
  },

  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
  },
  created() {
    this.minPrice = 0;
    this.maxPrice = 4999999999;
    this.enableCrossP = false;
    this.tooltipMergeP = false;
    this.formatterP = (value) => this.numberToKorean(value) + `원`;
    this.minArea = 0;
    this.maxArea = 200;
    this.enableCrossA = false;
    this.tooltipMergeA = false;
    this.formatterA = (value) => `${value}m2`;
    this.CLEAR_SIDO_LIST();
    this.getSido();
    this.setPrice();
    this.setArea();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getPrice",
      "getArea",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.gugunCode = null;
      this.dongCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode.split(" ")[0])
        this.getDong(this.gugunCode.split(" ")[0]);
    },
    searchApt() {
      this.dongSplit = this.dongCode.split(" ");
      if (this.gugunCode.split(" ")[0])
        this.getHouseList(
          this.gugunCode.split(" ")[0] +
            " " +
            this.gugunCode.split(" ")[1] +
            " " +
            this.dongCode.split(" ")[1]
        );
    },
    priceFilter() {
      this.showF = !this.showF;
    },
    sizeFilter() {
      this.showS = !this.showS;
    },
    setPrice() {
      this.getPrice(this.settingPrice);
    },
    setArea() {
      this.getArea(this.settingArea);
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
