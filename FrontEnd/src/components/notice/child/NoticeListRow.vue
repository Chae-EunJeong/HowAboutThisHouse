<template>
  <tr class="list__tr" @click="viewNotice(noticeNo)">
    <td>{{ this.notice.noticeNo }}</td>
    <td class="list__tr__title">{{ this.notice.noticeTitle }}</td>
    <!-- 2021.11.09 형식으로 출력하기 -->
    <td colspan="2">{{ changeDateFormat }}</td>
  </tr>
</template>

<script>
import moment from "moment";
import { getNoticeInfo } from "@/api/notice";

export default {
  name: "NoticeListRow",
  data() {
    return {
      notice: "",
    };
  },
  props: {
    noticeNo: Number,
    email: String,
    name: String,
    noticeTitle: String,
    noticeContent: String,
    regDate: String,
  },
  created() {
    getNoticeInfo(
      this.noticeNo,
      (response) => {
        this.notice = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },

  computed: {
    changeDateFormat() {
      return moment(new Date(this.notice.regDate)).format("YYYY.MM.DD HH:mm");
    },
  },
  methods: {
    viewNotice(noticeNo) {
      console.log(noticeNo);
      this.$router.push({
        name: "NoticeView",
        params: { noticeNo },
      });
    },
  },
};
</script>

<style></style>
