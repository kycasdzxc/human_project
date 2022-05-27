<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="../../common/head.jsp" />
	
	<title>여수어때 : 예약내역조회</title>
</head>
<body>
	<div class="site-mobile-menu site-navbar-target">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close">
				<span class="icofont-close js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>

	<jsp:include page="../../common/nav.jsp" />

	<div class="hero2 page-inner overlay">
		<div class="container">
			<div class="row justify-content-center align-items-center">
				<div class="col-lg-9 text-center">
					<h1 class="heading py-4 m-0" data-aos="fade-up">예약내역조회</h1>
				</div>
			</div>
		</div>
	</div>

	<!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- DataTales Example -->
                    <div class="card shadow my-5">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">예약내역조회</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr class="text-center">
                                            <th>예약번호</th>
                                            <th>숙소이름</th>
                                            <th>체크인</th>
                                            <th>체크아웃</th>
                                            <th>승인상태</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${reservation}" var="r">
	                                        <tr>
	                                            <td class="text-center">${r.reservationNum}</td>
	                                            <td class="text-center"><a href="/pension/detail?pensionid=${r.pensionid}" class="text-dark">${r.roomName}</a></td>
	                                            <td class="text-center">${r.checkin}</td>
	                                            <td class="text-center">${r.checkout}</td>
	                                            <td class="text-center">${r.reservationStatus ? "예약" : "취소"}</td>
	                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
	
	<jsp:include page="../../common/footer.jsp" />
	<script src="/resources/assets/admin/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="/resources/assets/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>
   	<script>
		$(function() {
			$('#dataTable').DataTable({
				order : [[3,"desc"]]
			})
		})
	</script>
  </body>
  </html>