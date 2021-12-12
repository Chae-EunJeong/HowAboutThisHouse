<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/navbar.jsp" %>
	<section class="accounts" id="signup">
	  <div class="accounts__container">
	    <header class="accounts__header">
	      <h2>Sign up</h2>
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
		      <div id="emailCheck" class="accounts__emailCheck__result hide"></div>
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
		        <label for="address">Address</label>
		        <input
		          type="text"
		          id="address"
		          name="address"
		          class="form__item__input"
		          placeholder="Enter your address"
		          required
		        />
		      </div>
		      <div class="accounts__form__item">
		        <label for="phone">Phone number</label>
		        <input
		          type="tel"
		          id="phone"
		          name="phone"
		          class="form__item__input"
		          placeholder="Enter your phone number"
		          required
		        />
		      </div>
		      <button class="accounts__form__submit-btn" type="submit">Register</button>
	    </form>
	  </div>
	</section>
	
	<script>
	// 이메일 중복 체크
	$(document).ready(function() {
		$("#email").keyup(e => {
			$.ajax({
				url: '${root}/user',
				data: {'act': 'ckemail', 'email': e.target.value},
				type: 'GET',
				dataType: 'xml',
				success: (response) => {
					var cnt = $(response).find("count").text();
					if (cnt == 1) $('#emailCheck').text("Email is invalid or already taken");
					else $('#emailCheck').text("");
				}
			});
		});
	});
	$(document).ready(function() {
		$("#email").keyup(e => {
			if(e.target.value.length == 0) $("#emailCheck").removeClass("hide");
			else $("#emailCheck").addClass("hide").text("");
		});
	});
	</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>