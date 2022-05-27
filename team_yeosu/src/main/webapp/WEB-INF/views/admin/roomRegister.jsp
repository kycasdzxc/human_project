<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html lang="ko">
<head>

    <jsp:include page="../common/admin_head.jsp"/>

    <title>여수어때 : 숙소등록</title>
</head>
<security:csrfMetaTags/>

<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">

        <jsp:include page="../common/admin_sidebar.jsp"/>
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
		
		    <!-- Main Content -->
		    <div id="content">
		        <jsp:include page="../common/admin_nav.jsp"/>
		    
				<div class="p-5 m-5 border bg-white">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">숙소 등록</h1>
                            </div>
                            <form class="user" method="post">
                                 <div class="form-group">
                                	<label for="name">숙소 이름</label>
                                    <input type="text" class="form-control form-control-user" id="name" name="roomName" >
                                </div>
                                 <div class="form-group">
                                	<label for="deadline">예약 마감시간</label>
                                    <input type="text" class="form-control form-control-user" id="deadline" name="deadline" >
                                </div>
                                 <div class="form-group">
                                	<label for="price">객실 가격</label>
                                    <input type="text" class="form-control form-control-user" id="price" name="price" >
                                </div>
                                <hr>
                                <div class="form-group uploadDiv">
							   		<label class="btn btn-success btn-sm" for="attach">첨부</label>
								    <input type="file" class="form-control d-none" placeholder="attach" id="attach" name="attach" multiple>
							    </div>
								   
							    <ul class="list-group small container px-1 upload-files">

							    </ul>
								   
							    <div class="container pt-3 px-1">
							   	  <div class="row thumbs">
							      </div>
						        </div>
                                <button class="btn btn-primary btn-user btn-block" id="btnReg">
                                    숙소 등록하기
                                </button>
                                <security:csrfInput/>
                            </form>
                        </div>
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

    <!-- Page level plugins -->
    <script src="/resources/assets/admin/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/resources/assets/admin/js/demo/chart-area-demo.js"></script>
    <script src="/resources/assets/admin/js/demo/chart-pie-demo.js"></script>

    <!-- Page level plugins -->
    <script src="/resources/assets/admin/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/resources/assets/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/resources/assets/admin/js/demo/datatables-demo.js"></script>
<script>
	$(function() {
		$("#attach").change(function() {
			var str = "";
			$(this.files).each(function() {
				str += "<p>" + this.name + "</p>";
			})
			$(this).next().html(str);
		})
	}) 		
</script>
<script>
	var headerName = $("meta[name='_csrf_header']").attr("content")
	var token = $("meta[name='_csrf']").attr("content")
	/* var pensionid = '${pension.pensionid}'; */
	/* console.log(headerName)
	console.log(token)
	console.log(pensionid) */
	
	$(document).ajaxSend(function(e, xhr) {
		xhr.setRequestHeader(headerName, token);
	})
	
$(function() {
	/* $("#name").focusout(function() {
		console.log($(this).val())
		if($(this).val().length == 0) {
			$(this).parent().find("small").text("펜션 이름을 입력하세요")
			$(this).addClass("is-invalid")
		}
	}) */

	var $clone = $(".uploadDiv").clone()
		var regexp = /(.*?)\.(exe||sh||js||jsp)$/
		var maxSize = 1204 * 1024 * 5
		
		function validateFiels(fileName, fileSize) {
			if(fileSize >= maxSize) {
				alert("너무 커")
				return false
			}
			if(regexp.test(fileName)) {
				alert("안 돼")
				return false
			}
			return true;
		}
		
		// 파일 첨부 이벤트
		$(".uploadDiv").on("change", ":file", function() {
			var formData = new  FormData()
			
			for(var i in this.files) {
				if(!validateFiels(this.files[i].name, this.files[i].size)) {
					return false;
				}
				formData.append("files", this.files[i])
			}
			formData.append("type", "room")
			
			$.post({
				processData : false,
				contentType : false,
				data : formData,
				url : "/upload",
				dataType : "json"
			}).done(function(result) {
				console.log(result)
				$(".uploadDiv").html($clone.html())

				var str = "";
				var thumbStr = "";
				for(var i in result) {
					// object >> queryString
					// result[i]
					console.log(result[i])
					console.log($.param(result[i]))
					str += '<li class="list-group-item" data-uuid="' + result[i].uuid + '" data-path="' + result[i].path + '" data-image="' + result[i].image + '" data-origin="' + result[i].origin + '" data-ord="' + result[i].ord +'">'
							+ result[i].origin + '</a><button type="button" class="close"><span>&times;</span></button></li>'
					if(result[i].image) {
						var o = {...result[i]};  // clone
						o.uuid = 's_' + o.uuid;
						thumbStr += '	<div class="col-sm-6 col-md4 col-lg-3 col-xl-2" data-uuid="' + result[i].uuid + '" data-path="' + result[i].path + '" data-image="' + result[i].image + '" data-origin="' + result[i].origin + '" data-ord="' + result[i].ord + '">';
						thumbStr += '		<figure class="d-inline-block " style="position:relative; overflow: ">';
						thumbStr += '			<figcaption><button type="button" class="close" style="position: absolute; top:15px; right:8px"><span>&times;</span></button></figcaption>';
						thumbStr += '			<a href="/display?' + $.param(result[i]) + '"data-lightbox="img" data-title="' + o.origin + '"><img alt="" src="/display?' + $.param(o) + '"class="mx-1 my-2 img-thumbnail"></a>';
						thumbStr += '		</figure>';
						thumbStr += '	</div>';
					}
				}
				$(".upload-files").append(str)
				$(".thumbs").append(thumbStr)
			})
		})
		
		$("#btnReg").click(function () {
			event.preventDefault()
			var str = "";
			var attrArr = ['uuid', 'origin', 'path', 'ord', 'image']
			$(".upload-files li").each(function (i) {
				for(var j in attrArr) {
					attrArr[j]
					str += 
						$("<input>")
						.attr("type", "hidden")
						.attr("name", "attachs[" + i +  "]." + attrArr[j])
						.attr("value", $(this).data(attrArr[j])).get(0).outerHTML + "\n"; 
				}
			})
			console.log(str)
			$(this).closest("form").append(str).submit();
		}) 
	
}) 
</script>
</body>
</html>