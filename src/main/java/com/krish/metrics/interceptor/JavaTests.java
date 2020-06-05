package com.krish.metrics.interceptor;

public class JavaTests {

	public static void main(String[] args) {
		String s1 = "apples:2";
		String s2 = "oranges";
		
		String[] s11 = s2.split(":");
		for(String p: s11) {
			System.out.println(">>"+p);
		}

	}

}
