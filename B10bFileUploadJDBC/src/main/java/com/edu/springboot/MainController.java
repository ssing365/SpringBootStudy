package com.edu.springboot;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.myfile.IMyFileService;
import com.edu.springboot.myfile.MyFileDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import utils.MyFunctions;

@Controller
public class MainController {
	
	@Autowired
	IMyFileService dao;
	
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
//	싱글파일 업로드 페이지 매핑
	@GetMapping("/fileUpload.do")
	public String fileUpload() {
		return "fileUpload";
	}
//	싱글파일 업로드 처리
	@PostMapping("/uploadProcess.do")
	public String uploadProcess(HttpServletRequest req, Model model,
			MyFileDTO myFileDTO) {
		try {
//			업로드 디렉토리의 물리적 경로 얻기
			String uploadDir = ResourceUtils
					.getFile("classpath:static/uploads/").toPath().toString();
			System.out.println("물리적 경로 : "+ uploadDir);
			
//			전송된 첨부파일을 Part 객체를 통해 얻어온다.
			Part part = req.getPart("ofile");
//			파일명 확인을 위해 헤더값을 얻어온다.
			String partHeader = part.getHeader("content-disposition");
			System.out.println("partHeader="+partHeader);
//			헤더값에서 파일명을 추출하기 위해 문자열 split()
			String[] phArr = partHeader.split("filename=");
//			따옴표를 제거하여 원본파일명 추출
			String originalFileName = phArr[1].trim().replace("\"", "");
			if(!originalFileName.isEmpty()) {
				part.write(uploadDir+File.separator+originalFileName);
			}
			
//			서버에 저장된 파일을 UUID를 통해 생성된 이름으로 변경
			String savedFileName=
					MyFunctions.renameFile(uploadDir, originalFileName);
			
//			DB입력
			myFileDTO.setOfile(originalFileName);
			myFileDTO.setSfile(savedFileName);
			int result = dao.insertFile(myFileDTO);
			if(result==1) System.out.println("입력 성공");
			else System.out.println("입력 실패");
		}
		catch(Exception e) {
			System.out.println("업로드 실패");
			e.printStackTrace();
		}
		return "redirect:fileUploadOK.do";
	}
	
	@GetMapping("fileUploadOK.do")
	public String fileUploadOK(Model model) {
		model.addAttribute("fileRows", dao.selectFile());
		return "fileUploadOK";
	}
	

}
