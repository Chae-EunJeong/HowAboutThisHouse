<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
</head>
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
<body>
	<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
	<div class="body-container">
		<section id="main">
			<header class="main__header">
				<img src="${root}/img/home_cover.png" alt="main cover image" />
				<div class="main__header__contents">
					<h1>HappyHouse</h1>
					<p>원하시는 지역의 아파트 실거래가 정보를 찾아보세요</p>
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
			</header>

			<main class="main__contents">
			<section class="main__content">
				<header class="main__content__header">
					<h3>공지사항</h3>
					<button>
					<a class="navbar__link" href="${root}/notice?act=list">
						더보기 <i class="bi bi-chevron-right"></i></a>
					</button>
				</header>
				<ul class="main__content__list">
					<li class="main__content__item">
						<div class="main__content__title">
							<span>정기 공지사항</span>
							<p class="main__content__link">2021년 09월 공지사항</p>
						</div>

						<button class="main__content__btn">
							<i class="bi bi-chevron-right"></i>
						</button>
					</li>

					<li class="main__content__item">
						<div class="main__content__title">
							<span>정기 공지사항</span>
							<p class="main__content__link">2021년 08월 공지사항</p>
						</div>
						<button class="main__content__btn">
							<i class="bi bi-chevron-right"></i>
						</button>
					</li>

					<li class="main__content__item">
						<div class="main__content__title">
							<span>정기 공지사항</span>
							<p class="main__content__link">2021년 07월 공지사항</p>
						</div>
						<button class="main__content__btn">
							<i class="bi bi-chevron-right"></i>
						</button>
					</li>

					<li class="main__content__item">
						<div class="main__content__title">
							<span>정기 공지사항</span>
							<p class="main__content__link">2021년 06월 공지사항</p>
						</div>
						<button class="main__content__btn">
							<i class="bi bi-chevron-right"></i>
						</button>
					</li>

					<li class="main__content__item">
						<div class="main__content__title">
							<span>정기 공지사항</span>
							<p class="main__content__link">2021년 05월 공지사항</p>
						</div>
						<button class="main__content__btn">
							<i class="bi bi-chevron-right"></i>
						</button>
					</li>
				</ul>
			</section>
			<section class="main__content">
				<header class="main__content__header">
					<h3>주택 관련 기사</h3>
					<button>
						더보기 <i class="bi bi-chevron-right"></i>
					</button>
				</header>
				<ul class="main__content__list">
					<li class="main__content__item">
						<div class="main__content__title">
							<span>인기 뉴스</span>
							<p class="main__content__link">인사 - 신동아건설</p>
						</div>
						<button class="main__content__btn">
							<i class="bi bi-chevron-right"></i>
						</button>
					</li>

					<li class="main__content__item">
						<div class="main__content__title">
							<span>인기 뉴스</span>
							<p class="main__content__link">12.16 대책 후 매수 매도자 모두 일단 관망세</p>
						</div>
						<button class="main__content__btn">
							<i class="bi bi-chevron-right"></i>
						</button>
					</li>

					<li class="main__content__item">
						<div class="main__content__title">
							<span>최신 뉴스</span>
							<p class="main__content__link">구미시, 낙동강 변 국가 3산단에 민간공원 조성</p>
						</div>
						<button class="main__content__btn">
							<i class="bi bi-chevron-right"></i>
						</button>
					</li>

					<li class="main__content__item">
						<div class="main__content__title">
							<span>최신 뉴스</span>
							<p class="main__content__link">은행과 보험사의 주택담보대출 조건, 어떻게 다를까?</p>
						</div>
						<button class="main__content__btn">
							<i class="bi bi-chevron-right"></i>
						</button>
					</li>

					<li class="main__content__item">
						<div class="main__content__title">
							<span>최신 뉴스</span>
							<p class="main__content__link">은행과 보험사의 주택담보대출 조건, 어떻게 다를까?</p>
						</div>
						<button class="main__content__btn">
							<i class="bi bi-chevron-right"></i>
						</button>
					</li>
				</ul>
			</section>
			</main>
		</section>
		</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>