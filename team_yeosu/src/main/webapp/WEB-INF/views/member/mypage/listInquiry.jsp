<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="../../common/head.jsp" />
	
	<title>여수어때 : 1:1문의내역</title>
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
					<h1 class="heading py-4 m-0" data-aos="fade-up">1:1문의내역</h1>
				</div>
			</div>
		</div>
	</div>

	<!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- DataTales Example -->
                    <div class="card shadow my-5">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">여수어때 고객센터</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                        	<th style="width: 15%"></th>
                                            <th>제목</th>
                                            <th style="width: 25%">답변자</th>
                                            <th style="width: 20%">문의시간</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%-- <c:forEach items="${list}" var="qna">
                                        
                                        <tr data-toggle="collapse" data-target="#rep${qna.bno}">
                                        	<c:choose>
                                        	<c:when test="${qna.bno != qna.groupno}">
                                        	<td class="pl-5"><span class="badge badge-warning p-2">여수어때의 답변</span></td>
                                        	</c:when>
                                        	<c:when test="${!qna.reply}">
                                        	<td><span class="badge badge-info p-2">처리 완료</span></td>
                                        	</c:when>
                                        	<c:when test="${qna.bno == qna.groupno}">
                                        	<td><span class="badge badge-danger p-2">미답변 </span></td>
                                        	</c:when>
                                        	</c:choose>
                                            <td>${qna.title }</td>
                                            <td>${qna.writer}</td>
                                            <td><fmt:formatDate value="${qna.regDate}" pattern="yyyy-MM-dd"/></td>
                                        </tr>
                                        <tr class="collapse" id="rep${qna.bno}">
                                        	<td colspan="4">
                                        	${qna.content}
                                        	</td>
                                        </tr>
                                    	
                                    	</c:forEach> --%>
                                    	
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
	                                    		<td>${qna.title }</td>
	                                            <td></td>
	                                            <td><fmt:formatDate value="${qna.regDate}" pattern="yyyy-MM-dd"/></td>
	                                        </tr>
	                                        <tr class="collapse" id="rep${qna.groupno}">
	                                        	<td colspan="4" style="height: 200px">${qna.content}</td>
	                                        </tr>
                                    		</c:when>
                                    		<c:otherwise>
                                    		<tr class="collapse" id="rep${qna.groupno}">
                                    		<td><span class="badge badge-warning p-2">여수어때의 답변</span></td>
                                    		<td>${qna.title }</td>
                                            <td>${qna.writer}</td>
                                            <td><fmt:formatDate value="${qna.regDate}" pattern="yyyy-MM-dd"/></td>
                                            </tr>
                                            <tr class="collapse" id="rep${qna.groupno}">
	                                        	<td colspan="4" style="height: 200px">${qna.content}</td>
	                                        </tr>
                                    		</c:otherwise>
                                    		</c:choose>
                                    	</c:forEach>
                                    </tbody>
                                </table>
                                <hr>
                                <sec:authorize access="!hasAnyRole('ROLE_ADMIN')">
                                <form method="post" class="col-8 mx-auto">
                                <input type="text" class="form-control" name="title" placeholder="문의 제목을 입력하세요">
                                <textarea class="form-control" name="content" placeholder="문의 내용을 입력하세요" style="min-height: 400px; resize: none;"></textarea>
                                <button class="btn btn-primary btn-block">1:1문의 등록</button>
                                <input type="hidden" name="writer" value='<sec:authentication property="principal.username"/>'>
                                <sec:csrfInput/>
                                </form>
                                </sec:authorize>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
	
	<jsp:include page="../../common/footer.jsp" />
  </body>
  </html>