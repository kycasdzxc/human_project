<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../common/head.jsp" />
<title>모여봐요, 동물의 숲 : 게시판</title>
</head>
<body>
	<jsp:include page="../common/nav.jsp" />
	<main>
		<div class="container-fluid px-4">
			<div class="card mb-4">
				<div class="card-header">
					<div class="col-2 float-end">
						<select class="form-select form-amount py-1">
							<option ${page.cri.amount == 5 ? 'selected' : ''} value="5">5개씩 보기</option>
							<option ${page.cri.amount == 10 ? 'selected' : ''} value="10">10개씩 보기</option>
							<option ${page.cri.amount == 20 ? 'selected' : ''} value="20">20개씩 보기</option>
							<option ${page.cri.amount == 30 ? 'selected' : ''} value="30">30개씩 보기</option>
						</select>
					</div>
				</div>
				<table class="table table-hover text-center">
					<thead>
						<tr>
							<th class="col-1">번호</th>
							<th class="col-5">제목</th>
							<th class="col-2">작성자</th>
							<th class="col-1">조회수</th>
							<th class="col-2">작성일</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${boards}" var="board">
						<tr>
							<td>${board.bno}</td>
							<td><a class="link" href="get${page.cri.params2}&bno=${board.bno}">${board.title}</a> <span class="text-secondary small">[${board.replyCnt}]</span></td>
							<td ${empty board.writer ? 'class="text-muted small"' : '' }>
							${empty board.writer ? '(탈퇴회원)' : board.writer}</td>
							<td>${board.hitCount}</td>
							<td>${board.regDate}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div>
					<ul class="pagination justify-content-center">
					<c:if test="${page.prev}">
						<li class="page-item"><a class="page-link" href="list${page.cri.params}&pageNum=${page.cri.pageNum-1}">prev</a></li>
					</c:if>
					<c:forEach begin="${page.start}" end="${page.end}" var="p">
						<li class="page-item ${p == page.cri.pageNum ? 'active' : ''}"><a class="page-link" href="list${page.cri.params}&pageNum=${p}">${p}</a></li>
					</c:forEach>
					<c:if test="${page.next}">
						<li class="page-item"><a class="page-link" href="list${page.cri.params}&pageNum=${page.cri.pageNum+1}">next</a></li>
					</c:if>
					</ul>
				</div>
				<div class="card-footer">
					<a class="btn btn-outline-success float-end" id="btnRegister" href="register${page.cri.params2}"> <i class="fas fa-edit"></i>
						글 작성
					</a>
					<div class="col-3">
						<form class="d-flex">
					        <input class="form-control me-2" type="text" name="keyword" placeholder="Search">
					        <input type="hidden" name="category" value="${page.cri.category}">
					        <button class="btn btn-primary">Search</button>
					      </form>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="../common/footer.jsp" />
	<script>
		$(function() {
			var pageNum = '${page.cri.pageNum}';
			var category = '${page.cri.category}';
			$(".form-amount").change(function() {
				console.log($(this).val());
				location.href='list?amount=' + $(this).val() + "&category=${page.cri.category}&pageNum=${page.cri.pageNum}";
			});
		})
	</script>
</body>
</html>