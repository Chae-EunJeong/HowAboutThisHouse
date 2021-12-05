<template>
  <section class="notice" id="write">
    <div class="notice__container">
      <header class="notice__header">
        <div class="notice__header__title" v-if="this.type === 'register'">
          질문하기
        </div>
        <div class="notice__header__title" v-else-if="this.type === 'reply'">
          답변하기
        </div>
        <div class="notice__header__title" v-else>질문 수정</div>
      </header>
      <form id="registerForm" class="notice__form" @submit="onSubmit">
        <div class="notice__form__item">
          <label for="title">제목</label>
          <input
            type="text"
            v-model="qna.qnaTitle"
            id="title"
            name="title"
            class="form__item__input"
            placeholder="Enter title"
            required
          />
        </div>
        <div class="notice__form__item">
          <label for="writerName">작성자</label>
          <input
            type="text"
            v-model="qna.name"
            id="writerName"
            name="writerName"
            class="form__item__input"
            readonly
          />
        </div>
        <div class="notice__form__item">
          <label for="content">내용</label>
          <textarea
            v-model="qna.qnaContent"
            id="content"
            name="content"
            class="form__item__input"
            placeholder="Enter content"
            rows="10"
            cols="100"
            required
          ></textarea>
        </div>
        <div class="notice__form__item" v-if="this.type === 'reply'">
          <label for="content">답변</label>
          <textarea
            v-model="reply"
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
          v-if="this.type === 'register' || 'reply'"
        >
          확인
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
import { getQna, registerQna, registerQnaReply, modifyQna } from "@/api/qna";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "qnaWriteForm",
  data() {
    return {
      qna: {
        qnaNo: 0,
        name: "",
        email: "",
        qnaTitle: "",
        qnaContent: "",
        originNo: "",
        groupOrd: "",
      },
      reply: "",
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
    this.qna.name = this.userInfo.name;
    this.qna.email = this.userInfo.email;
    if (this.type === "modify") {
      getQna(
        this.$route.params.qnaNo,
        ({ data }) => {
          this.qna = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isEmail = true;
    }
    if (this.type === "reply") {
      getQna(
        this.$route.params.qnaNo,
        ({ data }) => {
          this.qna = data;
          this.qna.name = this.userInfo.name;
          this.qna.email = this.userInfo.email;
          this.qna.originNo = this.$route.params.qnaNo;
          this.qna.groupOrd = 1;
        },
        (error) => {
          console.log(error);
        }
      );
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.qna.name &&
        ((msg = "작성자를 입력해주세요"),
        (err = false),
        this.$refs.name.focus());
      err &&
        !this.qna.qnaTitle &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.qnaTitle.focus());
      err &&
        !this.qna.qnaContent &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.qnaContent.focus());

      if (!err) alert(msg);
      else
        this.type === "register"
          ? this.writeQna()
          : this.type === "reply"
          ? this.writeQnaReply()
          : this.updateQna();
    },
    onReset(event) {
      event.preventDefault();
      this.qna.qnaNo = 0;
      this.qna.qnaTitle = "";
      this.qna.qnaContent = "";
      this.$router.push({ name: "QnaList" });
    },
    writeQna() {
      registerQna(
        {
          email: this.qna.email,
          name: this.qna.name,
          qnaTitle: this.qna.qnaTitle,
          qnaContent: this.qna.qnaContent,
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
    writeQnaReply() {
      registerQnaReply(
        {
          email: this.qna.email,
          name: this.qna.name,
          qnaTitle: "[답변] " + this.qna.qnaTitle,
          qnaContent: this.reply,
          originNo: this.qna.originNo,
          groupOrd: this.qna.groupOrd,
        },
        ({ data }) => {
          let msg = "답변 등록시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "답변 등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateQna() {
      modifyQna(
        {
          qnaNo: this.qna.qnaNo,
          email: this.qna.email,
          name: this.qna.name,
          qnaTitle: this.qna.qnaTitle,
          qnaContent: this.qna.qnaContent,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "QnaList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "QnaList" });
    },
  },
};
</script>

<style></style>
