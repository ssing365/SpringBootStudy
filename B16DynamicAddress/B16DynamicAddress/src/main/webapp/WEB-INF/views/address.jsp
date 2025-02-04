<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(function() {
	//<select>를 선택하면 change 이벤트가 발생됨
    $('#sido').change(function() {
        $.ajax({
            url : "./getGugun.do", 
            type : "get",
            contentType : "text/html;charset:utf-8;",
            data : { //파라미터
                sido : $('#sido option:selected').val()
            },
            dataType : "json", //콜백데이터형식
            success : function(d) {
            	/*
            	'시도'를 선택했을 때 콜백되는 데이터는 result 키값에 저장된
            	배열이므로, 갯수만큼 반복해서 <option>태그를 추가한다. 
            	*/
                console.log(d);
                var optionStr = "";
                optionStr += "<option value=''>";
                optionStr += "-구군을 선택하세요-";
                optionStr += "</option>";
                $.each(d.result, function(index, data) {
                  optionStr += "<option value='"+data.gugun+'">';
                  optionStr += data.gugun;
                  optionStr += "</option>";
                });
                //앞에서 조립한 <option>태그를 삽입한다. 
                $('#gugun').html(optionStr);
            },
            error : function(e) {
                alert("오류발생:"+e.status+":"+e.statusText);  
            }
        });
    });
});
</script>
</head>
<body>
<div class="container">	
    <div class="row">
    	<h2>우편번호DB를 이용한 시도/구군 동적셀렉트 구현</h2>
    </div>
    <form id="zipFrm">
    <div class="row">   	 
		<div class="col-4">
		 시/도:
		 <select id="sido" class="form-control">
			 <option value="">-시/도선택하삼-</option>
			 <c:forEach items="${sidoLists }" var="sidoRow">
				 <option value="${sidoRow.sido }">${sidoRow.sido }</option>
			 </c:forEach>
		 </select>
		</div>
		<div class="col-8">
		 구/군:
		 <select id="gugun" class="form-control">
			 <option value="">-구/군선택하삼-</option>
		 </select>
		</div>   	 
    </div>
    </form>
</div>
</body>
</html>
