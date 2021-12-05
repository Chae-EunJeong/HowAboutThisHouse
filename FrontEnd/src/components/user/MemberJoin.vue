<template>
  <section class="accounts" id="signup">
    <div class="accounts__container">
      <header class="accounts__header">
        <h2>회원가입</h2>
      </header>
      <form class="accounts__form" method="post" action="">
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
          />
        </div>
        <div id="emailCheck" class="accounts__emailCheck__result hide"></div>
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
          />
        </div>
        <div class="accounts__form__item">
          <label for="password2">비밀번호 확인</label>
          <input
            type="password"
            id="password2"
            name="password2"
            class="form__item__input"
            placeholder="Confirm your password"
            required
          />
        </div>
        <div class="accounts__form__item">
          <label for="name">이름</label>
          <input
            type="text"
            v-model="user.name"
            id="name"
            name="name"
            class="form__item__input"
            placeholder="Enter your name"
            required
          />
        </div>
        <div class="accounts__form__item">
          <label for="address">주소</label>
          <input
            type="text"
            v-model="user.address"
            id="address"
            name="address"
            class="form__item__input"
            placeholder="Enter your address"
            required
          />
        </div>
        <button
          class="accounts__form__submit-btn"
          type="button"
          @click="signUpUser"
        >
          가입
        </button>
      </form>
    </div>
  </section>
</template>

<script>
import { join } from "@/api/member";

export default {
  name: "MemberJoin",
  data() {
    return {
      user: {
        email: "",
        password: "",
        name: "",
        address: "",
      },
    };
  },
  methods: {
    signUpUser() {
      join(
        {
          email: this.user.email,
          password: this.user.password,
          name: this.user.name,
          address: this.user.address,
        },
        ({ data }) => {
          let msg = "회원 가입시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "회원 가입이 완료되었습니다.";
          }
          alert(msg);
          this.movePage();
        },
        (error) => {
          alert("이미 가입된 이메일입니다.");
          console.log(error);
        }
      );
    },
    movePage() {
      this.$router.push({ name: "SignIn" });
    },
  },
};
</script>

<style></style>
