<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- build.gradle의 JSP설정에 추가한 의존성을 보면 JSTL사용가능.
taglib지시어를 추가해 사용한다. -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>application.properties에서 가져오기</h2>
	
	<!-- 단일값이면 EL로 즉시 출력 -->
	<ul>
		<li>testKey1 : ${testKey1}</li>
		<li>testKey2 : ${testKey2}</li>
	</ul>
	
	<!-- 2개 이상의 값은 List로 저장되므로 반복문을 통해 출력 -->
	<ol>
	<c:forEach items="${testKey3 }" var="item">
		<li>testKey3 : ${item }</li>
	</c:forEach>
	</ol>
</body>
</html>