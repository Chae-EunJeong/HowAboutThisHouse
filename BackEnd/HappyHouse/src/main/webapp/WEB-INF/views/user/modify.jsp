<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/include/navbar.jsp" %>
<section class="accounts" id="edit">
  <div class="accounts__container">
    <header class="accounts__header">
      <h2>Edit Profile</h2>
    </header>
    <form class="accounts__form" method="post" action="">
      <div class="accounts__form__item">
        <label for="email">Email</label>
        <input
          type="email"
          id="email"
          name="email"
          class="form__item__input"
          value="${ userinfo.email }"
          readonly
        />
      </div>
      <div class="accounts__form__item">
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          name="password"
          class="form__item__input"
          placeholder="Enter new password"
          required
        />
      </div>
      <div class="accounts__form__item">
        <label for="password2">Confirm password</label>
        <input
          type="password"
          id="password2"
          name="password2"
          class="form__item__input"
          placeholder="Confirm your password"
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
      <div class="accounts__form__item">
        <label for="adress">Address</label>
        <input
          type="text"
          id="adress"
          name="address"
          class="form__item__input"
          placeholder="Enter your adress"
          value="서울특별시 강남구 역삼동 테헤란로 212"
          required
        />
      </div>
      <div class="accounts__form__item">
        <label for="adress">Phone number</label>
        <input
          type="tel"
          id="phone"
          name="phone"
          class="form__item__input"
          placeholder="Enter your phone number"
          value="010-0000-0000"
          required
        />
      </div>

      <button class="accounts__form__submit-btn" type="submit">Confirm</button>
    </form>
  </div>
</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>