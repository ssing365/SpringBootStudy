<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>바로가기</h2>
	<!-- 
	Spring Security를 적용했을 때 로그인과 로그아웃에 대한 요청명은
	아래와 같이 /login /logout으로 default 지정되어있다.
	(개발자가 변경하고 싶다면 Security 설정파일에서 커스텀하면 된다.)
	 -->
	<ul>
		<li> <a href="/myLogin.do">Custom Login</a></li>
		<li> <a href="/guest/index.do">Guest</a></li>
		<li> <a href="/member/index.do">Member</a></li>
		<li> <a href="/admin/index.do">Admin</a></li>
		<li> <a href="/myLogout.do">Custom Logout</a></li>
	</ul>
</body>
</html>