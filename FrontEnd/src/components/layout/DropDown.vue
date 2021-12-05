<template>
  <DxDropDownButton
    class="dropdown"
    :text="userName"
    icon="user"
    :items="actions"
    key-expr="id"
    @item-click="logAction"
  />
</template>

<script>
import "devextreme/dist/css/dx.light.css";
import { mapState, mapMutations } from "vuex";
import DxDropDownButton from "devextreme-vue/drop-down-button";

const memberStore = "memberStore";

export default {
  components: {
    DxDropDownButton,
  },
  data() {
    return {
      userName: "",
      actions: [
        { id: 1, text: "내 정보", icon: "user" },
        { id: 2, text: "공지사항", icon: "group" },
        { id: 3, text: "고객센터", icon: "email" },
        { id: 4, text: "로그아웃", icon: "runner" },
      ],
    };
  },
  created() {
    this.userName = this.userInfo.name + " 님";
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    logAction(e) {
      if (e.itemData.text === "로그아웃") {
        if (confirm("로그아웃 하시겠습니까?")) {
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          sessionStorage.removeItem("access-token");
          if (this.$route.path != "/") this.$router.push({ name: "Home" });
        }
      }
      if (e.itemData.text === "내 정보") {
        this.$router.push({ name: "MyPage" });
      }
      if (e.itemData.text === "공지사항") {
        this.$router.push({ name: "Notice", params: 1 });
      }
      if (e.itemData.text === "고객센터") {
        this.$router.push({ name: "Qna" });
      }
    },
  },
};
</script>

<style>
.dropdown .dx-icon-user {
  color: var(--color-white);
}
</style>
