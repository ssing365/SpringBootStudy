<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/webjars/jquery/3.7.1/jquery.js"></script>
<link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.css">
<script type="text/javascript" src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.js"></script>
</head>
<body>
	<h2>스프링부트</h2>
	<ul>
		<li><a href="/">home</a></li>
		<li><a href="/json.do">simple-json 라이브러리 사용하기</a></li>
		<li><a href="/jsonQuiz.do">JSON객체 출력 퀴즈</a></li>
	</ul>
	
	<h3>Webjars - 부트스트랩</h3>
	<button type="button" class="btn">Basic</button>
	<button type="button" class="btn btn-primary">Primary</button>
	<button type="button" class="btn btn-secondary">Secondary</button>
	<button type="button" class="btn btn-success">Success</button>
	<button type="button" class="btn btn-info">Info</button>
	<button type="button" class="btn btn-warning">Warning</button>
	<button type="button" class="btn btn-danger">Danger</button>
	<button type="button" class="btn btn-dark">Dark</button>
	<button type="button" class="btn btn-light">Light</button>
	<button type="button" class="btn btn-link">Link</button>
	
	<h3>Webjars - jQuery</h3>
	<button type="button" class="btn btn-warning" id="myBtn">>클릭하세요</button>
	<script>
		$(function(){
			$("#myBtn").click(function(){
				alert("jQuery 동작하나요?");
			});
		});
	</script>
	
	<h3>BootStrap5의 Modal 구현하기</h3>
	<!-- Button to Open the Modal -->
	<button type="button" class="btn btn-success"
	 data-bs-toggle="modal" data-bs-target="#myModal01">
	  Open modal
	</button>
	
	<!-- The Modal -->
	<div class="modal fade" id="myModal01">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">댓글작성폼</h4>
	        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	        <textarea rows="20" cols="40" class="form-control">
	        </textarea>
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	      	<button type="submit" class="btn btn-warning">
	      	작성완료</button>
	        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">
	        Close</button>
	      </div>
	
	    </div>
	  </div>
	</div>
</body>
</html>