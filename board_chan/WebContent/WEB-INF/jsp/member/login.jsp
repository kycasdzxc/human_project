<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../common/head.jsp" />
<title>모여봐요, 동물의 숲 : 로그인</title>
</head>
<body>
	<jsp:include page="../common/nav.jsp" />
	<main>
		<div class="row justify-content-center">
			<div class="col-4">
				<div class="card shadow-md border-1 rounded-md">
					<div class="card-header">
						<h3 class="text-center font-weight-light my-2">Login</h3>
					</div>
					<div class="card-body">
						<form method="post" name="login">
							<div class="form-floating mb-4">
								<input class="form-control" id="inputEmail" type="text" placeholder="name@example.com" name="id" />
								<label for="inputEmail">ID</label>
							</div>
							<div class="form-floating mb-4">
								<input class="form-control" id="inputPassword" type="password" placeholder="Password" name="pw" />
								<label for="inputPassword">Password</label>
							</div>
							<div class="d-flex align-items-center justify-content-between mt-4 mb-0">
								<a class="small link" id="forgetPw" style="cursor:pointer">Forgot Password?</a>
								<button class="btn btn-primary px-3">Login</button>
							</div>
						</form>
					</div>
					<div class="card-footer text-center py-3">
						<div class="small">
							<a class="link" href="contract">입주신청은 여기를 누르면 된다구리!</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="../common/footer.jsp" />
	<script>
		$("form").submit(function() {
			if(!document.login.id.value) {
		        alert("아이디를 입력하세요")
		        document.login.id.focus();
		        return false;
			}
			if(!document.login.pw.value) {
		        alert("비밀번호를 입력하세요")
		        document.login.pw.focus();
		        return false;
			}
		});
	    $("#forgetPw").click(function() {
			alert("준비 중입니다.");
	    });
    </script>
</body>
</html>