<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/include/navbar.jsp" %>
<c:if test="${!empty msg}">
<script>
	alert("${msg}");
</script>
</c:if>
<section class="accounts" id="mypage">
  <div class="accounts__container">
    <header class="accounts__header">
      <h2>My page</h2>
    </header>
    <ul class="accounts__info" data-email="${ userinfo.email }">
      <li class="accounts__info__item">
        <span class="info__item__title">Email</span>
        <p class="info__item__contents">${ userinfo.email }</p>
      </li>
      <li class="accounts__info__item">
        <span class="info__item__title">Name</span>
        <p class="info__item__contents">${ userinfo.name }</p>
      </li>
      <li class="accounts__info__item">
        <span class="info__item__title">Address</span>
        <p class="info__item__contents">
          서울특별시 강남구 역삼동 테헤란로 212
        </p>
      </li>
      <li class="accounts__info__item">
        <span class="info__item__title">Phone number</span>
        <p class="info__item__contents">010-0000-0000</p>
      </li>
	    <div class="accounts__info__btns">
	      <button class="accounts__info__btn btn__edit" type="button">
	        <a href="${root}/user/modify"> Edit </a>
	      </button>
	      <button class="accounts__info__btn btn__delete" type="button">
	        Delete
	      </button>
	    </div>
    </ul>
  </div>
</section>

<script>
  // edit 버튼 클릭시 edit 페이지로 이동
  $(".btn__edit").click((e) => {
    let $target = $(e.target);
    if (e.target.nodeName === "BUTTON") {
      $target = $(e.target.firstElementChild);
    }
    $("#pageBody").load($target.data("page"));
  });

  // 회원탈퇴 후 메인 페이지로 이동
  //$("#mypage .btn__delete").click(() => {
  //  if (confirm("탈퇴 시 회원정보를 복구할 수 없습니다. 정말 탈퇴 하시겠습니까?"))
  //    location.href = "${root}/user/${userinfo.email}";
  //});
  
  $(".btn__delete").click( function () {
	  if (confirm("탈퇴 시 회원정보를 복구할 수 없습니다. 정말 탈퇴 하시겠습니까?")) {
		  let delid = $(this).closest("ul").attr("data-email");
		  /*$.ajax({
			 url: '${root}/user/withdrawal/' + delid,
			 type: 'DELETE'
		  });*/
		  location.href = "${root}/user/withdrawal/" + delid;
	  }
  });
  
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>