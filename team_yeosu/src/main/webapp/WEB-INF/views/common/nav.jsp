<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!--  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> -->
	<!-- nav -->
	<nav class="site-nav">
		<div class="container">
			<div class="site-navigation">
				<a href="${pageContext.request.contextPath}/index" class="logo m-0">여수어때 <i class="fas fa-bed"></i></a>
				
				<ul class="js-clone-nav d-none d-lg-inline-block text-left float-right site-menu">
				 	<li><a href="${pageContext.request.contextPath}/map" class="pt-0"><span class="h5"><i class="fas fa-map-marked-alt"></i></span></a></li>
					<li><a href="${pageContext.request.contextPath}/pension/list" class="pt-0">펜션목록 <i class="far fa-home-heart"></i></a></li>
					<li><a href="${pageContext.request.contextPath}/member/mypage/listReservation" class="pt-0">예약내역</a></li>
					<li class="has-children" class="pt-0">
						<a href="#">더보기</a>
						<ul class="dropdown">
							<li><a href="#" id="notice">공지사항</a></li>
							<li><a href="#" id="event">이벤트</a></li>
							<div class="dropdown-divider"></div>
							<li><a href="${pageContext.request.contextPath}/member/mypage/listInquiry">1:1 문의</a></li>
						</ul>
					</li>
					
					<sec:authorize access="isAnonymous()">
						<li><a href="${pageContext.request.contextPath}/member/join" class="pt-0">회원가입</a></li> 
						<li class="cta-button active"><a href="${pageContext.request.contextPath}/member/login">로그인</a></li>
					</sec:authorize>
					
					<sec:authorize access="isAuthenticated()">
					<li class="has-children" class="pt-0">
						<a href="#"><sec:authentication property="principal.member.nickName"/></a>
						<ul class="dropdown">
							<li><a href="${pageContext.request.contextPath}/member/mypage">마이페이지</a></li>
							<li><a href="#" id="scrollPoint">포인트내역 조회</a></li>
							<li><a href="#" id="scrollReply">내가 쓴 댓글 조회</a></li>
							<li><a href="${pageContext.request.contextPath}/member/mypage/listInquiry">1:1 문의내역</a></li>
							<div class="dropdown-divider"></div>
							<li><a href="#" onclick="event.preventDefault(); confirm('로그아웃 하시겠습니까?') ? document.logout.submit() : ''">로그아웃</a></li>
						</ul>
					</li>
					</sec:authorize>
				</ul>
				
				<a href="#" class="burger ml-auto float-right site-menu-toggle js-menu-toggle d-inline-block d-lg-none" data-toggle="collapse" data-target="#main-navbar">
				</a>
				<form action="/logout" name="logout" method="post" onsubmit="return confirm('로그아웃 하시겠습니까?')">
					<sec:csrfInput/>
				</form>
			</div>
		</div>
	</nav>
	<script>
		$(function() {
			$('a[id="notice"]').click(function() {
				alert("준비 중입니다.");
			})
			$('a[id="event"]').click(function() {
				alert("준비 중입니다.");
			})
			$('a[id="scrollPoint"]').click(function() {
				alert("준비 중입니다.");
			})
			$('a[id="scrollReply"]').click(function() {
				alert("준비 중입니다.");
			})
		})
	</script>