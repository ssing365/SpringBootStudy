package com.edu.springboot;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
어노테이션을 통한 폼값 검증을 위해 디펜던시를 추가해야한다.
build.gradle에 starter-validation항목을 추가한 후 refresh한다.
*/
@Data
public class BoardVO {
	
	private int idx;
	
	/*
	 * @NotNull : 폼값이 null일때 메시지 출력
	 * @NotEmpty : 빈값일 때 메시지 출력
	 * @Size : 입력값의 길이 지정. 해당 범위를 벗어나면 메시지 출력
	 */
	@NotNull(message = "아이디가 Null입니다.")
	@NotEmpty(message = "아이디를 입력해주세요.")
	@Size(min = 5, max = 12, message="아이디는 5~12자로 입력해주세요")
	private String userid;
	

	@NotNull(message = "제목이 Null입니다.")
	@NotEmpty(message = "제목을 입력해주세요.")
	private String title;
	
	@NotNull(message = "내용이 Null입니다.")
	@NotEmpty(message = "내용을 입력해주세요.")
	private String content;
	
}
