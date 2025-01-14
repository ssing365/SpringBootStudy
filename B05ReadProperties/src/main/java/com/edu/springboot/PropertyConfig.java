package com.edu.springboot;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/*
 * 사용자정의 프로퍼티 파일을 스프링 컨테이너에 등록하기 위해 정의한 클래스로
 * 시작 시 자동으로 scan되어 빈을 생성하게된다.
 * 이 클래스는 반드시 기본패키지 하위에 위치해야한다. 
 */
@Configuration
public class PropertyConfig {
	// name속성을 지정하였으므로 myprops라는 이름으로 빈이 생성된다.
	@Bean(name="myprops")
	// PropertiesFactoryBean 타입의 인스턴스를 생성하여 스프링 컨테이너에 보관한다.
	public PropertiesFactoryBean propertiesFactoryBean() {
		// 프로퍼티 사용을 위한 인스턴스 생성
		PropertiesFactoryBean propertiesFactoryBean =
				new PropertiesFactoryBean();
		// 사용자정의 프로퍼티 파일의 경로 지정
		ClassPathResource classPathResource = 
				new ClassPathResource("my.properties");
		// 경로를 등록한 후 빈(인스턴스)을 반환한다.
		propertiesFactoryBean.setLocation(classPathResource);
		return propertiesFactoryBean;
	}

}
