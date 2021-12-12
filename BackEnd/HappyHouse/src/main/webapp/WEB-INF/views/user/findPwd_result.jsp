<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link rel="stylesheet" href="${root}/css/accounts.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/navBar.jsp" %>
<section class="accounts" id="findPwd">
  <div class="accounts__container findPwd__complete hide">
    <header class="accounts__header">
      <h2>${ msg1 }</h2>
    </header>
    <p>${ msg2 }</p>
    <a href="${root}/user?act=mvlogin">
      <button class="accounts__complete__btn" type="button">
         Login
      </button>
    </a>
  </div>
</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>