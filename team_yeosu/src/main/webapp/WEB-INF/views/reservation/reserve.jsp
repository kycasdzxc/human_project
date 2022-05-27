<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="ko">
<head>
	<jsp:include page="../common/head.jsp" />
	
	<!-- iamport.payment.js -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	
	<title>여수어때 : 숙소예약</title>
</head>
	<sec:csrfMetaTags/>
<body>
	<div class="site-mobile-menu site-navbar-target">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close">
				<span class="icofont-close js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>
	
	<jsp:include page="../common/nav.jsp" />
	
	<div class="p-4 bg-light">
		<form>
		    <div class="row d-flex justify-content-center pb-5">
		        <div class="col-md-12 col-lg-5 mx-3">
		            <div class="py-4 d-flex flex-row">
		                <h5><span class="fa fa-check-square-o"></span><b>펜션 예약</b> | </h5><span class="pl-2">Reservation</span>
		            </div>
		            <sec:authorize access="isAuthenticated()">
				      <sec:authentication property="principal.member" var="user"/>
				   </sec:authorize>
		            <div class="row">
			            <div class="mb-2">
				            <h4 class="green">펜션 이름</h4>
				            <input type="text" class="form-control bg-white" value="${pension.name} - ${room.roomName}" readonly>
			            </div>
			            <div class="mb-2 px-2 col-6">
				            <h4 class="green">입실날짜</h4>
				            <input type="text" class="form-control bg-white" value="${date1}" readonly>
			            </div>
			            <div class="mb-2 px-2 col-6">
				            <h4 class="green">퇴실날짜</h4>
				            <input type="text" class="form-control bg-white" value="${date2}" readonly>
			            </div>
			            <div class="mb-2">
				            <h4 class="green">예약자명</h4>
				            <input type="text" class="form-control bg-white" value="${user.name} (${user.userId})" readonly>
			            </div>
			            <div>
				            <h4 class="green">전화번호</h4>
				            <input type="text" class="form-control bg-white" value="${user.phone}-${user.phone2}-${user.phone3}">
			            </div>
		            </div>
		            <hr>
		            <div class="row mt-4">
			            <div class="mb-2">
				            <h4 class="green">포인트 사용</h4>
				            <input type="text" class="form-control bg-white text-right" id="usePoint" value="0">
				            <p class="float-end">사용 가능 : <input class="border-0 text-right bg-light" style="max-width:100px" id="point" readonly> 포인트</p>
			            </div>
		            </div>
		            
		        </div>
		        <div class="col-md-12 col-lg-5 mobile">
		            <div class="py-4 d-flex justify-content-end">
		                <h6><a href="/pension/detail?pensionid=${pension.pensionid}&startDate=${date1}&endDate=${date2}">뒤로가기</a></h6>
		            </div>
		            <div class="bg-white rounded d-flex flex-column">
		                <div class="mt-3 p-2 ml-3"><h4>주문내역서</h4></div>
		                <div class="border-top px-4 mx-3"></div>
		                <div class="px-3 py-2 my-2 d-flex">
		                    <div class="col-6 px-2">가격(1박 기준)</div>
		                    <div class="ml-auto"><fmt:formatNumber type="number" maxFractionDigits="3" value="${room.price}" /> 원</div>
		                </div>
		                <div class="px-3 py-2 my-2 d-flex">
		                    <div class="col-6 px-2">숙박 일수</div>
		                    <div class="ml-auto">${days} 일</div>
		                </div>
		                <div class="border-top px-4 mx-3"></div>
		                <div class="px-3 py-2 my-2 d-flex">
		                    <div class="col-6 px-2">결제 금액</div>
		                    <div class="ml-auto"><fmt:formatNumber type="number" maxFractionDigits="3" value="${prePayment}" /> 원</div>
		                </div>
		                <div class="px-3 py-2 my-2 d-flex">
		                    <div class="col-6 px-2">할인 금액</div>
		                    <div class="ml-auto" id="discount">0 원</div>
		                </div>
		                <div class="border-top px-4 mx-3"></div>
		                <div class="px-3 py-2 my-2 d-flex">
		                    <div class="col-6 px-2"><strong>총 금액</strong></div>
		                    <div class="ml-auto"><b id="payment"><fmt:formatNumber type="number" maxFractionDigits="3" value="${prePayment}" /> 원</b></div>
		                </div>
		                <div class="border-top px-4 mx-3 mb-3"></div>
		             </div>
		             <div class="mt-3">
	                    <input type="button" value="Proceed to payment" class="btn btn-primary btn-block" onclick="iamport()">
	                </div>
		        </div>        
		    </div>
	    </form>
	</div>
	
	<jsp:include page="../common/footer.jsp" />
	
	<script>
		var headerName = $("meta[name='_csrf_header']").attr("content")
		var token = $("meta[name='_csrf']").attr("content")
		
		$(document).ajaxSend(function(e, xhr) {
			xhr.setRequestHeader(headerName, token);
		})
		
		function iamport(){
			var amount =  '${prePayment}' - $('#usePoint').val();
			//가맹점 식별코드
			IMP.init('imp20015195');
			IMP.request_pay({
			    pg : 'html5_inicis',
			    pay_method : 'card',
			    merchant_uid : '${room.roomNum}' + new Date().getTime(),
			    name : '(주)여수어때' , //결제창에서 보여질 이름
			    amount : amount, //실제 결제되는 가격
			    buyer_email : '${user.email}',
			    buyer_name : '${user.name}',
			    buyer_tel : '${user.phone}',
			    buyer_addr : '${user.roadAddr}',
			    buyer_postcode : '${user.zipNo}'
			}, function(rsp) {
				console.log(rsp);
				
		        var reservation = {
	        		reservationNum: rsp.merchant_uid,
                    checkin: '${date1}',
                    checkout: '${date2}',
					paymentPrice: '${prePayment}',
                    roomNum: '${room.roomNum}',
                    userid: '${user.userId}',
                    pensionid: '${pension.pensionid}'
       	   	 	};
       	   	 	
			    if (rsp.success) {
			        var msg = '결제가 완료되었습니다.';
			        console.log(reservation);
			        alert(msg);
			      
			        $.ajax({
			         	url: "/verifyIamport/" + rsp.imp_uid,
			        	type: "POST",
			        	headers: { "Content-Type": "application/json" },
			        	data: JSON.stringify(reservation),
			        	dataType:"json",
			            contentType:"application/json; charset=utf-8"
			        })
			      
			        location.href = '${pageContext.request.contextPath}/member/mypage';
			    } else {
			      var msg = rsp.error_msg;
			      alert(msg);
			    }
			});
		}
	</script>
	<script>
		var point = '${user.point}';
		var prePayment = '${prePayment}';
		$(function() {
			$('#point').val((point * 1).toLocaleString());
			$('#usePoint').keyup(function() {
				if($('#usePoint').val() - $('#point').val() > 0) {
					alert('사용 가능 포인트를 초과하였습니다.')
					$('#point').val(point.toLocaleString());
					$('#usePoint').val(0);
					$('#discount').html('0 원');
					$('#payment').text(prePayment + ' 원');
				}
				else {
					if(prePayment - $('#usePoint').val() < 1000) {
						alert('최소 결제 금액은 1,000입니다.')
						$('#usePoint').val((prePayment - 1000));
						$('#point').val((point - prePayment - 1000).toLocaleString());
						$('#discount').html('- ' + (prePayment -1000 * 1).toLocaleString() + ' 원');
						$('#payment').text('1,000 원');
					}
					else {
						$('#point').val((point - $('#usePoint').val()).toLocaleString());
						$('#discount').html('- ' + $('#usePoint').val() + ' 원');
						$('#payment').text((prePayment - $('#usePoint').val()).toLocaleString() + ' 원');
					}
				}
			})
		})
	</script>
	
</body>
</html>