package com.edu.springboot.bean1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	@Bean
	public Person person1() {
		Person person = new Person();
		person.setName("성유겸");
		person.setAge(11);
		person.setNotebook(new Notebook("레노버"));
		
		return person;
	}
	
	@Bean(name ="person2")
	public Person ptemp() {
		Person person = new Person("알파고", 20, new Notebook("인텔"));
		return person;
	}
}
