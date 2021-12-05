<template>
  <nav class="navbar">
    <!-- <div class="navbar__logo"> -->
    <img
      src="@/assets/final_main.jpg"
      class="navbar__logo__img"
      @click="moveHome"
    />
    <ul class="navbar__ul navbar__menu">
      <li class="navbar__li__item">
        <router-link :to="{ name: 'Home' }" class="link">홈</router-link>
      </li>
      <li class="navbar__li__item">
        <router-link :to="{ name: 'House' }" class="link">실거래가</router-link>
      </li>
      <li class="navbar__li__item">
        <router-link :to="{ name: 'Interest' }" class="link"
          >관심지역</router-link
        >
      </li>
      <li class="navbar__li__item">
        <router-link :to="{ name: 'Board' }" class="link">커뮤니티</router-link>
      </li>
    </ul>
    <ul class="navbar__ul navbar__account" v-if="userInfo">
      <li class="navbar__li__item navbar__icon">
        <dx-drop-down-button></dx-drop-down-button>
      </li>
    </ul>
    <ul class="navbar__ul navbar__account" v-else>
      <!--로그인 안 했을 때-->
      <li class="navbar__li__item">
        <router-link :to="{ name: 'SignIn' }" class="link">로그인</router-link>
      </li>
      <li class="navbar__li__item">
        <router-link :to="{ name: 'SignUp' }" class="link"
          >회원가입</router-link
        >
      </li>
    </ul>

    <!-- <a href="#" class="navbar__toggleBtn" @click="toggle"
      ><font-awesome-icon icon="bars"
    /></a> -->
  </nav>
</template>

<script>
import { mapState, mapMutations } from "vuex";
// import DropDown from "./DropDown.vue";
import DxDropDownButton from "./DropDown.vue";

const memberStore = "memberStore";

export default {
  name: "NavBar",
  components: {
    // DropDown,
    DxDropDownButton,
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
    moveHome() {
      this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style scoped>
@import "~@/assets/styles/navbar.css";

a:hover {
  text-decoration: none;
  color: var(--color-white);
}
</style>
