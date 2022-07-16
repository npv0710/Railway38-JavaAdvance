package com.vti;

public class DemoSingleton1 {//Eager Singleton
	
	private static final DemoSingleton1 instance = new DemoSingleton1();
	
	private DemoSingleton1() {
		
	}
	
	public static DemoSingleton1 getInstance() {
		return instance;
	}
	
	@Override
	public String toString() {
		return "123abc";
	}
}
