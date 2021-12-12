<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<script>
	$(document).ready(function () {
		//logout 후 메인 페이지로 이동
		$("#logout").click(() => {
		   confirm("정말 로그아웃 하시겠습니까?") && (location.href = "${root}/user/logout");
	    });
	});
</script>
<nav class="navbar">
    <div class="navbar__logo">
        <i class="fas fa-home"></i>
        <a class="navbar__logo__title" href="${root}/">Happy House</a>
    </div>
    <ul class="navbar__ul navbar__menu">
        <li class="navbar__li__item"><a href="${root}/">홈</a></li>
        <li class="navbar__li__item"><a href="#1">실거래가</a></li>
        <li class="navbar__li__item"><a href="#1">지역정보</a></li>
        <li class="navbar__li__item"><a href="#1">관심목록</a></li>
    </ul>
    <ul class="navbar__ul navbar__account">
        <!--로그인 했을 때-->
        <c:if test="${ !empty userinfo }">
	        <!--관리자 계정일 때-->
	        <c:if test="${ userinfo.auth eq 'S'}">
	        	<li class="navbar__li__item"><a href="#1">회원관리</a></li>
	        </c:if>
	        <li class="navbar__li__item"><a href="${root}/notice/list">공지사항</a></li>
	        <li class="navbar__li__item"><a href="${root}/user/mypage">내 정보</a></li>
	        <li class="navbar__li__item" id="logout"><a href="${root}/user/logout">로그아웃</a></li>
        </c:if>
        <!--로그인 안 했을 때-->
        <c:if test="${ empty userinfo }">
        <li class="navbar__li__item"><a href="${root}/user/login">로그인</a></li>
        <li class="navbar__li__item"><a href="${root}/user/join">회원가입</a></li>
        </c:if>
    </ul>
</nav>