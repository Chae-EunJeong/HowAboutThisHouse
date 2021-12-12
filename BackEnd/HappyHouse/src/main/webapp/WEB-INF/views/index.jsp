<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		// submit 작업
		$("#apt-btn").click(() => {
			if (!$("#inputAPT").val()) {
				alert("찾고 싶은 아파트를 입력해 주세요.");
				return;
			} else {		
				$("#aptSearch")
 				.attr("action", "${root}/map/aptSearch")
 				.submit();
			}
		});
	});
</script>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
	<!--main cover-->
	<section class="main__section main__cover">
	    <img src="${root}/img/home.jpeg">
	<div class="main__cover__contents">
	    <p>Welcome To HappyHouse</p>
	    <div class="main__header__selector selector">
			<%@ include file="/WEB-INF/views/include/selector.jsp"%>
		</div>
		<div class="main__header__selector selector">
			<form id="aptSearch" method="get">
				<input id="inputAPT" name="inputAPT" type="search" placeholder=" 아파트 명" style="width:82%">
				<input type="hidden" name="check" value="2">
				<button id="apt-btn" type="button">
					<i class="bi bi-search"></i>
				</button>
			</form>
		</div>
   	</div>
   	</section>
   	
	<!--main service introduction-->
	<section class="main__section main__contents">
	    <header class="main__contents__intro">
	        <h3>Our Services</h3>
	    </header>
	    <ul class="main__service">
	        <li class="main__service__card">
	            <img src="${root}/img/house1.jpg" class="card__img">
	            <p class="card__descript card__title">동별 실거래가 조회</p>
	            <p class="card__descript card__contents">해피 하우스는 고객이 원하는 지역의 동별 실거래가 조회 서비스를 제공하고 있습니다.</p>
	            <button class="card__descript card__btn">자세히 보기</button>
	        </li>
	        <li class="main__service__card">
	            <img src="${root}/img/house2.jpg" class="card__img">
	            <p class="card__descript card__title">아파트별 실거래가 조회</p>
	            <p class="card__descript card__contents">해피 하우스는 고객이 원하는 아파트의 실거래가 조회 서비스를 제공하고 있습니다.</p>
	            <button class="card__descript card__btn">자세히 보기</button>
	        </li>
	        <li class="main__service__card">
	            <img src="${root}/img/house3.jpg" class="card__img">
	            <p class="card__descript card__title">주변 지역 조회</p>
	            <p class="card__descript card__contents">해피 하우스는 고객이 원하는 지역의 환경, 상가 정보 등 기타 서비스를 제공하고 있습니다.</p>
	            <button class="card__descript card__btn">자세히 보기</button>
	        </li>
	    </ul>
	</section>
	
    <!--main ??-->
    <section class="main__section"></section>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>