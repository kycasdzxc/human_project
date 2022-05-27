<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!doctype html>
<html lang="ko">

<head>
	<jsp:include page="../common/head.jsp" />
	
	<title>여수어때 : 로그인</title>
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
	
	<jsp:include page="../common/nav.jsp" />

	<div class="untree_co-section2 bg-light">
		<div class="container">
			<div class="row justify-content-center text-center mb-5">
				<div class="col-lg-6">
					<h2 class="text-secondary heading-2">로그인</h2>
				</div>
			</div>
			
			<div class="row justify-content-center">
				<div class="col-lg-5 bg-white p-5">
					<form class="user" action="/login" method="post" data-aos="fade-up" data-aos-delay="200">
					
						<div class="form-group">
							<label class="text-black" for="ID">아이디</label>
							<input type="text" class="form-control" id="username" name="username">
						</div>
						
						<div class="form-group">
							<label class="text-black" for="password">비밀번호</label>
							<input type="password" class="form-control" id="password" name="password" >
						</div>
						
						<div class="mb-4 ">
							<button class="btn btn-primary" id="btnLogin">로그인</button>
							<div class="custom-control custom-switch float-right mt-2">
								<input type="checkbox" class="custom-control-input" id="rememberMe" name="remember-me">
								<label class="custom-control-label" for="rememberMe">자동로그인</label>
							</div>
						</div>

						<div class="form-group">
							<p><small>아직 회원이 아니신가요?<a href="join">  회원가입</a></small></p>
						</div>
						<sec:csrfInput/>
					</form>
				</div>
			</div>
		</div>
	</div>
  
	<jsp:include page="../common/footer.jsp" />
  
</body>
</html>