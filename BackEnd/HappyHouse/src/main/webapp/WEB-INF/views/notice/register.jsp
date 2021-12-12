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
	<section class="notice" id="write">
	  <div class="notice__container">
	    <header class="notice__header">
	      <h2>새 글 작성</h2>
	    </header>
	    <form id="registerForm" class="notice__form" method="post" action="">
		      <div class="notice__form__item">
		        <label for="title">제목</label>
		        <input
		          type="text"
		          id="title"
		          name="title"
		          class="form__item__input"
		          placeholder="Enter title"
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
		          value="${userinfo.name}"
		          readonly
		        />
		      </div>
		      <div class="notice__form__item">
		      	<label for="content">내용</label>
		        <textarea
		          id="content"
		          name="content"
		          class="form__item__input"
		          placeholder="Enter content"
		          rows="10" cols="100"
		          required
		        ></textarea>
		      </div>
		      <button class="notice__form__submit-btn" type="button" id="regBtn">Register</button>
	    </form>
	  </div>
	</section>
</c:if>
<script>
	$('#regBtn').click(function () {
		$('#registerForm').attr("action", "${root}/notice/register").submit();
	});
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>