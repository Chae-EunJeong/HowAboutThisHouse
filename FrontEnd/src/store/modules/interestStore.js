import { sidoList, gugunList, dongList, realtorList } from "@/api/interest.js";

const interestStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "도/광역시" }],
    guguns: [{ value: null, text: "시/군/구" }],
    dongs: [{ value: null, text: "동" }],
    address: [],
    realty: [],
    rows: [],
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_REALTOR_LIST: (state, data) => {
      // console.log("주소: ", data.ADDRESS);
      // console.log("상호명:", data.CMP_NM);
      var parsedobj1 = JSON.parse(JSON.stringify(data.ADDRESS));
      // console.log(parsedobj1);
      var parsedobj2 = JSON.parse(JSON.stringify(data.CMP_NM));
      // console.log(parsedobj2);
      state.address.push(parsedobj1);
      state.realty.push(parsedobj2);
      // state.realtors.push(rows);
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "도/광역시" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "시/군/구" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동" }];
    },
    CLEAR_REALTOR_LIST: (state) => {
      state.address = [];
      state.realty = [];
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getRealtorList: ({ commit }, dongCode) => {
      window.localStorage.clear();
      const SERVICE_KEY = process.env.VUE_APP_RLT_KEY_API_KEY;
      // for (var i = 1; i < 26000; i += 1000) {
      let params = SERVICE_KEY + "/json/landBizInfo/"; // 최대 1000개씩 요청 가능
      params += 1 + "/" + 1000;
      realtorList(
        // console.log(dongCode);
        params,
        (response) => {
          let temp = response.data.landBizInfo.row;
          temp.forEach((data) => {
            if (data.BJDONG_CD === dongCode) {
              commit("SET_REALTOR_LIST", data);
            }
          });
        },
        (error) => {
          console.log(error);
        }
      );
      // }
    },
  },
};

export default interestStore;
