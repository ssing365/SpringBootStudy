<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>웹소켓채팅</title>
    <script>
    	/*
    	페이지가 로드되면 제일 먼저 웹소켓 객체를 생성한다.
    	이때 사용하는 주소는 웹소켓 설정 클래스에서 지정한 요청명을 사용한다.
    	localhost(127.0.0.1)로 기술하면 내 컴퓨터에서만 테스트할 수 있고,
    	내부 IP를 사용하면 다른 사람이 내 컴퓨터에 접속할 수 있다.
    	*/
        const webSocket = new WebSocket("ws:/localhost:8586/myChatServer");
    	// 채팅을 위한 전역변수 생성
        let chatWindow, chatMessage, chatId;
		//채팅창이 열리면 대화창, 메시지 입력상자, 대화명 입력상자의 DOM을 얻어와 전역변수에 저장
        window.onload = function(){
            chatWindow = document.getElementById("chatWindow");
            chatMessage = document.getElementById("chatMessage");
            chatId = document.getElementById("chatId").value;
        }

		// 입력된 메시지를 서버로 전송할 때 호출
        function sendMessage(){
			// 입력한 메시지를 대화창에 추가
            chatWindow.innerHTML += '<div class="myMsg">'+chatMessage.value+"</div>"
            // 웹소켓 서버에 메시지 전송. 형식은 '채팅대화명|메시지'
            webSocket.send(chatId+"|"+chatMessage.value);
            // 다음 메시지 입력하도록 내용 지움
            chatMessage.value="";
            // 대화창 스크롤은 항상 제일 아래로 내려야 함
            chatWindow.scrollTop = chatWindow.scrollHeight;

        }
		// 웹소켓 서버에서 접속 종료
        function disconnect(){
            webSocket.close();
        }
		// 메시지 입력 후 엔터키를 누르면 즉시 메시지 전송
        function enterKey(){
			console.log("키 눌러짐", window.event.keyCode);
			// keyCode를 통해 입력한 키보드가 엔터키인지 확인
            if(window.event.keyCode == 13){
                sendMessage();
            }
        }

		// 웹소켓 서버에 연결되었을 때 이벤트 리스너를 통해 자동 호출
        webSocket.onopen = function(event){
            chatWindow.innerHTML += "웹소켓 서버에 연결되었습니다. <br/>"
        }
        
     	// 웹소켓 서버가 종료되었을 때 
        webSocket.onclose = function(event){
            chatWindow.innerHTML += "웹소켓 서버가 종료되었습니다. <br/>"
        }
     	
     	// 웹소켓 서버에서 에러가 발생했을 때
        webSocket.onerror = function(event){
            alert(event.data);
            chatWindow.innerHTML += "채팅 중 에러가 발생하였습니다. <br/>"
        }

     	// 웹소켓 서버가 메시지를 받았을 때
        webSocket.onmessage = function(event){
     		// 대화명과 메시지 분리. 전송할 때 '|'로 조립해서 보냈다.
            let message = event.data.split("|");
            let sender = message[0];
            let content = message[1];
            // 메시지가 빈 값이 아닌 경우
            if(content!=""){
            	// 메시지에 '/'(슬러시)가 포함된 경우 명령어로 인식
                if(content.match("/")){
                	/* 귓속말의 경우 '/받는사람 보낼메시지'와 같이 작성한다.
                	따라서 받는 사람의 아이디와 동일한 대화창에만 대화내용을 디스플레이 한다. */
                    if(content.match(("/"+chatId))){
                        let temp = content.replace(("/"+chatId),"[귓속말] : ");
                        chatWindow.innerHTML += "<div>" + sender + temp + "</div>";
                    }
                    
                }
            	// 메시지에 '/'(슬러시)가 없다면 일반메시지이므로 즉시 출력
                else{
                    chatWindow.innerHTML += "<div>" + sender + " : " +content + "</div>";
                }
            }
            chatWindow.scrollTop = chatWindow.scrollHeight;
        }
    </script>
    <style>
        #chatWindow{border: 1px solid black; width: 270px; height: 310px; overflow: scroll; padding: 5px;}
        #chatMessage{width: 236px; height: 30px;}
        #sendBtn{height: 30px; position: relative; top: 2px; left: -2px;}
        #closeBtn{margin-bottom: 3px; position: relative; top: 2px; left: -2px;}
        #chatId{
            width: 158px; height: 24px; border: 1px solid #AAAAAA; background-color: #EEEEEE;

        }
        .myMsg{text-align: right; color:blue;}
    </style>
</head>
<body>
	<!-- 파라미터로 전달된 대화명을 얻어와서 사용 -->
    대화명 :
    <input type="text" name="" id="chatId" value="${param.chatId}" readonly>
    <button id="closeBtn" onclick="disconnect();">채팅 종료</button>
    <!-- 채팅 내역이 출력되는 부분 -->
    <div id="chatWindow"></div>
    <!-- 메시지를 입력하고 전송을 위한 버튼 -->
    <div>
        <input type="text" name="" id="chatMessage" onkeyup="enterKey();">
        <button id="sendBtn" onclick="sendMessage();">전송</button>
    </div>
    
</body>
</html>