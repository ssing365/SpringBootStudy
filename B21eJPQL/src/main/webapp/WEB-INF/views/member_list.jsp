<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Spring boot 프로젝트</h2>
	<ul>
		<li><a href="/">홈</a></li>
	</ul>
	
	<h2>JPQL - @Query</h2>
	<c:if test="${not empty totalElements }">
		<h3>Pageable 적용 결과</h3>
		총 레코드 개수 : ${totalElements } <br>
		전체 페이지 수 : ${totalPages } <br>
		페이지 당 레코드 수 : ${size } <br>
		페이지 번호 : ${pageNumber } <br>
		엘리먼트(콘텐츠) 개수 : ${numberOfElements } <br>
		<hr>
	</c:if>
	
	<h3>레코드 출력</h3>
	<c:forEach items="${members }" var="member">
		<p>
			아이디 : ${member.id} <br>
			이름 : ${member.name} <br>
			이메일 : ${member.email} 
		</p>
	</c:forEach>

</body>
</html>