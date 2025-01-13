package com.edu.springboot.bean2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 스프링 컨테이너를 시작할 때 이름을 지정해서 빈을 생성한다.
 * 즉 Computer 타입의 macbook이라는 빈이 생성된다.
 * Computer macBook = new Computer()와 같은 의미
 */
@Component("macBook")
public class Computer {
	
	@Value("M1")
	private String cpu;
	
	public void setCpu(String cpu) {
		this.cpu = cpu;
		
	}
	
	@Override
	public String toString() {
		return "Notebook[cpu="+cpu+"]";
	}

}
