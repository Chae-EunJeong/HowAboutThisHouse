<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/include/navBar.jsp" %>
<c:if test="${ !empty notice }"> 
	<section class="notice" id="write">
	  <div class="notice__container">
	    <header class="notice__header">
	      <h2>공지사항</h2>
	    </header>
	   		<div>
	    		<c:if test="${ userinfo.email eq notice.writerEmail }">
    			  <button class="notice__info__btn btn__edit" type="button">
			        <a href="${root}/notice?act=mvmodify&no=${notice.no}"> Edit </a>
			      </button>
			      <button class="notice__info__btn btn__delete" type="button">
			        Delete
			      </button>
	    		</c:if>
	    	</div>
		      <div class="notice__form__item">
		        <label for="title">제목</label>
		        <span>${ notice.title }</span>
		      </div>
		      <div class="notice__form__item">
		        <label for="name">작성자</label>
		        <span>${ notice.writerName }</span>
		      </div>
		      <div class="notice__form__item">
		        <label for="regDate">작성일</label>
		        <span>${ notice.regDate }</span>
		      </div>
		      <div class="notice__form__item">
		        <label for="viewCnt">조회수</label>
		        <span>${ notice.viewCnt }</span>
		      </div>
		      <div class="notice__form__item">
		        <div>
		        	${ notice.content }
		        </div>
		      </div>
		      <button class="notice__form__submit-btn" type="button">
		      	<a href="${root}/notice?act=list"> 목록으로 이동 </a>
		      </button>
	  </div>
	</section>
</c:if>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>