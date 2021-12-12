<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link rel="stylesheet" href="${root}/css/accounts.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/navBar.jsp" %>
<section class="accounts" id="findPwd">
  <div class="accounts__container findPwd__home">
    <header class="accounts__header">
      <h2>Find password</h2>
    </header>
    <form class="accounts__form" method="post" action="${root}/user">
    <input type="hidden" name="act" value="findPwd">
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
        <label for="name">Name</label>
        <input
          type="text"
          id="name"
          name="name"
          class="form__item__input"
          placeholder="Enter your name"
          required
        />
      </div>

      <button class="accounts__form__submit-btn" type="submit">Confirm</button>
    </form>
  </div>

</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>