import jwt_decode from "jwt-decode";
import { login, modifyMember, withdrawal } from "@/api/member.js";
import { findById } from "../../api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.email,
        (response) => {
          if (response.data.message === "success") {
            console.log(response.data.userInfo);
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateUser({ commit }, user) {
      modifyMember(user, (response) => {
        if (response.data === "success") {
          commit("SET_USER_INFO", user);
        } else {
          console.log("사용자 정보 수정 실패");
        }
      });
    },
    deleteUser({ commit }, email) {
      withdrawal(email, (response) => {
        if (response.data === "success") {
          commit("SET_IS_LOGIN", false);
          commit("SET_USER_INFO", null);
          sessionStorage.removeItem("access-token");
        } else {
          console.log("사용자 탈퇴 실패");
        }
      });
    },
  },
};

export default memberStore;
