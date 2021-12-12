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
<script>
	if("${key}")
		$("#key").val("${key}").prop("selected", true);

	$('.btn__search').click(function () {
		var word = $('#word').val();
		if (word == "") 
			alert("모든 목록 조회");
		$('#searchform').attr("action", "${root}/notice/list").submit();		
	});
	
	/* pagination 항목 클릭시 페이지 이동해서 목록 출력 */
	$(document).on('click', '.page-item', function (e) {
		$("#pg").val(($(this).attr("data-pg")));
		$("#pageform").attr("action", "${root}/notice/list").submit();
	})
</script>
<form name="pageform" id="pageform" method="GET" action="">
	<input type="hidden" name="pg" id="pg" value="">
	<input type="hidden" name="key" id="key" value="${key}">
	<input type="hidden" name="word" id="word" value="${word}">
</form>
<section class="notice" id="detail">
	<div class="notice__container">
    <header class="notice__header">
      <h2>공지사항</h2>
      <div class="notice__header__container">
	      <div class="notice__header__write">
	      		<button class="notice__header__write-btn" type="submit">새 글 작성</button>
	      </div>
	    </div>
    </header>
    <section class="notice__list__main">
	        <table class="notice__list">
				<colgroup>
					<col style="width:90px;">
					<col style="width:*">
					<col style="width:110px;">
					<col style="width:76px;">
				</colgroup>
				<thead class="notice_list_head">
	            <tr>
	                <th scope="col">번호</th>
					<th scope="col" class="title">제목</th>
					<th scope="col">등록일</th>
					<th scope="col">조회수</th>
	            </tr>
				</thead>
				<!-- <tbody id="contents-list">
				</tbody> -->
	            <c:if test="${ !empty notices }">
	            <c:forEach var="notice" items="${ notices }">
	            <tr>
	                <td>${ notice.no }</td>
	                <td><a href="${root}/notice/${notice.no}">
	                	${ notice.title }
	                </a></td>
	                <!-- 2021.11.09 형식으로 출력하기 -->
	                <td>${ notice.regDate }</td>
	                <td>${ notice.viewCnt }</td>
	            </tr>
	            </c:forEach>
	            </c:if>
	            <c:if test="${ empty notices }">
		            <tr>
		                <td colspan="4">등록된 공지사항이 없습니다.</td>
		            </tr>
	            </c:if>
	        </table>
	        <!-- 검색 박스 -->
	      <div class="notice__header__selector">
	            <form id="searchform" method="get" action="">
	            	<input type="hidden" name="pg" value="1">
	            	<select name="key">
	            		<option value="title" class="notice__select__item">제목
	            		<option value="content" class="notice__select__item">내용
	            	</select>
	            	<input type="text" name="word" id="word" class="form__item__input item_search" value="${word}">
	            	<button type="button" class="notice__header__search-btn btn__search"> 검색  </button>
	            </form>
	      </div>
	        <div id="contents-paging" class="paging">
	        	<ul class="pagination">
	        		<li class="page-item" data-pg="1">
	        			최신
	        		</li>
	        		<li class="page-item" data-pg="${navigation.mynavigator.startRange}">
	        			이전
	        		</li>
	        		<c:forEach var="i" begin="${navigation.mynavigator.startPage}" end="${navigation.mynavigator.endPage}" step="1">
		        		<li class="page-item" data-pg="${i}">
		        			${i}
		        		</li>
	        		</c:forEach>
	        		<li class="page-item" data-pg="${navigation.mynavigator.endRange}">
	        			다음
	        		</li>
	        		<li class="page-item" data-pg="${navigation.mynavigator.totalPageCount}">
	        			마지막
	        		</li>
	        	</ul>
			</div>
    </section>
    </div>
    </section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>