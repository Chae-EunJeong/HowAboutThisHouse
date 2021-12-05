<template>
  <div id="contents-paging" class="paging">
    <ul class="pagination">
      <li class="page-item" @click="settingParams(1)">최신</li>
      <li
        class="page-item"
        :pg="this.navigation.startRange"
        @click="settingParams(pg)"
      >
        이전
      </li>
      <li
        class="page-item"
        v-for="pg in range(this.navigation.startPage, this.navigation.endPage)"
        v-bind:key="pg"
        @click="settingParams(pg)"
      >
        {{ pg }}
      </li>
      <li
        class="page-item"
        :pg="this.navigation.endRange"
        @click="settingParams(pg)"
      >
        다음
      </li>
      <li
        class="page-item"
        :pg="this.navigation.totalPageCount"
        @click="settingParams(this.navigation.totalPageCount)"
      >
        마지막
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "PagingNotice",
  data() {
    return {
      params: { pg: 1, spp: 10, key: null, word: null },
      navigation: {
        startRange: "",
        endRange: "",
        startPage: "",
        endPage: "",
        currentPage: "",
        totalPageCount: "",
      },
      pg: "",
    };
  },
  props: {
    nav: Object,
  },
  created() {
    this.navigation.startRange = this.nav.startRange;
    this.navigation.endRange = this.nav.endRange;
    this.navigation.startPage = this.nav.startPage;
    this.navigation.endPage = this.nav.endPage;
    this.navigation.currentPage = this.nav.currentPage;
    this.navigation.totalPageCount = this.nav.totalPageCount;
    this.navigation = this.nav;
    console.log(this.navigation.currentPage);
  },
  methods: {
    moveListPage(pg) {
      console.log(pg);
      //   this.$router.go({
      //     params: { pg },
      //   });
    },
    range: function (start, end) {
      let list = [];
      for (let i = start; i <= end; i++) list.push(i);
      return list;
    },
    settingParams(pg) {
      this.params.pg = pg;
      console.log("check:", this.params.pg);
      this.moveListPage(this.params.pg);
    },
  },
};
</script>

<style>
.paging {
  padding: var(--size-large);
}
</style>
