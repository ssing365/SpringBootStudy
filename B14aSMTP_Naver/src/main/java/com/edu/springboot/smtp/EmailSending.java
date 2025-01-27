package com.edu.springboot.smtp;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailSending {
	
	private final JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String from;
	
	public void myEmailSender(InfoDTO infoDTO) {
		try {
			MimeMessage m = mailSender.createMimeMessage();
			MimeMessageHelper h = new MimeMessageHelper(m,"UTF-8");
			
			h.setFrom(from);
			h.setTo(infoDTO.getTo());
			h.setSubject(infoDTO.getSubject());
			if(infoDTO.getFormat().equals("text")) {
//				메일을 Text형식으로 발송할 때는 순수 문자열만 설정
				h.setText(infoDTO.getContent());
			}
			else {
				/*
				 * HTML형식으로 지정한 경우 미리 만들어둔 메일템플릿의 내용을 읽어온 후
				 * 우리가 입력한 내용으로 변경하는 작업이 필요하다.
				 */
				String emailTpl = readHTMLFile();
				// 입력내용 줄바꿈 처리
				String contents = infoDTO.getContent()
						.replace("\r\n","<br/>");
				// 메일 템플릿에 우리가 작성한 내용을 삽입
				emailTpl = emailTpl.replace("__CONTENT__", contents);
//				HTML 형식인 경우 두 번째 인자를 true로 설정한다.
				h.setText(emailTpl, true);
			}
//			메일 발송
			mailSender.send(m);
			System.out.println("메일 전송 완료");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// HTML문서를 읽어 그 내용(HTML태그)을 반환하는 메서드 정의
	public String readHTMLFile() {
//		StringBuffer 인스턴스 생성
		StringBuffer htmlContents = new StringBuffer();
		try {
//			HTML 템플릿이 있는 디렉토리의 물리적 경로 얻기
			String uploadDir = ResourceUtils.getFile("classpath:static/").toPath().toString();
			String templatePath = uploadDir+"/MailTemplate.html";
//			HTML 파일의 내용을 IO스트림을 통해 한 줄씩 읽어온다.
			BufferedReader br = new BufferedReader(new FileReader(templatePath));
			String oneLine;
			while((oneLine= br.readLine())!=null) {
//				HTML문서의 내용을 한 줄씩 스트링버퍼에 추가한다.
				htmlContents.append(oneLine+"\n");
			}
			// 다 읽었으면 닫기
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		// 읽어온 내용을 String으로 변환 후 반환
		return htmlContents.toString();
	}

}
