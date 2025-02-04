<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	$("#btnBoard").click(function(){
		$.ajax({
			type:'get',
			url : './restBoardView.do',
			// 파라미터 : 조회할 게시물의 일련번호
			data : {num : $("#num").val()},
			contentType : "text/html;charset:utf-8",
			dataType : "json",
			success : sucCallBack,
			error : errCallBack
		})	
	})
	$("#btnBoard").trigger("click");
})

function sucCallBack(resData){
	let tableData = "";
	$("#td1").html(resData.num);
	$("#td2").html(resData.id);
	$("#td3").html(resData.postdate);
	$("#td4").html(resData.visitcount);
	$("#td5").html(resData.title);
	$("#td6").html(resData.content);
}

function errCallBack(errData){
	console.log(errData.status+":"+errData.statusText);
}
</script>
<div class="container">
	<h2>게시판 API 활용하여 내용 출력하기</h2>
	<table class="table table-bordered">
		<tr>
			<th>번호</th><td id="td1"></td>
			<th>아이디</th><td id="td2"></td>
		</tr>
		<tr>
			<th>작성일</th><td id="td3"></td>
			<th>조회수</th><td id="td4"></td>
		</tr>
		<tr>
			<th>제목</th><td colspan="3" id="td5"></td>
		</tr>
		<tr>
			<th>내용</th><td colspan="3" id="td6"></td>
		</tr>
	</table>
	<div>
		<input type="number" id="num" value="2668">
		<input type="button" value="내용불러오기" id="btnBoard">
	</div>
</div>

</body>
</html>