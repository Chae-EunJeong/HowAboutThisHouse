<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${root}/css/houseDeal.css" />

<!-- Kakao map -->

</head>
<body>
<%@ include file="/WEB-INF/views/include/navbar.jsp" %>
<body>
<div class="body-container">
	<section id="houseDeal">
	  <header class="houseDeal__header">
	    <div class="houseDeal__header__container">
	      <div class="houseDeal__header__selector selector">
	      	<script>

				$(document).ready(function() {
						// 메인에서 검색. 1 - 주소 검색, 2 - 아파트명 검색
						$.get( (1 == '${check}') ? "${root}/map/apt" : "${root}/map/aptlist"
								, (1 == '${check}') ? {dong: '${dong}'} : {aptName: '${inputAPT}'}
								,function(data, status){
									$("#searchResult").empty();
									$.each(data, function(index, vo) {
										let str = `
											<li class='houseDeal__list__item'>
												<p>`+ vo.aptName +`</p>
												<span>`+ vo.sidoName +` `+ vo.gugunName +` `+ vo.dongName +` `+ vo.jibun +`</span>
												<span>`+ vo.recentPrice +` 만원</span>
												<span>`+ vo.floor +` 층</span>
												<span>`+ vo.area +` m<sub>2</sub></span>
											</li>

										`;
										$("#searchResult").append(str);
									});
									displayMarkers(data);
								}
								, "json"
						);
					
						
					// submit 작업
					$("#search-btn").click( () => {
						if (!$("#sido").val()) {
				        	alert("찾고 싶은  시/도를 선택해 주세요.");
				            return;
				        } else if ($("#gugun").val() == 0) {
				             alert("찾고 싶은 구/군을 선택해 주세요.");
				             return;
				        } else if ($("#dong").val() == 0) {
				             alert("찾고 싶은 동을 선택해 주세요.");
				             return;
				        } 
					});
					
					$.get("${root}/map/sido"
						,function(data, status){
							$.each(data, function(index, vo) {

								$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
							});
						}
						, "json"
					);
					
				});
				$(document).on("change", "#sido", function() {
					$.get("${root}/map/gugun"
							,{sido: $("#sido").val()}
							,function(data, status){
								$("#gugun").empty();
								$("#gugun").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#gugun", function() {
					$.get("${root}/map/dong"
							,{gugun: $("#gugun").val()}
							,function(data, status){
								$("#dong").empty();
								$("#dong").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#dong").append("<option value='"+vo.dongCode+"'>"+vo.dongName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#dong", function() {
					$(document).on("click", "#search-btn", function() {
						$.get("${root}/map/apt"
								,{dong: $("#dong").val()}
								,function(data, status){
									$("#searchResult").empty();
									$.each(data, function(index, vo) {
										console.log("3" + index);
										console.dir(vo);
										let str = `
											<li class='houseDeal__list__item'>
												<p>`+ vo.aptName +`</p>
												<span>`+ vo.sidoName +` `+ vo.gugunName +` `+ vo.dongName +` `+ vo.jibun +`</span>
												<span>`+ vo.recentPrice +` 만원</span>
												<span>`+ vo.floor +` 층</span>
												<span>`+ vo.area +` m<sub>2</sub></span>
											</li>
	
										`;
										$("#searchResult").append(str);
									});
									console.log("1" + data);
									console.dir("2" + data);
									displayMarkers(data);
								}
								, "json"
						);
					});
				});
				
				$(document).on("click", "#aptSearchBtn", function() {
					var param = {
							serviceKey:'TBB3b3MCVdb00PwioBKTRJGhqAbTdwBuahqxAmINLuzv5Fpt/IX+2/F94uaGybmblLB92d2WFUhE7LHzp8ARdg==',
							pageNo:encodeURIComponent('1'),
							numOfRows:encodeURIComponent('10'),
							LAWD_CD:encodeURIComponent($("#gugun").val()),
							DEAL_YMD:encodeURIComponent('202110')
					};
					$.get('http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev'
							,param
							,function(data, status){
						console.log(data);
								var items = $(data).find('item');
								var jsonArray = new Array();
								items.each(function() {
									var jsonObj	= new Object();
									jsonObj.aptCode = $(this).find('일련번호').text();
									jsonObj.aptName = $(this).find('아파트').text();
									jsonObj.dongCode = $(this).find('법정동읍면동코드').text();
									//jsonObj.dongName = $(this).find('').text();
									//jsonObj.sidoName = $(this).find('').text();
									//jsonObj.gugunName = $(this).find('').text();
									jsonObj.buildYear = $(this).find('건축년도').text();
									jsonObj.jibun = $(this).find('지번').text();
									jsonObj.recentPirce = $(this).find('거래금액').text();
										
									jsonObj = JSON.stringify(jsonObj);
									//String 형태로 파싱한 객체를 다시 json으로 변환
									jsonArray.push(JSON.parse(jsonObj));
								});
								console.log(jsonArray);
								displayMarkers(jsonArray);
							}
							, "xml"
					);
					/* var xhr = new XMLHttpRequest();
					var url = 'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev';
					var queryParams = '?' + encodeURIComponent('serviceKey') + '='+encodeURIComponent(' API KEY ');
					queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); 
					queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); 
					queryParams += '&' + encodeURIComponent('LAWD_CD') + '=' + encodeURIComponent($("#gugun").val()); 
					queryParams += '&' + encodeURIComponent('DEAL_YMD') + '=' + encodeURIComponent('202110'); 
					xhr.open('GET', url + queryParams);
					xhr.onreadystatechange = function () {
					    if (this.readyState == 4) {
					    	console.log(this.responseXML);
					        alert('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
					    }
					};

					xhr.send(''); */
				});
</script>

<form id="search" method="get">
	<input type="hidden" id="act" name="list" />
	
	<select name="sido" id="sido">
		<option value="">도/광역시</option>
	</select> 
	
	<select name="gugun" id="gugun">
		<option value="">시/군/구</option>
	</select> 
	<input type="hidden" id="gugunCode" name="gugunCode" /> 
	
	<select name="dong" id="dong">
		<option value="">동</option>
	</select>


	<button id="search-btn" type="button">
		<i class="bi bi-search"></i>
	</button>
</form>
	      	
	      </div>
	      <div class="houseDeal__header__btns">
	      	
	      	<!-- 국민안심병원 설정 -->
	     	<button class="houseDeal__header__btn btn__off" id="safeHospital-btn">
				<span><i class="fas fa-clinic-medical"></i></span>
			    <span class="btn__text">국민안심병원 보기</span>
			</button>
		 
		    
		    
	      	<!-- 선별진료소 설정 -->
	  		<button class="houseDeal__header__btn btn__off" id="selectedClinic-btn">
		      	<span><i class="fas fa-shield-virus"></i></span>
		      	<span class="btn__text">선별진료소 보기</span>
		    </button>
	      	
	     
	      	<%-- <!-- 관심지역 설정 -->
	      	<c:if test="${empty interestArea}">
		      	<button class="houseDeal__header__btn" id="interestArea-btn">
		      	  <span><i class="bi bi-bookmark-star"></i></span>
		      	  <span>관심지역 등록</span>
		      	</button>
	      	</c:if>
	      	<c:if test="${!empty interestArea}">
		      	<button class="houseDeal__header__btn" id="interestArea-btn">
		      	  <span><i class="bi bi-bookmark-star-fill"></i></span>
		      	  <span>관심지역 해제</span>
		      	</button>
	      	</c:if> --%>
	      	
	      </div>
	    </div>
	  </header>
	  <div class="houseDeal__container">
	    <main class="houseDeal__main">
	      <section class="houseDeal__text">
			<p class="houseDeal__title">아파트 정보</p>
	        <ul id="searchResult" class="houseDeal__list">
	        
<%-- 	        	<c:if test="${!empty housedeal}"> --%>
<%-- 		        	<c:forEach items="${housedeal}" var="item"> --%>
<!-- 		        		 <li class='houseDeal__list__item'> -->
<%-- 	  		        		<p>${item.aptName}</p> --%>
<%-- 	  		        		<span>전용면적: ${item.area}m<sup>2</sup></span> --%>
<%-- 	  		        		<span>거래금액: ${item.dealAmount}만원</span> --%>
<!-- 	  	  	   			 </li> -->
<%-- 		        	</c:forEach> --%>

<%-- 	        	</c:if> --%>
<%-- 	        	<c:if test="${empty housedeal}"> --%>
<!-- 	        		<span>해당 지역 거래 매물이 없습니다.</span> -->
<%-- 	        	</c:if> --%>
	        	
	        </ul>
	        
					
	      </section>
	      <section class="houseDeal__map">
	        <div id="map" class="mb-3 mapSize houseDeal__map"></div>
	        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=128b31fd00964c2087e3c1b1093d7cdc&libraries=services"></script>
			<script type="text/javascript" src="${root}/js/map.js"></script>
	      </section>
	    </main>
	  </div>
	</section>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>