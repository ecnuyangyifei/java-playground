package com.yifeiyang.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMain {

	@Test
	public int add(int num1, int num2) {
		return num1 + num2;
	}
	
	@Test(limit=2000)
	public int slowlyAdd(int num1, int num2) throws InterruptedException {
		Thread.sleep(3000);
		return num1 + num2;
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		runTests();
	}
	
	static void runTests() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TestMain tests = new TestMain();
		for(Method m : tests.getClass().getMethods()) {
			Test t = m.getAnnotation(Test.class);
			if (t == null) {
				continue;
			}
			long start = System.currentTimeMillis();
			Object res = m.invoke(tests, 1, 2);
			long end = System.currentTimeMillis();
			if (res.equals(3)) {
				long cost = end - start;
				if (cost < t.limit()) {
					System.out.println(String.format("%s runs successfully", m.getName()));
				} else {
					System.out.println(String.format("%s runs successfully, but too slowly, costs %d ms", m.getName(), cost));
				}

			} else {
				System.out.println(String.format("%s runs successfully", m.getName()));
			}
		}
	}

}
