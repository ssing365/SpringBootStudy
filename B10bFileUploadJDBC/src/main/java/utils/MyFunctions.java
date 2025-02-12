package utils;

import java.io.File;
import java.util.UUID;

public class MyFunctions {
	
	/*
	 * UUID(Universally Unique Identifier) : 범용 고유 식별자
	 * JDK에서 기본 제공되는 클래스로 32자의 영문과 숫자를 포함한 고유 문자열을 반환한다.
	 */
	public static String getUuid() {
//		고유 문자열 생성
		String uuid = UUID.randomUUID().toString();
//		문자열에 포함된 하이픈을 제거  모두 4개가 포함되어있음
		uuid = uuid.replaceAll("-", "");
		System.out.println("생성된 UUID : "+ uuid);
		return uuid;
	}
	
//	파일명 변경
	public static String renameFile(String sDirectory, String fileName) {
//		파일의 확장자를 파일명의 끝에서부터 잘라냄
		String ext = fileName.substring(fileName.lastIndexOf("."));
//		파일명으로 사용할 UUID를 얻어옴
		String now = getUuid();
//		둘을 합쳐 새로운 파일명을 만듦
		String newFileName = now + ext;
		
//		기존 파일과 새 파일의 객체를 만든 후 이름 변경
		File oldFile = new File(sDirectory + File.separator + fileName);
		File newFile = new File(sDirectory + File.separator + newFileName);
		
		oldFile.renameTo(newFile);
//		변경된 파일명 반환
		return newFileName;
		
	}
}
