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
          <div class="notice__header__title">자유게시판</div>
          <div class="notice__header__container">
            <div class="notice__header__describe">
              <font-awesome-icon icon="exclamation-circle" /> 개인정보를 등록할
              경우 제3자에 의해 피해를 입을 수 있습니다.<br />
              <font-awesome-icon icon="exclamation-circle" /> 답변이 필요하신
              사항에 대해선 고객센터를 이용해주시기 바랍니다.
            </div>
            <div class="notice__header__write">
              <button
                class="notice__header__write-btn"
                type="button"
                @click="moveWrite"
              >
                새 글 작성
              </button>
            </div>
          </div>
        </header>
        <section class="notice__list__main">
          <table class="notice__list">
            <colgroup>
              <col style="width: 90px" />
              <col style="width: *" />
              <col style="width: 90px" />
              <col style="width: 200px" />
              <col style="width: 90px" />
            </colgroup>
            <thead class="notice_list_head">
              <tr>
                <th scope="col">번호</th>
                <th scope="col" class="title">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">등록일</th>
                <th scope="col">조회수</th>
              </tr>
            </thead>
            <!-- <tbody id="contents-list">
				</tbody> -->

            <tbody v-if="boards.length">
              <board-list-row
                class="list__row"
                v-for="(board, index) in boards"
                :key="index"
                v-bind="board"
              />
            </tbody>
            <tbody v-else>
              <tr>
                <td colspan="4">등록된 글이 없습니다.</td>
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
          </div>-->
          <paging-board v-bind:nav="nav" />
        </section>
      </div>
    </section>
  </div>
</template>

<script>
import BoardListRow from "@/components/board/child/BoardListRow";
import { getBoardList } from "@/api/board.js";
import PagingBoard from "@/components/board/child/PagingBoard";

export default {
  name: "BoardList",
  components: {
    BoardListRow,
    PagingBoard,
  },
  data() {
    return {
      boards: [],
      pg: 1,
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
    getBoardList(
      this.$route.params,
      (response) => {
        this.boards = response.data.list;
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
      this.$router.push({ name: "BoardWrite" });
    },
  },
};
</script>

<style scope></style>
