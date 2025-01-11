<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul>
	<li>아이디 : ${userDTO.id}</li>
	<li>패스워드 : ${userDTO.pass1}</li>
	<li>이름:${userDTO.name}</li>
	<li>성별 : ${userDTO.sex}</li>
	<li>이메일:${userDTO.email1}@${userDTO.email2}</li>
	<li>이메일 수신여부:${userDTO.mailing }</li>
	<li>우편번호:${userDTO.zipcode}</li>
	<li>주소:${userDTO.addr1 }-${userDTO.addr2 }</li>
	<li>핸드폰:${userDTO.phone1}-${userDTO.phone2}-${userDTO.phone3}</li>
	<li>SMS수신여부:${userDTO.sms }</li>
	<li>관심분야:${userDTO.etc_no1 }</li>
	<li>가입경로:${userDTO.etc_no2 }</li>
</ul>
</body>
</html>