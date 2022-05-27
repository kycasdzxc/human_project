<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2022. 5. 10.오후 12:38:54</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<style>
#star {width: 550px}
#star i {width: 100px; margin:5px}
</style>
</head>
<body>
<div class="text-warning display-1 d-inline-block" id="star">
	<i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i>
</div>
<input id="score" type="number" max="10" min="0">
<h3 id="location">현재 좌표 : <span></span></h3>
<script>
$("#score").change(function() {
	var score = this.value / 1;
	console.log(score);
	$('#star').html(starStr(score));
});

$("#star").mousemove(function() {
	$("#location span").html("x : " + event.x + ", y : " + event.y);
})
// 550 / 55
$("#star").click(function() {
	var inputScore = parseInt(parseInt(event.x/55+1) / 2) * 2;
	console.log(inputScore);
	$('#star').html(starStr(inputScore));
})

function starStr(score) {
	var str = "";
	for(var i = 1 ; i <= 5 ; i++) { // 11회
		var val = parseInt(score/2);
		if(i <= val) {
			str += '<i class="fas fa-star"></i>';
		}
		else if(score-val == i){
			str += '<i class="fas fa-star-half-alt"></i>';
		}
		else {
			str += '<i class="far fa-star"></i>';
		} 
	}
	return str;
}
</script>
</body>
</html>