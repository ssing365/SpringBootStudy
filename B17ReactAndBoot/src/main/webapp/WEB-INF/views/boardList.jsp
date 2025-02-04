<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- BootStrap, jQuery CDN추가 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>
// jQuery Entry Point 정의
$(function(){
	$("#btnBoard").click(function(){
		$.ajax({
			type : 'get',
			url : './restBoardList.do',
			// data : 서버로 요청할 때 전송할 파라미터(페이지번호)
			data : {pageNum : $("#pageNum").val()},
			contentType : "text/html;charset:utf-8",
			dataType : "json",
			success : sucCallBack,
			error : errCallBack
		});
	});
	// 페이지 로드가 완료되면 click 이벤트를 트리거(최초 자동실행)한다.
	$("#btnBoard").trigger("click");
});

// 요청 성공시 실행 함수
function sucCallBack(resData){
	console.log(resData);
	let tableData = "";
	// 콜백데이터의 크기만큼 자동으로 반복해서 tr태그 추가
	$(resData).each(function(index, data){
		tableData += ""
		+"<tr>"
		+" 		<td>"+data.num+"</td>"
		+" 		<td>"+data.title+"</td>"
		+" 		<td>"+data.id+"</td>"
		+" 		<td>"+data.postdate+"</td>"
		+" 		<td>"+data.visitcount+"</td>"
		+"</tr>"
	});
	// 이 영역에 삽입한다.
	$("#show_data").html(tableData);
}
function errCallBack(errData){
	console.log(errData.status+":"+errData.statusText);
}
</script>
</head>
<body>
<div class="container">
	<h2>게시판 API 활용하여 목록 출력하기</h2>
	<table class="table table-bordered">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>아이디</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<tbody id="show_data"></tbody>
	</table>
	<div>
		<select id="pageNum">
		<c:forEach begin="1" end="200" var="num">
			<option value="${num }">${num }page</option>
		</c:forEach>
		</select>
		<input type="button" value="목록 불러오기" id="btnBoard"/>
	</div>
</div>

</body>
</html>