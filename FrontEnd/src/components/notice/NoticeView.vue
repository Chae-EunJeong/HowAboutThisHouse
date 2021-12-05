<template>
  <section class="notice" id="detail">
    <div class="notice__container">
      <header class="notice__header">
        <div class="notice__header__title">공지사항</div>
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
        <span v-if="userInfo.email == notice.email">
          <button
            type="button"
            class="notice__info__btn btn__edit"
            @click="moveUpdateNotice"
          >
            수정
          </button>
          <button
            class="notice__info__btn btn__delete"
            type="button"
            @click="removeNotice"
          >
            삭제
          </button>
        </span>
      </div>
      <ul class="notice__info">
        <li class="notice__info__title notice__info__item">
          <h3>{{ notice.noticeTitle }}</h3>
          <p class="info__item__title">
            {{ notice.name }} | {{ changeDateFormat }}
          </p>
        </li>
        <li class="notice__info__item">
          <p class="info__item__contents">{{ notice.noticeContent }}</p>
        </li>
      </ul>
    </div>
  </section>
</template>

<script>
import moment from "moment";
import { getNotice, deleteNotice } from "@/api/notice";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  data() {
    return {
      notice: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),

    message() {
      if (this.notice.noticeContent)
        return this.notice.noticeContent.split("\n").join("<br>");
      return "";
    },
    changeDateFormat() {
      return moment(new Date(this.notice.regDate)).format("YYYY.MM.DD hh:mm");
    },
  },
  created() {
    getNotice(
      this.$route.params.noticeNo,
      (response) => {
        this.notice = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveList() {
      this.$router.push({ name: "NoticeList" });
    },
    moveUpdateNotice() {
      this.$router.replace({
        name: "NoticeUpdate",
        params: { noticeNo: this.notice.noticeNo },
      });
      //   this.$router.push({ path: `/notice/modify/${this.article.articleno}` });
    },
    removeNotice() {
      if (confirm("작성된 글을 삭제하시겠습니까?")) {
        deleteNotice(this.notice.noticeNo, () => {
          this.$router.push({ name: "NoticeList" });
        });
      }
    },
  },
};
</script>

<style></style>
