<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Spring boot Project</h2>
	<ul>
		<li><a href="/">홈</a></li>
	</ul>
	
	<h2>Spring Data JPA - update</h2>
	<p>
		아이디 : ${member.id } <br>
		이름 : ${member.username } <br>
		날짜 : ${member.createDate} 
	</p>
</body>
</html>