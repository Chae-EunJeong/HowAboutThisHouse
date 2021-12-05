<template>
  <section class="accounts" id="login">
    <div class="accounts__container">
      <header class="accounts__header">
        <div class="accounts__header__title">로그인</div>
      </header>
      <form class="accounts__form">
        <div class="accounts__form__item">
          <label for="email">이메일</label>
          <input
            type="email"
            v-model="user.email"
            id="email"
            name="email"
            class="form__item__input"
            placeholder="Enter your email"
            required
            @keyup.enter="confirm"
          />
        </div>
        <div class="accounts__form__item">
          <label for="password">비밀번호</label>
          <input
            type="password"
            v-model="user.password"
            id="password"
            name="password"
            class="form__item__input"
            placeholder="Enter your password"
            required
            @keyup.enter="confirm"
          />
        </div>
        <div class="accounts__form__item">
          <label for="idsave">
            <input
              class="form-check-input"
              type="checkbox"
              id="idsave"
              name="idsave"
              value="saveok"
            />
            아이디저장
          </label>
        </div>
        <!-- <div class="accounts__login__result">${ msg }</div> -->
        <button
          class="accounts__form__submit-btn"
          type="button"
          @click="confirm"
        >
          로그인
        </button>
      </form>
      <div>
        <button>
          <a class="accounts__link" href="#"> Forgot your password? </a>
        </button>
      </div>

      <div>
        Don't have an account?
        <button class="accounts__link" @click="movePage">Sign up</button>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        email: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "Home" });
      }
    },
    movePage() {
      this.$router.push({ name: "SignUp" });
    },
  },
};
</script>

<style></style>
