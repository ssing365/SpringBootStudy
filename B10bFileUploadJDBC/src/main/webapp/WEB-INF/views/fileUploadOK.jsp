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
	<table border="1">
		<tr>
			<th>No</th>
			<th>Title</th>
			<th>카테고리</th>
			<th>원본파일명</th>
			<th>썸네일</th>
			<th>날짜</th>
		</tr>
		
		<c:forEach items="${fileRows }"	var="row" varStatus="status">
			<tr>
				<td>${row.idx }</td>
				<td>${row.title }</td>
				<td>${row.cate }</td>
				<td>${row.ofile }</td>
				<td>
				<img style="max-width:150px" src="./uploads/${row.sfile }" />
				</td>
				<td>${row.postdate }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>