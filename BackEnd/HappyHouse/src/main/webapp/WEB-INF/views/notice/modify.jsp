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
<c:if test="${ !empty userinfo }"> 
	<section class="notice" id="modify">
	  <div class="notice__container">
	    <header class="notice__header">
	      <h2>글 수정</h2>
	    </header>
	    <form id="modForm" class="notice__form" method="post" action="">
		      <div class="notice__form__item">
		        <label for="no">번호</label>
		        <input
		          type="text"
		          id="no"
		          name="no"
		          class="form__item__input"
		          value="${ notice.no }"
		          readonly
		        />
		      </div>
		      <div class="notice__form__item">
		        <label for="title">제목</label>
		        <input
		          type="text"
		          id="title"
		          name="title"
		          class="form__item__input"
		          value="${ notice.title }"
		          required
		        />
		      </div>
		      <div class="notice__form__item">
		        <label for="writerName">작성자</label>
		        <input
		          type="text"
		          id="writerName"
		          name="writerName"
		          class="form__item__input"
		          value="${ notice.writerName }"
		          readonly
		        />
		      </div>
		      <div class="notice__form__item">
		        <textarea
		          id="content"
		          name="content"
		          class="form__item__input"
		          rows="10" cols="100"
		          required
		        >${ notice.content }</textarea>
		      </div>
		      <button class="notice__form__submit-btn" type="button" id="modBtn">Modify</button>
	    </form>
	  </div>
	</section>
</c:if>
<script>
	$('#modBtn').click(function () {
		$('#modForm').attr("action", "${root}/notice/modify").submit();
	});
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>