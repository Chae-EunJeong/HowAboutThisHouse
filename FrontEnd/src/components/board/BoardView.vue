<template>
  <section class="notice" id="detail">
    <div class="notice__container">
      <header class="notice__header">
        <div class="notice__header__title">자유게시판</div>
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
        <span v-if="userInfo.email == board.email">
          <button
            type="button"
            class="notice__info__btn btn__edit"
            @click="moveupdateBoard"
          >
            수정
          </button>
          <button
            class="notice__info__btn btn__delete"
            type="button"
            @click="removeBoard"
          >
            삭제
          </button>
        </span>
      </div>
      <ul class="notice__info">
        <li class="notice__info__title notice__info__item">
          <h3>{{ board.boardTitle }}</h3>
          <p class="info__item__title title__layout">
            {{ board.name }} | 조회 {{ board.viewCnt }} |
            {{ changeDateFormat }} | 댓글 {{ this.count }}
          </p>
        </li>
        <li class="notice__info__item">
          <p class="info__item__contents">{{ board.boardContent }}</p>
        </li>
      </ul>
      <button class="comment__count" @click="foldComments">
        댓글수 {{ this.count }}
      </button>
      <div class="comment__container" v-show="isClickedComment">
        <div class="comment__input">
          <div>
            <textarea
              v-model="comment.content"
              id="content"
              name="content"
              class="form__item__input"
              placeholder="Enter comment"
              rows="2"
              cols="100"
              required
            ></textarea>
          </div>
          <div class="comment__register__btn">
            <button class="" type="button" @click="writeComment()">등록</button>
          </div>
        </div>
        <div v-if="comments.length">
          <comment-list-row
            class="comment__container__item list__row"
            v-for="(comment, index) in comments"
            :key="index"
            v-bind="comment"
          ></comment-list-row>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import moment from "moment";
import CommentListRow from "@/components/board/child/CommentListRow";
import {
  getBoard,
  deleteBoard,
  registerComment,
  getComment,
} from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardView",

  data() {
    return {
      board: {},
      comment: {
        name: "",
        email: "",
        content: "",
        boardNo: "",
      },
      comments: [],
      count: "",
      isClickedComment: true,
    };
  },
  components: { CommentListRow },
  computed: {
    ...mapState(memberStore, ["userInfo"]),

    message() {
      if (this.board.boardContent)
        return this.board.boardContent.split("\n").join("<br>");
      return "";
    },
    changeDateFormat() {
      return moment(new Date(this.board.regDate)).format("YYYY.MM.DD HH:mm");
    },
  },
  created() {
    getBoard(
      this.$route.params.boardNo,
      (response) => {
        this.board = response.data.boardDto;
      },
      (error) => {
        console.log(error);
      }
    );
    getComment(
      this.$route.params.boardNo,
      (response) => {
        this.comments = response.data.list;
        this.count = response.data.count;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveList() {
      this.$router.push({ name: "BoardList" });
    },
    moveupdateBoard() {
      this.$router.replace({
        name: "BoardUpdate",
        params: { boardNo: this.board.boardNo },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    removeBoard() {
      if (confirm("작성된 글을 삭제하시겠습니까?")) {
        deleteBoard(this.board.boardNo, () => {
          this.$router.push({ name: "BoardList" });
        });
      }
    },
    writeComment() {
      if (this.comment.content === "") alert("내용을 입력하세요.");
      else {
        registerComment(
          {
            name: this.userInfo.name,
            email: this.userInfo.email,
            content: this.comment.content,
            boardNo: this.board.boardNo,
          },
          ({ data }) => {
            let msg = "댓글 등록시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "댓글 등록이 완료되었습니다.";
            }
            alert(msg);
            this.viewBoard(this.board.boardNo);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    viewBoard() {
      this.$router.go();
    },
    foldComments() {
      this.isClickedComment = !this.isClickedComment;
    },
  },
};
</script>

<style></style>
