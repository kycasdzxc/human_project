<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="../common/admin_head.jsp"/>

    <title>여수어때 : 숙소목록</title>
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

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">숙소관리</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th style="width: 12%">펜션번호</th>
                                            <th style="width: 22%">숙소명</th>
                                            <th style="width: 15%">예약시작시간</th>
                                            <th style="width: 15%">마감시간</th>
                                            <th style="width: 12%">가격</th>
                                            <th style="width: 12%">예약상태</th>
                                            <th style="width: 12%">객실번호</th>
                                        </tr>
                                    </thead>
	                                    <tbody>
	                                    	<c:forEach items="${roomList}" var="r">
		                                    <tr data-toggle="collapse" data-target="#demo1" class="accordion-toggle room1">
			                                    <td>${r.pensionid}</td>
			                                    <td>${r.roomName}</td>
			                                    <td>${r.startTime}</td>
			                                    <td>${r.deadline}</td>
			                                    <td>${r.price}</td>
			                                    <td>${r.reservationStatus}</td>
			                                    <td>${r.roomNum}</td>
                                            </tr>
		                                    </c:forEach>
	                                     </tbody>
            <td colspan="12" class="hiddenRow">
							<div class="accordian-body collapse" id="demo1"> 
              <table class="table table-striped">
                      <thead>
                        <tr class="info">
													<th>숙소명</th>
													<th>마감시간(년/월/일)</th>
													<th>가격</th>		
												</tr>
											</thead>
											<tbody>
												<tr>
											<form class="user" method="post">
													<td>
                                    <input type="text" class="form-control form-control-user" id="name" name="roomName" >
                                    </td>
                                 <td>
                                    <input type="text" class="form-control form-control-user" id="deadline" name="deadline" >
                                    </td>
                                    <td>
                                    <input type="text" class="form-control form-control-user" id="price" name="price" >
                                    </td>
                                <hr>
		                		<input type="hidden" name="roomNum" value="${r.roomNum}">
                                <button class="btn btn-primary btn-user btn-block" id="btnReg">
                                    숙소 수정하기
                                </button>
                                <button class="btn btn-primary btn-user btn-block" id="btnReg" formaction="remove">
                                    삭제하기
                                </button>
                                <security:csrfInput/>
                            </form>
												</tr>
											</tbody>	
								  		

                                </table>
                                </div>
                                </td>
                                
                                              </table>
                                              
                            </div>
                            
                        </div>
                    </div>

                </div>


            </div>



            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>

        </div>

    </div>

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

    <!-- Page level plugins -->
    <script src="/resources/assets/admin/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/resources/assets/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/resources/assets/admin/js/demo/datatables-demo.js"></script>
    <script src="/resources/js/room.js"></script>
    
    <script>
     $(function() {
		var pensionid ='${pension.pensionid}';
		var lastRoomNum;
		var amount;
		function getRoomStr(room) {
			var str="";
			str+='<td>'+room.pensionid+'</td>'
			str+='<td>'+room.roomName+'</td>'
			str+='<td>'+room.startTime+'</td>'
			str+='<td>'+room.deadline+'</td>'
			str+='<td>'+room.price+'</td>'
			str+='<td>'+room.reservationStatus+'</td>'
			str+='<td>'+room.roomNum+'</td>'
		return str;
		}
		
		
		function showList(pensionid,lastRoomNum, amount) {
			var param ={pensionid:pensionid,lastRoomNum:lastRoomNum,amount:amount}
			roomService.getListAdmin(param, function(result) {
				console.log(result);
				var str='';
				for (var i in result) {
					str+=getRoomStr(result[i]);
				}
					$(".room1").html(str);
			})
		}
		showList(pensionid,lastRoomNum,amount)
    });
    
    </script>

</body>

</html>