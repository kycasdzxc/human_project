<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="ko">
<head>
	<jsp:include page="../common/head.jsp" />
	
	<title>여수어때 : 펜션목록</title>
</head>
<body>

	<div class="site-mobile-menu site-navbar-target">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close">
				<span class="icofont-close js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>

	<jsp:include page="../common/nav.jsp" />
	<div class="hero2">
		<div class="container" style="width: 70%;">
			<div class="row">
				<div class="col-12">
					<form class="form mt-1" action="/pension/list" name="filterFrm">
						<div class="row">
							<div class="col-sm-12 col-md-12 col-lg-3 py-2">
								<h5 class="my-2 pl-2">
									<i class="far fa-calendar-alt"></i>
									예약날짜 선택 :
								</h5>
							</div>
							<div class="col-sm-12 col-md-6 col-lg-5">
								<input type="text" class="form-control my-2 text-center" id="daterange" readonly>
								<input type="hidden" class="form-control my-2" name="startDate" value="${startDate}" readonly>
								<input type="hidden" class="form-control my-2" name="endDate" value="${endDate}" readonly>
							</div>
							<div class="col-sm-12 col-md-6 col-lg-4">
								<input type="submit" class="btn btn-primary btn-block my-2" value="Search">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="section search-result-wrap mt-4">
		<div class="container">
			<div class="row posts-entry">
				<div class="col-lg-8 pensionlist">
					<div class="row text-start pt-5 border-top mb-4">
					
					</div>
				</div>
				
				<div class="col-lg-4 sidebar">
					
					<!--  END sidebar-box-->
					<div class="sidebar-box">
						<h3 class="heading">Popular Posts</h3>
						<div class="post-entry-sidebar">
							<ul>
							<c:forEach items="${pensions}" var="p" begin="0" end="3">
								<li>
									<a href="/pension/detail?pensionid=${p.pensionid}">
										<img src="/display?path=${p.attachs[0].path}&uuid=${p.attachs[0].uuid}" alt="Image placeholder" class="me-4 rounded">
										<div class="text">
											<h4>${p.name}</h4>
											<div class="post-meta">
												<span class="mr-2"><fmt:formatNumber type="number" maxFractionDigits="3" value="${p.price}" /> 원</span>
											</div>
										</div>
									</a>
								</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					
					<!--  END sidebar-box-->

					<div class="sidebar-box">
						<form>
							<h3 class="heading">Categories</h3>
							<div class="filter" >
								<div class="px-3">
									<p class="mb-3 font-weight-bold">펜션 유형</p>
									<div class="px-3">
										<input type="checkbox" name="category" id="category1" value="1"  class="mr-1"><label for="category1">펜션</label>
										<input type="checkbox" name="category" id="category2" value="2"  class="ml-2 mr-1"><label for="category2">풀빌라</label>
									</div>
								</div>
								<hr>
								<div class="public px-3">
									<p class="mb-3 font-weight-bold">공용 시설</p>
									<div class="px-3">
										<input type="checkbox" id="footVolleyballCourt" name="footVolleyballCourt" class="mr-1"><label for="footVolleyballCourt">족구장</label>
										<input type="checkbox" id="karaoke" name="karaoke" class="ml-2 mr-1"><label for="karaoke">노래방</label>
										<input type="checkbox" id="restaurant" name="restaurant" class="ml-2 mr-1"><label for="restaurant">식당</label>
										<input type="checkbox" id="convenienceStore" name="convenienceStore" class="ml-2 mr-1"><label for="convenienceStore">편의점</label><br>
										<input type="checkbox" id="parkingLot" name="parkingLot" class="mr-1"><label for="parkingLot">주차장</label>
										<input type="checkbox" id="bbq" name="bbq" class="ml-2 mr-1"><label for="bbq">바베큐</label>
										<input type="checkbox" id="seminarRoom" name="seminarRoom" class="ml-2 mr-1"><label for="seminarRoom">세미나실</label>
									</div>
								</div>
								<hr>
								<div class="internal px-3">
									<p class="mb-3 font-weight-bold">내부 시설</p>
									<div class="px-3">
										<input type="checkbox"  id="airConditioner" name="airConditioner" class="mr-1"><label for="airConditioner">에어컨</label>
										<input type="checkbox"  id="miniBar" name="miniBar" class="ml-2 mr-1"><label for="miniBar">미니바</label>
										<input type="checkbox"  id="bathTub" name="bathTub" class="ml-2 mr-1"><label for="bathTub">욕조</label>
										<input type="checkbox"  id="tv" name="tv" class="ml-2 mr-1"><label for="tv">TV</label><br>
										<input type="checkbox"  id="refrigerator" name="refrigerator" class="mr-1"><label for="refrigerator">냉장고</label>
										<input type="checkbox"  id="wifi" name="wifi" class="ml-2 mr-1"><label for="wifi">와이파이</label>
									</div>
								</div>
								<hr>
								<div class="other px-3">
									<p class="mb-3 font-weight-bold">기타</p>
									<div class="px-3">
										<input type="checkbox" id="breakfast" name="breakfast" class="mr-1"><label for="breakfast">조식</label>
										<input type="checkbox" id="pickup" name="pickup" class="ml-1 mr-1"><label for="pickup">픽업</label>
										<input type="checkbox" id="cooking" name="cooking" class="ml-1 mr-1"><label for="cooking">취사가능</label>
										<input type="checkbox" id="freeParking" name="freeParking" class="ml-1 mr-1"><label for="freeParking">무료주차</label><br>
										<input type="checkbox" id="campfire" name="campfire" class="mr-1"><label for="campfire">캠프파이어</label>
									</div>
								</div>
								<hr>
								<input type="submit" class="btn btn-primary btn-block btnSearch" value="적용" id="btnSearchFilter">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" />

  </body>
  <script src="/resources/js/pension.js" ></script>
  <script>
  jQuery.fn.serializeObject = function() {
	    var obj = null;
	    try {
	        if (this[0].tagName && this[0].tagName.toLowerCase() == "form") {
	            var arr = this.serializeArray();
	            if (arr) {
	                obj = {};
	                jQuery.each(arr, function() {
	                	if(this.name.indexOf('.') != -1) {
	                		var key = this.name.substring(0, this.name.indexOf("."));
	                		console.log(key);
	                		if(!(key in obj)) {
	                			obj[key] = [];
	                		}
	                		var subkey = this.name.substring(this.name.indexOf(".")+1);
	                		var subvalue = this.value;
	                		var o = {};
	                		o[subkey] = subvalue;
	                		obj[key].push(o);
	                	}else {
		                    obj[this.name] = this.value;
	                	}
	                });
	            }
	        }
	    } catch (e) {
	        alert(e.message);
	    } finally {
	    }
	 
	    return obj;
	};
	
	var options;  
  $(function() {
	  $("#btnSearchFilter").click(function() {
		  event.preventDefault();
		  $(".pensionlist").html("")
		  options = $(this).closest("form").serializeObject();
		showList(lastPensionid, amount, options);
	  })	  
		  
	function getPensionStr(pension) {
		var path = '';
		var uuid = '';
		var price = pension.price != null ? pension.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') : 0;
		for(var i in pension.attachs) {
			path = pension.attachs[i].path.trim();
			uuid = pension.attachs[i].uuid;
			break;
		}
		str = "";
		str += '<div class="blog-entry d-flex blog-entry-search-item pensions" data-pensionid ="' + pension.pensionid + '">'
		str += '	<button class="img-link me-4">'
		str += '		<img src="/display?path=' + path + '&uuid=' + uuid + '" alt="Image" class="img-fluid">'
		str += '	</button>'
		str += '	<div>'
		str += '		<h2><a href="/pension/detail?pensionid=' + pension.pensionid + ('${startDate}' != "" ? "&startDate=" + '${startDate}' : "") + ('${endDate}' != "" ? "&endDate=" + '${endDate}' : "") + '" class="bg-white border-0">' + pension.name + '</a></h2>'
		str += '		<p>' + pension.address + '</p>'
		str += '		<p>가격 ' + price + ' 원</p>'
		str += '		<p>별점 ' + pension.starRate +  '</p>'
		str += '		<p>리뷰 ' + pension.replyCnt +  '</p>'
		str += '	</div>'
		str += '</div>'
		return str;
	}
	  
	  /* console.log(getPensionStr(pension)) */
	
		var lastPensionid;
		var amount; 
		function showList(lastPensionid, amount, option) {
			var param = {lastPensionid : lastPensionid, amount : amount, option : option}
			pensionService.getList(param, function(result) {
      		/* console.log(result) */
				var str = '';
				for (var i in result) {
					str += getPensionStr(result[i]);
				}
				/* console.log(str); */
				$(".pensionlist").append(str);
      		})
		}  
		showList(lastPensionid, amount);

	$(document).scroll(function () {
		var dh = $(document).height();
		var wh = $(window).height();
		var wst = $(window).scrollTop();
		
		if(dh == wh + wst) {
			var lastPensionid = $(".pensions").last().data("pensionid")
			showList(lastPensionid, amount, options);
		}
	})
  })
  
  </script>
  </html>
