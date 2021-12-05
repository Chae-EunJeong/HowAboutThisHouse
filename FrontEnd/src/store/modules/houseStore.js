import { sidoList, gugunList, dongList, houseList } from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
    isOK: false,
    isOK2: false,
    isOK3: false,
    isOK4: false,
    gugun: null,
    dong: null,
    settingPrice: [],
    settingArea: [],
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
        state.guguns.push({
          value: gugun.gugunCode + " " + gugun.gugunName,
          text: gugun.gugunName,
        });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({
          value: dong.dongCode + " " + dong.dongName,
          text: dong.dongName,
        });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      console.log(houses);
      state.houses = houses.houses;
      state.isOK = !state.isOK;
      state.gugun = houses.gugun;
      state.dong = houses.dong;
      console.log(state);
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
      state.isOK2 = !state.isOK2;
    },
    SET_PRICE: (state, settingPrice) => {
      state.settingPrice = settingPrice;
      state.isOK3 = !state.isOK3;
    },
    SET_AREA: (state, settingArea) => {
      state.settingArea = settingArea;
      state.isOK3 = !state.isOK3;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
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
      console.log(params);
      gugunList(
        params,
        ({ data }) => {
          console.log(data);
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
      console.log(params);

      dongList(
        params,
        ({ data }) => {
          console.log("7777");
          console.log(data);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, gugunCode) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      console.log(gugunCode);

      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;

      const params = {
        pageNo: 1,
        numOfRows: 100,
        LAWD_CD: gugunCode.split(" ")[0],
        DEAL_YMD: "202110",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      houseList(
        params,
        (response) => {
          console.log(response);
          console.log(response.data.response.body.items.item);
          commit("SET_HOUSE_LIST", {
            houses: response.data.response.body.items.item,
            gugun: gugunCode.split(" ")[1],
            dong: gugunCode.split(" ")[2],
          });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    getPrice: ({ commit }, settingPrice) => {
      commit("SET_PRICE", settingPrice);
    },
    getArea: ({ commit }, settingArea) => {
      commit("SET_AREA", settingArea);
    },
  },
};

export default houseStore;
