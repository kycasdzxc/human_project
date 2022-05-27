<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../common/head.jsp" />
<title>모여봐요, 동물의 숲 : 회원가입</title>
</head>
<body>
	<jsp:include page="../common/nav.jsp" />
	<main>
		<div class="row justify-content-center">
			<div class="col-6">
				<div class="card border-1 rounded-md">
					<div class="card-header">
						<h3 class="text-center my-4">입주신청서</h3>
					</div>
					<div class="card-body">
						<form method="post" name="join">
							<div class="row mb-4">
								<div class="col-9">
									<div class="form-floating mb-2">
										<input class="form-control" id="id" type="text" name="id" placeholder="enter" />
										<label for="id">ID</label>
									</div>
								</div>
								<div class="d-grid col-md-3 mb-2">
									<button class="btn btn-success" type="button" id='btnId'>ID중복 체크</button>
									<input type="hidden" value="1" id="chkId">
								</div>
							</div>
							<div class="row mb-4">
								<div class="col-6">
									<div class="form-floating mb-2">
										<input class="form-control" id="inputPassword" name="pw" type="password" placeholder="Create a password" />
										<label for="inputPassword">Password</label>
									</div>
								</div>
								<div class="col-6">
									<div class="form-floating mb-2">
										<input class="form-control" id="inputPasswordConfirm"
											name="pw2" type="password" placeholder="Confirm password" />
										<label for="inputPasswordConfirm">Confirm Password</label>
									</div>
								</div>
							</div>
							<div class="row mb-4">
								<div class="col">
									<div class="form-floating mb-2">
										<input class="form-control" id="name" name="name" type="text" placeholder="enter name" />
										<label for="name">name</label>
									</div>
								</div>
							</div>
							<div class="row mb-2">
								<div class="col-9">
									<div class="form-floating">
										<input class="form-control" id="email" name="email" type="email" placeholder="enter email" />
										<label for="name">email</label>
									</div>
								</div>
								<div class="d-grid col-3">
									<button class="btn btn-success" type="button" id="btnEmail">Email중복 체크</button>
									<input type="hidden" value="1" id="chkEmail">
								</div>
							</div>
							<hr>
							<div class="row mb-4">
								<div class="col-md-7">
									<div class="mb-3 mb-md-0">
										<input class="form-control" id="roadAddr" name="roadAddr" readonly />
									</div>
								</div>
								<div class="col-md-3">
									<div class="mb-3 mb-md-0">
										<input class="form-control" id="addrDetail" name="addrDetail" readonly />
									</div>
								</div>
								<div class="d-grid col-md-2">
									<button type="button" id="btnSearchAddr" class="btn btn-sm btn-secondary">주소검색</button>
								</div>
								<input type="hidden" name="si" id="si">
								<input type="hidden" name="sgg" id="sgg">
								<input type="hidden" name="emd" id="emd">
								<input type="hidden" name="zipNo" id="zipNo">
								<input type="hidden" name="roadFullAddr" id="roadFullAddr">
								<input type="hidden" name="jibunAddr" id="jibunAddr">
							</div>
							<div class="mt-4 mb-0">
								<div class="d-grid">
									<button class="py-3 btn btn-primary btn-block">입주 신청하기</button>
								</div>
							</div>
						</form>
					</div>
					<div class="card-footer text-center py-3">
						<div class="small">
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="../common/footer.jsp" />
	<script>
		$(function() {
			var cp = '${pageContext.request.contextPath}';
			var id = document.join.id;
			var pw = document.join.pw;
			var pw2 = document.join.pw2;
			var name = document.join.name;
			var email = document.join.email;
			
			$("#btnSearchAddr").click(function() {
				var pop = window.open("${pageContext.request.contextPath}/juso","pop","width=570,height=420, scrollbars=yes, resizable=yes");
			});
			
			$("#btnId").click(function() {
				var id = {id : $("#id").val()}
				$.ajax(cp + "/member/findMember", {
					data : id,
					method : "get",
					success : function(data) {
					if(!document.join.id.value) {
						alert("아이디를 입력하세요.")
						document.join.id.focus();
						return false;
					}
					if(data == 1) {
						alert("이미 사용 중인 아이디입니다.")
						document.join.id.focus();
					} else {
						alert("사용 가능한 아이디입니다.")
						document.join.pw.focus();
					}
					$("#chkId").val(data);
					}
				})
			})
			   
			$("#id").change(function() {
			$("#chkId").val(1);
			});
			      
			$("#btnEmail").click(function() {
				var email = {email : $("#email").val()}
				$.ajax(cp + "/member/findMember", {
					data : email,
					method : "get",
					success : function(data) {
					if(!document.join.email.value) {
						alert("이메일을 입력하세요.")
						document.join.email.focus();
					return false;
					}
					if(data == 1) {
						alert("이미 사용 중인 이메일입니다.")
						document.join.email.focus();
					} else {
						alert("사용 가능한 이메일입니다.")
					}
					$("#chkEmail").val(data);
					}
				})
			})
			      
			$("#email").change(function() {
				$("#chkEmail").val(1);
			});
			   
			$("form").submit(function() {
				if(!id.value) {
					alert("아이디를 입력하세요")
					id.focus();
					return false;
				}
				for (i = 0 ; i < id.value.length ; i++) {
				    if (!(id.value.charAt(i) >= '0' && id.value.charAt(i) <= '9')
				    		&& !(id.value.charAt(i) >= 'A' && id.value.charAt(i) <= 'Z')
				    		&& !(id.value.charAt(i) >= 'a' && id.value.charAt(i) <= 'z')) {
						id.focus();
				    	alert("영문자 또는 숫자만 입력해주세요.")
				        return false;
						break;
				    }
				}
				 if($("#chkId").val()) {
					alert("ID 중복 체크를 해주세요.")
					return false;
				} 
				if(!pw.value) {
					alert("비밀번호를 입력하세요")
					pw.focus();
					return false;
				}
				if(pw.value != pw2.value) {
					alert("비밀번호를 확인해주세요")
					pw2.focus();
					return false;
				}
				if(!name.value) {
					alert("이름을 입력하세요")
					name.focus();
					return false;
				}
				for (i = 0 ; i < name.value.length ; i++) {
				    if (!(name.value.charAt(i) >= '가' && name.value.charAt(i) <= '힣')
				    		&& !(name.value.charAt(i) >= 'A' && name.value.charAt(i) <= 'Z')
				    		&& !(name.value.charAt(i) >= 'a' && name.value.charAt(i) <= 'z')) {
						name.focus();
				    	alert("올바른 문자를 입력해주세요.")
				        return false;
						break;
				    }
				}
				if(!email.value) {
					alert("이메일을 입력하세요")
					email.focus();
					return false;
				} else if($("#chkEmail").val()) {
					alert("이메일 중복 체크를 해주세요.")
					return false;
				}
			})
		});
		  
		function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
			$("#si").val(siNm);
			$("#sgg").val(sggNm);
			$("#emd").val(emdNm);
			$("#roadAddr").val(roadAddrPart1);
			$("#addrDetail").val(addrDetail);
			$("#zipNo").val(zipNo);
			$("#roadFullAddr").val(roadFullAddr);
			$("#jibunAddr").val(jibunAddr);
		}
	</script>
</body>
</html>