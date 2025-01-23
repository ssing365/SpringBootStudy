<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FileUpload</title>
</head>
<body>
	<h2>파일 업로드 성공</h2>
	<!-- 모델 객체에 있는거 출력 -->
	업로드한 파일명 : ${originalFileName } <br>
	저장된 파일명 : ${savedFileName } <br>
	<img alt="" src="./uploads/${savedFileName } "> <br>
	
	제목 : ${title } <br>
	카테고리 : 
	<c:forEach items="${cate }" var="row" varStatus="status">
		${row }
		<!-- varStatus 속성에서 마지막 , 는 출력되지 않도록 -->
		<c:if test="${status.last eq false }">,</c:if>
	</c:forEach>
	
</body>
</html>