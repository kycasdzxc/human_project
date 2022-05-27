<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <jsp:include page="../common/head.jsp" />
    <title>모여봐요, 동물의 숲 : 글내용</title>
</head>
<body>
    <jsp:include page="../common/nav.jsp" />
	<main>
		<div class="container-fluid px-4">
			<div class="card mb-4">
				<div class="card-body">
					<form>
						<div class="mb-3 mt-3">
							<label for="bno" class="form-label"><i class="fas fa-list-ol"></i> bno</label>
							<input type="text" class="form-control" id="bno" name="bno" value="${board.bno}" disabled>
						</div>
						<div class="mb-3">
							<label for="title" class="form-label"><i class="fas fa-heading"></i> title</label>
							<input type="text" class="form-control" id="title" name="title" value="${board.title}" disabled>
						</div>
						<div class="mb-3">
							<label for="content" class="form-label"><i class="far fa-comment"></i> content</label>
							<textarea class="form-control" rows="5" id="content" name="content" disabled>${board.content}</textarea>
						</div>
						<div class="mb-3">
							<label for="regDate" class="form-label"><i class="far fa-clock"></i> regDate</label>
							<input type="text" class="form-control" id="regDate" name="regDate" value="${board.regDate}" disabled>
						</div>
						<div class="mb-3">
							<label for="writer" class="form-label"><i class="far fa-user"></i> writer</label>
							<input type="text" class="form-control ${empty board.writer ? 'text-muted small' : '' }" id="writer" name="writer" value="${empty board.writer ? '(탈퇴회원)' : board.writer}" disabled>
						</div>
						<div class="mb-3">
						    <div class="clearfix">
							    <span class="form-label mb-4"><i class="far fa-edit"></i> replies</span>
						    </div>
							<c:if test="${not empty member}">
						    <div class="row py-2">
						    	<div class="col-10 d-grid">
						    		<textarea class="" id="replyContent"></textarea>
						    	</div>
						    	<div class="col-2 d-grid">
						    		<button class="btn btn-primary" id="btnReplyReg" type="button">글 등록</button>
						    	</div>
						    </div>
						    </c:if>
							<c:if test="${empty member}">
						    <div class="row py-2">
						    	<div class="col-10 d-grid">
						    		<textarea class="" id="replyContent"></textarea>
						    	</div>
						    	<div class="col-2 d-grid">
						    		<button class="btn btn-primary" id="checkedLogin" onclick="location.href='${cp}member/login'" type="button">글 등록</button>
						    	</div>
						    </div>
						    </c:if>
						    <ul class="list-group list-group-flush my-3 small replies">
						    	
						    </ul>
						</div>
						<a href="list${cri.params2}" class="btn btn-outline-secondary">list</a>
						<c:if test="${not empty member && member.name == board.writer}">
							<a href="modify${cri.params2}&bno=${board.bno}" class="btn btn-outline-warning">modify</a>
							<a href="remove${cri.params2}&bno=${board.bno}" class="btn btn-outline-danger" onclick="return confirm('삭제하시겠습니까?')">remove</a>
						</c:if>
					</form>
				</div>
			</div>
		</div>
    </main>
	<jsp:include page="../common/footer.jsp" />
	<script>
		$("#checkedLogin").click(function() {
			alert("댓글을 작성하려면 로그인을 해주세요.");
	    });
	
		const cp = '${pageContext.request.contextPath}';

		$(function() {
			const bno = '${board.bno}';
			const writer = '${member.name}';
			showList();
		
			function showList() {
				replyService.list(bno, function(data) {
					console.log(data);
					var str = "";
					for(var i in data) {
						str += '';
str +='				<li class="list-group-item" data-rno="' + data[i].rno + '">'
str +='					<div class="list-group-item list-group-item-info small">'
str +='						<span>' + data[i].writer + '</span>'
str +='						<span class="small float-end">' + data[i].regDate + '</span>'
					if(writer == data[i].writer) {
str +='						<span class="small float-end mx-2 btnReplyRemove"><i class="far fa-trash-alt" style="cursor:pointer"></i></span>'
str +='						<span class="small float-end btnReplyModify"><i class="fas fa-pencil-alt" style="cursor:pointer"></i></span>'
					}
					else {}
str +='					</div>'
str +='					<div class="list-group-item">' + data[i].content + '</div>'
str +='				</li>'
					}
					$(".replies").html(str);
				}, cp);
			}

			// 댓글 삭제
			$(".replies").on("click", ".btnReplyRemove", function() {
				var rno = $(this).closest("li").data("rno");
				var reply = {"rno":rno};
				replyService.remove(reply, function(data) {
					alert("댓글이 삭제되었습니다.");
					showList();
				}, cp);
			});
			
			// 댓글 수정 : 준비 중입니다.
			$(".replies").on("click", ".btnReplyModify", function() {
					alert("준비 중입니다.");
			});

			// 댓글 등록 버튼 클릭 이벤트
			$("#btnReplyReg").click(function() {
				var name = '${memberInfo.name}'
				var reply =
				{bno:bno, content:$("#replyContent").val(), writer:name};
				if($("#replyContent").val()) {
					replyService.add(reply, function(data) {
						showList();
						$("#replyContent").val("");
						alert("댓글이 등록되었습니다.");
					}, cp);
				} else {
					alert("댓글을 입력해주세요.")
				}
			});
		});
	</script>
</body>
</html>