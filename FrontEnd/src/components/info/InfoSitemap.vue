<template>
  <div>
    <section class="notice" id="detail">
      <div class="notice__container">
        <header class="notice__header">
          <div class="notice__header__title">사이트맵</div>
        </header>
        <section class="notice__list__main">
          <div class="info__content">
            <ul class="info__content__menu">
              <li class="info__content__title">실거래가</li>
              <li class="info__content__item">
                <font-awesome-icon icon="minus" />
                <router-link :to="{ name: 'House' }" class="link">
                  홈</router-link
                >
              </li>
            </ul>
            <ul>
              <li class="info__content__title">관심지역</li>
              <li class="info__content__item">
                <font-awesome-icon icon="minus" /><router-link
                  :to="{ name: 'Interest' }"
                  class="link"
                >
                  홈</router-link
                >
              </li>
            </ul>
            <ul>
              <li class="info__content__title">커뮤니티</li>
              <li class="info__content__item">
                <font-awesome-icon icon="minus" /><router-link
                  :to="{ name: 'Board' }"
                  class="link"
                >
                  홈</router-link
                >
              </li>
            </ul>
            <ul>
              <li class="info__content__title">고객센터</li>
              <li class="info__content__item">
                <font-awesome-icon icon="minus" /><router-link
                  :to="{ name: 'Notice' }"
                  class="link"
                >
                  공지사항</router-link
                >
              </li>
              <li class="info__content__item">
                <font-awesome-icon icon="minus" />
                <router-link :to="{ name: 'Qna' }" class="link">
                  FAQ</router-link
                >
              </li>
            </ul>
            <ul>
              <li class="info__content__title">기타</li>
              <li class="info__content__item">
                <font-awesome-icon icon="minus" />
                <router-link :to="{ name: 'SignIn' }" class="link">
                  회원가입</router-link
                >
              </li>
            </ul>
          </div>
        </section>
      </div>
    </section>
  </div>
</template>

<script>
export default { name: "InfoDirection" };
</script>

<style scoped>
.info__content {
  padding: 4rem var(--size-small);
  display: flex;
  text-align: left;
  justify-content: space-between;
}
.info__content__title {
  font-weight: 600;
  font-size: var(--font-large);
}
.info__content__item:hover {
  cursor: pointer;
  /* text-decoration: none; */
}
.link {
  font-size: var(--font-regular);
  color: var(--font-black);
  text-decoration: none;
}
</style>
