<template>
  <section class="notice" id="write">
    <div class="notice__container">
      <header class="notice__header">
        <h2 v-if="this.type === 'register'">새 글 작성</h2>
        <h2 v-else>글 수정</h2>
      </header>
      <form id="registerForm" class="notice__form" @submit="onSubmit">
        <div class="notice__form__item">
          <label for="title">제목</label>
          <input
            type="text"
            v-model="notice.noticeTitle"
            id="title"
            name="title"
            class="form__item__input"
            placeholder="Enter title"
            required
          />
        </div>
        <div class="notice__form__item">
          <label for="content">내용</label>
          <textarea
            v-model="notice.noticeContent"
            id="content"
            name="content"
            class="form__item__input"
            placeholder="Enter content"
            rows="10"
            cols="100"
            required
          ></textarea>
        </div>
        <button
          class="notice__form__submit-btn"
          type="submit"
          id="regBtn"
          v-if="this.type === 'register'"
        >
          등록
        </button>
        <button
          class="notice__form__submit-btn"
          type="submit"
          id="modBtn"
          v-else
        >
          수정
        </button>
      </form>
    </div>
  </section>
</template>

<script>
import { getNotice, registerNotice, modifyNotice } from "@/api/notice";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "NoticeWriteForm",
  data() {
    return {
      notice: {
        noticeNo: 0,
        name: "",
        email: "",
        notice: "",
        noticeContent: "",
      },
      isEmail: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.notice.name = this.userInfo.name;
    this.notice.email = this.userInfo.email;
    if (this.type === "modify") {
      getNotice(
        this.$route.params.noticeNo,
        ({ data }) => {
          this.notice = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isEmail = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";

      !this.notice.noticeTitle &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.noticeTitle.focus());
      err &&
        !this.notice.noticeContent &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.noticeContent.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.writeNotice() : this.updateNotice();
    },
    onReset(event) {
      event.preventDefault();
      this.notice.noticeNo = 0;
      this.notice.noticeTitle = "";
      this.notice.noticeContent = "";
      this.$router.push({ name: "NoticeList" });
    },
    writeNotice() {
      registerNotice(
        {
          email: this.notice.email,
          name: this.notice.name,
          noticeTitle: this.notice.noticeTitle,
          noticeContent: this.notice.noticeContent,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateNotice() {
      modifyNotice(
        {
          noticeNo: this.notice.noticeNo,
          email: this.notice.email,
          name: this.notice.name,
          noticeTitle: this.notice.noticeTitle,
          noticeContent: this.notice.noticeContent,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "NoticeList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "NoticeList" });
    },
  },
};
</script>

<style></style>
