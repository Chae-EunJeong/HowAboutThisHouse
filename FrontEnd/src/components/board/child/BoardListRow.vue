<template>
  <tr class="list__tr" @click="viewBoard(boardNo)">
    <td>{{ this.board.boardNo }}</td>
    <td class="list__tr__title">
      {{ this.board.boardTitle }}
      <span class="list__tr__title__commentCnt" v-if="this.commentCnt !== 0">{{
        this.commentCnt
      }}</span>
    </td>
    <!-- 2021.11.09 형식으로 출력하기 -->
    <td>{{ this.board.name }}</td>
    <td>{{ changeDateFormat }}</td>
    <td>{{ this.board.viewCnt }}</td>
  </tr>
</template>

<script>
import moment from "moment";
import { getBoardInfo } from "@/api/board";

export default {
  name: "BoardListRow",
  data() {
    return {
      board: "",
      commentCnt: "",
    };
  },
  props: {
    boardNo: Number,
    email: String,
    name: String,
    boardTitle: String,
    boardContent: String,
    viewCnt: Number,
    regDate: String,
  },
  created() {
    getBoardInfo(
      this.boardNo,
      (response) => {
        this.board = response.data.boardDto;
        this.commentCnt = response.data.count;
      },
      (error) => {
        console.log(error);
      }
    );
  },

  computed: {
    changeDateFormat() {
      return moment(new Date(this.board.regDate)).format("YYYY.MM.DD HH:mm");
    },
  },
  methods: {
    viewBoard(boardNo) {
      console.log(boardNo);
      this.$router.push({
        name: "BoardView",
        params: { boardNo },
      });
    },
  },
};
</script>

<style></style>
