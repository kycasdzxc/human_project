<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!-- /*
* Template Name: Property
* Template Author: Untree.co
* Template URI: https://untree.co/
* License: https://creativecommons.org/licenses/by/3.0/
*/ -->
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="../common/head.jsp" />
	
	<title>여수어때 : 마이페이지</title>
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

	<div class="hero2 page-inner overlay">
		<div class="container">
			<div class="row justify-content-center align-items-center">
				<div class="col-lg-9 text-center">
					<h1 class="heading py-4 m-0" data-aos="fade-up">Mypage</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="section bg-light">
		<div class="container">
			<div class="row">
				<div class="col-6 col-lg-4">
					<div class="box-feature mb-4">
						<img class="d-block-3 mb-4" src="/resources/assets/images/note.png" style="width:50px">
						<h3 class="text-black mb-3 font-weight-bold">예약내역 조회</h3>
						<p class="text-black-50">펜션 예약 내역을 조회합니다.</p>
						<p><a href="/member/mypage/listReservation" class="learn-more">페이지 이동</a></p>
					</div>
				</div>
				<div class="col-6 col-lg-4">
					<div class="box-feature mb-4">
						<img class="d-block-3 mb-4" src="/resources/assets/images/get-money.png" style="width:50px">
						<h3 class="text-black mb-3 font-weight-bold">포인트내역 조회</h3>
						<p class="text-black-50">포인트 내역을 조회합니다.</p>
						<p><a href="#" class="learn-more" id="mypagePoint">페이지 이동</a></p>
					</div>
				</div>
				<div class="col-6 col-lg-4">
					<div class="box-feature mb-4">
						<img class="d-block-3 mb-4" src="/resources/assets/images/chat.png" style="width:50px">
						<h3 class="text-black mb-3 font-weight-bold">내가 쓴 댓글 조회</h3>
						<p class="text-black-50">내가 쓴 댓글을 조회합니다.</p>
						<p><a href="#" class="learn-more" id="mypageReply">페이지 이동</a></p>
					</div>
				</div>
				<div class="col-6 col-lg-4">
					<div class="box-feature mb-4">
						<img class="d-block-3 mb-4" src="/resources/assets/images/phone-call.png" style="width:50px">
						<h3 class="text-black mb-3 font-weight-bold">1:1 문의내역</h3>
						<p class="text-black-50">1:1 문의내역을 조회합니다.</p>
						<p><a href="/member/mypage/listInquiry" class="learn-more">페이지 이동</a></p>
					</div>
				</div>	

				<div class="col-6 col-lg-4">
					<div class="box-feature mb-4">
						<img class="d-block-3 mb-4" src="/resources/assets/images/man.png" style="width:50px">
						<h3 class="text-black mb-3 font-weight-bold">회원정보 수정</h3>
						<p class="text-black-50">회원정보를 수정합니다.</p>
						<p><a href="/member/modify" class="learn-more">페이지 이동</a></p>
					</div>
				</div>
				<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')">
				<div class="col-6 col-lg-4">
					<div class="box-feature mb-4">
						<img class="d-block-3 mb-4" src="/resources/assets/images/settings.png" style="width:50px">
						<h3 class="text-black mb-3 font-weight-bold">관리자 페이지</h3>
						<p class="text-black-50">관리자 페이지로 이동합니다.</p>
						<p><a href="/admin/index" class="learn-more">페이지 이동</a></p>
					</div>
				</div>
				</sec:authorize>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" />

	<script>
		$(function() {
			$('a[id="mypagePoint"]').click(function() {
				alert("준비 중입니다.");
			})
			$('a[id="mypageReply"]').click(function() {
				alert("준비 중입니다.");
			})
		})
	</script>
</body>
</html>