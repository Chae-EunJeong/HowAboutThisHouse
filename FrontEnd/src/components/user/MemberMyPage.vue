<template>
  <section class="accounts" id="mypage">
    <div class="accounts__container">
      <header class="accounts__header">
        <div class="accounts__header__title">내 정보</div>
      </header>
      <ul class="accounts__info" data-email="${ userinfo.email }">
        <li class="accounts__info__item">
          <span class="info__item__title">이메일</span>
          <p class="info__item__contents">{{ userInfo.email }}</p>
        </li>
        <li class="accounts__info__item">
          <span class="info__item__title">이름</span>
          <p class="info__item__contents">{{ userInfo.name }}</p>
        </li>
        <li class="accounts__info__item">
          <span class="info__item__title">주소</span>
          <p class="info__item__contents">
            {{ userInfo.address }}
          </p>
        </li>
      </ul>
      <div class="accounts__info__btns">
        <button
          class="accounts__info__btn btn__edit"
          type="button"
          @click="moveModifyUser"
        >
          수정
        </button>
        <button
          class="accounts__info__btn btn__delete"
          type="button"
          @click="withdrawalUser"
        >
          탈퇴
        </button>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
  },
  methods: {
    ...mapActions(memberStore, ["deleteUser"]),
    moveModifyUser() {
      this.$router.push({ name: "Modify" });
    },
    updateMember() {
      this.updateUser(this.user);
      console.log("vue,", this.userInfo);
      this.$router.push({ name: "MyPage" });
    },
    withdrawalUser() {
      console.log("userinfo", this.userInfo.email);
      if (confirm("탈퇴시 복구할 수 없습니다.\n정말 탈퇴하시겠습니까?")) {
        this.deleteUser(this.userInfo.email);
        this.$router.push({ name: "Home" });
      }
    },
  },
};
</script>

<style></style>
