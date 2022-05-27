<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">

<head>
	<jsp:include page="../common/admin_head.jsp"/>

    <title>관리자페이지 : 1:1문의</title>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

		<jsp:include page="../common/admin_sidebar.jsp"/>
		
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
			<jsp:include page="../common/admin_nav.jsp"/>

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <div class="card shadow my-5">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">1:1문의</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                        	<th style="width: 15%"></th>
                                            <th>제목</th>
                                            <th style="width: 25%">문의자/답변자</th>
                                            <th style="width: 20%">문의시간</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${list}" var="qna">
                                    		<c:choose>
                                    		<c:when test="${qna.bno == qna.groupno}">
                                    		<tr style="cursor: pointer;" data-toggle="collapse" data-target="#rep${qna.bno}">
                                    			<c:choose>
                                    			<c:when test="${!qna.reply}">
	                                        	<td><span class="badge badge-info p-2">처리 완료</span></td>
	                                        	</c:when>
	                                        	<c:when test="${qna.bno == qna.groupno}">
	                                        	<td><span class="badge badge-danger p-2">미답변 </span></td>
	                                        	</c:when>
                                    			</c:choose>
	                                    		<td>${qna.title}</td>
	                                            <td>${qna.writer}</td>
	                                            <td><fmt:formatDate value="${qna.regDate}" pattern="yyyy-MM-dd"/></td>
	                                        </tr>
	                                        <tr class="collapse" id="rep${qna.groupno}">
	                                        	<td colspan="4" style="height: 200px">${qna.content}</td>
	                                        </tr>
	                                        <tr class="collapse" id="rep${qna.groupno}">
	                                        	<td colspan="4">
	                                        	<form method="post" class="col-10 mx-auto">
				                                <input type="text" class="form-control mb-4" name="title" placeholder="답변 제목을 입력하세요" value="re : ${qna.title}">
				                                <textarea class="form-control mb-4" name="content" placeholder="답변 내용을 입력하세요" style="min-height: 400px; resize: none;"></textarea>
				                                <button class="btn btn-primary btn-block">답변글 등록</button>
				                                <input type="hidden" name="groupno" value="${qna.bno}">
				                                <input type="hidden" name="writer" value='<sec:authentication property="principal.username"/>'>
				                                <sec:csrfInput/>
				                                </form>
	                                        	</td>
	                                        </tr>
                                    		</c:when>
                                    		<c:otherwise>
                                    		<tr style="cursor: pointer;" data-toggle="collapse" data-target="#rep2${qna.bno}">
                                    		<td><span class="badge badge-warning p-2">답변글</span></td>
                                    		<td>${qna.title }</td>
                                            <td>${qna.writer}</td>
                                            <td><fmt:formatDate value="${qna.regDate}" pattern="yyyy-MM-dd"/></td>
                                            </tr>
                                            <tr class="collapse" id="rep2${qna.groupno}">
	                                        	<td colspan="4" style="height: 200px">${qna.content}</td>
	                                        </tr>
                                    		</c:otherwise>
                                    		</c:choose>
                                    		<c:if test="${qna.bno == qna.groupno}">
                                    		</c:if>
                                    	</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->

            <ul class="pagination justify-content-center">
				<c:if test="${page.prev}">
					<li class="page-item"><a class="page-link" href="listInquiry${page.cri.paramWithoutPageNum}&pageNum=${page.start-1}">이전</a></li>
				</c:if>
				<c:forEach begin="${page.start}" end="${page.end}" var="p">
					<li class="page-item ${page.cri.pageNum == p ? 'active' : ''}"><a class="page-link" href="listInquiry${page.cri.paramWithoutPageNum}&pageNum=${p}">${p}</a></li>
				</c:forEach>
				<c:if test="${page.next}">
					<li class="page-item"><a class="page-link" href="listInquiry${page.cri.paramWithoutPageNum}&pageNum=${page.end+1}">다음</a></li>
				</c:if>
			</ul>
            </div>
            <!-- End of Main Content -->

            <jsp:include page="../common/admin_footer.jsp"/>

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->


    <!-- Bootstrap core JavaScript-->
    <script src="/resources/assets/admin/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/assets/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/assets/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/assets/admin/js/sb-admin-2.min.js"></script>

</body>

</html>