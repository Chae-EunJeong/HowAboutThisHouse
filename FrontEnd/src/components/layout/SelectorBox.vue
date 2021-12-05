<template>
  <form
    id="search"
    method="get"
    class="search__form__container"
    @submit="addInterest"
  >
    <select name="sido" id="sido" v-model="sidoCode" @change="gugunList">
      <option v-for="(sido, index) in sidos" :key="index" :value="sido.value">
        {{ sido.text }}
      </option>
    </select>

    <select name="gugun" id="gugun" v-model="gugunCode" @change="dongList">
      <option
        v-for="(gugun, index) in guguns"
        :key="index"
        :value="gugun.value"
      >
        {{ gugun.text }}
      </option>
    </select>
    <input type="hidden" id="gugunCode" name="gugunCode" />

    <select name="dong" id="dong" v-model="dongCode">
      <option v-for="(dong, index) in dongs" :key="index" :value="dong.value">
        {{ dong.text }}
      </option>
    </select>

    <button type="submit" class="search__form__sub-btn">
      <font-awesome-icon icon="check-square" />
    </button>
  </form>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { getInterestInfo, registerInterest } from "@/api/interest";
const interestStore = "interestStore";
const memberStore = "memberStore";

export default {
  name: "SelectorBox",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      interest: [],
    };
  },
  computed: {
    ...mapState(interestStore, ["sidos", "guguns", "dongs"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(interestStore, ["getSido", "getGugun", "getDong"]),
    ...mapMutations(interestStore, [
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
    addInterest(event) {
      event.preventDefault();
      console.log(this.userInfo.email);
      if (this.dongCode) {
        getInterestInfo(
          this.dongCode,
          (response) => {
            this.interest = response.data.dongcodeDto;
            this.addUserInterest();
          },
          (error) => {
            console.log(error);
          }
        );
        // email:this.userInfo.email
        // dongCode:this.dongCode
        // interestSido:
        // interestGugun:
        // interestDong:
        // registerInterest({});
      }
    },
    addUserInterest() {
      registerInterest(
        {
          email: this.userInfo.email,
          dongCode: this.dongCode,
          interestSido: this.interest.sidoName,
          interestGugun: this.interest.gugunName,
          interestDong: this.interest.dongName,
        },
        ({ data }) => {
          let msg = "관심지역 등록시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "관심지역 등록이 완료되었습니다.";
          }
          alert(msg);
          this.viewInterest();
        },
        (error) => {
          alert("이미 등록된 관심지역입니다.");
          console.log(error);
        }
      );
    },
    viewInterest() {
      this.$router.go();
    },
  },
};
</script>

<style scoped>
@import "~@/assets/styles/selector.css";
</style>
