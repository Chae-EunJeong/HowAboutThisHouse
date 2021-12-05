<template>
  <b-container
    fluid
    v-if="housesOne && housesOne.length != 0"
    class="housecontainer"
  >
    <house-list-row
      v-for="(house, index) in housesOne"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="housecontainer">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListRow from "@/components/house/HouseListRow.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, [
      "houses",
      "dong",
      "gugun",
      "dongCode",
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
};
</script>

<style>
@import "~@/assets/styles/house.css";
</style>
