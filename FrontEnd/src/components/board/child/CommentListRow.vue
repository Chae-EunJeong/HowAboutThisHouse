<template>
  <div>
    <p class="comment__item__info">
      <span>{{ name }} | {{ changeDateFormat }}</span>
      <span class="comment__item__btns" v-if="userInfo.email === email"
        ><button class="comment__item__btn" @click="updateCommentForm">
          수정</button
        ><button class="comment__item__btn" @click="removeComment">
          삭제
        </button></span
      >
    </p>
    <p class="comment__item__content">{{ content }}</p>
    <div class="comment__input" v-show="isShow">
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
        <button class="" type="button" @click="updateComment">등록</button>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { modifyComment, deleteComment } from "@/api/board";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "CommentListRow",
  data() {
    return {
      comment: {
        commentNo: "",
        name: "",
        email: "",
        content: "",
        boardNo: "",
      },
      isShow: false,
    };
  },
  props: {
    commentNo: Number,
    email: String,
    name: String,
    content: String,
    boardNo: Number,
    regDate: String,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.comment.content)
        return this.comment.content.split("\n").join("<br>");
      return "";
    },
    changeDateFormat() {
      return moment(new Date(this.regDate)).format("YYYY.MM.DD HH:mm");
    },
  },
  created() {
    this.comment.content = this.content;
  },
  methods: {
    updateCommentForm() {
      this.isShow = true;
    },
    updateComment() {
      console.log(typeof this.boardNo);
      modifyComment(
        {
          commentNo: this.commentNo,
          name: this.name,
          email: this.email,
          content: this.comment.content,
          boardNo: this.boardNo,
        },
        ({ data }) => {
          console.log(data);
          let msg = "댓글 수정시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "댓글 수정이 완료되었습니다.";
          }
          alert(msg);
          this.$router.go();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    removeComment() {
      if (confirm("정말 삭제 하시겠습니까?")) {
        deleteComment(this.commentNo, () => {
          this.$router.go();
        });
        console.log(this.commentNo);
      }
    },
  },
};
</script>

<style></style>
