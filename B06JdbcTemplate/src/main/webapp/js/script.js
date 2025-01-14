const deleteGet = (userid) => {
	if(window.confirm("삭제하시겠습니까?")){
		location.href='delete.do?id='+userid;
	}
}

function deletePost(userid){
	let f = document.delFrm;
	// form태그의 name속성 하위태그를 통해 DOM에 접근 가능
	// 즉 삭제할 아이디를 input에 설정
	f.id.value=userid;
	// 전송 방식을 post로 설정
	f.method="post";
	// 삭제할 요청명을 설정
	f.action="delete.do";
	if(window.confirm("삭제하시겠습니까?")){
		// 폼값 전송
		f.submit();
	}
}