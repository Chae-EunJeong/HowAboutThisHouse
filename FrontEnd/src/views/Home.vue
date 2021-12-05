<template>
  <div>
    <section class="main__section main__cover">
      <img src="@/assets/home.jpeg" />
      <div class="main__cover__contents">
        <div class="main__header__comment">실거래가를 바로 검색해보세요.</div>
        <div class="main__header__move">
          <input
            type="text"
            placeholder="찾는 아파트명을 입력해주세요"
            size="40"
            class="main__header__input"
          />
          <font-awesome-icon icon="search" class="main__header__icon" />
        </div>
      </div>
    </section>

    <!--main service introduction-->
    <section class="main__section main__contents">
      <header class="main__contents__intro">
        <div>Our Services</div>
      </header>
      <ul class="main__service">
        <li class="main__service__card">
          <img src="@/assets/house3.jpg" class="card__img" />
          <p class="card__descript card__title">실거래가 검색</p>
          <p class="card__descript card__contents">
            원하는 지역의 동별, 아파트별 <br />실거래가를 검색해보세요.
          </p>
          <button class="card__descript card__btn" @click="moveHouse">
            자세히 보기
          </button>
        </li>
        <li class="main__service__card">
          <img src="@/assets/house1.jpg" class="card__img" />
          <p class="card__descript card__title">관심 지역 조회</p>
          <p class="card__descript card__contents">
            관심지역을 설정하고 <br />주변 정보를 살펴보세요.
          </p>
          <button class="card__descript card__btn" @click="moveInterest">
            자세히 보기
          </button>
        </li>
        <li class="main__service__card">
          <img src="@/assets/house2.jpg" class="card__img" />
          <p class="card__descript card__title">부동산 뉴스 보러가기</p>
          <p class="card__descript card__contents">
            최근 부동산 관련 뉴스를 <br />바로 보러 가세요.
          </p>
          <button
            class="card__descript card__btn"
            onClick="location.href = 'https://land.naver.com/'"
          >
            자세히 보기
          </button>
        </li>
      </ul>
    </section>
  </div>
</template>

<script>
export default {
  name: "Main",
  methods: {
    moveHouse() {
      this.$router.push({ name: "House" });
    },
    moveInterest() {
      this.$router.push({ name: "Interest" });
    },
  },
};
</script>

<style scoped>
@import "~@/assets/styles/main.css";
</style>
