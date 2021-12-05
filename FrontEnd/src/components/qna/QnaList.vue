<template>
  <div>
    <!-- <form name="pageform" id="pageform" method="GET" action="">
      <input type="hidden" name="pg" id="pg" value="" />
      <input type="hidden" name="key" id="key" value="${key}" />
      <input type="hidden" name="word" id="word" value="${word}" />
    </form> -->
    <section class="notice" id="detail">
      <div class="notice__container">
        <header class="notice__header">
          <div class="notice__header__title">고객센터</div>
          <div class="notice__header__write">
            <button
              class="notice__header__write-btn"
              type="button"
              @click="moveWrite"
            >
              문의하기
            </button>
          </div>
        </header>
        <section class="notice__list__main">
          <table class="notice__list">
            <colgroup>
              <col style="width: 100px" />
              <col style="width: *" />
              <col style="width: 100px" />
              <col style="width: 200px" />
            </colgroup>
            <thead class="notice_list_head">
              <tr>
                <th scope="col">번호</th>
                <th scope="col" class="title">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">등록일</th>
              </tr>
            </thead>
            <!-- <tbody id="contents-list">
				</tbody> -->

            <tbody v-if="qnas.length">
              <qna-list-row
                class="list__row"
                v-for="(qna, index) in qnas"
                :key="index"
                v-bind="qna"
              />
            </tbody>
            <tbody v-else>
              <tr>
                <td colspan="4">등록된 문의가 없습니다.</td>
              </tr>
            </tbody>
          </table>
          <!-- 검색 박스 
          <div class="notice__header__selector">
            <form id="searchform">
              <input type="hidden" name="pg" value="1" />
              <select name="key">
                <option value="title" class="notice__select__item">제목</option>
                <option value="content" class="notice__select__item">
                  내용
                </option>
              </select>

              <input
                type="text"
                name="word"
                id="word"
                class="form__item__input item_search"
                value="${word}"
              />
              <button
                type="button"
                class="notice__header__search-btn btn__search"
              >
                검색
              </button>
            </form>
          </div> -->
          <paging-qna v-bind:nav="nav" />
        </section>
      </div>
    </section>
  </div>
</template>

<script>
import QnaListRow from "@/components/qna/child/QnaListRow";
import { getQnaList } from "@/api/qna.js";
import PagingQna from "@/components/qna/child/PagingQna";

export default {
  name: "QnaList",
  components: {
    QnaListRow,
    PagingQna,
  },
  data() {
    return {
      qnas: [],
      pg: 1,
      notices: [],
      navigation: [],
      nav: {
        startRange: "",
        endRange: "",
        startPage: "",
        endPage: "",
        currentPage: "",
        totalPageCount: "",
      },
    };
  },
  created() {
    // let param = {
    //   pg: 1,
    //   spp: 20,
    //   key: null,
    //   word: null,
    // };
    getQnaList(
      this.$route.params,
      (response) => {
        this.qnas = response.data.list;
        this.navigation = response.data.pageNavigation;
        this.nav.startRange = this.navigation.mynavigator.startRange;
        this.nav.endRange = this.navigation.mynavigator.endRange;
        this.nav.startPage = this.navigation.mynavigator.startPage;
        this.nav.endPage = this.navigation.mynavigator.endPage;
        this.nav.currentPage = this.navigation.mynavigator.currentPage;
        this.nav.totalPageCount = this.navigation.mynavigator.totalPageCount;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "QnaWrite" });
    },
  },
};
</script>

<style scope></style>
