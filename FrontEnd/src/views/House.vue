<template>
  <b-container fluid class="text-center">
    <b-row>
      <b-col sm="12">
        <house-search-bar></house-search-bar>
      </b-col>
    </b-row>

    <b-row>
      <b-col sm="3">
        <b-row>
          <b-col cols="12">
            <house-list />
          </b-col>
        </b-row>
      </b-col>

      <b-col sm="9">
        <div id="housedetail">
          <span
            onclick="this.parentElement.style.display='none'"
            class="houseclose"
            >X</span
          >
          <house-detail />
        </div>
        <house-map></house-map>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import HouseMap from "@/components/house/HouseMap.vue";

import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "House",
  data() {
    return {
      showF: false,
    };
  },
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
    HouseMap,
  },
  computed: {
    ...mapState(houseStore, ["isOK2"]),
  },
  watch: {
    isOK2: function () {
      document.getElementById("housedetail").style.display = "block";
    },
  },
  methods: {
    sizeFilter() {
      this.showF = !this.showF;
    },
  },
};
</script>
<style scoped>
#housedetail {
  position: absolute;
  z-index: 2;
  width: 20%;
  background-color: rgba(255, 255, 255, 0.733);
  display: none;
}
.houseclose {
  float: right;
  cursor: pointer;
}
.house__header__btn {
  margin-top: 20px;
  margin-bottom: 0;
  padding: var(--size-small);
  border-radius: var(--size-micro);
  color: var(--color-white);
  background-color: var(--color-main-point);
}
.showfilter {
  position: absolute;
  z-index: 2;
}
</style>
