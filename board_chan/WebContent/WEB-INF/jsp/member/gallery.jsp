<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../common/head.jsp" />
<title>모여봐요, 동물의 숲 : 주민목록</title>
</head>
<body>
	<jsp:include page="../common/nav.jsp" />
	<main>
		<div class="container-fluid px-4">
			<div class="card mb-4">
				<div class="card-header">
					<div class="col-2 float-end">
						<select class="form-select form-amount">
							<option ${page.cri.amount == 5 ? 'selected' : ''} value="5">5개씩 보기</option>
							<option ${page.cri.amount == 10 ? 'selected' : ''} value="10">10개씩 보기</option>
							<option ${page.cri.amount == 15 ? 'selected' : ''} value="15">15개씩 보기</option>
							<option ${page.cri.amount == 20 ? 'selected' : ''} value="20">20개씩 보기</option>
						</select>
					</div>
				</div>
				<div class="card-body">
					<div class="gallery-list row p-2 justify-content-center">
					<c:forEach items="${members}" var="member">
						<div class="col-2 mx-2 p-2 border-0 card">
							<a class="link" href="profile?id=${member.id}">
								<div class="border p-1">
									<img onerror="this.src='${cp}images/noimage.png'" class="card-img-top" src="${cp}display?uuid=s_${member.attach.uuid}&path=${member.attach.path}" alt="${member.attach.origin}">
									<div class="card-body">
										<h4 class="card-title small text-truncate">${member.name}</h4>
										<p class="card-text small text-truncate">
											<span class="small float-end">${member.id}</span>
										</p>
									</div>
								</div>
							</a>
						</div>
					</c:forEach>
					</div>
					
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