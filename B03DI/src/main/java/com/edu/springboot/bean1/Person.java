package com.edu.springboot.bean1;

// 데이터 저장 기능만 있는 일반적인 DTO클래스(VO라고도 함)
public class Person {
	
	private String name;
	private int age;
	private Notebook notebook;
	
	// 기본 생성자
	public Person() {}
	
	// 인수 생성자
	public Person(String name, int age, Notebook notebook) {
		super();
		this.name = name;
		this.age = age;
		this.notebook = notebook;
	}
	
	// getter&setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Notebook getNotebook() {
		return notebook;
	}
	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}

	// toString() 메서드 오버라이딩. 
	// : 인스턴스 변수를 직접 print해서 클래스의 
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", notebook=" + notebook + "]";
	}

}
