<template>
  <div id="app">
    <nav-bar />
    <router-view />
    <footer-bar />
  </div>
</template>

<script>
//import NaviBar from "./components/layout/NaviBar.vue";
import NavBar from "./components/layout/NavBar.vue";
import FooterBar from "./components/layout/FooterBar.vue";

export default {
  name: "App",

  components: {
    NavBar,
    FooterBar,
  },
};
</script>

<style>
@import "~@/assets/styles/common.css";
@import "~@/assets/styles/reset.css";
* {
  font-family: var(--font-style);
}
.pagination {
  color: var(--color-grey);
  display: flex;
  justify-content: center;
  padding-left: 30%;
  margin: 0 auto;
}

.page-item {
  display: flex;
  list-style: none;
  padding: 8px 12px;
  border: 1px solid var(--color-light-grey);
}

.page-item:hover {
  background-color: var(--color-shadow);
}
</style>
