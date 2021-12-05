<template>
  <section class="accounts" id="edit">
    <div class="accounts__container">
      <header class="accounts__header">
        <div class="accounts__header__title">정보 수정</div>
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
            value="user.email"
            readonly
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
          @click="updateMember"
        >
          수정하기
        </button>
      </form>
    </div>
  </section>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import { getMember } from "@/api/member";
const memberStore = "memberStore";

export default {
  name: "MemberUpdate",
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
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapMutations(memberStore, ["SET_USER_INFO"]),
  },
  created() {
    (this.user.email = this.userInfo.email),
      (this.user.password = this.userInfo.password),
      (this.user.name = this.userInfo.name),
      (this.user.address = this.userInfo.address),
      getMember(
        this.$route.params.email,
        ({ data }) => {
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
  },
  methods: {
    ...mapActions(memberStore, ["updateUser"]),
    updateMember() {
      this.updateUser(this.user);
      console.log("vue,", this.userInfo);
      this.$router.push({ name: "MyPage" });
    },
  },
};
</script>

<style></style>
