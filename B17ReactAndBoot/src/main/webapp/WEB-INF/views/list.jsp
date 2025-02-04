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
	<h2>회원리스트</h2>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>패스워드</th>
			<th>이름</th>
			<th>가입일</th>
			<th></th>
		</tr>
		<c:forEach items="${memberList }" var="row" varStatus="loop">
		<tr id="member_${row.id }">
			<td>${row.id }</td>
			<td>${row.pass }</td>
			<td>${row.name }</td>
			<td>${row.regidate }</td>
			<td>
				<a href="edit.do?id=${row.id }">수정</a>
				<a href="#" onclick="deletePost('${row.id}')">삭제1</a>
				<a href="#" onclick="ajaxDelete('${row.id}')">삭제2</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<a href="regist.do">회원등록</a>
	<form name="delFrm">
		<input type="hiddden" name="id"></input>
	</form>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	function deletePost(idx){
		const f = document.delFrm;
		f.id.value = idx;
		f.method = "post";
		f.action = "delete.do";
		if(window.confirm("삭제하시겠습니까?")){
			f.submit();
		}
	}
	
	function ajaxDelete(del_id){
		console.log("삭제할 아이디:", del_id);
			if(confirm("삭제하시겠습니까?")){
					$.ajax({
						url: "deleteAjax.do", // 요청 URL
		                type: "post", // 전송 방식 
		                dataType: "json", // 콜백데이터 타입
		                data : { 'id' : del_id }, // 파라미터
		                success: function (resData) {
		                	console.log("삭제결과",resData);
		                	if(resData.result == "success"){
		                		alert("삭제되었습니다.");
		                		// location.reload();
		                		// 선택한 엘리먼트를 물리적으로 삭제
		                		$("#member_"+del_id).remove();
		                	}
		                	else if(resData.result == "fail"){
		                		alert("삭제 실패 ~");
		                	}
		                },
		                error: function (errData) {
		                    console.log(errData);
		                    console.log(errData.state, errData.statusText);
		                },
					})
			}
	}
</script>
</body>
</html>