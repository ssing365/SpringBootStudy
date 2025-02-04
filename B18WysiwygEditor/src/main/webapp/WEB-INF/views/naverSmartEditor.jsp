<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
var oEditors = [];
window.onload = function(){
	nhn.husky.EZCreator.createInIFrame({
		 oAppRef: oEditors,
		 elPlaceHolder: "contents",
		 sSkinURI: "/se2/SmartEditor2Skin.html",
		 fCreator: "createSEditor2"
});
}

function validateForm(f){
	if(f.subject.value == ''){
		alert("제목을 입력하세요.");
		f.subject.focus();
		return false;
	}
	
	// 에디터의 내용이 textarea에 적용된다.
	oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
	let contents = document.getElementById("contents").value;
	if(contents ==''){
		alert("내용을 입력하세요");
		oEditors.getById["contents"].exec("FOCUS")
		return false;
	}
	console.log("contents", contents);
	return false;
}
	
</script>
</head>
<body>
	<h2>네이버 스마트 에디터</h2>
	<form method="post" onsubmit="return validateForm(this);">
	<span style="color:red;">${submit }</span>
	<table border="1" style="width:900px;">
	<colgroup>
		<col width="100px" />
		<col width="*" />
	</colgroup>
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject" style="width:400px;"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<!-- 에디터에 기본으로 삽입할 글(수정 모드)이 없다면 
				value 값을 지정하지 않으시면 됩니다. -->
				<textarea name="contents" id="contents" rows="10" cols="70"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="전송하기" />
			</td>
		</tr>
	</table>
	</form>
</body>

</html>