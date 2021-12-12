<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/include/navbar.jsp" %>
<section class="accounts" id="login">
  <div class="accounts__container">
    <header class="accounts__header">
      <h2>로그인</h2>
    </header>
    <form class="accounts__form" method="post" action="">
	      <div class="accounts__form__item">
	        <label for="email">Email</label>
	        <input
	          type="email"
	          id="email"
	          name="email"
	          class="form__item__input"
	          placeholder="Enter your email"
	          required
	        />
	      </div>
	      <div class="accounts__form__item">
	        <label for="password">Password</label>
	        <input
	          type="password"
	          id="password"
	          name="password"
	          class="form__item__input"
	          placeholder="Enter your password"
	          required
	        />
	      </div>
	      <div class="accounts__form__item">
              <label for="idsave">
                  <input class="form-check-input" type="checkbox" id="idsave" name="idsave" value="saveok"${idck}> 아이디저장
              </label>
          </div>
	      <div class="accounts__login__result">
	      	${ msg }
	      </div>
	      <button>
	        <a class="accounts__link" href="${root}/user/findPwd">
	          Forgot your password?
	        </a>
	      </button>
	      <button class="accounts__form__submit-btn" type="submit">Login</button>
    </form>

    <button>
      Don't have an account?
      <a class="accounts__link" href="${root}/user/join">
        Sign up
      </a>
    </button>
  </div>
</section>

<script>
  // navbar 메뉴 클릭시 page body 받아오기
  $(".accounts__link").click((e) => {
    $nav = $(e.target);
    $("#pageBody").load($nav.data("page"));
    $("#nav-login").removeClass("active");
  });

</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>