<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="ko">

<head>
	<jsp:include page="../views/common/head.jsp" />

	<title>여수어때 : 지도보기</title>
</head>

<body>
	<div class="lines-wrap">
		<div class="lines-inner">
			<div class="lines"></div>
		</div>
	</div>
	<div class="site-mobile-menu site-navbar-target">
		<div class="site-mobile-menu-header">
				<div class="site-mobile-menu-close">
					<span class="icofont-close js-menu-toggle"></span>
				</div>
			</div>
		<div class="site-mobile-menu-body"></div>
	</div>
	
	<jsp:include page="../views/common/nav.jsp" />
  	
	<!-- 메인페이지 필터 -->
						<!-- 메인페이지 필터 시작 -->
						<div id="map" style="width:1280px;height:700px;margin:0 auto; border:1px solid;"></div>
				<!-- 필터 슬라이드 이미지 -->

	<!-- 펜션 목록 -->
		<!-- 펜션 내용 -->


	<jsp:include page="../views/common/footer.jsp" />
	<script src="/resources/js/map.js" ></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=53da4885eed3b04fe18257eeb209aa7c&libraries=services"></script>
<script>
	
	$(function() {
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
	mapOption = { 
	    center: new kakao.maps.LatLng(34.74756145157297, 127.74561623573636), // 지도의 중심좌표
	    level: 5 // 지도의 확대 레벨
	};
	
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	//마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다
	var positions = [],
	selectedMarker=null;
	
		mapService.getList(function(result) {
			console.log(result);
			for(var a in result) {
				var marker = positions.push({content:'<div class="mt-3 mx-2 mb-4">'+result[a].name+'<hr>'+result[a].comments+'<br><br>별점 : '+result[a].starRate+'<br></div><a href="/pension/detail?pensionid='+result[a].pensionid+'" class="btn btn-primary btn-register btn-block" >상세보기</a>',latlng: new kakao.maps.LatLng(result[a].latitude, result[a].longitude)});
			}
		})
	
	
	
	
	for (var i = 0; i < positions.length; i ++) {
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    map: map, // 마커를 표시할 지도
	    position: positions[i].latlng // 마커의 위치
	});
	
	// 마커에 표시할 인포윈도우를 생성합니다 
	var infowindow = new kakao.maps.InfoWindow({
	    content: positions[i].content // 인포윈도우에 표시할 내용
	   // removable :true
	});
	
	var infowindowList = [];
	infowindowList.push(infowindow);
		
	// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	// 이벤트 리스너로는 클로저를 만들어 등록합니다 
	// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
			kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
			kakao.maps.event.addListener(marker, 'mouseover', makeOutListener(infowindow));
	}
		
	
	//인포윈도우를 표시하는 클로저를 만드는 함수입니다 
 	function makeOverListener(map, marker, infowindow) {
	return function() {
	    infowindow.open(map, marker);
	};
	}
	
	//인포윈도우를 닫는 클로저를 만드는 함수입니다 
	function makeOutListener(infowindow) {
	return function() {
	    	infowindow.close();
	};
	}
	
	/* 아래와 같이도 할 수 있습니다 */
	/*
	for (var i = 0; i < positions.length; i ++) {
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    map: map, // 마커를 표시할 지도
	    position: positions[i].latlng // 마커의 위치
	});
	
	// 마커에 표시할 인포윈도우를 생성합니다 
	var infowindow = new kakao.maps.InfoWindow({
	    content: positions[i].content // 인포윈도우에 표시할 내용
	});
	
	// 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
	// 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	(function(marker, infowindow) {
	    // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다 
	    kakao.maps.event.addListener(marker, 'mouseover', function() {
	        infowindow.open(map, marker);
	    });
	
	    // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
	    kakao.maps.event.addListener(marker, 'mouseout', function() {
	        infowindow.close();
	    });
	})(marker, infowindow);
	}
	*/
	})
</script>
</body>
</html>