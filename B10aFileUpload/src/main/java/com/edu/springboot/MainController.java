package com.edu.springboot;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import utils.MyFunctions;

@Controller
public class MainController {
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
	public String uploadProcess(HttpServletRequest req, Model model) {
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
			
//			여기선 JDBC연동이 없으므로 Model에 파일명 저장
			model.addAttribute("originalFileName", originalFileName);
			model.addAttribute("savedFileName", savedFileName);
//			파일 외 나머지 폼값도 Model에 저장
			model.addAttribute("title", req.getParameter("title"));
			model.addAttribute("cate", req.getParameterValues("cate"));
		}
		catch(Exception e) {
			System.out.println("업로드 실패");
		}
		return "fileUploadOK";
	}
	
	@GetMapping("/multiFileUpload.do")
	public String multiFileUpload() {
		return "multiFileUpload";
	}
	
	@PostMapping("/multiUploadProcess.do")
	public String multiUploadProcess(HttpServletRequest req, Model model) {
		try {
			String uploadDir = ResourceUtils
					.getFile("classpath:static/uploads/").toPath().toString();
			System.out.println("물리적 경로 : "+ uploadDir);
			
			/*
			 * 파일명 저장을 위해 Map 생성
			 * Key는 원본 파일명, Value는 서버에 저장된 파일명을 저장한다.
			 */
			Map<String, String> saveFileMaps = new HashMap<>();
			
//			2개 이상의 파일이므로 getParts()를 통해 폼값을 얻어온다.
			Collection<Part> parts = req.getParts();
			for(Part part : parts) {
				
				/* 여러 폼값 중 파일인 경우에만 업로드 처리를 위해 아래 문장을 실행한다.
				 * 나머지는 반복문의 처음으로 돌아간다. */
				if(!part.getName().equals("ofile"))
					continue;
				
				String partHeader = part.getHeader("content-disposition");
				System.out.println("partHeader="+partHeader);
				String[] phArr = partHeader.split("filename=");
				String originalFileName = phArr[1].trim().replace("\"", "");
				if(!originalFileName.isEmpty()) {
					part.write(uploadDir+File.separator+originalFileName);
				}
				
//				서버에 저장된 파일을 UUID를 통해 생성된 이름으로 변경
				String savedFileName=
						MyFunctions.renameFile(uploadDir, originalFileName);
				
				saveFileMaps.put(originalFileName, savedFileName);
			}
			
			model.addAttribute("saveFileMaps", saveFileMaps);
			model.addAttribute("title", req.getParameter("title"));
			model.addAttribute("cate", req.getParameterValues("cate"));
		
		}
		catch (Exception e){
			System.out.println("업로드 실패");
		}
		return "multiFileUploadOk";
	}
	

}
