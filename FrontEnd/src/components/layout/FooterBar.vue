<template>
  <footer class="footer">
    <ul class="footer__menu footer__item">
      <li>
        <router-link :to="{ name: 'InfoDirection' }" class="link"
          >오시는길</router-link
        >
      </li>
      <li>
        <router-link :to="{ name: 'InfoTerms' }" class="link"
          >이용약관</router-link
        >
      </li>
      <li>
        <router-link :to="{ name: 'Qna' }" class="link">고객센터</router-link>
      </li>
      <li>
        <router-link :to="{ name: 'InfoSitemap' }" class="link"
          >사이트맵</router-link
        >
      </li>
    </ul>
    <ul class="footer__info footer__item">
      <li>사업장 : 대전광역시 유성구 덕명동 124</li>
      <li>대표메일 : happyhouse@ssafy.net</li>
      <li>문의 : happyhouse@ssafy.net</li>
      <li>고객센터 : 0603-5555</li>
    </ul>
    <p class="footer__copyright footer__item">
      Copyright &copy; 주식회사 해피하우스 All right reserved.
    </p>
  </footer>
</template>

<script>
export default {
  name: "FooterBar",
};
</script>

<style scoped>
.footer {
  flex-direction: column;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 12px;
  background-color: var(--color-main);
  height: 10rem;
}

.footer a {
  text-decoration: none;
  color: var(--color-light-grey);
}

.footer__menu {
  display: flex;
  list-style: none;
  padding-left: 0;
}

.footer__menu li {
  padding: 8px 12px;
  font-size: larger;
}

.footer__info {
  list-style: none;
  display: flex;
  padding-left: 0;
}

.footer__info li {
  padding: 8px 12px;
  color: var(--color-light-grey);
  font-size: var(--font-small);
}

.footer__info li:after {
  margin-left: 14px;
  content: "|";
}

.footer__info li:last-child::after {
  content: "";
}

.footer p {
  color: var(--color-light-grey);
  font-size: var(--font-small);
}
</style>
