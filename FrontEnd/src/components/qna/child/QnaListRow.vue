<template>
  <tr class="list__tr" @click="viewQna(qnaNo)">
    <td>{{ this.qna.qnaNo }}</td>
    <td class="list__tr__title">{{ this.qna.qnaTitle }}</td>
    <td>{{ this.qna.name }}</td>
    <td>{{ changeDateFormat }}</td>
  </tr>
</template>

<script>
import moment from "moment";
import { getQnaInfo } from "@/api/qna";

export default {
  name: "QnaListRow",
  data() {
    return {
      qna: "",
    };
  },
  props: {
    qnaNo: Number,
    email: String,
    name: String,
    qnaTitle: String,
    viewCnt: Number,
    regDate: String,
  },
  created() {
    getQnaInfo(
      this.qnaNo,
      (response) => {
        this.qna = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },

  computed: {
    changeDateFormat() {
      return moment(new Date(this.qna.regDate)).format("YY.MM.DD HH:mm");
    },
  },
  methods: {
    viewQna(qnaNo) {
      this.$router.push({
        name: "QnaView",
        params: { qnaNo },
      });
    },
  },
};
</script>

<style></style>
