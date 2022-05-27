<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
	<jsp:include page="../common/admin_head.jsp"/>

    <title>여수어때 : 광고 승인</title>
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
                <sec:authorize access="hasRole('ROLE_ADMIN')">
	                <div class="container-fluid">
						<%-- ${members} --%>
	                    <!-- Page Heading -->
	                    <h1 class="h3 mb-2 text-gray-800">광고 미승인 업체 목록</h1>
	                    
	                    <!-- DataTales Example -->
	                    <div class="card shadow mb-4">
	                        <div class="card-body">
	                        	<ul class="list-group">
	                        		<c:forEach items="${pension}" var="p" varStatus="stat">
									<li class="list-group-item" >
										<form method="post">
											<div data-toggle="collapse" data-target="#list${stat.index}"> 
												<h4>${p.name}</h4> 
												<div class="row">
													<p class="col-3" >${p.userid}</p>
													<p class="col-3" >${p.address}</p>
													<div class="col-6">
														<span>${p.starRate }</span> 
														<span class="float-right"><fmt:formatNumber type="number" maxFractionDigits="3" value="${p.price}" />원</span>
													</div>
												</div>
											</div>
											<input type="hidden" name="pensionid" value="${p.pensionid}">
											<button type="submit" name="modifyBtn" class="btn btn-primary btn-modify float-right">승인</button>
											<sec:csrfInput/>
										</form> 
									</li>
									</c:forEach>
								</ul>
	                        </div>
	                    </div>
	                    <ul class="pagination justify-content-center">
							<c:if test="${page.prev}">
								<li class="page-item"><a class="page-link" href="pensionAdStatus${page.cri.paramWithoutPageNum}&pageNum=${page.start-1}">prev</a></li>
							</c:if>
							<c:forEach begin="${page.start}" end="${page.end}" var="p">
								<li class="page-item ${page.cri.pageNum == p ? 'active' : ''}"><a class="page-link" href="pensionAdStatus${page.cri.paramWithoutPageNum}&pageNum=${p}">${p}</a></li>
							</c:forEach>
							<c:if test="${page.next}">
								<li class="page-item"><a class="page-link" href="pensionAdStatus${page.cri.paramWithoutPageNum}&pageNum=${page.end+1}">next</a></li>
							</c:if>
						</ul>
	                </div>
                </sec:authorize>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <jsp:include page="../common/admin_footer.jsp"/>

        </div>
        <!-- End of Content Wrapper -->
		
		
    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/resources/assets/admin/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/assets/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/assets/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/assets/admin/js/sb-admin-2.min.js"></script>
    
    <script>
    	// 회원권한수정
		$(function() {
			
		})	
    	
	</script>
</body>
</html>