<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../common/head.jsp" />
<title>모여봐요, 동물의 숲 : 주민정보</title>
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
								<div class="row px-3">
									<div class="col-4 container-fluid text-center">
										<img onerror="this.src='${cp}images/noimage.png'" class="border border-secondary" style="width:210px; height:210px;" src="${cp}display?uuid=s_${memberInfo.attach.uuid}&path=${memberInfo.attach.path}" alt="${memberInfo.attach.origin}">
									</div>
									<div class="col-8">
										<div class="row mb-3">
											<div class="col-md-8">
												<div class="form-floating mb-3 mb-md-0">
													<input class="form-control" id="name" name="name" type="text" placeholder="Modify name" value="${memberInfo.name}" readonly />
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
											<div class="col-md">
												<div class="form-floating mb-3 mb-md-0">
													<input class="form-control" id="inputPassword" name="speak" type="text" value="${memberInfo.speak}" placeholder="Modify comment" readonly />
													<label for="inputPassword">Comment</label>
												</div>
											</div>
										</div>
										<div class="row">
										<c:if test="${member.id == memberInfo.id}">
											<div class="d-grid col-6"><button class="btn btn-outline-info btn-block py-3" onclick="location.href='myPage'">회원정보 수정</button></div>
											<div class="d-grid col-6"><button class="btn btn-outline-warning btn-block py-3" onclick="location.href='${cp}board/list?category=2&keyword=${memberInfo.name}'">게시글 확인</button></div>
										</c:if>
										<c:if test="${member.id != memberInfo.id}">
											<div class="d-grid col-6"><button class="btn btn-outline-info btn-block py-3" onclick="location.href='${cp}board/list?category=2&keyword=${memberInfo.name}'">게시글 보기</button></div>
											<c:if test="${not empty member}">
											<div class="d-grid col-6"><button class="btn btn-outline-warning btn-block py-3" type="button" id="btnChatReg">쪽지 보내기</button></div>
											</c:if>
											<c:if test="${empty member}">
											<div class="d-grid col-6"><button class="btn btn-outline-warning btn-block py-3" onclick="location.href='login?info=${memberInfo.id}'">쪽지 보내기 (로그인)</button></div>
											</c:if>
										</c:if>
										</div>
									</div>
								</div>
								<c:if test="${not empty member}">
								<hr>
								<h3 class="mt-3 mx-3">- ${memberInfo.name} 님의 쪽지이력</h3>
								<ul class="list-group list-group-flush my-3 small chats">
						    	
						    	</ul>
								</c:if>
							</div>
						<div class="card-footer text-center py-3"></div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!-- The Modal -->
		<div class="modal" id="chatModal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title">Modal Heading</h4>
		        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		      </div>
		
		      <!-- Modal body -->
		      <div class="modal-body">
				  <div class="mb-3">
				      <label for="replyWriter" class="form-label"><i class="fas fa-pencil-alt"></i> 보낸사람</label>
				      <input type="text" class="form-control" id="sender" name="sender">
				  </div>
				  <div class="mb-3">
				      <label for="replyWriter" class="form-label"><i class="fas fa-envelope-open-text"></i> 받는사람</label>
				      <input type="text" class="form-control" id="receiver" name="receiver">
				  </div>
				  <div class="mb-3">
				      <label for="replyContent" class="form-label"><i class="far fa-comment"></i> 쪽지내용</label>
				      <textarea class="form-control" id="chatContent" name="chatContent"></textarea>
				  </div>
				  <div class="mb-3">
				      <label for="replyRegDate" class="form-label"><i class="far fa-clock"></i> 보낸시간</label>
				      <input type="text" class="form-control" id="sDate" name="sDate">
				  </div>
				  <div class="mb-3">
				      <label for="replyRegDate" class="form-label"><i class="far fa-clock"></i> 읽은시간</label>
				      <input type="text" class="form-control" id="rDate" name="rDate">
				  </div>
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary">쪽지 보내기</button>
		        <button type="button" class="btn btn-warning">Modify</button>
		        <button type="button" class="btn btn-danger">Remove</button>
		      </div>
		
		    </div>
		  </div>
		</div>
		<%@ include file="../common/footer.jsp" %>
		<script>
			const cp = '${pageContext.request.contextPath}';

			$(function() {
				const cno = '${chat.cno}';
				const sender = '${member.name}';
				const receiver = '${memberInfo.name}';
				showList();
				
				function showList() {
					chatService.list(sender, receiver, function(data) {
						console.log(data);
						var str = "";
						for(var i in data) {
							str += '';
str +='				<li class="list-group-item" data-cno="' + data[i].cno + '">'
str +='					<div class="list-group-item list-group-item-warning small">'
					if(data[i].rDate == null) {
str +='						<span><i class="fas fa-envelope"></i>　' + data[i].sender + "　>　" + data[i].receiver + '　<span class="text-danger">(읽지않음)</span> </span>'						
					} else {
str +='						<span><i class="fas fa-envelope-open"></i>　' + data[i].sender + "　>　" + data[i].receiver + '　<span class="text-primary">(읽음)</span> </span>'						
					}
str +='						<span class="small float-end">' + data[i].sDate + '</span>'
str +='					</div>'
str +='					<div class="list-group-item">' + data[i].content + '</div>'
str +='				</li>'
						}
						$(".chats").html(str);
					}, cp);
				}
				
				// 쪽지 상세 조회
				$(".chats").on("click", "li", function() {
					chatService.get($(this).data("cno"), sender, function(data) {
						console.log(data);
						
						// 값 부여
						$("#cno").val(data.cno);
						$("#sender").val(data.sender);
						$("#receiver").val(data.receiver);
						$("#chatContent").val(data.content);
						$("#sDate").val(data.sDate);
						if(data.rDate == null) {
						$("#rDate").val("읽지않음");
						}
						else {
						$("#rDate").val(data.rDate);
						}
						
						$("#chatModal")
						.data("cno", data.cno)
							.find(".modal-footer button").hide()
						.end()
							.find("input, textarea").prop("disabled", true)
						.end().modal("show");
					}, cp);
				});
				
				// 쪽지 보내기 창 활성화
				$("#btnChatReg").click(function() {
					$("#chatModal .modal-body div").eq(0).hide();
					$("#chatModal .modal-body div").eq(3).hide();
					$("#chatModal .modal-body div").eq(4).hide();
					
					$("#chatModal")
						.find(".modal-footer button").hide()
							.eq(0).show()
						.end()
					.end()
						.find("input, textarea").prop("disabled", false).val("");
					
					$("#chatModal .modal-body div").eq(1).find("input").prop("disabled", true).val(receiver);
					$("#chatModal").modal("show");
				});

				// 쪽지 보내기 버튼 클릭 이벤트
				$("#chatModal .modal-footer button:eq(0)").click(function() {
					var chat =
					{sender:sender, receiver:receiver, content:$("#chatContent").val()};
					if($("#chatContent").val()) {
						chatService.add(chat, function(data) {
							showList();
							$("#chatModal").modal("hide")
						}, cp);
					}
					else {
						alert("내용을 입력해주세요.")
					}
				});
			});
	</script>
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