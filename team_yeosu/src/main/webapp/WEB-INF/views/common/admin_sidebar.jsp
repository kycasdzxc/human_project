<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!-- Sidebar -->
	<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
	
	    <!-- Sidebar - Brand -->
	    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/admin/index">
	        <div class="sidebar-brand-icon rotate-n-15">
	            <i class="fas fa-laugh-wink"></i>
	        </div>
	        <div class="sidebar-brand-text mx-3">관리자페이지</div>
	    </a>
	
	    <!-- Divider -->
	    <sec:authorize access="!hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')">
	    <hr class="sidebar-divider">
	    
	    <!-- 펜션 등록 안한 회원만 보기 -->
	    <div class="sidebar-heading text-light">
	        회원 등록
	    </div>
	    <li class="nav-item">
	        <a class="nav-link" href="/admin/pensionregister">
	            <i class="fas fa-fw fa-home"></i>
	            <span>업체 회원 등록</span></a>
	    </li>
	    
	    <hr class="sidebar-divider">
		</sec:authorize>
		
	    <!-- Heading -->
	    <sec:authorize access="hasRole('ROLE_MANAGER') AND !hasRole('ROLE_ADMIN')">
	    <hr class="sidebar-divider">
	    <div class="sidebar-heading text-light">
	        펜션 관리
	    </div>
	
	    <!-- Nav Item - Pages Collapse Menu -->
	    <li class="nav-item">
	        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseOne"
	            aria-expanded="true" aria-controls="collapseOne">
	            <i class="fas fa-fw fa-home"></i>
	            <span>숙소 관리</span>
	        </a>
	        <div id="collapseOne" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
	            <div class="bg-white py-2 collapse-inner rounded">
	                <a class="collapse-item" href="/admin/roomList">- 숙소 목록 조회</a>
	                <a class="collapse-item" href="/admin/roomRegister">- 신규 숙소 등록</a>
	            </div>
	        </div>
	    </li>
	    
	    <!-- Nav Item - Pages Collapse Menu -->
	    <li class="nav-item">
	        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
	            aria-expanded="true" aria-controls="collapseTwo">
	            <i class="fas fa-fw fa-calendar"></i>
	            <span>예약 관리</span>
	        </a>
	        <div id="collapseTwo" class="collapse" aria-labelledby="headingOne" data-parent="#accordionSidebar">
	            <div class="bg-white py-2 collapse-inner rounded">
	                <a class="collapse-item" href="/admin/listReservation">- 이용 내역 조회</a>
	                <a class="collapse-item" href="/admin/checkReservation">- 예약 내역 확인</a>
	            </div>
	        </div>
	    </li>
	
	    <li class="nav-item">
        <a class="nav-link" href="/admin/listPayment">
            <i class="fas fa-fw fa-coins"></i>
            <span>매출 조회</span></a>
	    </li>
	    
	    <li class="nav-item">
        <a class="nav-link" href="" onclick="alert('준비중입니다.')">
            <i class="fas fa-fw fa-comment"></i>
            <span>댓글 조회</span></a>
	    </li>
	    <hr class="sidebar-divider">
		</sec:authorize>
		
	    <!-- Divider -->
	    <sec:authorize access="hasRole('ROLE_ADMIN')">
	    <hr class="sidebar-divider">
	
	    <!-- Heading -->
	    <div class="sidebar-heading text-light">
	        사이트 관리
	    </div>
	
	    <!-- Nav Item - Pages Collapse Menu -->
	    <li class="nav-item">
	        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
	            aria-expanded="true" aria-controls="collapsePages">
	            <i class="fas fa-fw fa-users"></i>
	            <span>회원 관리</span>
	        </a>
	        <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
	            <div class="bg-white py-2 collapse-inner rounded">
	                <a class="collapse-item" href="memberList">- 회원 목록 조회</a>
	                <a class="collapse-item" href="#" id="newMember" onclick="alert('준비중입니다.')">- 신규 회원 등록</a>
	                <a class="collapse-item" href="listpensionAuth">- 업체 회원 승인</a>
	                <a class="collapse-item" href="listInquiry">- 1:1문의 내역</a>
	            </div>
	        </div>
	    </li>
	    
	    <li class="nav-item">
	        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages2"
	            aria-expanded="true" aria-controls="collapsePages2">
	            <i class="fas fa-fw fa-house-user"></i>
	            <span>펜션 관리</span>
	        </a>
	        <div id="collapsePages2" class="collapse" aria-labelledby="headingPages2" data-parent="#accordionSidebar">
	            <div class="bg-white py-2 collapse-inner rounded">
	                <a class="collapse-item" href="/admin/listpension">- 펜션 목록 조회</a>
	                <a class="collapse-item" href="/admin/listReservationAll">- 예약 내역 조회</a>
	                <a class="collapse-item" href="/admin/pensionAdStatus">- 펜션 광고 승인</a>
	                <a class="collapse-item" href="/admin/pensionListWithAd">- 광고 목록 조회</a>
	            </div>
	        </div>
	    </li>
	    
	    <li class="nav-item">
	        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages3"
	            aria-expanded="true" aria-controls="collapsePages3">
	            <i class="fas fa-fw fa-user-cog"></i>
	            <span>사이트 관리</span>
	        </a>
	        <div id="collapsePages3" class="collapse" aria-labelledby="headingPages3" data-parent="#accordionSidebar">
	            <div class="bg-white py-2 collapse-inner rounded">
	                <a class="collapse-item" href="#" id="replyListAll">- 댓글 목록 조회</a>
	                <a class="collapse-item" href="/admin/listPaymentAll">- 전체 매출 조회</a>
	                <a class="collapse-item" href="#" onclick="alert('준비중입니다.')">- 관리자 권한 부여</a>
	            </div>
	        </div>
	    </li>
		
	    <!-- Divider -->
	    <hr class="sidebar-divider d-none d-md-block">
		</sec:authorize>
		
		<div class="sidebar-heading text-light">
		        사이트 이동
	    </div>
		<li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/index">
	            <i class="fas fa-fw fa-plane-departure"></i>
	            <span>메인페이지로</span></a>
	    </li>
	    
	    <hr class="sidebar-divider">
	
	    <!-- Sidebar Toggler (Sidebar) -->
	    <div class="text-center d-none d-md-inline">
	        <button class="rounded-circle border-0" id="sidebarToggle"></button>
	    </div>
	</ul>
	<!-- End of Sidebar -->