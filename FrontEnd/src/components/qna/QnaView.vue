<template>
  <section class="notice" id="detail">
    <div class="notice__container">
      <header class="notice__header">
        <div class="notice__header__title">고객센터</div>
      </header>
      <div class="notice__btns">
        <span class="notice__info__btns">
          <button
            type="button"
            @click="moveList"
            class="notice__form__submit-btn notice__info__btns"
          >
            목록
          </button>
        </span>
        <span>
          <button
            v-if="userInfo.email == qna.email"
            type="button"
            class="notice__info__btn btn__edit"
            @click="moveUpdateQna"
          >
            수정
          </button>
          <button
            v-if="userInfo.email == qna.email"
            class="notice__info__btn btn__delete"
            type="button"
            @click="removeQna"
          >
            삭제
          </button>
          <button
            v-if="this.userInfo.auth == 'S'"
            class="notice__info__btn notice__form__submit-btn"
            type="button"
            @click="moveWriteReply"
          >
            답변
          </button>
        </span>
      </div>
      <ul class="notice__info">
        <li class="notice__info__title notice__info__item">
          <h3>{{ qna.qnaTitle }}</h3>
          <p class="info__item__title">
            {{ qna.name }} | {{ changeDateFormat }}
          </p>
        </li>
        <li class="notice__info__item">
          <p class="info__item__contents">{{ qna.qnaContent }}</p>
        </li>
      </ul>
    </div>
  </section>
</template>

<script>
import moment from "moment";
import { getQna, deleteQna } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  data() {
    return {
      qna: {},
      isShow: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),

    message() {
      if (this.qna.qnaContent)
        return this.qna.qnaContent.split("\n").join("<br>");
      return "";
    },
    changeDateFormat() {
      return moment(new Date(this.qna.regDate)).format("YY.MM.DD HH:mm");
    },
  },
  created() {
    getQna(
      this.$route.params.qnaNo,
      (response) => {
        this.qna = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveList() {
      this.$router.push({ name: "QnaList" });
    },
    moveUpdateQna() {
      this.$router.replace({
        name: "QnaUpdate",
        params: { qnaNo: this.qna.qnaNo },
      });
      //   this.$router.push({ path: `/qna/modify/${this.article.articleno}` });
    },
    removeQna() {
      if (confirm("작성된 글을 삭제하시겠습니까?")) {
        deleteQna(this.qna.qnaNo, () => {
          this.$router.push({ name: "QnaList" });
        });
      }
    },
    moveWriteReply() {
      this.$router.push({ name: "QnaReplyWrite" });
    },
  },
};
</script>

<style></style>
