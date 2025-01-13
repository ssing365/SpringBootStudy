package com.edu.springboot;

class Person{
	String name;
	int age;
	/*
	 * 생성자가 public이면 외부에서 인스턴스 생성 가능
	 * 하지만 private으로 선언하면 외부에서 인스턴스를 생성할 수 없다.
	 */
	private Person() {
		System.out.println("public 생성자 호출");
	}
}

public class DI_Test {
	/*
	 * 강한 결합(독립성 낮음) : new를 통해 직접 인스턴스 생성
	 * 이 경우 객체 간 결합도가 높아 Person클래스의 변화(접근제한자 변화)에 직접적인 영향을 받는다. 
	 */
	public static void aPerson() {
		// 생성자의 접근제한자를 private로 바꾸는 순간 에러 발생
		Person person1 = new Person();
		person1.name = "홍길동";
		person1.age = 10;
	}
	/*
	 * 약한 결합(독립성 높음) : 미리 생성된 인스턴스를 주입(Injection)받아 사용.
	 * 결합도가 낮아지기 때문에 persons클래스에 변화가 생기더라도 직접적인 영향을 받지 않는다.
	 * 코드도 간결해진다. 
	 */
	public static void bPerson(Person person2) {
		person2.name = "전우치";
		person2.age = 20;
	}
	
	/**
	 * 따라서 DI(의존성주입)의 목적은 객체간 독립성을 높이고 결합도를 낮춰
	 * 프로그램을 간결하게 만드는 것에 있다.
	 */
}