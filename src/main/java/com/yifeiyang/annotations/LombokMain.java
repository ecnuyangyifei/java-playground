package com.yifeiyang.annotations;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class LombokMain {

	private String name;
	private int age;
	
	public static void main(String[] args) {
		LombokMain lombok = new LombokMain("Java Mooc", 2);
		System.out.println(lombok.getName());
		System.out.println(lombok);
	}

}
