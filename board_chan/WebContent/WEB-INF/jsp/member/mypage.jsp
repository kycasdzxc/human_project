<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../common/head.jsp" />
<title>모여봐요, 동물의 숲 : 정보수정</title>
</head>
<body>
	<jsp:include page="../common/nav.jsp" />
	<main>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-10">
					<div class="card shadow-lg border-0 rounded-lg">
						<div class="card-header"><h3 class="text-center font-weight-light my-4">My Account</h3></div>
						<div class="card-body">
							<form method="post" enctype="multipart/form-data">
								<div class="row px-3">
									<div class="col-4 container-fluid text-center">
										<img onerror="this.src='${cp}images/noimage.png'" class="border border-secondary mb-2" style="width:210px; height:210px;" src="${cp}display?uuid=s_${memberInfo.attach.uuid}&path=${memberInfo.attach.path}" alt="${memberInfo.attach.origin}">
										<input type="file" class="form-control mt-3" id="file" name="file">
									</div>
									<div class="col-8">
										<div class="row mb-3">
											<div class="col-md-8">
												<div class="form-floating mb-3 mb-md-0">
													<input class="form-control" id="name" name="name" type="text" placeholder="Modify name" value="${memberInfo.name}" />
													<label for="name">name</label>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-floating mb-3 mb-md-0">
													<input class="form-control" id="id" type="text" name="id" placeholder="enter" value="${memberInfo.id}" readonly />
													<label for="id">ID</label>
												</div>
											</div>
										</div>
										<div class="row mb-3">
											<div class="col-md-6">
												<div class="form-floating mb-3 mb-md-0">
													<input class="form-control" id="inputPasswordConfirm" name="pw" type="password" value="${memberInfo.pw}" placeholder="Modify password" />
													<label for="inputPasswordConfirm">Password</label>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-floating mb-3 mb-md-0">
													<input class="form-control" id="inputPasswordConfirm" name="pw2" type="password" value="${memberInfo.pw}" placeholder="Confirm password" />
													<label for="inputPasswordConfirm">Confirm Password</label>
												</div>
											</div>
										</div>
										<div class="row mb-3">
											<div class="col-md">
												<div class="form-floating mb-3 mb-md-0">
													<input class="form-control" id="inputPassword" name="speak" type="text" value="${memberInfo.speak}" placeholder="Modify comment" />
													<label for="inputPassword">Comment</label>
												</div>
											</div>
										</div>
										<div class="row mb-2">
											<div class="col-9">
												<div class="form-floating">
													<input class="form-control" id="email" name="email" type="email" placeholder="enter email" value="${memberInfo.email}" readonly />
													<label for="email">email</label>
												</div>
											</div>
											<div class="d-grid col-3">
												<c:if test="${memberInfo.auth == 0}">
													<button class="btn btn-danger" type="button" id="btnEmail">이메일 인증</button>
												</c:if>
												<c:if test="${memberInfo.auth == 1}">
													<button class="btn btn-success" type="button" disabled >인증된 이메일</button>
												</c:if>
												<input type="hidden" value="1" id="chkEmail">
											</div>
										</div>
									</div>
								</div>
								<hr>
								<div class="row mb-3 px-3">
									<div class="col-md-7">
										<div class="mb-3 mb-md-0">
											<input class="form-control" id="roadAddr" name="roadAddr" readonly value="${memberInfo.roadAddr}" />
										</div>
									</div>
									<div class="col-md-3">
										<div class="mb-3 mb-md-0">
											<input class="form-control" id="addrDetail" name="addrDetail" value="${memberInfo.addrDetail}" />
										</div>
									</div>
									<div class="d-grid col-md-2">
										<button type="button" id="btnSearchAddr" class="btn btn-sm btn-secondary">주소검색</button>
									</div>
									<input type="hidden" name ="si" id="si" value="${memberInfo.si}">
									<input type="hidden" name ="sgg" id="sgg" value="${memberInfo.sgg}">
									<input type="hidden" name ="emd" id="emd" value="${memberInfo.emd}">
									<input type="hidden" name ="zipNo" id="zipNo" value="${memberInfo.zipNo}">
									<input type="hidden" name ="roadFullAddr" id="roadFullAddr" value="${memberInfo.roadFullAddr}">
									<input type="hidden" name ="jibunAddr" id="jibunAddr" value="${memberInfo.jibunAddr}">
								</div>
								<hr>
								<div class="row px-3">
									<div class="d-grid col-6"><button class="btn btn-outline-primary btn-block py-3">회원정보 수정</button></div>
									<div class="d-grid col-6"><button class="btn btn-outline-danger btn-block py-3" onclick="return confirm('정말로 삭제하시겠습니까?')" formaction="secession">회원정보 삭제</button></div>
								</div>
							</form>
						</div>
						<div class="card-footer text-center py-3"></div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="../common/footer.jsp" />
	<script>
		$(function() {
			var cp = '${pageContext.request.contextPath}';
			$("#btnSearchAddr").click(function() {
				var pop = window.open("${pageContext.request.contextPath}/juso","pop","width=570,height=420, scrollbars=yes, resizable=yes");
			});
		              
			$("#btnEmail").click(function() {
				var $btnEmail = $(this);
				var str = '<img src="https://i.stack.imgur.com/qq8AE.gif" width="16">';
				console.log("clicked!");
				var data = {email : $("#email").val(), id : $("#id").val()}
				$.ajax(cp + "/member/memberAuth", {
					data : data,
					method : "get",
					beforeSend : function() {
						$btnEmail.prop("disabled", true).html(str + " 발송중");
					},
					success : function(data) {
						$btnEmail.prop("disabled", false).html("이메일 인증");
						alert("인증메일이 발송되었습니다.")
						console.log(data);
					}
				})
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