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
          <div class="notice__header__title">공지사항</div>
          <div class="notice__header__container">
            <!--관리자만 글 작성 가능 -->
            <div class="notice__header__write" v-if="userInfo.auth == 'S'">
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
              <col style="width: 100px" />
              <col style="width: *" />
              <col style="width: 200px" />
            </colgroup>
            <thead class="notice_list_head">
              <tr>
                <th scope="col">번호</th>
                <th scope="col" class="title">제목</th>
                <th scope="col" colspan="2">등록일</th>
              </tr>
            </thead>
            <!-- <tbody id="contents-list">
				</tbody> -->

            <tbody v-if="notices.length">
              <notice-list-row
                class="list__row"
                v-for="(notice, index) in notices"
                :key="index"
                v-bind="notice"
              />
            </tbody>
            <tbody v-else>
              <tr>
                <td colspan="4">등록된 내용이 없습니다.</td>
              </tr>
            </tbody>
          </table>
          <!-- 검색 박스 
          <div class="notice__header__selector">
            <form id="searchform">
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
                value=""
              />
              <button
                type="button"
                class="notice__header__search-btn btn__search"
              >
                검색
              </button>
            </form>
          </div>-->
          <!-- 페이징 -->
          <paging-notice v-bind:nav="nav" />
        </section>
      </div>
    </section>
  </div>
</template>

<script>
import NoticeListRow from "@/components/notice/child/NoticeListRow";
import PagingNotice from "@/components/notice/child/PagingNotice";
import { getNoticeList } from "@/api/notice.js";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeList",
  components: {
    NoticeListRow,
    PagingNotice,
  },
  data() {
    return {
      notices: [],
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
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    getNoticeList(
      this.$route.params,
      (response) => {
        this.notices = response.data.list;
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
      this.$router.push({ name: "NoticeWrite" });
    },
  },
};
</script>

<style scope></style>
