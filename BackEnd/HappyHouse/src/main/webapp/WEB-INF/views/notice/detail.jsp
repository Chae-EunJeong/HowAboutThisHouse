<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/include/navBar.jsp" %>
<c:if test="${ empty userinfo }">
	<script type="text/javascript">
		alert("로그인 후 이용해주세요.");
		location.href = "${root}/user/login";
	</script>
</c:if>
<c:if test="${ !empty notice }"> 
	<section class="notice" id="detail">
	<div class="notice__container">
    <header class="notice__header">
      <h2>공지사항</h2>
    </header>
    <ul class="notice__info">
      <li class="notice__info__item">
        <span class="info__item__no">${ notice.no }</span>
      </li>
      <li class="notice__info__item">
        <span class="info__item__title">제목</span>
        <p class="info__item__contents">${ notice.title }</p>
      </li>
      <li class="notice__info__item">
        <span class="info__item__title">조회수</span>
        <p class="info__item__contents">${ notice.viewCnt }</p>
      </li>
      <li class="notice__info__item">
        <span class="info__item__title">작성자</span>
        <p class="info__item__contents">
          ${ notice.writerName }
        </p>
      </li>
      <li class="notice__info__item">
        <span class="info__item__title">작성일</span>
        <p class="info__item__contents">${ notice.regDate }</p>
      </li>
      <li class="notice__info__item">
        <span class="info__item__title">내용</span>
        <p class="info__item__contents">
          ${ notice.content }
        </p>
      </li>
    </ul>
    <div class="notice__info__btns">
		<a class="notice__form__submit-btn" href="${root}/notice?act=list">
	      <button type="submit" class="notice__complete__back-btn">
	      	 Back 
	      </button>
      	</a>
  	</div>
    <c:if test="${ userinfo.email eq notice.writerEmail }">
	  <a href="${root}/notice/modify/${notice.no}">
		  <button type="button" class="notice__info__btn btn__edit">
		      Edit 
		  </button>
	  </a>
	  <button class="notice__info__btn btn__delete" type="button">
	    Delete
	  </button>
	</c:if>
   </div>
</section>
<script>
	$("#detail .btn__delete").click(() => {
		if (confirm("해당 글을 삭제하시겠습니까?"))
			location.href = "${root}/notice/delete/${notice.no}";
	});
</script>
</c:if>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>