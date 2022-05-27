<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">

<head>
	<jsp:include page="../common/admin_head.jsp"/>

    <title>관리자페이지 : 펜션목록</title>
    <sec:csrfMetaTags/>
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

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">업체회원승인</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>펜션번호</th>
                                            <th>펜션이름</th>
                                            <th>소유자아이디</th>
                                            <th>승인상태</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${pension}" var="p">
	                                        <tr>
	                                            <td>${p.pensionid}</td>
	                                            <td><a href="/admin/pensionmodify?pensionid=${p.pensionid}">${p.name}</a></td>
	                                            <td>${p.userid}</td>
	                                            <td>
	                                            <form>
												  <div class="custom-control custom-switch">
												    <input type="checkbox" id="id${p.pensionid}" data-pensionid='${p.pensionid}' data-userid='${p.userid}' class="custom-control-input auth-change" ${p.enabled ? 'checked' : ''}>
												    <label class="custom-control-label" for="id${p.pensionid}"></label>
												  </div>
												</form>
	                                            </td>
	                                        </tr>
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
						<li class="page-item"><a class="page-link" href="listpensionAuth${page.cri.paramWithoutPageNum}&pageNum=${page.start-1}">이전</a></li>
					</c:if>
					<c:forEach begin="${page.start}" end="${page.end}" var="p">
						<li class="page-item ${page.cri.pageNum == p ? 'active' : ''}"><a class="page-link" href="listpensionAuth${page.cri.paramWithoutPageNum}&pageNum=${p}">${p}</a></li>
					</c:forEach>
					<c:if test="${page.next}">
						<li class="page-item"><a class="page-link" href="listpensionAuth${page.cri.paramWithoutPageNum}&pageNum=${page.end+1}">다음</a></li>
					</c:if>
				</ul>
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
	var headerName = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	
	$(document).ajaxSend(function(e, xhr) {
		xhr.setRequestHeader(headerName, token);
	}) 
	
	$(function() {
		$('.auth-change').change(function() {
			var data = {
					userid:$(this).data('userid'), 
					pensionid:$(this).data("pensionid"),
					enabled:$(this).prop("checked")
			}
			console.log(data);
			$.ajax({
				url : "/admin/pensionAuthModify",
				method:'put',
				data:JSON.stringify(data),
	            dataType:"text",
	            contentType:"application/json; charset=utf-8",
				success : function(result) {
					console.log(result);
				}
			})
		})
	})
	
	</script>
</body>

</html>