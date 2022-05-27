<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!doctype html>
<html lang="ko">

<head>
	<jsp:include page="../common/head.jsp" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<title>여수어때 : 회원정보수정</title>
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
					<h2 class="text-secondary heading-2">회원정보수정</h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-lg-5 bg-white p-5">
					<form class="contact-form" method="post" data-aos="fade-up" data-aos-delay="200">
						<div class="row">
							<!-- 아이디 -->
							<div class="col-12">
								<div class="form-group">
									<label class="text-black" for="userId">아이디</label>
									<input type="text" class="form-control" id="userId" name="userId" value="${member.userId}" readonly>
								</div>
							</div>
							
							<!-- 비밀번호 -->
							<div class="col-12">
								<div class="form-group">
									<label class="text-black" for="password">기존 비밀번호</label>
									<input type="password" class="form-control" id="oldPw" >
									<small id="passwordHelpInline" class="text-muted"></small>
								</div>
							</div>
							
							<div class="col-12">
								<div class="form-group">
									<label class="text-black" for="password">신규 비밀번호</label>
									<input type="password" class="form-control" id="pw" name="pw" >
									<small id="passwordHelpInline" class="text-muted"></small>
								</div>
							</div>
							<div class="col-12">
								<div class="form-group">
									<label class="text-black" for="password">비밀번호 확인</label>
									<input type="password" class="form-control" id="pw2" name="pw2" >
									<small id="passwordHelpInline" class="text-muted"></small>
								</div>
							</div>
						</div>
						
						<!-- 이름 -->
						<div class="form-group">
							<label class="text-black" for="name">이름</label>
							<input type="text" class="form-control" id="name" name="name" value="${member.name}" >
							<small id="passwordHelpInline" class="text-muted"></small>
						</div>
						
						<!-- 닉네임  -->
						<div class="form-group">
							<label class="text-black" for="nickName">닉네임</label>
							<input type="text" class="form-control" id="nickName" name="nickName" value="${member.nickName}" >
							<small id="passwordHelpInline" class="text-muted"></small>
						</div>
						
						<!-- 주소 -->
						<div class="form-group">
							<label class="text-black d-block" for="btnAddr"></label>
							<button type="button" class="btn btn-primary w-50 mr-3 d-inline-block" id="btnAddr">주소검색</button>
							<br>
							<p>우편번호<input class="form-control" type="text" id="result3" name="zipNo" placeholder="우편번호" value="${member.zipNo}" readonly></p>
							<p>도로명주소<input class="form-control" type="text" id="result1" name="roadAddr" value="${member.roadAddr}" readonly></p>
							<p>지번주소<input class="form-control" type="text" id="result2" name="jibunAddr" value="${member.jibunAddr}"></p>
							<p>상세주소<input class="form-control" type="text" id="addrDetail" name="addrDetail" value="${member.addrDetail}"></p>
						</div>
						  
						<!-- 이메일 -->
						<div class="form-group mb-3 mr-4 input-group">
							<div class="input-group">
								<label class="text-black" for="email">이메일</label>
							</div>
							<input type="text" class="form-control d-inline-block" id="email" name="email" value="${member.email}" readonly>
							<c:if test="${member.authEmail}">
							<button class="btn btn-success" type="button" id="btnEmail" disabled>인증된 이메일</button>
							</c:if>
							<c:if test="${!member.authEmail}">
							<button class="btn btn-danger" type="button" id="btnEmail">이메일 인증</button>
							</c:if>
							<input type="hidden">
						</div>

						<!-- 핸드폰번호  -->
						<div class="row">
							<label class="text-black" for="phone">핸드폰번호</label>
							<div class="form-group col-4">  
								<select name="phone" id="phone" class="form-control custom-select">
									<option>010</option>
									<option>011</option>
								</select>
							</div>
							<div class="form-group col-4">
								<input type="text" class="form-control d-inline-block" id="phone2" name="phone2" value="${member.phone2}">
							</div>
							<div class="form-group col-4">
								<input type="text" class="form-control d-inline-block" id="phone3" name="phone3" value="${member.phone3}">
							</div>	
						</div>
						
						<!-- 생년월일 -->
						<!-- <div class="row">
							<label class="text-black" for="birthDate">BirthDate</label>
							<div class="form-group col-4">
								<select name="birthDate" id="birthDate" class="form-control custom-select">
									<option>1993</option>
									<option>1994</option>
								</select>
							</div>
							<div class="form-group col-4">
								<select name="birthDate" id="birthDate" class="form-control custom-select">
									<option>8</option>
									<option>9</option>
								</select>
							</div>
							<div class="form-group col-4">	
								<select name="birthDate" id="birthDate" class="form-control custom-select">
									<option>11</option>
									<option>12</option>
								</select>
							</div>
						</div> -->
						
						<sec:csrfInput/>
						
						<button class="btn btn-primary w-50 mr-3 d-inline-block" id="btnMod">수정하기</button>
						
					</form>
				</div>
			</div>
		</div>
	</div>
  
	<jsp:include page="../common/footer.jsp" />
	
	<script>
	
		// 주소 API
		$(function() {
			$("#btnAddr").click(function() {
			    new daum.Postcode({
			        oncomplete: function(data) {
			            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
			            $("#result1").val(data.roadAddress);
			            $("#result2").val(data.jibunAddress);
			            $("#result3").val(data.zonecode);
			        }
			    }).open()
			});
		
			// 유효성 검증 (본인 확인)
			var validateOldPw = function(){
				console.log("validateOldPw()")
				var $p = $("#oldPw").parent();
				var id = $("#userId").val().trim();
				var pw = $("#oldPw").val().trim();
				$.ajax("/member/idcheck", {
					data : {userId : id, pw:pw}
				}).done(function(result) {
					if(pw.length == 0) {
						$p.find("small").text("기존 비밀번호를 입력하세요.")
						$("#oldPw").addClass("is-invalid").removeClass("is-valid")
						return;
					}
					if(result == 0) {
						$p.find("small").text("기존 비밀번호와 일치 하지않습니다.")
						$("#oldPw").addClass("is-invalid").removeClass("is-valid")
						return;
					}
					else {
						$p.find("small").text("")
						$("#oldPw").addClass("is-valid").removeClass("is-invalid")
					}
				})
			}
			
			// 유효성 검증 (비밀번호 UNIQUE 조건)
			var validatePw = function(){
				console.log("validatePw()")
				var $p = $("#pw").parent();
				var val = $("#pw").val().trim();
				console.log(val)
				console.log(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,16}$/.test(val))
				if(val.length == 0) {
					$p.find("small").text("비밀번호를 입력하세요")
					$("#pw").addClass("is-invalid").removeClass("is-valid")
					return;
				}
				else if(!(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,16}$/.test(val))) {
					
					$p.find("small").text("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
					$("#pw").addClass("is-invalid").removeClass("is-valid")
					return;
				} 
				else {
					$p.find("small").text("")
					$("#pw").addClass("is-valid").removeClass("is-invalid")
				}
			}
			
			// 유효성 검증 (비밀번호 일치 여부 확인)
			var validatePw2 = function(){
				console.log("validatePw2()")
				var $p = $("#pw2").parent();
				var val = $("#pw2").val().trim();
				if(val.length == 0) {
					$p.find("small").text("비밀번호 확인을 입력하세요")
					$("#pw2").addClass("is-invalid").removeClass("is-valid")
					return;
				}
				else if(val !== $("#pw").val()) {
					
					$p.find("small").text("비밀번호와 동일한 값을 입력하세요.")
					$("#pw2").addClass("is-invalid").removeClass("is-valid")
					return;
				} 
				else {
					$p.find("small").text("")
					$("#pw2").addClass("is-valid").removeClass("is-invalid")
				}
			}
			
			// 유효성 검증 (이름 중복 확인)
			var validateName = function(){
				console.log("validateName()")
				var $p = $("#name").parent();
				var val = $("#name").val().trim();
				if(val.length == 0) {
					$p.find("small").text("이름을 입력하세요")
					$("#name").addClass("is-invalid").removeClass("is-valid")
					return;
				}
				else {
					$p.find("small").text("")
					$("#name").addClass("is-valid").removeClass("is-invalid")
				}
			}
				
			// 유효성 검증 (닉네임 중복 확인)
			var validateNickName = function(){
				console.log("validateNickName()")
				var id = $("#userId").val();
				var $p = $("#nickName").parent();
				var val = $("#nickName").val().trim();
				$.ajax("/member/idcheck", {
					data : {userId: id, nickName : val}
				}).done(function(result) {
					if(val.length == 0) {
						$p.find("small").text("닉네임을 입력하세요")
						$("#nickName").addClass("is-invalid").removeClass("is-valid")
						return;
					}
					if(result == 1) {
						$p.find("small").text("이미 사용중인 닉네임 입니다.")
						$("#nickName").addClass("is-invalid").removeClass("is-valid")
						return;
					}
					else {
						$p.find("small").text("")
						$("#nickName").addClass("is-valid").removeClass("is-invalid")
					}
				})
			}
			
			// 유효성 검증 (이메일 중복 확인/ UNIQUE 조건)
			var validateEmail = function(){
				console.log("validateEmail()")
				var id = $("#userId").val();
				var $p = $("#email").parent();
				var val = $("#email").val().trim();
				$.ajax("/member/idcheck", {
					data : {userId: id, email : val}
				}).done(function(result) {
					if(val.length == 0) {
						$p.find("small").text("이메일을 입력하세요")
						$("#email").addClass("is-invalid").removeClass("is-valid")
						return;
					}
					if(!(/^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(val))) {
						$p.find("small").text("유형에 맞는 이메일을 입력하세요")
						$("#email").addClass("is-invalid").removeClass("is-valid")
						return;
					}
					if(result == 1) {
						$p.find("small").text("이미 사용중인 이메일 입니다.")
						$("#email").addClass("is-invalid").removeClass("is-valid")
						return;
					}
					else {
						$p.find("small").text("")
						$("#email").addClass("is-valid").removeClass("is-invalid")
					}
				})
			}
			
			// 유효성 검증 (전화번호 필수 입력 조건)
			var validatePhone2 = function(){
				console.log("validatePhone2()")
				var $p = $("#phone2").parent();
				var val = $("#phone2").val().trim();
				if(!(/\d{3,4}/.test(val))) {
					$p.find("small").text("전화번호를 입력하세요")
					$("#phone2").addClass("is-invalid").removeClass("is-valid")
					return;
				}
				else {
					$p.find("small").text("")
					$("#phone2").addClass("is-valid").removeClass("is-invalid")
				}
			}
			var validatePhone3 = function(){
				console.log("validatePhone3()")
				var $p = $("#phone3").parent();
				var val = $("#phone3").val().trim();
				if(!(/\d{4}/.test(val))) {
					$p.find("small").text("전화번호를 입력하세요")
					$("#phone3").addClass("is-invalid").removeClass("is-valid")
					return;
				}
				else {
					$p.find("small").text("")
					$("#phone3").addClass("is-valid").removeClass("is-invalid")
				}
			}
				
			// 유효성 검증 (focusout 시 유효성 검증 메서드 실행)
			$("#oldPw").on("focusout", validateOldPw);
			$("#pw").on("focusout", validatePw);
			$("#pw2").on("focusout", validatePw2);
			$("#name").on("focusout", validateName);
			$("#nickName").on("focusout", validateNickName);
			$("#email").on("focusout", validateEmail);
			$("#phone2").on("focusout", validatePhone2);
			$("#phone3").on("focusout", validatePhone3);
	        	
			// 유효성 검증 (최종 수정 button 클릭시, 유효성 검증 여부에 따라 미적용 태그로 포커스 이동)
	       	$("#btnMod").click(function() {
	     		if(!$("#phone3").hasClass("is-valid")) {
	     			validatePhone3();
	     			$("#phone3").focus();
	     			event.preventDefault();
	     		}
	     		if(!$("#phone2").hasClass("is-valid")) {
	     			validatePhone2();
	     			$("#phone2").focus();
	     			event.preventDefault();
	     		}
	     		if(!$("#email").hasClass("is-valid")) {
	     			validateEmail();
	     			$("#email").focus();
	     			event.preventDefault();
	     		}
	     		if(!$("#nickName").hasClass("is-valid")) {
	     			validateNickName();
	     			$("#nickName").focus();
	     			event.preventDefault();
	     		}
	     		if(!$("#name").hasClass("is-valid")) {
	     			validateName();
	     			$("#name").focus();
	     			event.preventDefault();
	     		}
	     		if(!$("#pw2").hasClass("is-valid")) {
	     			validatePw2();
	     			$("#pw2").focus();
	     			event.preventDefault();
	     		}
	     		if(!$("#pw").hasClass("is-valid")) {
	     			validatePw();
	     			$("#pw").focus();
	     			event.preventDefault();
	     		}
	     		if(!$("#oldPw").hasClass("is-valid")) {
	     			validateOldPw();
	     			$("#oldPw").focus();
	     			event.preventDefault();
	     		}
	     	})  
		})
		
		// 유효성 검증 (수정 시 이메일 인증 확인 여부에 따라 회원정보수정 가능/ 미인증 0, 인증 1)
		$("#btnEmail").click(function() {
       		var $btnEmail = $(this);
       		var str = '<img src="https://i.stack.imgur.com/qq8AE.gif" width="20">';
       		console.log("clicked!");
       		var data =  {email : $("#email").val(), userId : $("#userId").val()}
       		$.ajax("/member/sendmail", {
       			data : data,
       			method : "get",
       			beforeSend : function() {
       				$btnEmail.prop("disabled", true).html(str + "발송중");
       			},
       			success : function(data) {
       				console.log(data);
       				$btnEmail.prop("disabled", false).html("이메일 인증");
       			}
       		}) 
       	});
		
		var emailAuthresult = '${result}' 
	 </script>
</body>
</html>