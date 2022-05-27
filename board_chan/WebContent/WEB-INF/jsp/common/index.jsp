 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <jsp:include page="../common/head.jsp" />
    <title>모여봐요, 동물의 숲</title>
</head>
<body>
	<jsp:include page="../common/nav.jsp" />
    <main>
	    <div class="container-fluid">
	        <div class="index row">
	            <div class="mainbox col-9">
	                <div class="box">
	                    <div class="top">
	                    </div>
	                    <a class="link" href="board/list">
		                    <h2>모여봐요, 동물의 숲!</h2>
	                    </a>
		                    <p>새로운 생활을 위해 찾아온 이곳은 '자연 그대로의 무인도'. 사람의 손길이 닿지 않은 곳이었다.</p>
	                </div>
	                <hr>
	
	                <div class="row">
	                    <a class="link" href="member/list">
	                    	<h2>- 커몬섬의 NPC</h2>
	                    </a>
	                    <div class="character col-6">
	                        <div class="mt-1">
		                        <img src="${cp}images/face/npc1.png" class="profile col-4 mx-2">
	                        </div>
	                        <div class="text p-2 col-8 py-4 mx-2">
	                            <h3>미소천사, 여울</h3>
	                            <p class="my-0">웃는 얼굴에 침 못 뱉는다.</p>
	                        </div>
	                    </div>
	                    <div class="character col-6">
	                    	<div class="mt-1">
	                        	<img src="${cp}images/face/npc2.png" class="profile col-4 mx-2">
	                       	</div>
	                        <div class="text p-2 col-8 py-4 mx-2">
	                            <h3>척척박사, 부엉</h3>
	                            <p class="my-0">지혜를 한 번에 드러내지 말 것.</p>
	                        </div>
	                    </div>
	                    <div class="character col-6 mt-3">
	                    	<div class="mt-1">
	                        	<img src="${cp}images/face/npc3.png" class="profile col-4 mx-2">
	                        </div>
	                        <div class="text p-2 col-8 py-4 mx-2">
	                            <h3>천재파일럿, 로드리</h3>
	                            <p class="my-0">나비처럼 부드럽게, 벌처럼 빠르게!</p>
	                        </div>
	                    </div>
	                    	<div class="character col-6 mt-3">
	                    	<div class="mt-1">
	                        	<img src="${cp}images/face/npc4.png" class="profile col-4 mx-2">
							</div>
	                        <div class="text p-2 col-8 py-4 mx-2">
	                            <h3>명언제조기, 해탈한</h3>
	                            <p class="my-0">배울수록 어리석어지는 것을 배워야 한다.</p>
	                        </div>
	                    </div>
	                </div>
	                <hr>
	                
	                <div>
	                <a class="link" href="board/list?category=2">
	                    <h2>- 우리들의 추억</h2>
	                </a>
	                    <div class="bottom1"></div>
	                    <h3>계절의 흐름에 몸을 맡기고, 모두와 함께 교류하며 생활한다.</h3>
	                    <div class="bottom2"></div>
	                    <h3>내일은 과연 무슨 일이 일어날까?</h3>
	                    <div class="bottom3"></div>
	                    <h3>아무 것도 없던 곳에 지금 보니 모두가 있었다. 모여봐요 동물의 숲</h3>
	                </div>
	            </div>
	            
	            <div class="sidebar col-3 text-center">
	            <c:if test="${not empty memberInfo}">
	            <div class="border mx-4 mt-1">
	            	<img onerror="this.src='${cp}images/noimage.png'" class="card-img-top" src="${cp}display?uuid=s_${memberInfo.attach.uuid}&path=${memberInfo.attach.path}" alt="${memberInfo.attach.origin}">
				</div>
				<p class="small mt-2">안녕하세요, ${memberInfo.name} 님!</p>
				<hr>
				<p><a href="${cp}member/myPage">Settings </a> / <a href="${cp}member/logout"> Logout</a></p>
	            </c:if>
	            <c:if test="${empty memberInfo}">
				<a href="${cp}member/login" class="btn btn-warning col-10 py-2 border border-dark">로그인</a>
				<hr>
				<p><a href="${cp}member/contract">회원가입</a> / <a class="btnAccount" style="cursor:pointer">아이디·패스워드 찾기</a></p>
                </c:if>
	            </div>
	        </div>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp" />
    <script>
	    $(".sidebar").on("click", ".btnAccount", function() {
			alert("준비 중입니다.");
		});
    </script>
</body>
</html>