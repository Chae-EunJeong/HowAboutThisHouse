<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function(){
					// submit 작업
					$("#search-btn").click(() => {
						if (!$("#sido").val()) {
				        	alert("찾고 싶은  시/도를 선택해 주세요.");
				            return;
				        } 
						if ($("#gugun").val() == 0) {
				             alert("찾고 싶은 구/군을 선택해 주세요.");
				             return;
				        } 
						if ($("#dong").val() == 0) {
				             alert("찾고 싶은 동을 선택해 주세요.");
				             return;
				        } 
						else {
				           	$("#search")
				 				.attr("action", "${root}/map/search")
				 				.submit();
				        }
					});
					
					console.log("1");
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
	
	<input type="hidden" name="check" value="1">

	<button id="search-btn" type="button">
		<i class="bi bi-search"></i>
	</button>
</form>
