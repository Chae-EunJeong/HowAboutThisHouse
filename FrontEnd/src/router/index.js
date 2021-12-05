import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";

import Member from "@/views/Member.vue";
import MemberLogin from "@/components/user/MemberLogin.vue";
import MemberJoin from "@/components/user/MemberJoin.vue";
import MemberMyPage from "@/components/user/MemberMyPage.vue";
import MemberUpdate from "@/components/user/MemberUpdate.vue";

import Board from "@/views/Board.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardView from "@/components/board/BoardView.vue";
import BoardUpdate from "@/components/board/BoardUpdate.vue";

import Notice from "@/views/Notice.vue";
import NoticeList from "@/components/notice/NoticeList.vue";
import NoticeWrite from "@/components/notice/NoticeWrite.vue";
import NoticeView from "@/components/notice/NoticeView.vue";
import NoticeUpdate from "@/components/notice/NoticeUpdate.vue";

import Qna from "@/views/Qna.vue";
import QnaList from "@/components/qna/QnaList.vue";
import QnaWrite from "@/components/qna/QnaWrite.vue";
import QnaReplyWrite from "@/components/qna/QnaReplyWrite.vue";
import QnaView from "@/components/qna/QnaView.vue";
import QnaUpdate from "@/components/qna/QnaUpdate.vue";

import House from "@/views/House.vue";

import Interest from "@/views/Interest.vue";
import InterestList from "@/components/interest/InterestList.vue";
// import InterestWrite from "@/components/interest/IntrestWrite.vue";

import Info from "@/views/Info.vue";
import InfoDirection from "@/components/info/InfoDirection.vue";
import InfoSitemap from "@/components/info/InfoSitemap.vue";
import InfoTerms from "@/components/info/InfoTerms.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "SignIn" });
    router.push({ name: "SignIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/user",
    name: "Member",
    component: Member,
    children: [
      {
        path: "signin",
        name: "SignIn",
        component: MemberLogin,
      },
      {
        path: "signup",
        name: "SignUp",
        component: MemberJoin,
      },
      {
        path: "mypage",
        name: "MyPage",
        beforeEnter: onlyAuthUser,
        component: MemberMyPage,
      },
      {
        path: "modify",
        name: "Modify",
        beforeEnter: onlyAuthUser,
        component: MemberUpdate,
      },
    ],
  },
  {
    path: "/board",
    name: "Board",
    component: Board,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "BoardList",
        component: BoardList,
      },
      {
        path: "write",
        name: "BoardWrite",
        component: BoardWrite,
      },
      {
        path: "detail/:boardNo",
        name: "BoardView",
        component: BoardView,
      },
      {
        path: "update/:boardNo",
        name: "BoardUpdate",
        component: BoardUpdate,
      },
    ],
  },
  {
    path: "/notice",
    name: "Notice",
    component: Notice,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "NoticeList",
        component: NoticeList,
      },
      {
        path: "write",
        name: "NoticeWrite",
        component: NoticeWrite,
      },
      {
        path: "detail/:noticeNo",
        name: "NoticeView",
        component: NoticeView,
      },
      {
        path: "update/:noticeNo",
        name: "NoticeUpdate",
        component: NoticeUpdate,
      },
    ],
  },
  {
    path: "/qna",
    name: "Qna",
    component: Qna,
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "QnaList",
        component: QnaList,
      },
      {
        path: "write",
        name: "QnaWrite",
        component: QnaWrite,
      },
      {
        path: "reply/:qnaNo",
        name: "QnaReplyWrite",
        component: QnaReplyWrite,
      },
      {
        path: "detail/:qnaNo",
        name: "QnaView",
        component: QnaView,
      },
      {
        path: "update/:no",
        name: "QnaUpdate",
        component: QnaUpdate,
      },
    ],
  },
  {
    path: "/house",
    name: "House",
    component: House,
  },
  {
    path: "/interest",
    name: "Interest",
    component: Interest,
    redirect: "/interest/list",
    children: [
      {
        path: "list",
        name: "InterestList",
        beforeEnter: onlyAuthUser,
        component: InterestList,
      },
    ],
  },
  {
    path: "/info",
    name: "Info",
    component: Info,
    redirect: "/info/sitemap",
    children: [
      {
        path: "direction",
        name: "InfoDirection",
        component: InfoDirection,
      },
      {
        path: "terms",
        name: "InfoTerms",
        component: InfoTerms,
      },
      {
        path: "sitemap",
        name: "InfoSitemap",
        component: InfoSitemap,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
});

export default router;
