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
            v-model="board.boardTitle"
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
            v-model="board.name"
            id="writerName"
            name="writerName"
            class="form__item__input"
            readonly
          />
        </div>
        <div class="notice__form__item">
          <label for="content">내용</label>
          <textarea
            v-model="board.boardContent"
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
import { getBoard, registerBoard, modifyBoard } from "@/api/board";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "BoardWriteForm",
  data() {
    return {
      board: {
        boardNo: 0,
        name: "",
        email: "",
        boardTitle: "",
        boardContent: "",
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
    this.board.name = this.userInfo.name;
    this.board.email = this.userInfo.email;
    if (this.type === "modify") {
      getBoard(
        this.$route.params.boardNo,
        ({ data }) => {
          this.board = data.boardDto;
          console.log(this.board.boardContent);
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
      !this.board.name &&
        ((msg = "작성자를 입력해주세요"),
        (err = false),
        this.$refs.name.focus());
      err &&
        !this.board.boardTitle &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.boardTitle.focus());
      err &&
        !this.board.boardContent &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.boardContent.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.writeBoard() : this.updateBoard();
    },
    onReset(event) {
      event.preventDefault();
      this.board.boardNo = 0;
      this.board.boardTitle = "";
      this.board.boardContent = "";
      this.$router.push({ name: "BoardList" });
    },
    writeBoard() {
      registerBoard(
        {
          email: this.board.email,
          name: this.board.name,
          boardTitle: this.board.boardTitle,
          boardContent: this.board.boardContent,
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
    updateBoard() {
      modifyBoard(
        {
          boardNo: this.board.boardNo,
          email: this.board.email,
          name: this.board.name,
          boardTitle: this.board.boardTitle,
          boardContent: this.board.boardContent,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "BoardList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "BoardList" });
    },
  },
};
</script>

<style></style>
