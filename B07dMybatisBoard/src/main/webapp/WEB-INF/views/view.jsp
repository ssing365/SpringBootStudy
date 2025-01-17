<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시판 읽기(Mybatis)</h2>	
	
	<table border="1" width="90%">
	    <colgroup>
	        <col width="15%"/> <col width="35%"/>
	        <col width="15%"/> <col width="*"/>
	    </colgroup>	
	    <!-- 게시글 정보 -->
	    <tr>
	        <td>번호</td> <td>${ boardDTO.idx }</td>
	        <td>작성자</td> <td>${ boardDTO.name }</td>
	    </tr>
	    <tr>
	        <td>작성일</td> <td>${ boardDTO.postdate }</td>
	        <td>조회수</td> <td>${ boardDTO.visitcount }</td>
	    </tr>
	    <tr>
	        <td>제목</td>
	        <td colspan="3">${ boardDTO.title }</td>
	    </tr>
	    <tr>
	        <td>내용</td>
	        <td colspan="3" height="100">
	        	${ boardDTO.content }	        	
	        </td>
	    </tr>
	    <!-- 하단 메뉴(버튼) -->
	    <tr>
	        <td colspan="4" align="center">
	            <button type="button" onclick="location.href='./edit.do?idx=${ param.idx }';">
	                수정하기
	            </button>
	            <button type="button" onclick="deletePost(${ param.idx });">
	                삭제하기
	            </button>
	            <button type="button" onclick="location.href='./list.do';">
	                목록 바로가기
	            </button>
	        </td>
	    </tr>
	</table>
	<form name="deleteFrm">
		<input type="hid-den" name="idx" value="${boardDTO.idx }" />
	</form>
</body>
<script type="text/javascript">
function deletePost(idx){
	const f = document.deleteFrm
	f.method="post";
	f.action="delete.do";
	if(confirm("삭제하시겠습니까?")){
		f.submit();
	}
}
</script>
</html>